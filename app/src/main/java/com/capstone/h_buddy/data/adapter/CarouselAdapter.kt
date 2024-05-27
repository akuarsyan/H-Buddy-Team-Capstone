package com.capstone.h_buddy.data.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.h_buddy.data.preference.CarouselModel
import com.capstone.h_buddy.databinding.CarouselItemBinding
import com.google.android.material.animation.AnimationUtils.lerp

class CarouselAdapter(val list: ArrayList<CarouselModel>, val context: Context) :
    RecyclerView.Adapter<CarouselAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: CarouselItemBinding) :
        RecyclerView.ViewHolder(binding.root){
            @SuppressLint("RestrictedApi")
            fun bind(model: CarouselModel){
                binding.apply {
                    carouselImageView.setImageResource(model.imageId)
                    carouselTextView.text = model.title
                    carouselItemContainer.setOnMaskChangedListener { maskRect ->
                        carouselTextView.translationX = maskRect.left
                        carouselTextView.alpha = lerp(1F, 0F, 0F, 80F, maskRect.left)
                    }
                }
            }
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ItemViewHolder {
        return ItemViewHolder(CarouselItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val model = list[position]
        holder.bind(model)
    }

    override fun getItemCount() = list.size
}