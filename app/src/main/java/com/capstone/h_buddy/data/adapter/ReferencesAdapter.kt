package com.capstone.h_buddy.data.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.h_buddy.data.preference.ReferencesModel
import com.capstone.h_buddy.databinding.ReferencesItemBinding
import com.google.android.material.animation.AnimationUtils

class ReferencesAdapter (val list: ArrayList<ReferencesModel>, val context: Context) :
    RecyclerView.Adapter<ReferencesAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(private val binding: ReferencesItemBinding) :
        RecyclerView.ViewHolder(binding.root){
        @SuppressLint("RestrictedApi")
        fun bind(model: ReferencesModel){
            binding.apply {
                carouselImageView.setImageResource(model.imageId)
                carouselTextView.text = model.title
                carouselItemContainer.setOnMaskChangedListener { maskRect ->
                    carouselTextView.translationX = maskRect.left
                    carouselTextView.alpha = AnimationUtils.lerp(1F, 0F, 0F, 80F, maskRect.left)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ItemViewHolder {
        return ItemViewHolder(ReferencesItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val model = list[position]
        holder.bind(model)
    }

    override fun getItemCount() = list.size
}