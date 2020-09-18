package state.reducers

import redux.RAction
import state.actions.FetchVideos

fun videos(state: Array<Video> = emptyArray(), action: RAction): Array<Video> = when (action) {
    is FetchVideos -> arrayOf(
        Video(1, "Video 1", "Speaker 1", "https://youtu.be/PsaFVLr8t4E"),
        Video(2, "Video 2", "Speaker 2", "https://youtu.be/PsaFVLr8t4E")
    )
    else -> state
}