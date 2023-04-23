package ru.netology.nmedia.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.adapter.PostListener
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.utils.AndroidUtils
import ru.netology.nmedia.viewmoodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val viewModel: PostViewModel by viewModels()

        val newPostContract = registerForActivityResult(NewPostActivity.Contract) { result ->
            result ?: return@registerForActivityResult
            viewModel.changeContent(result)
            viewModel.save()

        }
        val adapter = PostAdapter(
            object : PostListener {
                override fun onRemove(post: Post) {
                    viewModel.removeById(post.idPost)
                }
                override fun onLike(post: Post) {
                    viewModel.likeById(post.idPost)
                }
                override fun onShare(post: Post) {
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, post.contentPost)
                        type = "text/plain"
                    }
                    val startIntent = Intent.createChooser(intent, getString(R.string.nmedia))
                    startActivity(startIntent)
                }
                override fun onEdit(post: Post) {
                    newPostContract.launch(post.contentPost)
                    viewModel.edit(post)
                }

                override fun vidioPlay(post: Post) {
                     val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.video))
                     startActivity(intent)
                }
            }
    )


        activityMainBinding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
        activityMainBinding.add.setOnClickListener {
            newPostContract.launch("")
        }


    }}









