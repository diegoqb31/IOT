package com.example.iot_delta

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.iot_delta.databinding.ActivityNavegadorBinding

class NavegadorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavegadorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        val direccion = bundle?.getString("direccion")
        //setContentView(R.layout.activity_navegador)
        binding = ActivityNavegadorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl("${direccion}")
    }
}