package ru.netology.nmedia.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.adapter.PostListener
import ru.netology.nmedia.databinding.FragmentFeedBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmoodel.PostViewModel
import androidx.fragment.app.viewModels


class FeedFragment : Fragment() {
    val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentFeedBinding.inflate(layoutInflater,container,false)

        val adapter = PostAdapter(
            object : PostListener {
                override fun onRemove(post: Post) {
                    viewModel.removeById(post.idPost)
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
                    val startIntent = Intent.createChooser(intent, getString(R.string.nmedia))
                    startActivity(startIntent)
                    viewModel.shareById(post.idPost)
                }

                override fun onEdit(post: Post) {
                    viewModel.edit(post)
                    findNavController().navigate(
                        R.id.action_feedFragment_to_newPostFragment5,
                        bundleOf("textArg" to post.contentPost)
                    )
                }

                override fun vidioPlay(post: Post) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.video))
                    startActivity(intent)
                }

                override fun onDetielsPost(post: Post) {
                     viewModel.viewingById(post.idPost)
                     findNavController().navigate(R.id.action_feedFragment_to_ditailsPostFragment,
                     bundleOf("idArg" to post.idPost.toString())
                     )
                }
            }
        )


        binding.list.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) { posts ->
            adapter.submitList(posts)
        }
        binding.add.setOnClickListener {
            findNavController()
                .navigate(R.id.action_feedFragment_to_newPostFragment5)
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.notEdit()
    }

}
















