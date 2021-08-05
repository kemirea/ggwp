package com.kemikalreaktion.ggwp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.kemikalreaktion.ggwp.data.Character
import com.kemikalreaktion.ggwp.data.FrameData
import com.kemikalreaktion.ggwp.data.MockData
import kotlinx.coroutines.flow.collect

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FrameDataList(frameDataList = emptyList())
        }
    }

    override fun onStart() {
        super.onStart()

        lifecycleScope.launchWhenStarted {
            viewModel.frameDataFlow.collect { frameData ->
                setContent {
                    FrameDataList(frameDataList = frameData)
                }
            }
        }
    }

    @Composable
    fun FrameDataList(frameDataList: List<FrameData>) {
        Box {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(frameDataList) { frameData ->
                    FrameDataCard(frameData = frameData)
                }
            }
        }
    }

    @Composable
    fun FrameDataCard(frameData: FrameData) {
        Column (modifier = Modifier.padding(10.dp)) {
            Text(text = "Character: ${frameData.character}")
            Text(text = "Input: ${frameData.input}")
            Text(text = "Damage: ${frameData.damage}")
            Text(text = "Guard: ${frameData.guard}")
            Text(text = "Startup: ${frameData.startup}")
            Text(text = "Active: ${frameData.active}")
            Text(text = "Recovery: ${frameData.recovery}")
            Text(text = "On Block: ${frameData.onBlock}")
            Text(text = "On Hit: ${frameData.onHit}")
        }
    }

    @Composable
    fun CharacterCard(character: Character) {
        Column {
            Text(text = character.name)
            Spacer(modifier = Modifier.height(5.dp))
            for (move in character.frameData) {
                FrameDataCard(frameData = move)
            }

        }
    }

    @Preview
    @Composable
    fun PreviewCard() {
        CharacterCard(MockData.character)
    }
}