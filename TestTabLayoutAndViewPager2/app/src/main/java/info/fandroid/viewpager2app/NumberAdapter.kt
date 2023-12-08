package info.fandroid.viewpager2app

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class NumberAdapter(fragment: FragmentActivity, val array: Array<String>) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = array.size

    override fun createFragment(position: Int): Fragment {
        val fragment = NumberFragment()
        fragment.arguments = Bundle().apply {
            putInt(NUMBER, position+1)
            putInt(COLOR, Color.rgb((0..255).random(), (0..255).random(), (0..255).random()))
        }
        return fragment
    }
}