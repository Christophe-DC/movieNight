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
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView


@Composable
fun VideoPlayer(
    modifier: Modifier = Modifier,
    sourceUrl: String
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

    object : YouTubeExtractor(context) {
        override fun onExtractionComplete(ytFiles: SparseArray<YtFile>?, vMeta: VideoMeta?) {
            if (ytFiles != null) {
                val itag = 22
                val downloadUrl: String = ytFiles[itag].getUrl()
                exoPlayer.setMediaItem(MediaItem.fromUri(downloadUrl))
            }
        }
    }.extract(sourceUrl)

    Box(modifier = modifier)
    {
        DisposableEffect(
            AndroidView(
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
            )
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
