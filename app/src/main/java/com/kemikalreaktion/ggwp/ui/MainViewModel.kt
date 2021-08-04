package com.kemikalreaktion.ggwp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kemikalreaktion.ggwp.data.DatabaseManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val databaseManager = DatabaseManager()

    fun query() {
        viewModelScope.launch(Dispatchers.IO) {
            databaseManager.refreshData()
        }
    }

    override fun onCleared() {
        super.onCleared()
        databaseManager.cleanUp()
    }
}