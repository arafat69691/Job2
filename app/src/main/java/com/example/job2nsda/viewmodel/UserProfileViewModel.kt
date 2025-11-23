package com.example.job2nsda.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.job2nsda.data.UserDatabase
import com.example.job2nsda.data.UserProfile
import com.example.job2nsda.repository.UserProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserProfileViewModel(application: Application): AndroidViewModel(application) {
    private val repository: UserProfileRepository

    init {
        val userProfileDao = UserDatabase.getDatabase(application).userProfileDao()
        repository = UserProfileRepository(userProfileDao)
    }
    fun getUserProfiles(): LiveData<List<UserProfile>>{
        return repository.getUserProfiles()
    }
    fun insertUserProfile(userProfile: UserProfile){
        viewModelScope.launch (Dispatchers.IO){
            repository.insert(userProfile)
        }
    }
    fun updateUserProfile(userProfile: UserProfile){
        viewModelScope.launch(Dispatchers.IO){
            repository.update(userProfile)
        }
    }
    fun deleteUserProfile(userProfile: UserProfile){
        viewModelScope.launch (Dispatchers.IO){
            repository.delete(userProfile)
        }
    }
}