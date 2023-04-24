@file:Suppress("OVERRIDE_DEPRECATION")

package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.viewModels
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityNewPostBinding
import ru.netology.nmedia.viewmoodel.PostViewModel


class NewPostActivity : AppCompatActivity() {

    val viewModel: PostViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.content.setText(intent?.getStringExtra(Intent.EXTRA_TEXT))
        binding.addButton.setOnClickListener {
            val text = binding.content.text.toString()
            if (text.isBlank()){
                setResult(Activity.RESULT_CANCELED)
            }else{
                setResult(Activity.RESULT_OK, Intent().apply { putExtra(Intent.EXTRA_TEXT,text) })
            }
            finish()
        }

    }
    object Contract:ActivityResultContract<String,String?>(){
        override fun createIntent(context: Context, input: String) =
            Intent(context,NewPostActivity::class.java).putExtra(Intent.EXTRA_TEXT,input)

        override fun parseResult(resultCode: Int, intent: Intent?) = intent?.getStringExtra(Intent.EXTRA_TEXT)

    }
}