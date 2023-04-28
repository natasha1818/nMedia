package ru.netology.nmedia.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemory (private val context: Context, ): PostRepository {
     private val gson = Gson()
    private var type = TypeToken.getParameterized(List::class.java, Post::class.java).type
    private val fileName = "posts.json"
    private var nextId = 1L
    private var posts: List<Post> = emptyList()
    private val data = MutableLiveData(posts)

    init {
        val file = context.filesDir.resolve(fileName)
        if (file.exists()){
            context.openFileInput(fileName).bufferedReader().use {
                posts = gson.fromJson(/* json = */ it,/* typeOfT = */ type)
                data.value = posts
            }
        }else{
           sync()
        }
    }
    override fun getData(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map { post ->
            if (post.idPost == id) {
                post.copy(
                    likesByMe = !post.likesByMe,
                    countLike = if (post.likesByMe) post.countLike - 1 else post.countLike + 1
                )
            } else {
                post
            }
        }
        data.value = posts
        sync()
        return
    }

    override fun shareById(id: Long) {
        posts = posts.map { post ->
            if (post.idPost == id) {
                post.copy(shareCount = post.shareCount + 1)
            } else {
                post
            }
        }
        data.value = posts
        sync()
    }

    override fun removeById(id: Long) {
        posts = posts.filter {
            it.idPost != id
        }
        data.value = posts
        sync()
    }

    override fun save(post:Post) {
        if (post.idPost == 0L) {
            posts = listOf(post.copy(idPost = nextId++, author = "Me", dataPost = "now")) + posts
            data.value = posts
            return
        }
        posts = posts.map {
            if (it.idPost != post.idPost) it else it.copy(contentPost = post.contentPost)
            }
        data.value = posts
        sync()
        }
    private fun sync(){
        context.openFileOutput(fileName,Context.MODE_PRIVATE).bufferedWriter().use {
            it.write(gson.toJson(posts))
        }
          }

    }


