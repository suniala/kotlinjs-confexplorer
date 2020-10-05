package state.reducers

import redux.RAction
import state.actions.RequestMoreVideos
import state.actions.MoreVideosReceived

fun videos(state: Videos = Videos(emptyArray(), false), action: RAction): Videos = when (action) {
    is RequestMoreVideos -> {
        state.copy(fetching = true)
    }
    is MoreVideosReceived -> {
        state.copy(videos = state.videos + action.videos, fetching = false)
    }
    else -> state
}