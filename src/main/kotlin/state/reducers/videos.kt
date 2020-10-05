package state.reducers

import redux.RAction
import state.actions.RequestVideos
import state.actions.VideosReceived

fun videos(state: Videos = Videos(emptyArray(), false), action: RAction): Videos = when (action) {
    is RequestVideos -> {
        Videos(emptyArray(), true)
    }
    is VideosReceived -> {
        Videos(action.videos, false)
    }
    else -> state
}