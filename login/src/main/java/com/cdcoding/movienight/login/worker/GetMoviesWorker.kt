package com.cdcoding.movienight.login.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.cdcoding.movienight.api.domain.repository.MovieRemoteRepository
import com.cdcoding.movienight.database.domain.repository.MovieRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext


@HiltWorker
class GetMoviesWorker @AssistedInject constructor(
    @Assisted ctx: Context,
    @Assisted params: WorkerParameters,
    private val movieRepository: MovieRepository,
    private val movieRemoteRepository: MovieRemoteRepository
) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                // Simulate latency
                delay(1000)
                val movies = movieRemoteRepository.fetchMovies()
                val movieList = movies.results
                movieRepository.insertMovies(movieList)

                return@withContext Result.success(
                    workDataOf(
                        WorkerKeys.MOVIES to movies.totalResults
                    )
                )
            } catch (e: Throwable) {
                e.printStackTrace()
                return@withContext Result.failure(
                    workDataOf(
                        WorkerKeys.ERROR_MSG to e.localizedMessage
                    )
                )
            }
        }
    }
}