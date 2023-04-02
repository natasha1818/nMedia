package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.ShotNumber
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemory : PostRepository {
    private var post = List(1000) {
        Post(
            idPost = it.toLong() + 1,
            author = "Нетология.Университет интернет-профессий будущего",
            dataPost = "05 Марта 2023 15:24",
            contentPost = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растем сами и помогаем расти студентам: от новичков до увлеченных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставит хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен -> https://netology.ru",
            likesByMe = false,
            countLike = 999,
            shareCount = 99,
            viewingCount = 2000
        )
    }.reversed()

    private val data = MutableLiveData(post)
    override fun getData(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        post = post.map { post ->
            if (post.idPost == id) {
                post.copy(
                    likesByMe = !post.likesByMe,
                    countLike = if (post.likesByMe) post.countLike - 1 else post.countLike + 1
                )
            } else {
                post
            }
        }
        data.value = post
    }

    override fun shareById(id: Long) {
        post = post.map { post ->
            if (post.idPost == id) {
                post.copy(shareCount = post.shareCount + 1)
            } else {
                post
            }
        }
        data.value = post
    }
}