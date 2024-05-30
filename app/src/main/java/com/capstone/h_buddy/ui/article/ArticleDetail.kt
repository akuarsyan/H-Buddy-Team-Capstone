package com.capstone.h_buddy.ui.article

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.h_buddy.R
import com.capstone.h_buddy.databinding.ActivityArticleDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetail : AppCompatActivity() {
    private lateinit var binding: ActivityArticleDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.articleToolbar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getWebView()


    }

    private fun getWebView() {
        val articleUrl = intent.getStringExtra(ARTICLE_DATA)
        if (articleUrl != null) {
            setupWebView(articleUrl)
        }
    }

    private fun setupWebView(url: String) {
       binding.apply {
           articleWebView.settings.javaScriptEnabled = true
           articleWebView.loadUrl(url)
       }
    }


    companion object {
        const val ARTICLE_DATA = "article_data"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}