package com.kemikalreaktion.ggwp.data

class MockData {
    companion object {
        val frameData = FrameData(
            input = "5p",
            damage = 26,
            guard = FrameData.Guard.ALL,
            startup = 5,
            active = 4,
            recovery = 7,
            onBlock = -1,
            onHit = 1,
        )

        val character = Character(
            name = "Ky Kiske",
            frameData = listOf(frameData)
        )

    }
}