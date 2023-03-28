package ru.netology.nmedia

import android.icu.text.CompactDecimalFormat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var post = Post (
            idPost = 1,
            author = "Нетология.Университет интернет-профессий будущего",
            dataPost = "05 Марта 2023 15:24",
            contentPost = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растем сами и помогаем расти студентам: от новичков до увлеченных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставит хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен -> https://netology.ru",
            likesByMe = false,
            likedCount = 999,
            shareCount = 8555_000
                )
        with(binding){
            textView.text = post.author
            dataPostTextView.text= post.dataPost
            contentTextView.text=post.contentPost
            likeTextView.text = shortNumber(post.likedCount)
           shareTextView.text = shortNumber(post.shareCount)

            if (post.likesByMe){
                likeImageView2.setImageResource(R.drawable.ic_liked_24)
            }
            likeImageView2.setOnClickListener {
                post.likesByMe = !post.likesByMe
                post.likedCount = if (post.likesByMe){
                    post.likedCount+1
                }else{
                    post.likedCount-1
                }
                likeTextView.text = shortNumber(post.likedCount)

                likeImageView2.setImageResource(if (post.likesByMe){
                    R.drawable.ic_liked_24
                }else{
                    R.drawable.baseline_favorite_border_24
                })

            }
          shareImageView3.setOnClickListener {
              post.shareCount++
              shareTextView.text= shortNumber(post.shareCount)
          }


        }



    }



}