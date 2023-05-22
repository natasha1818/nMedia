package ru.netology.nmedia.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.netology.nmedia.entity.PostEntity


@Dao
interface PostDao {
    @Query("SELECT * FROM PostEntity ORDER BY idPost DESC")
    fun getAll():LiveData<List<PostEntity>>
    @Query(
        """
           UPDATE PostEntity SET
               countLike = countLike + CASE WHEN likesByMe THEN -1 ELSE 1 END,
               likesByMe = CASE WHEN likesByMe THEN 0 ELSE 1 END
           WHERE idPost = :idPost
        """
    )
    fun likeById(idPost:Long)

    @Query(
        """     UPDATE PostEntity SET
                shareCount = shareCount +1
                WHERE idPost = :idPost
            """
    )
    fun shareById(idPost:Long)
    @Query("DELETE FROM PostEntity WHERE idPost = :idPost ")
    fun removeById(idPost:Long)
    @Insert
    fun insert(post: PostEntity)

    @Query(
        """     UPDATE PostEntity SET
                viewingCount = viewingCount +1
                WHERE idPost = :idPost
            """
    )
    fun viewingById(idPost:Long)

    @Query("UPDATE PostEntity SET contentPost = :text WHERE idPost = :idPost")
    fun updateContentById(idPost:Long, text:String)
    fun save(post: PostEntity) = if (post.idPost ==0L) insert(post) else updateContentById(post.idPost, post.contentPost)
}