package ru.netology.nmedia.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.PostListener
import ru.netology.nmedia.adapter.PostViewHolder
import ru.netology.nmedia.databinding.FragmentDetailsBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmoodel.PostViewModel



class DetailsPostFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: PostViewModel by activityViewModels()
        val binding = FragmentDetailsBinding.inflate(layoutInflater)
        val id = requireArguments().getString("idArg").let { requireNotNull(it) }
        viewModel.data.observe(viewLifecycleOwner) { posts ->
            val post = posts.find { it.idPost == id.toLong() }
            with(binding) {
                post?.let {
                    PostViewHolder(
                        binding.postOne,
                        object : PostListener {
                            override fun onRemove(post: Post) {
                                viewModel.removeById(post.idPost)
                                findNavController().navigateUp()
                            }

                            override fun onLike(post: Post) {
                                viewModel.likeById(post.idPost)
                            }

                            override fun onShare(post: Post) {
                                val intent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, post.contentPost)
                                    type = "text/plain"
                                }
                                val startIntent =
                                    Intent.createChooser(intent, getString(R.string.nmedia))
                                startActivity(startIntent)
                            }

                            override fun onEdit(post: Post) {
                                viewModel.edit(post)
                                findNavController().navigate(
                                    R.id.action_ditailsPostFragment_to_newPostFragment5,
                                    bundleOf("textArg" to post.contentPost)
                                )
                            }

                            override fun vidioPlay(post: Post) {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.video))
                                startActivity(intent)
                            }

                            override fun onDetielsPost(post: Post) {
                                findNavController().navigateUp()

                            }
                        }
                    ).bind(it)
                }

            }

        }


        return binding.root

    }

}



