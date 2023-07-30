package com.example.mytask5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mytask5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels {
        ViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvNews.layoutManager = LinearLayoutManager(this)

        setupViewModel()
    }

    private fun setupViewModel() {

        mainViewModel.getHeadlineNews()

        val adapter = NewsListAdapter()
        binding.rvNews.adapter = adapter
        mainViewModel.allNews.observe(this, {
            adapter.submitData(lifecycle, it)
        })

        mainViewModel.headlineNews.observe(this, {
            binding.beritaTerkiniTitleTv.text = it.title
            binding.beritaTerkiniPublishedAtTv.text = it.publishedAt
            Glide.with(applicationContext)
                .load(it.urlToImage)
                .into(binding.beritaTerkiniImageView)
        })
    }
}