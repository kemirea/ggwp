package com.kemikalreaktion.ggwp.data

import android.content.Context
import android.util.Log
import com.kemikalreaktion.ggwp.GGWPDatabase
import com.kemikalreaktion.ggwp.db.model.FrameDataDao
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class DatabaseManager(context: Context) {
    // Ref: https://github.com/movildima/GGStriveUtilsBot/blob/master/GGStriveUtilsBot/Utils/DustloopDataFetcher.cs
    private val mainQuery = "https://www.dustloop.com/wiki/index.php?" +
            "title=Special:CargoExport&" +
            "tables=MoveData_GGST&" +
            "fields=chara%2C+input%2C+name%2C+images%2C+damage%2C+guard%2C+startup%2C+active%2C+recovery%2C+onBlock%2C+onHit&order+by=%60chara%60%2C%60input%60%2C%60name%60%2C%60cargo__MoveData_GGST%60.%60images__full%60%2C%60damage%60&" +
            "limit=1000&" +
            "format=json"

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                kotlinx.serialization.json.Json {
                    isLenient = true
                })
        }
    }

    private val database =
        GGWPDatabase(AndroidSqliteDriver(GGWPDatabase.Schema, context, "database.db"))
    private val frameDataQueries = database.frameDataDAOQueries

    suspend fun refreshFrameData(): List<FrameData> {
        val response: List<FrameData> = client.get(mainQuery)
        Log.d("ggwp", "refreshFrameData response: $response")

        response.forEach {
            frameDataQueries.insertOrReplace(
                FrameDataDao(
                    chara = it.character,
                    input = it.input,
                    damage = it.damage,
                    guard = it.guard,
                    startup = it.startup,
                    active = it.active,
                    recovery = it.recovery,
                    onBlock = it.onBlock,
                    onHit = it.onHit,
                    name = it.name,
                    images = Json.encodeToString(it.images)

                )
            )
        }

        return response
    }

    fun getFrameData(): Flow<List<FrameData>> {
        return frameDataQueries.getFrameData { chara, input, damage, guard, startup, active, recovery, onBlock, onHit, name, images ->
            FrameData(
                character = chara,
                input = input,
                damage = damage,
                guard = guard,
                startup = startup,
                active = active,
                recovery = recovery,
                onBlock = onBlock,
                onHit = onHit,
                name = name,
                images = Json.decodeFromString(images)
            )
        }
            .asFlow()
            .mapToList()
            .distinctUntilChanged()
    }

    fun getCharacters(): Flow<List<String>> {
        return frameDataQueries.getCharacters()
            .asFlow()
            .mapToList()
            .distinctUntilChanged()
    }

    fun cleanUp() {
        client.close()
    }
}