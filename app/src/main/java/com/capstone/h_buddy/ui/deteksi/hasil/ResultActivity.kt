package com.capstone.h_buddy.ui.deteksi.hasil

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.h_buddy.data.adapter.CarouselAdapter
import com.capstone.h_buddy.data.preference.CarouselModel
import com.capstone.h_buddy.R
import com.capstone.h_buddy.data.adapter.ArticleAdapter
import com.capstone.h_buddy.data.adapter.ReferencesAdapter
import com.capstone.h_buddy.data.preference.ReferencesModel
import com.capstone.h_buddy.databinding.ActivityResultBinding
import com.capstone.h_buddy.ui.beranda.HomeViewModel
import com.capstone.h_buddy.utils.MyResponse
import com.capstone.h_buddy.utils.tools.MarginItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private val list = ArrayList<CarouselModel>()
    private var listReferences = ArrayList<ReferencesModel>()
    private lateinit var carouselAdapter: CarouselAdapter
    private lateinit var referencesAdapter: ReferencesAdapter

    // viewModel
    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var articleAdapter: ArticleAdapter




    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        carouselAdapter = CarouselAdapter(list, this)
        binding.rvResultOther.adapter = carouselAdapter

        referencesAdapter = ReferencesAdapter(listReferences, this)
        binding.rvResultReferences.adapter = referencesAdapter

        setUpViews()
        viewModel.getAllArticle()
        addItemOtherResult()
        observeArticleReference()
        //classification result
        setResultClassification()
    }




    private fun setUpViews() {
        val verticalMargin = resources.getDimensionPixelSize(R.dimen.vertical_margin)
        val horizontalMargin = resources.getDimensionPixelSize(R.dimen.horizontal_margin)

        binding.rvResultReferences.apply {
            layoutManager = LinearLayoutManager(this@ResultActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = articleAdapter
            addItemDecoration(MarginItemDecoration(verticalMargin, horizontalMargin))
        }
    }

    private fun observeArticleReference() {
        viewModel.articleData.observe(this) { response ->
            when (response.status) {
                MyResponse.Status.SUCCESS -> {
                    binding.progressBarArticleReference.visibility = View.GONE
                    response.data?.articles?.let { articleAdapter.setData(it) }
                }
                MyResponse.Status.ERROR -> {
                    binding.progressBarArticleReference.visibility = View.GONE
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                }
                MyResponse.Status.LOADING -> {
                    binding.progressBarArticleReference.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setResultClassification() {

        val result = intent.getStringExtra("result")
        val confidenceScore = intent.getStringExtra("confidenceScore")
        val description = intent.getStringExtra("description")
        val binomial = intent.getStringExtra("binomial")
        val benefit = intent.getStringExtra("benefit")

        binding.tvResultTitle.text = result
        binding.tvBioma.text = binomial
        binding.tvDescription.text = description
        binding.tvBenefit.text = benefit
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addItemOtherResult() {
        if (list.isEmpty()) {
            list.add(CarouselModel(R.drawable.asoka, "Asoka"))
            list.add(CarouselModel(R.drawable.bunga_telang, "Bunga Telang"))
            list.add(CarouselModel(R.drawable.daun_jambu_biji, "Daun Jambu Biji"))
            list.add(CarouselModel(R.drawable.daun_jarak, "Daun Jarak"))
            list.add(CarouselModel(R.drawable.daun_jeruk_nipis, "Daun Jeruk Nipis"))
            list.add(CarouselModel(R.drawable.daun_kayu_putih, "Daun Kayu Putih"))
            list.add(CarouselModel(R.drawable.daun_pepaya, "Daun Pepaya"))
            list.add(CarouselModel(R.drawable.daun_sirih, "Daun Sirih"))
            list.add(CarouselModel(R.drawable.lidah_buaya, "Lidah Buaya"))
            list.add(CarouselModel(R.drawable.semanggi, "Semanggi"))
            carouselAdapter.notifyDataSetChanged()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
