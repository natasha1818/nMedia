package ru.netology.nmedia.adapter

import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.ShotNumber
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post

class PostViewHolder(
    private val binding: CardPostBinding,
    private val listener: PostListener

) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            textView.text = post.author
            dataPostTextView.text = post.dataPost
            contentTextView.text = post.contentPost
            shareImageView3.text = ShotNumber.shortNumber(post.shareCount)
            viewingTextView.text = ShotNumber.shortNumber(post.viewingCount)
            likeImageView2.isChecked = post.likesByMe
            likeImageView2.text = ShotNumber.shortNumber(post.countLike)
            likeImageView2.setOnClickListener {
                listener.onLike(post)
            }
            shareImageView3.setOnClickListener {
                listener.onShare(post)
            }
            menuImegeView.setOnClickListener {
                PopupMenu(it.context, it).apply {
                  inflate(R.menu.post_options)
                    setOnMenuItemClickListener {item ->
                      when(item.itemId) {
                       R.id.remove -> {
                           listener.onRemove(post)
                              true}
                       R.id.edit ->{
                           listener.onEdit(post)
                           true
                       }
                        else -> false
                    }}

                }.show()
            }


        }
    }
}