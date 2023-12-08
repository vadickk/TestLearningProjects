package info.fandroid.viewpager2app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import info.fandroid.viewpager2app.databinding.FragmentNumberBinding

const val NUMBER = "object"
const val COLOR = "color"

class NumberFragment : Fragment() {
    lateinit var binding: FragmentNumberBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.apply {
            binding.textView.text = getInt(NUMBER).toString()
            binding.constaintLayout.setBackgroundColor(getInt(COLOR))
        }
    }

}