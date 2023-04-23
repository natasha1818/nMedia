package ru.netology.nmedia.dto

data class Post(
    val idPost: Long,
    val author: String,
    val dataPost: String,
    val contentPost: String,
    val likesByMe: Boolean,
    val countLike: Long,
    val shareCount: Long,
    val viewingCount: Long,
    val video: String?
) {

}





