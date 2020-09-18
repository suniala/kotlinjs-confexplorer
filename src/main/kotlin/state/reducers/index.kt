package state.reducers

typealias VideoId = Int

data class Video(val id: VideoId, val title: String, val speaker: String, val videoUrl: String)

data class State(
    val videos: Array<Video> = emptyArray(),
)

fun combinedReducers() = combineReducers(
    mapOf(
        State::videos to ::videos
    )
)
