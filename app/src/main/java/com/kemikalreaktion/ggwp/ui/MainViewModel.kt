package com.kemikalreaktion.ggwp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kemikalreaktion.ggwp.data.DatabaseManager
import com.kemikalreaktion.ggwp.data.FrameData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val databaseManager = DatabaseManager(application.baseContext)

    val frameDataFlow: Flow<List<FrameData>> = databaseManager.getFrameData()

    fun refreshData() {
        viewModelScope.launch(Dispatchers.IO) {
            databaseManager.refreshFrameData()
        }
    }

    override fun onCleared() {
        super.onCleared()
        databaseManager.cleanUp()
    }
}