package state.actions

import redux.RAction
import state.reducers.Video

class FetchVideos(): RAction

class SelectVideo(val video: Video): RAction