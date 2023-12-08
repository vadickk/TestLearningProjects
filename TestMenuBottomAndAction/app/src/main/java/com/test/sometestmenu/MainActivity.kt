package com.test.sometestmenu

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.test.sometestmenu.databinding.ActivityMainBinding
fun Toast.ShowToastShort(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}
class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Test Menu"
        binding.bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.firstItemBottom -> Toast(this).ShowToastShort(this, "First Item Bottom")
                R.id.secondItemBottom -> Toast(this).ShowToastShort(this, "Second Item Bottom")
                R.id.thirdItemBottom -> Toast(this).ShowToastShort(this, "Third Item Bottom")
                R.id.fourthItemBottom -> Toast(this).ShowToastShort(this, "Fourth Item Bottom")
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.firstItem -> Toast(this).ShowToastShort(this, "First Item")
            R.id.secondItem -> Toast(this).ShowToastShort(this, "Second Item")
            R.id.thirdItem -> Toast(this).ShowToastShort(this, "Third Item")
        }
        return true
    }

    override fun onNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}