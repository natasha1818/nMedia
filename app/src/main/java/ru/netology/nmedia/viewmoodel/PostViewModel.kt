package ru.netology.nmedia.viewmoodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemory

private val empty = Post(
    idPost = 0L,
    author = "",
    dataPost = "",
    contentPost = "",
    likesByMe = false,
    countLike = 0L,
    shareCount = 0L,
    viewingCount = 0L,
    video = null



)
class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemory()

    val data: LiveData<List<Post>> = repository.getData()
    fun likeById(id: Long) = repository.likeById(id)
    fun shareById(id: Long) = repository.shareById(id)
    fun removeById(id: Long) = repository.removeById(id)
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