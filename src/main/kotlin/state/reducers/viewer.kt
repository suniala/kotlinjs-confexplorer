package state.reducers

import redux.RAction
import state.actions.MarkWatched
import state.actions.SelectVideo

fun viewer(state: Viewer = Viewer(null, emptyArray()), action: RAction): Viewer = when (action) {
    is SelectVideo -> {
        state.copy(selectedVideo = action.video.id)
    }
    is MarkWatched -> {
        val watchedVideos =
            if (!state.watchedVideos.contains(action.video)) state.watchedVideos + action.video
            else state.watchedVideos.filterNot { it == action.video }.toTypedArray()
        state.copy(watchedVideos = watchedVideos)
    }
    else -> state
}