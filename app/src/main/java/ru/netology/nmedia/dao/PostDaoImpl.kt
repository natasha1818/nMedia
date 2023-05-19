package ru.netology.nmedia.dao

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import ru.netology.nmedia.dto.Post
import android.database.Cursor

class PostDaoImpl(private val db:SQLiteDatabase):PostDao {

    companion object{
        val DDL = """
        CREATE TABLE ${PostColumns.TABLE}(
              ${PostColumns.COLUMN_IDPOST} INTEGER PRIMARY KEY AUTOINCREMENT,
              ${PostColumns.COLUMN_AUTHOR} TEXT NOT NULL,
              ${PostColumns.COLUMN_DATAPOST} TEXT NOT NULL,
              ${PostColumns.COLUMN_CONTENTPOST} TEXT NOT NULL,
              ${PostColumns.COLUMN_LIKESBYME} BOOLEAN NOT NULL DEFAULT 0,
              ${PostColumns.COLUMN_COUNTLIKE} INTEGER NOT NULL DEFAULT 0,
              ${PostColumns.COLUMN_SHARECOUNT} INTEGER NOT NULL DEFAULT 0,
              ${PostColumns.COLUMN_VIEWINGCOUNT} INTEGER NOT NULL DEFAULT 0,
              ${PostColumns.COLUMN_VIDEO} TEXT
                        );""".trimIndent()
    }
    object PostColumns{
        const val TABLE = "posts"
        const val COLUMN_IDPOST = "idPost"
        const val COLUMN_AUTHOR = "author"
        const val COLUMN_DATAPOST = "dataPost"
        const val COLUMN_CONTENTPOST = "contentPost"
        const val COLUMN_LIKESBYME = "likesByMe"
        const val COLUMN_COUNTLIKE = "countLike"
        const val COLUMN_SHARECOUNT = "shareCount"
        const val COLUMN_VIEWINGCOUNT = "viewingCount"
        const val COLUMN_VIDEO = "video"
        val ALL_COLUMNS  = arrayOf(
            COLUMN_IDPOST,
            COLUMN_AUTHOR,
            COLUMN_DATAPOST,
            COLUMN_CONTENTPOST,
            COLUMN_LIKESBYME,
            COLUMN_COUNTLIKE,
            COLUMN_SHARECOUNT,
            COLUMN_VIEWINGCOUNT,
            COLUMN_VIDEO
        )
    }

    override fun getAll(): List<Post> {
        val posts = mutableListOf<Post>()

        db.query(
            PostColumns.TABLE,
            PostColumns.ALL_COLUMNS,
            null,
            null,
            null,
            null,
            "${PostColumns.COLUMN_IDPOST} DESC"
        ).use {
            while (it.moveToNext()){
                posts.add(map(it))
            }
        }
        return posts
    }

    override fun likeById(idPost: Long) {
        db.execSQL(
        """
           UPDATE posts SET
               countLike = countLike + CASE WHEN likesByMe THEN -1 ELSE 1 END,
               likesByMe = CASE WHEN likesByMe THEN 0 ELSE 1 END
           WHERE idPost = ?;
        """.trimIndent(), arrayOf(idPost)
    )
    }

    override fun shareById(id: Long) {
        db.execSQL(
            """
                UPDATE posts SET
                shareCount = shareCount +1
            """.trimIndent(), arrayOf(id)
        )

    }

    override fun removeById(id: Long) {
        db.delete(
            PostColumns.TABLE,
            "${PostColumns.COLUMN_IDPOST} = ?",
            arrayOf(id.toString())
        )
    }

    override fun save(post: Post): Post {
        val values = ContentValues().apply {
            put(PostColumns.COLUMN_AUTHOR,"Me")
            put(PostColumns.COLUMN_CONTENTPOST,post.contentPost)
            put(PostColumns.COLUMN_DATAPOST,"now")
        }
        val id = if (post.idPost != 0L){
            db.update(
                PostColumns.TABLE,
                values,
                "${PostColumns.COLUMN_IDPOST} = ?",
                arrayOf(post.idPost.toString()),
            )
            post.idPost} else{
                db.insert(PostColumns.TABLE,null,values)
        }
        db.query(
            PostColumns.TABLE,
            PostColumns.ALL_COLUMNS,
            "${PostColumns.COLUMN_IDPOST} = ?",
            arrayOf(id.toString()),
            null,
            null,
            null,
        ).use {
            it.moveToNext()
            return map(it)
        }
    }
    private fun map(cursor: Cursor): Post {
        with(cursor) {
            return Post(
                idPost = getLong(getColumnIndexOrThrow(PostColumns.COLUMN_IDPOST)),
                author = getString(getColumnIndexOrThrow(PostColumns.COLUMN_AUTHOR)),
                dataPost = getString(getColumnIndexOrThrow(PostColumns.COLUMN_DATAPOST)),
                contentPost = getString(getColumnIndexOrThrow(PostColumns.COLUMN_CONTENTPOST)),
                likesByMe = getInt(getColumnIndexOrThrow(PostColumns.COLUMN_LIKESBYME)) != 0,
                countLike = getLong(getColumnIndexOrThrow(PostColumns.COLUMN_COUNTLIKE)),
                shareCount = getLong(getColumnIndexOrThrow(PostColumns.COLUMN_SHARECOUNT)),
                viewingCount = getLong(getColumnIndexOrThrow(PostColumns.COLUMN_VIEWINGCOUNT)),
                video = getString(getColumnIndexOrThrow(PostColumns.COLUMN_VIDEO))
            )
        }
    }

}