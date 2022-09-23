package com.cdcoding.movienight.moviedetail.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cdcoding.movienight.moviedetail.domain.use_case.MovieDetailUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailUseCases: MovieDetailUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _state = mutableStateOf(MovieDetailState())
    val state: State<MovieDetailState> = _state


    init {
        savedStateHandle.get<Int>("movieDetailId")?.let { movieDetailId ->
            if (movieDetailId != -1) {
                viewModelScope.launch {
                    movieDetailUseCases.getMovieDetail(movieDetailId)?.also { movieDetail ->
                        _state.value = state.value.copy(
                            movieDetail = movieDetail
                        )
                    }
                }
            }
        }
    }

}