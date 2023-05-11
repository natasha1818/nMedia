package ru.netology.nmedia.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityAppBinding


class appActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent?.let { intent ->
            if (intent.action != Intent.ACTION_SEND) {
                return@let
            }
            val content = intent.getStringExtra(Intent.EXTRA_TEXT)
            if (content?.isNotBlank() !=true) {
                return@let
            }
            intent.removeExtra(Intent.EXTRA_TEXT)
            findNavController(viewId = R.id.nav_host_fragment_container)
                .navigate(
                    R.id.action_feedFragment_to_newPostFragment5, bundleOf("textArg" to content)
                )
        }
    }

}
