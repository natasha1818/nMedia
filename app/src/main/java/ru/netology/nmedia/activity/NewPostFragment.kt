
package ru.netology.nmedia.activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.databinding.FargmentNewPostBinding
import ru.netology.nmedia.viewmoodel.PostViewModel



class NewPostFragment : Fragment() {

   override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       val viewModel: PostViewModel by viewModels(
           ownerProducer = ::requireParentFragment
       )

       val binding = FargmentNewPostBinding.inflate(layoutInflater, container,false)

       arguments?.let {
           val text = it.getString("textArg", null)
           binding.content.setText(text)
       }


        binding.addButton.setOnClickListener {
            if (!binding.content.text.isNullOrBlank()) {
                val text = binding.content.text.toString()
                viewModel.changeContent(text)
                viewModel.save()}
            findNavController().navigateUp()
        }
        return binding.root
    }

}