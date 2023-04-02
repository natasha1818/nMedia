package ru.netology.nmedia.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmoodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val viewModel: PostViewModel by viewModels()

        val adapter = PostAdapter(
            onLikeClicked = {viewModel.likeById(it.idPost)},
            onShareClicked = { viewModel.shareById(it.idPost)}
        )

        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)

        }
        activityMainBinding.list.adapter = adapter

    }


}






