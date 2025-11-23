package com.example.job2nsda.ui

import android.speech.RecognitionListener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.job2nsda.data.UserProfile
import com.example.job2nsda.databinding.ActivityProfileListBinding
import com.example.job2nsda.databinding.ProfileItemBinding

class ProfileAdapter: ListAdapter<UserProfile, ProfileAdapter.ProfileViewHolder>(DiffCallBack()) {
    private var onItemClick:((UserProfile)-> Unit)?=null
    private var onDeleteClick:((UserProfile)-> Unit)?=null
    private var onUpdateClick:((UserProfile)-> Unit)?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = ProfileItemBinding.inflate(LayoutInflater.from(parent.context), parent, false
        )
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setOnClickLister(listener: (UserProfile)-> Unit){
        onItemClick = listener
    }
    fun setOnDeleteClickListener(listener: (UserProfile)->Unit){
        onDeleteClick = listener

    }
    fun setOnUpdateClickListener(listener:(UserProfile)-> Unit){
        onUpdateClick = listener
    }
    inner class ProfileViewHolder(private val binding: ProfileItemBinding) : RecyclerView.ViewHolder(binding.root) {

            fun bind(user: UserProfile){

                binding.tvNameLabel.text = user.name
                binding.tvEmailLabel.text = user.email
                binding.tvDistrictLabel.text = user.district
                binding.tvMobileLabel.text = user.mobile
                binding.tvDobLabel.text = user.dob

                binding.btnEdit.setOnClickListener { onUpdateClick?.invoke(user) }
                binding.btnDelete.setOnClickListener { onDeleteClick?.invoke(user) }
                binding.root.setOnClickListener { onItemClick?.invoke(user) }

            }

    }
    class DiffCallBack: DiffUtil.ItemCallback<UserProfile>(){
        override fun areItemsTheSame(oldItem: UserProfile, newItem: UserProfile): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserProfile, newItem: UserProfile): Boolean {
            return oldItem == newItem
        }

    }
}