package com.cdcoding.movienight.movies.presentation

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.cdcoding.movienight.common.util.InputDataKeys
import com.cdcoding.movienight.database.data.model.Account
import com.cdcoding.movienight.movies.domain.use_case.MovieUseCases
import com.cdcoding.movienight.movies.worker.GetMovieDetailWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    application: Application,
    private val movieUseCases: MovieUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _state = mutableStateOf(MoviesState())
    val state: State<MoviesState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var movieDetailId: Int = -1
    private val workManager = WorkManager.getInstance(application)
    private var workerCollectorJob: Job? = null

    init {
        savedStateHandle.get<Int>("accountId")?.let { accountId ->
            if (accountId != -1) {
                viewModelScope.launch {
                    movieUseCases.getAccount(accountId)?.also { account ->
                        _state.value = state.value.copy(
                            account = account
                        )
                    }
                }
            }
        }
        getMovies()
    }

    fun onEvent(event: MoviesEvent) {
        when (event) {
            is MoviesEvent.OpenMovieDetail -> {
                if (event.movieId != null) {
                    fetchMovieDetail(event.movieId)
                }
            }
        }
    }


    private fun observeMovieWorker(workerId: UUID) {
        val workInfo: Flow<WorkInfo> = workManager.getWorkInfoByIdLiveData(workerId).asFlow()
        workerCollectorJob = viewModelScope.launch {
            workInfo.collect {
                when (it.state) {
                    WorkInfo.State.ENQUEUED, WorkInfo.State.RUNNING -> {}
                    WorkInfo.State.SUCCEEDED -> {
                         _eventFlow.emit(
                             UiEvent.MovieDetailFetched(movieDetailId = movieDetailId)
                         )
                    }
                    WorkInfo.State.FAILED -> {
                        _eventFlow.emit(
                            UiEvent.ShowToast(
                                message = "Unknown network error"
                            )
                        )
                    }
                    WorkInfo.State.CANCELLED -> {}
                    else -> {}
                }
            }
        }
    }

    private fun fetchMovieDetail(movieId: Int) {
        movieDetailId = movieId
        workerCollectorJob?.cancel()
        workManager.cancelAllWork()
        val workerData: Data = workDataOf(InputDataKeys.MOVIE_ID to movieId)
        val movieDetailWorker = OneTimeWorkRequestBuilder<GetMovieDetailWorker>()
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(
                        NetworkType.CONNECTED
                    )
                    .build()
            )
            .setInputData(workerData)
            .build()

        observeMovieWorker(movieDetailWorker.id)

        workManager.beginUniqueWork(
            "GET_MOVIE_DETAIL",
            ExistingWorkPolicy.REPLACE,
            movieDetailWorker
        ).enqueue()
    }

    private fun getMovies() {
        movieUseCases.getMovies().onEach { movies ->
            _state.value = state.value.copy(
                movies = movies
            )
        }.launchIn(viewModelScope)
    }


    sealed class UiEvent {
        data class ShowToast(val message: String) : UiEvent()
        data class MovieDetailFetched(val movieDetailId: Int?) : UiEvent()
    }
}