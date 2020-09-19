package state.reducers

import redux.RAction
import state.actions.MarkWatched
import state.actions.SelectVideo

fun viewer(state: Viewer = Viewer(null, emptyArray()), action: RAction): Viewer = when (action) {
    is SelectVideo -> {
        state.copy(selectedVideo = action.video)
    }
    is MarkWatched -> {
        val watchedVideos =
            if (action.watched) state.watchedVideos + action.video
            else state.watchedVideos.filterNot { it == action.video }.toTypedArray()
        state.copy(watchedVideos = watchedVideos)
    }
    else -> state
}