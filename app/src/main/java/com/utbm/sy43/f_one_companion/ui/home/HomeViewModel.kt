package com.utbm.sy43.f_one_companion.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.utbm.sy43.f_one_companion.data.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState : StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    init {
        fetchUserProfile()
    }

    fun addDriverFav(driverName: String) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val user = _uiState.value.user
            if (user != null) {
                val updatedFavorites = user.favoriteDrivers.toMutableList()
                updatedFavorites.add(driverName)
                val updatedUser = user.copy(favoriteDrivers = updatedFavorites)

                db.collection("users").document(userId).set(updatedUser)
                    .addOnSuccessListener {
                        _uiState.update { currentState ->
                            currentState.copy(user = updatedUser)
                        }
                    }
                    .addOnFailureListener { e ->
                        Log.e("HomeViewModel", e.toString())
                    }
            }
        }
    }

    fun removeDriverFav(driverName: String) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val user = _uiState.value.user
            if (user != null) {
                val updatedFavorites = user.favoriteDrivers.toMutableList()
                updatedFavorites.remove(driverName)
                val updatedUser = user.copy(favoriteDrivers = updatedFavorites)

                db.collection("users").document(userId).set(updatedUser)
                    .addOnSuccessListener {
                        _uiState.update { currentState ->
                            currentState.copy(user = updatedUser)
                        }
                    }
                    .addOnFailureListener { e ->
                        Log.e("HomeViewModel", e.toString())
                    }
            }
        }
    }

    fun addConstructorFav(constructorName: String) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val user = _uiState.value.user
            if (user != null) {
                val updatedFavorites = user.favoriteTeams.toMutableList()
                updatedFavorites.add(constructorName)
                val updatedUser = user.copy(favoriteTeams = updatedFavorites)

                db.collection("users").document(userId).set(updatedUser)
                    .addOnSuccessListener {
                        _uiState.update { currentState ->
                            currentState.copy(user = updatedUser)
                        }
                    }
                    .addOnFailureListener { e ->
                        Log.e("HomeViewModel", e.toString())
                    }
            }
        }
    }

    fun removeConstructorFav(constructorName: String) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val user = _uiState.value.user
            if (user != null) {
                val updatedFavorites = user.favoriteTeams.toMutableList()
                updatedFavorites.remove(constructorName)
                val updatedUser = user.copy(favoriteTeams = updatedFavorites)

                db.collection("users").document(userId).set(updatedUser)
                    .addOnSuccessListener {
                        _uiState.update { currentState ->
                            currentState.copy(user = updatedUser)
                        }
                    }
                    .addOnFailureListener { e ->
                        Log.e("HomeViewModel", e.toString())
                    }
            }
        }
    }



    private fun fetchUserProfile() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            db.collection("users").document(userId).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val userProfile = document.toObject(UserProfile::class.java)
                        userProfile?.let {
                            _uiState.value = _uiState.value.copy(user = it)
                        }
                        _uiState.value.user?.let { Log.d("test firestore", it.userName) }
                    }
                }
                .addOnFailureListener { e ->
                    Log.e("test firestore", e.toString())
                }
        }
    }


    fun logOutUser(){
        auth.signOut()
        _uiState.value = HomeUiState(user = null)
    }

    fun updateUser(firstName: String, lastName: String, userName: String) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val currentUser = _uiState.value.user
            if (currentUser != null) {
                val newFirstName = if (firstName.isBlank()) currentUser.firstName else firstName
                val newLastName = if (lastName.isBlank()) currentUser.lastName else lastName
                val newUserName = if (userName.isBlank()) currentUser.userName else userName

                val updatedUser = currentUser.copy(
                    firstName = newFirstName,
                    lastName = newLastName,
                    userName = newUserName
                )

                db.collection("users").document(userId).set(updatedUser)
                    .addOnSuccessListener {
                        _uiState.update { currentState ->
                            currentState.copy(user = updatedUser)
                        }
                        Log.d("HomeViewModel", "User profile updated successfully")
                    }
                    .addOnFailureListener { e ->
                        Log.e("HomeViewModel", "Error updating user profile", e)
                    }
            } else {
                Log.e("HomeViewModel", "No user found in UI state")
            }
        } else {
            Log.e("HomeViewModel", "No user ID found")
        }
    }



}