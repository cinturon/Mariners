package dev.jbelt.seattlemariners.screens.news

import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

private const val TAG = "VideoPlayer"

@Composable
fun VideoPlayer(
    videoUrl: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Log.d(TAG, "VideoPlayer recomposed with URL: $videoUrl")

    // Create single ExoPlayer instance that persists across recompositions
    val exoPlayer = remember {
        Log.d(TAG, "Creating ExoPlayer instance")
        ExoPlayer.Builder(context).build().apply {
            Log.d(TAG, "ExoPlayer created")
        }
    }

    // Update media item when URL changes
    LaunchedEffect(videoUrl) {
        Log.d(TAG, "LaunchedEffect triggered for URL: $videoUrl")
        try {
            Log.d(TAG, "Stopping playback")
            exoPlayer.stop()
            exoPlayer.clearMediaItems()

            Log.d(TAG, "Creating MediaItem from URL: $videoUrl")
            val mediaItem = MediaItem.fromUri(videoUrl)
            exoPlayer.setMediaItem(mediaItem)

            Log.d(TAG, "Preparing player")
            exoPlayer.prepare()

            Log.d(TAG, "Playback started successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Error in LaunchedEffect: ${e.message}", e)
            e.printStackTrace()
        }
    }

    AndroidView(
        modifier = modifier.background(Color.Black),
        factory = { ctx ->
            Log.d(TAG, "Creating PlayerView")
            PlayerView(ctx).apply {
                player = exoPlayer
                layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                useController = true
                Log.d(TAG, "PlayerView configured with player")
            }
        }
    )

    DisposableEffect(Unit) {
        Log.d(TAG, "DisposableEffect setup")
        onDispose {
            Log.d(TAG, "Cleaning up VideoPlayer")
            exoPlayer.stop()
            exoPlayer.clearMediaItems()
            exoPlayer.release()
            Log.d(TAG, "VideoPlayer cleaned up")
        }
    }
}

