package com.cdcoding.movienight.login.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cdcoding.movienight.login.domain.model.Account
import com.cdcoding.movienight.login.domain.model.InvalidAccountException
import com.cdcoding.movienight.login.domain.use_case.AccountUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(
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

    init {
        getAccounts()
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
                                pseudo =  accountPseudo.value.text
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
        }
    }

    private fun getAccounts() {
        accountUseCases.getAccounts().onEach { accounts ->
            _state.value = state.value.copy(
                accounts = accounts
            )
        }.launchIn(viewModelScope)
    }

    private fun initNewAccountFields(){
        _accountFirstName.value = InputEditTextState( hint = "Gérard")
        _accountLastName.value = InputEditTextState( hint = "Depardieu")
        _accountPseudo.value = InputEditTextState( hint = "Gégé")
    }
}