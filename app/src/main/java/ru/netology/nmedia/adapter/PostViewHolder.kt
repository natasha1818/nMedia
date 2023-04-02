package ru.netology.nmedia.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.netology.nmedia.R
import ru.netology.nmedia.ShotNumber
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeClicked: (Post) -> Unit,
    private val onShareClicked: (Post) -> Unit
) : ViewHolder(binding.root) {
    fun bind(post: Post) {
        with(binding) {
            textView.text = post.author
            dataPostTextView.text = post.dataPost
            contentTextView.text = post.contentPost
            likeTextView.text = ShotNumber.shortNumber(post.countLike)
            shareTextView.text = ShotNumber.shortNumber(post.shareCount)
            viewingTextView.text = ShotNumber.shortNumber(post.viewingCount)
            likeImageView2.setImageResource(
                if (post.likesByMe) R.drawable.ic_liked_24
                else R.drawable.baseline_favorite_border_24
            )
            likeImageView2.setOnClickListener {
                onLikeClicked(post)
            }
            shareImageView3.setOnClickListener {
                onShareClicked(post)
            }

        }
    }
}