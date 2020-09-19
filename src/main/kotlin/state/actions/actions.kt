package state.actions

import redux.RAction
import state.reducers.Video
import state.reducers.VideoId

class FetchVideos() : RAction

class SelectVideo(val video: Video) : RAction

class MarkWatched(val video: VideoId) : RAction
