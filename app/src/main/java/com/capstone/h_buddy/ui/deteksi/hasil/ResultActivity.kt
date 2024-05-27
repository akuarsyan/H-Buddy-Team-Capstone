package com.capstone.h_buddy.ui.deteksi.hasil

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.h_buddy.data.adapter.CarouselAdapter
import com.capstone.h_buddy.data.preference.CarouselModel
import com.capstone.h_buddy.R
import com.capstone.h_buddy.data.adapter.ReferencesAdapter
import com.capstone.h_buddy.data.preference.ReferencesModel
import com.capstone.h_buddy.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private val list = ArrayList<CarouselModel>()
    private var listReferences = ArrayList<ReferencesModel>()
    private lateinit var carouselAdapter: CarouselAdapter
    private lateinit var referencesAdapter: ReferencesAdapter

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

        addItemOtherResult()
        addItemReferences()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addItemReferences() {
        if(listReferences.isEmpty()){
            listReferences.add(ReferencesModel(R.drawable.jahe, "Jahe"))
            listReferences.add(ReferencesModel(R.drawable.kencur, "Kencur"))
            listReferences.add(ReferencesModel(R.drawable.temulawak, "Temulawak"))
            listReferences.add(ReferencesModel(R.drawable.kunyit, "Kunyit"))
            listReferences.add(ReferencesModel(R.drawable.kayumanis, "Kayu Manis"))
            listReferences.add(ReferencesModel(R.drawable.lidahbuaya, "Lidah Buaya"))
            referencesAdapter.notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addItemOtherResult() {
        if(list.isEmpty()){
            list.add(CarouselModel(R.drawable.jahe, "Jahe"))
            list.add(CarouselModel(R.drawable.kencur, "Kencur"))
            list.add(CarouselModel(R.drawable.temulawak, "Temulawak"))
            list.add(CarouselModel(R.drawable.kunyit, "Kunyit"))
            list.add(CarouselModel(R.drawable.kayumanis, "Kayu Manis"))
            list.add(CarouselModel(R.drawable.lidahbuaya, "Lidah Buaya"))
            carouselAdapter.notifyDataSetChanged()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}