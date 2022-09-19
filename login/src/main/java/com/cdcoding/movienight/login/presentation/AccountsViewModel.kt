package com.cdcoding.movienight.login.presentation

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.cdcoding.movienight.database.data.model.Account
import com.cdcoding.movienight.database.data.model.InvalidAccountException
import com.cdcoding.movienight.login.domain.use_case.AccountUseCases
import com.cdcoding.movienight.login.worker.GetMoviesWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(
    application: Application,
    private val accountUseCases: AccountUseCases
) : ViewModel() {

    private val _state = mutableStateOf(AccountsState())
    val state: State<AccountsState> = _state

    private val _accountFirstName = mutableStateOf(InputEditTextState())
    val accountFirstName: State<InputEditTextState> = _accountFirstName

    private val _accountLastName = mutableStateOf(InputEditTextState())
    val accountLastName: State<InputEditTextState> = _accountLastName

    private val _accountPseudo = mutableStateOf(InputEditTextState())
    val accountPseudo: State<InputEditTextState> = _accountPseudo

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var accountLoading: Account? = null
    private val workManager = WorkManager.getInstance(application)
    private val moviesWorker = OneTimeWorkRequestBuilder<GetMoviesWorker>()
        .setConstraints(
            Constraints.Builder()
                .setRequiredNetworkType(
                    NetworkType.CONNECTED
                )
                .build()
        )
        .build()

    init {
        getAccounts()
        observeMovieWorker()
    }

    fun onEvent(event: AccountsEvent) {
        when (event) {
            is AccountsEvent.AddAccount -> {
                viewModelScope.launch {
                    try {
                        _state.value = state.value.copy(
                            isNewAccountCreating = true
                        )
                        accountUseCases.addAccount(
                            Account(
                                firstName = accountFirstName.value.text,
                                lastName = accountLastName.value.text,
                                pseudo = accountPseudo.value.text
                            )
                        )
                        _state.value = state.value.copy(
                            isNewAccountCreating = false,
                            isNewAccountModalVisible = false
                        )
                    } catch (e: InvalidAccountException) {
                        _state.value = state.value.copy(
                            isNewAccountCreating = false
                        )
                    }
                }
            }
            is AccountsEvent.OpenNewAccountModal -> {
                initNewAccountFields()
                _state.value = state.value.copy(
                    isNewAccountModalVisible = true
                )
            }
            is AccountsEvent.CloseNewAccountModal -> {
                _state.value = state.value.copy(
                    isNewAccountModalVisible = false
                )
            }
            is AccountsEvent.EnteredFirstName -> {
                _accountFirstName.value = accountFirstName.value.copy(
                    text = event.value
                )
            }
            is AccountsEvent.ChangeFirstNameFocus -> {
                _accountFirstName.value = accountFirstName.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            accountFirstName.value.text.isBlank()
                )
            }
            is AccountsEvent.EnteredLastName -> {
                _accountLastName.value = accountLastName.value.copy(
                    text = event.value
                )
            }
            is AccountsEvent.ChangeLastNameFocus -> {
                _accountLastName.value = accountLastName.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            accountLastName.value.text.isBlank()
                )
            }
            is AccountsEvent.EnteredPseudo -> {
                _accountPseudo.value = accountPseudo.value.copy(
                    text = event.value
                )
            }
            is AccountsEvent.ChangePseudoFocus -> {
                _accountPseudo.value = accountPseudo.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            accountPseudo.value.text.isBlank()
                )
            }
            is AccountsEvent.OpenAccount -> {
                fetchMovies(event.account)
            }
        }
    }

    private fun getAccounts() {
        accountUseCases.getAccounts().onEach { accounts ->
            _state.value = state.value.copy(
                accounts = accounts
            )
        }.launchIn(viewModelScope)
    }

    private fun initNewAccountFields() {
        _accountFirstName.value = InputEditTextState(hint = "Gérard")
        _accountLastName.value = InputEditTextState(hint = "Depardieu")
        _accountPseudo.value = InputEditTextState(hint = "Gégé")
    }


    private fun observeMovieWorker() {
        val workInfo: Flow<WorkInfo> = workManager.getWorkInfoByIdLiveData(moviesWorker.id).asFlow()
        viewModelScope.launch {
            workInfo.collect {
                when (it.state) {
                    WorkInfo.State.ENQUEUED, WorkInfo.State.RUNNING -> {
                        _state.value = state.value.copy(
                            accountLoading = accountLoading
                        )
                    }
                    WorkInfo.State.SUCCEEDED -> {
                        _state.value = state.value.copy(
                            accountLoading = null
                        )
                        _eventFlow.emit(
                            UiEvent.MoviesFetched(accountId = accountLoading?.id)
                        )
                    }
                    WorkInfo.State.FAILED -> {
                        _state.value = state.value.copy(
                            accountLoading = null
                        )
                        _eventFlow.emit(
                            UiEvent.ShowToast(
                                message = "Unknown network error"
                            )
                        )
                    }
                    WorkInfo.State.CANCELLED -> {
                        _state.value = state.value.copy(
                            accountLoading = null
                        )
                    }
                }
            }
        }
    }

    private fun fetchMovies(account: Account) {
        accountLoading = account
        workManager.beginUniqueWork(
            "GET_MOVIES",
            ExistingWorkPolicy.REPLACE,
            moviesWorker
        ).enqueue()
    }


    sealed class UiEvent {
        data class ShowToast(val message: String) : UiEvent()
        data class MoviesFetched(val accountId: Int?) : UiEvent()
    }
}