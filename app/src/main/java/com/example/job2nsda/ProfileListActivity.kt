package com.example.job2nsda

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.job2nsda.databinding.ActivityProfileListBinding
import com.example.job2nsda.ui.ProfileAdapter
import com.example.job2nsda.viewmodel.UserProfileViewModel

class ProfileListActivity : AppCompatActivity() {
    private lateinit var profileViewModel: UserProfileViewModel
    private lateinit var profileAdapter: ProfileAdapter

    private lateinit var binding: ActivityProfileListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProfileListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        profileViewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)
        profileAdapter = ProfileAdapter()
        binding.profileRecycleView.adapter = profileAdapter
        binding.profileRecycleView.layoutManager = LinearLayoutManager(this )

        profileViewModel.getUserProfiles().observe(this,{profiles ->
            profileAdapter.submitList(profiles)



        })


        profileAdapter.setOnClickLister { userProfile ->
            val intent  = Intent(this@ProfileListActivity, ProfileDetailsActivity::class.java)
            intent.putExtra("USER_PROFILE",userProfile)
            startActivity(intent)

        }
        profileAdapter.setOnDeleteClickListener { userProfile ->
            profileViewModel.deleteUserProfile(userProfile)
        }
        profileAdapter.setOnUpdateClickListener { userProfile ->
            val intent = Intent(this@ProfileListActivity, UpdateProfileActivity::class.java)
            intent.putExtra("USER_PROFILE",userProfile)
            startActivity(intent)
        }
        binding.addBtn.setOnClickListener {
            startActivity(Intent(this@ProfileListActivity, AddProfileActivity::class.java))
        }
    }
}