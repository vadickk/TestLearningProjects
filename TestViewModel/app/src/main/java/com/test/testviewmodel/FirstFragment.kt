package com.test.testviewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.test.testviewmodel.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private val dataModel: ClassViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonToActivityFromFragmentOne.setOnClickListener {
            dataModel.setMessageForActivity("Hello activity from first fragment")
        }
        binding.buttonToFragmentFromFragmentOne.setOnClickListener {
            dataModel.setMessageForFragment2("Hello second fragment from first fragment")
        }
        dataModel.getMessageFragment1().observe(activity as LifecycleOwner) {
            binding.textViewFragmentOne.text = it
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = FirstFragment()
    }
}