package ru.netology.nmedia.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityIntentHandlerBinding

class IntentHandlerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityIntentHandlerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent?.let {intent->
            if (intent.action != Intent.ACTION_SEND){
                return@let
                 }
            val text = intent.getStringExtra(Intent.EXTRA_TEXT)
            if (text.isNullOrBlank()){
                Snackbar.make(binding.root,R.string.cant_be_empty, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok){
                        finish()
                    }.show()
            } else{
                Toast.makeText(this,text,Toast.LENGTH_LONG).show()
            }
        }
    }
}