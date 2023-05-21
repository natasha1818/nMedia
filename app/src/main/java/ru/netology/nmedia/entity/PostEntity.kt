package ru.netology.nmedia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.dto.Post

@Entity
data class PostEntity (
    @PrimaryKey(autoGenerate = true)
    val idPost: Long,
    val author: String,
    val dataPost: String,
    val contentPost: String,
    val likesByMe: Boolean,
    val countLike: Long,
    val shareCount: Long,
    val viewingCount: Long,
    val video: String?,
        ){
    fun toDto() = Post(idPost,author,dataPost,contentPost,likesByMe,countLike,shareCount,viewingCount,video)

    companion object {
        fun fromDto(post: Post) = with(post)
        { PostEntity(idPost,author,dataPost,contentPost,likesByMe,countLike,shareCount,viewingCount,video)}
    }
}