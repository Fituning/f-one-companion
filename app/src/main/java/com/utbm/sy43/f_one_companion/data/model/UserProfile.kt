package com.utbm.sy43.f_one_companion.data.model

data class UserProfile(
    var firstName: String = "",
    var lastName: String = "",
    var userName: String = "",
    val favoriteDrivers: List<String> = listOf(),
    val favoriteTeams: List<String> = listOf()
)
