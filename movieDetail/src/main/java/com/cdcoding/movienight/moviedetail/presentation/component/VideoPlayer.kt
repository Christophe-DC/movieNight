package com.cdcoding.movienight.moviedetail.presentation.component

import android.util.SparseArray
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


@Composable
fun VideoPlayer(
    modifier: Modifier = Modifier,
    videoId: String
) {
    val context = LocalContext.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            repeatMode = ExoPlayer.REPEAT_MODE_ALL
            playWhenReady = true
            prepare()
            play()

        }
    }

    var isPlaying by remember { mutableStateOf(true) }

    /*object : YouTubeExtractor(context) {
        override fun onExtractionComplete(ytFiles: SparseArray<YtFile>?, vMeta: VideoMeta?) {
            if (ytFiles != null) {
                val itag = 22
                println("ytFiles: $ytFiles")
                val downloadUrl: String = ytFiles[itag].getUrl()
                exoPlayer.setMediaItem(MediaItem.fromUri(downloadUrl))
            }
        }
    }.extract(sourceUrl, true, true)*/

    Box(modifier = modifier)
    {
        DisposableEffect(
            AndroidView(
                modifier = modifier,
                factory = {
                    val playerView = YouTubePlayerView(context)
                    playerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            youTubePlayer.loadVideo(videoId, 0f)
                        }
                    })
                    playerView
                }
            )
            /*AndroidView(
                modifier = Modifier.clickable  {
                    if (exoPlayer.isPlaying) {
                        exoPlayer.pause()
                        isPlaying = false
                    } else {
                        exoPlayer.play()
                        isPlaying = true
                    }
                },
                factory = {
                    StyledPlayerView(context).apply {
                        hideController()
                        useController = false
                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                        player = exoPlayer
                        FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                    }
                }
            )*/
        ) {
            onDispose {
                exoPlayer.pause()
                exoPlayer.release()
            }
        }

        PlayerControl(
            modifier = Modifier.fillMaxSize(),
            isPlaying = { isPlaying },
        )
    }
}
