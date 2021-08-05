package com.kemikalreaktion.ggwp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.kemikalreaktion.ggwp.data.DatabaseManager
import com.kemikalreaktion.ggwp.data.FrameData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val databaseManager = DatabaseManager()

    val frameDataFlow: Flow<List<FrameData>> = flow { emit(databaseManager.fetchFrameData()) }

    override fun onCleared() {
        super.onCleared()
        databaseManager.cleanUp()
    }
}