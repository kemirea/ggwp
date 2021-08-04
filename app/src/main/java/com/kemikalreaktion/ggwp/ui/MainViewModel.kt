package com.kemikalreaktion.ggwp.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.kemikalreaktion.ggwp.sync.DatabaseManager

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val databaseManager = DatabaseManager()

    fun query() {
        Log.d("ggwp", "test text")
    }
}