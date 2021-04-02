package com.example.actionscorrection.ui.login

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
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
        //... other data fields that may be accessible to the UI
)