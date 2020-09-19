package state.reducers

typealias VideoId = Int

data class Video(val id: VideoId, val title: String, val speaker: String, val videoUrl: String)

// NOTE: It is recommended to use Array instead of List here. But why?
data class Videos(val videos: Array<Video>, val fetching: Boolean)

data class Viewer(val selectedVideo: VideoId?, val watchedVideos: Array<VideoId>)

data class State(
    val videos: Videos = Videos(emptyArray(), fetching = false),
    val viewer: Viewer = Viewer(null, watchedVideos = emptyArray())
)

fun combinedReducers() = combineReducers(
    mapOf(
        State::videos to ::videos,
        State::viewer to ::viewer
    )
)
