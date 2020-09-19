package state.reducers

import redux.RAction
import state.actions.SelectVideo

fun viewer(state: Viewer = Viewer(null), action: RAction): Viewer = when (action) {
    is SelectVideo -> {
        state.copy(selectedVideo = action.video)
    }
    else -> state
}