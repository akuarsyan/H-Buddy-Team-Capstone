package com.capstone.h_buddy.data.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.h_buddy.R
import com.capstone.h_buddy.data.api.article.ArticlesItem
import com.capstone.h_buddy.databinding.ArticleItemBinding
import com.capstone.h_buddy.ui.article.ArticleDetail
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class ArticleAdapter @Inject constructor(@ActivityContext private val context: Context): RecyclerView.Adapter<ArticleAdapter.MyViewholder>() {
    private lateinit var binding: ArticleItemBinding
    private var articleList = emptyList<ArticlesItem>()

    inner class MyViewholder : RecyclerView.ViewHolder(binding.root){
        fun setData(data: ArticlesItem){
            binding.apply {
                tvArticleTitle.text = data.title
                tvArticleBody.text = data.description
                tvArticleLabel.text = data.herb
                Glide.with(context)
                    .load(data.photoUrl)
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(ivArticleImage)
                buttonFavorite.setOnClickListener {
                    Toast.makeText(context, "Artikel ditambahkan ke favorit", Toast.LENGTH_LONG).show()
                }
            }
            itemView.setOnClickListener {
                val intent = Intent(context, ArticleDetail::class.java)
                intent.putExtra(ArticleDetail.ARTICLE_DATA, data.url)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        binding = ArticleItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewholder()
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.setData(articleList[position])
    }

    fun setData(data: List<ArticlesItem>){
        val articleDiffUtil = ArticleDiffUtils(articleList, data)
        val articleDiffResult = DiffUtil.calculateDiff(articleDiffUtil)
        articleList = data
        articleDiffResult.dispatchUpdatesTo(this)
    }

    class ArticleDiffUtils(
        private val oldItem : List<ArticlesItem>,
        private val newItem : List<ArticlesItem>
    ): DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldItem.size
        }

        override fun getNewListSize(): Int {
            return newItem.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] == newItem[newItemPosition]
        }
    }

}