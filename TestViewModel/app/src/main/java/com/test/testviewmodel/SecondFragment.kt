package com.test.testviewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Transformations
import com.test.testviewmodel.databinding.FragmentFirstBinding
import com.test.testviewmodel.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private val dataModel: ClassViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonToActivityFromFragmentTwo.setOnClickListener {
            dataModel.setMessageForActivity("Hello activity from second fragment")
        }
        binding.buttonToFragmentFromFragmentTwo.setOnClickListener {
            dataModel.setMessageForFragment1("Hello first fragment from second fragment")
        }
        dataModel.getMessageFragment2().observe(activity as LifecycleOwner) {
            binding.textViewFragmentTwo.text = it
        }
    }
    companion object {
        @JvmStatic fun newInstance() = SecondFragment()
    }

}