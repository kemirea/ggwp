package com.kemikalreaktion.ggwp.data

import android.util.Log
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class DatabaseManager {
    // Ref: https://github.com/movildima/GGStriveUtilsBot/blob/master/GGStriveUtilsBot/Utils/DustloopDataFetcher.cs
    private val mainQuery = "https://www.dustloop.com/wiki/index.php?" +
            "title=Special:CargoExport&" +
            "tables=MoveData_GGST&" +
            "fields=chara%2C+input%2C+name%2C+images%2C+damage%2C+guard%2C+startup%2C+active%2C+recovery%2C+onBlock%2C+onHit&order+by=%60chara%60%2C%60input%60%2C%60name%60%2C%60cargo__MoveData_GGST%60.%60images__full%60%2C%60damage%60&" +
            "limit=1000&" +
            "format=json"

    private val client = HttpClient() {
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                kotlinx.serialization.json.Json {
                    isLenient = true
                })
        }
    }

    suspend fun fetchFrameData(): List<FrameData> {
        val response: List<FrameData> = client.get(mainQuery)
        Log.d("ggwp", "refreshData response: $response")
        return response
    }

    fun cleanUp() {
        client.close()
    }
}