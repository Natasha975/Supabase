package com.example.supabase

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var dataList: MutableList<Main>
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        dataList = mutableListOf()
        adapter = ArrayAdapter(this, android.R.layout.activity_mac, mutableListOf<String>())
        listView.adapter = adapter

        var buton = findViewById<Button>(R.id.button)
        var t2 = findViewById<EditText>(R.id.editTextText2)
        var t3 = findViewById<EditText>(R.id.editTextText3)
        var t4 = findViewById<EditText>(R.id.editTextText4)

        buton.setOnClickListener {
            lifecycleScope.launch {
                SupabaseManager.PostMain(
                    t2.text.toString(),
                    t3.text.toString(),
                    t4.text.toString()
                )
                refreshData()
                t2.text.clear()
                t3.text.clear()
                t4.text.clear()
            }
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = dataList[position]
            t2.setText(selectedItem.Name)
            t3.setText(selectedItem.Photo)
            t4.setText(selectedItem.Birthday)

            buton.setOnClickListener {
                lifecycleScope.launch {
                    SupabaseManager.UpdateMain(
                        selectedItem.id,
                        t2.text.toString(),
                        t3.text.toString(),
                        t4.text.toString()
                    )
                    refreshData()
                    t2.text.clear()
                    t3.text.clear()
                    t4.text.clear()
                }
            }

            findViewById<Button>(R.id.deleteButton).setOnClickListener {
                lifecycleScope.launch {
                    supabaseManager.DeleteMain(selectedItem.id)
                    refreshData()
                }
            }
        }

        refreshData()
    }

    private fun refreshData() {
        lifecycleScope.launch {
            dataList.clear()
            adapter.clear()
            val items = SupabaseManager.GetMain()
            dataList.addAll(items)
            items.forEach {
                adapter.add("Name: ${it.Name}, Birthday: ${it.Birthday}, Photo: ${it.Photo}")
            }
            adapter.notifyDataSetChanged()
        }
    }
}