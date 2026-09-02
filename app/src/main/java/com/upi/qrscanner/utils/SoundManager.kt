package com.upi.qrscanner.utils

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Build
import com.upi.qrscanner.R

class SoundManager(private val context: Context) {

    private val soundPool: SoundPool
    private var beepSoundId = 0
    private var successSoundId = 0
    private var errorSoundId = 0

    init {
        soundPool = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
            SoundPool.Builder()
                .setMaxStreams(3)
                .setAudioAttributes(audioAttributes)
                .build()
        } else {
            @Suppress("DEPRECATION")
            SoundPool(3, android.media.AudioManager.STREAM_NOTIFICATION, 0)
        }

        // Load sounds
        loadSounds()
    }

    private fun loadSounds() {
        try {
            // Load beep sound for QR code detection
            beepSoundId = soundPool.load(context, R.raw.beep, 1)
            // Load success sound for payment completion
            successSoundId = soundPool.load(context, R.raw.success, 1)
            // Load error sound for payment failure
            errorSoundId = soundPool.load(context, R.raw.error, 1)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun playBeepSound() {
        try {
            soundPool.play(beepSoundId, 0.7f, 0.7f, 1, 0, 1.0f)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun playSuccessSound() {
        try {
            soundPool.play(successSoundId, 1.0f, 1.0f, 1, 0, 1.0f)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun playErrorSound() {
        try {
            soundPool.play(errorSoundId, 1.0f, 1.0f, 1, 0, 1.0f)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun release() {
        soundPool.release()
    }
}
