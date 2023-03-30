package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmoodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                textView.text = post.author
                dataPostTextView.text = post.dataPost
                contentTextView.text = post.contentPost
                likeTextView.text = ShotNumber.shortNumber(post.countLike)
                shareTextView.text = ShotNumber.shortNumber(post.shareCount)
                viewingTextView.text = ShotNumber.shortNumber(post.viewingCount)
                likeImageView2.setImageResource(if (post.likesByMe) R.drawable.ic_liked_24
                 else  R.drawable.baseline_favorite_border_24)
                }
            }

        binding.likeImageView2.setOnClickListener {
            viewModel.like()
        }
        binding.shareImageView3.setOnClickListener {
            viewModel.share()
        }
    }
    }






