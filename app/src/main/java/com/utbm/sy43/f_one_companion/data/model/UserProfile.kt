package com.utbm.sy43.f_one_companion.data.model

data class UserProfile(
    var firstName: String = "",
    var lastName: String = "",
    var userName: String = "",
    var favoriteDrivers: MutableList<String> = mutableListOf(),
    var favoriteTeams: MutableList<String> = mutableListOf()
)

