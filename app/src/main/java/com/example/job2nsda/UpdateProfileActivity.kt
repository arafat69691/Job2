package com.example.job2nsda

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.job2nsda.data.UserProfile
import com.example.job2nsda.databinding.ActivityUpdateProfileBinding
import com.example.job2nsda.viewmodel.UserProfileViewModel

class UpdateProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateProfileBinding
    private val profileViewModel: UserProfileViewModel by viewModels()
    private lateinit var userProfile: UserProfile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Receive the clicked user profile from intent
        userProfile = intent.getSerializableExtra("USER_PROFILE") as UserProfile

        // Fill UI with existing data
        updateUser()

        // Save/update button
        binding.btnSave.setOnClickListener {
            updateUserProfile()
        }
    }

    private fun updateUser() {
        binding.etName.setText(userProfile.name)
        binding.etEmail.setText(userProfile.email)
        binding.etDob.setText(userProfile.dob)
        binding.etDistrict.setText(userProfile.district)
        binding.etMobile.setText(userProfile.mobile)
    }

    private fun updateUserProfile() {

        // get updated values
        val updatedName = binding.etName.text.toString()
        val updatedEmail = binding.etEmail.text.toString()
        val updatedDob = binding.etDob.text.toString()
        val updatedDistrict = binding.etDistrict.text.toString()
        val updatedMobile = binding.etMobile.text.toString()

        // create updated user object (keeping old ID)
        val updatedUser = UserProfile(
            id = userProfile.id,
            name = updatedName,
            email = updatedEmail,
            dob = updatedDob,
            district = updatedDistrict,
            mobile = updatedMobile
        )

        // update in database
        profileViewModel.updateUserProfile(updatedUser)

        // finish the activity
        finish()
    }
}
