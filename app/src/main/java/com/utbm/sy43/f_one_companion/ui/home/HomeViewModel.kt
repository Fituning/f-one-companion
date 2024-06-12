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
        //auth.signInWithEmailAndPassword("carlo@test.com","210901")
        Log.d("test firestore", auth.currentUser?.uid.toString())
        // Fetch user data at the launch of the application
        fetchUserProfile()
        //createData()
    }

    private fun createData() {
        val userId = "nbTEMSMhmmMMOb1Jg81sPSAvseh2"
        val testUser = UserProfile(userName = "test")
        _uiState.value = _uiState.value.copy(user = testUser)
        val user = _uiState.value.user

        if (userId != null && user != null) {
            db.collection("users").document(userId).set(user)
                .addOnSuccessListener {
                }
                .addOnFailureListener { e ->
                    Log.e("authtag", e.toString())
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



}