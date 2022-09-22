package com.cdcoding.movienight.movies.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.cdcoding.movienight.api.domain.repository.MovieRemoteRepository
import com.cdcoding.movienight.common.util.InputDataKeys
import com.cdcoding.movienight.common.util.WorkerKeys
import com.cdcoding.movienight.database.domain.repository.MovieDetailRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@HiltWorker
class GetMovieDetailWorker @AssistedInject constructor(
    @Assisted ctx: Context,
    @Assisted params: WorkerParameters,
    private val movieDetailRepository: MovieDetailRepository,
    private val movieRemoteRepository: MovieRemoteRepository
) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                val movieId = inputData.getInt(InputDataKeys.MOVIE_ID, 0)
                val movieDetail = movieRemoteRepository.fetchMovieById(movieId)
                movieDetailRepository.insertMovieDetail(movieDetail)

                return@withContext Result.success(
                    workDataOf(
                        WorkerKeys.MOVIE_DETAIL_ID to movieDetail.id
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