package ru.netology.nmedia.viewmoodel

import android.app.Application
import androidx.lifecycle.*
import ru.netology.nmedia.db.AppDb
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositorySQLiteImpl

private val empty = Post(
    idPost = 0L,
    author = "Me",
    dataPost = "now",
    contentPost = "",
    likesByMe = false,
    countLike = 0L,
    shareCount = 0L,
    viewingCount = 0L,
    video = null,
)
class PostViewModel(application: Application): AndroidViewModel(application) {
    val repository: PostRepository = PostRepositorySQLiteImpl(
        AppDb.getInstance(application).postDao
    )

    val data: LiveData<List<Post>> = repository.getAll()
    fun likeById(id: Long) = repository.likeById(id)
    fun shareById(id: Long) = repository.shareById(id)
    fun removeById(id: Long) = repository.removeById(id)
    fun viewingById(id: Long) = repository.viewingById(id)


    val adited = MutableLiveData(empty)
    fun save(){
       adited.value?.let {
           repository.save(it)
       }
       adited.value = empty
    }
    fun edit(post: Post){
        adited.value = post
    }
    fun notEdit(){
        adited.value = empty
    }

    fun changeContent(content:String){
        adited.value?.let {posts ->
           if(content != posts.contentPost){
                    adited.value = posts.copy(contentPost = content)
              }
        }

    }

}