package com.example.manageyourcar.UIlayer.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.manageyourcar.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GeminiFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    sendPrompt("A tu une limite de prompt que je peux t'envoyer jusqu'à un éventuel blocage?")
    }
}
private fun sendPrompt(text: String) {
    val generativeModel = GenerativeModel(
        // For text-only input, use the gemini-pro model
        modelName = "gemini-pro",
        // Access your API key as a Build Configuration variable (see "Set up your API key" above)
        apiKey = BuildConfig.GEMINI_API_KEY
    )
    CoroutineScope(Dispatchers.IO).launch {
        val response = generativeModel.generateContent(text)
        withContext(Dispatchers.Main) {
            Log.i("GeminiFragment", "sendPrompt: ${response.text}")
        }

    }

}