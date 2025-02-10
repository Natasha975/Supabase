package com.example.supabase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        var buton = findViewById<Button>(R.id.button)
        var t2 = findViewById<EditText>(R.id.editTextText2)
        var t3 = findViewById<EditText>(R.id.editTextText3)
        var t4 = findViewById<EditText>(R.id.editTextText4)
        buton.setOnClickListener{
            var sup = SupabaseManager()
            lifecycleScope.launch {
                sup.PostMain(t2.text.toString(),t3.text.toString(),t4.text.toString())
            }
        }
    }
}