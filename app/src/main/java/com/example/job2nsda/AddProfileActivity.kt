package com.example.job2nsda

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.job2nsda.data.UserProfile
import com.example.job2nsda.databinding.ActivityAddProfileBinding
import com.example.job2nsda.viewmodel.UserProfileViewModel

class AddProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProfileBinding
    private lateinit var profileViewModel: UserProfileViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        profileViewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)

        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val dob = binding.etDob.text.toString()
            val district = binding.etDistrict.text.toString()
            val mobile = binding.etMobile.text.toString()

            val userProfile = UserProfile(name = name, email = email, dob = dob, district = district, mobile = mobile)
            profileViewModel.insertUserProfile(userProfile)

            finish()
        }
    }
}