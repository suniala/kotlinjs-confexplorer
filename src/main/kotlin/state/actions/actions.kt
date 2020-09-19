package state.actions

import redux.RAction
import state.reducers.Video
import state.reducers.VideoId

// TODO: async actions, https://github.com/AltmanEA/KotlinExamples/tree/master/js/reduxAsync
class FetchVideos() : RAction

class VideosReceived(val videos: Array<Video>) : RAction

class SelectVideo(val video: Video) : RAction

class MarkWatched(val video: VideoId) : RAction
