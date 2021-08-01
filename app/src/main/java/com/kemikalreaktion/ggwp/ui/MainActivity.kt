package com.kemikalreaktion.ggwp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kemikalreaktion.ggwp.data.Character
import com.kemikalreaktion.ggwp.data.FrameData
import com.kemikalreaktion.ggwp.data.MockData

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FrameDataCard(frameData = MockData.frameData)
        }
    }

    @Composable
    fun FrameDataCard(frameData: FrameData) {
        Column {
            Text(text = "Input: ${frameData.input}")
            Text(text = "Damage: ${frameData.damage}")
            Text(text = "Guard: ${frameData.guard.name}")
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
            for(move in character.frameData) {
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