package com.utbm.sy43.f_one_companion.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utbm.sy43.f_one_companion.network.ErgastApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class ErgastViewModel : ViewModel() {
    var _uistate : ErgastUiState by mutableStateOf(ErgastUiState.Loading)
        private set

    init {
        getStandings()
    }
    
    private fun getStandings() {
        viewModelScope.launch {
            _uistate = try{
                val driverStandings = ErgastApi.retrofitService.getDriversStandings()
                val teamStandings = ErgastApi.retrofitService.getTeamStandings()
                ErgastUiState.Success(
                    driversStandings = driverStandings.MRData.standingsTable?.standingsLists?.get(0)?.driversStandings,
                    constructors = teamStandings.MRData.standingsTable?.standingsLists?.get(0)?.constructorStandings
                )
            }catch (e: IOException){
                Log.e("", e.toString())
                ErgastUiState.Error
            }catch (e: HttpException) {
                ErgastUiState.Error
            }
        }
    }

}