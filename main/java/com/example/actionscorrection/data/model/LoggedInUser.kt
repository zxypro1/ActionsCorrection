package com.example.actionscorrection.data.model

import android.media.Image
import android.widget.ImageView

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
        val userId: String,
        val displayName: String,
        //val personalSign: String,
        //val headImage: Image,
        val gender: Boolean,
        val birthDate: Int,
        val height: Int,
        val weight: Int,
        val signInCount: Int,
        val signInContinuousCount: Int,
        val workoutCount: Int,
        val workoutTime: Int,
        val uploadCount: Int,
        val workoutLength: Double,
        val todayHaveSignedIn: Boolean,
        )
