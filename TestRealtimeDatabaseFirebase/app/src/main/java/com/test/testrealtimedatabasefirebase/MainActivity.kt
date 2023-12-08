package com.test.testrealtimedatabasefirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.test.testrealtimedatabasefirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MessageRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initRecyclerView()
        onClickButtonSend()
    }

    private fun initRecyclerView() {
        recyclerView = binding.rvMessages
        adapter = MessageRecyclerViewAdapter()
        recyclerView.adapter = adapter
    }

    private fun onClickButtonSend() {
        val database = Firebase.database
        val myRef = database.getReference("messages")

        binding.btnSend.setOnClickListener {
            if (!binding.etMessage.text.isNullOrEmpty()) {
                myRef.child(myRef.push().key ?: "some")
                    .setValue(Message(binding.etMessage.text.toString()))
                onChangeListener(myRef)
            }
            binding.etMessage.setText("")
        }
        onChangeListener(myRef)
    }

    private fun onChangeListener(dRef: DatabaseReference) {
        dRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = arrayListOf<Message>()
                for (s in snapshot.children) {
                    val message = s.getValue(Message::class.java)
                    if (message != null) list.add(message)
                }
                adapter.submitMessages(list)
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }
}