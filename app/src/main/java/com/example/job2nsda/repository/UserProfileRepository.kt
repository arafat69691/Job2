package com.example.job2nsda.repository

import androidx.lifecycle.LiveData
import com.example.job2nsda.data.UserProfile
import com.example.job2nsda.data.UserProfileDao

class UserProfileRepository(private val userProfileDao: UserProfileDao) {
    fun getUserProfiles(): LiveData<List<UserProfile>>{
        return userProfileDao.getAllUserProfile()
    }
    suspend fun insert(userProfile: UserProfile){
        userProfileDao.insert(userProfile)
    }

    suspend fun update(userProfile: UserProfile){
        userProfileDao.update(userProfile)
    }
    suspend fun delete(userProfile: UserProfile){
        userProfileDao.delete(userProfile)
    }
}