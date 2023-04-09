package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
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

        val adapter = PostAdapter(
            object:PostListener{
                override fun onRemove(post: Post) {
                   viewModel.removeById(post.idPost)
                }

                override fun onLike(post: Post) {
                    viewModel.likeById(post.idPost)
                }

                override fun onShare(post: Post) {
                    viewModel.shareById(post.idPost)
                }

                override fun onEdit(post: Post) {
                    viewModel.edit(post)
                }

            }
        )
        viewModel.adited.observe(this){
            if (it.idPost == 0L){
                activityMainBinding.notEdit.visibility = View.GONE
                return@observe
            }

            activityMainBinding.content.requestFocus()
            activityMainBinding.notEdit.visibility = View.VISIBLE
            activityMainBinding.content.setText(it.contentPost)
        }

        activityMainBinding.save.setOnClickListener {
            with(activityMainBinding.content){
              if(text.isNullOrBlank()){
                    Toast.makeText(
                        this@MainActivity,
                        context.getString(R.string.not_be_empty),
                        Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                viewModel.changeContent(text.toString())
                viewModel.save()
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }
        activityMainBinding.notEdit.setOnClickListener {
            with(activityMainBinding.content){
                viewModel.notEdit(text.toString())
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }

        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)

        }
        activityMainBinding.list.adapter = adapter

    }


}






