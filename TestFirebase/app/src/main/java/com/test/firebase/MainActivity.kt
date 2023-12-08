package com.test.firebase

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import com.test.firebase.databinding.ActivityMainBinding
import org.jetbrains.annotations.NotNull
import kotlin.math.sign

class MainActivity : AppCompatActivity(), UserAdapter.Listener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var launcher: ActivityResultLauncher<Intent>
    lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        auth = Firebase.auth
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}
        setContentView(binding.root)
        setAppActionBar()
        val myDataBase = Firebase.database
        val myRef = myDataBase.getReference("messages")
        binding.buttonSend.setOnClickListener {
            if (!binding.editTextMessage.text.isNullOrEmpty()) {
                myRef.child(myRef.push().key ?: "bla-bla")
                    .setValue(User(auth.currentUser?.displayName, binding.editTextMessage.text.toString()))
                onChangedListener(myRef)
            }
            binding.editTextMessage.setText("")
        }
        onChangedListener(myRef)
        initRcView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sign_out) {
            auth.signOut()
            launcher.launch(Intent(this, SignInAct::class.java))
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initRcView() {
        adapter = UserAdapter(this@MainActivity)
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.recyclerView.adapter = adapter
    }

    private fun onChangedListener(dRef: DatabaseReference) {
        dRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = arrayListOf<User>()
                for (s in snapshot.children) {
                    val user = s.getValue(User::class.java)
                    if (user != null) list.add(user)
                }
                adapter.submitList(list)
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun setAppActionBar() {
        val actionBar = supportActionBar
        Thread {
            val bitMapAvatar = Picasso.get().load(auth.currentUser?.photoUrl).get()
            val drawableAvatar = BitmapDrawable(resources, bitMapAvatar)
            runOnUiThread {
                actionBar?.setDisplayHomeAsUpEnabled(true)
                actionBar?.setHomeAsUpIndicator(drawableAvatar)
                actionBar?.title = auth.currentUser?.displayName
            }
        }.start()
    }
    override fun onClick(user: User) {
        Toast.makeText(this@MainActivity, "User: ${user.userName}\nMessage: ${user.message}", Toast.LENGTH_SHORT).show()
    }
}