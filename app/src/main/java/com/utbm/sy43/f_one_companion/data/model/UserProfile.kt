package com.utbm.sy43.f_one_companion.data.model

data class UserProfile(
    val firstName: String = "",
    val lastName: String = "",
    val userName: String = "",
    val favoriteDrivers: List<String> = listOf(),
    val favoriteTeams: List<String> = listOf()
)
