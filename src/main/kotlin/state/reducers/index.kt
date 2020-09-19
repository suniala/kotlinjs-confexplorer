package state.reducers

data class Video(val id: Int, val title: String, val speaker: String, val videoUrl: String)

data class Viewer(val selectedVideo: Video?)

data class State(
    // NOTE: It is recommended to use Array instead of List here. But why?
    val videos: Array<Video> = emptyArray(),
    val viewer: Viewer = Viewer(null)
)

fun combinedReducers() = combineReducers(
    mapOf(
        State::videos to ::videos,
        State::viewer to ::viewer
    )
)
