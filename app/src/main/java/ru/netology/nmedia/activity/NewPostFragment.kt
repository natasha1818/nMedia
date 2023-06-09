
package ru.netology.nmedia.activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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
//           val vidiotext = it.getString("vidioArg", null)
//           binding.videoLink.setText(vidiotext)

       }




        binding.addButton.setOnClickListener {
            if (!binding.content.text.isNullOrBlank()) {
           //     val vidiotext = binding.videoLink.text.toString()
                val text = binding.content.text.toString()
              viewModel.changeContent(text)
                viewModel.save()}
            findNavController().navigateUp()
        }
        return binding.root
    }

}