package com.test.navigationcomponent

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavArgs
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.test.navigationcomponent.databinding.FragmentSecondBinding
import com.test.navigationcomponent.Constants.COLOR
import com.test.navigationcomponent.Constants.TEXT_COLOR
import com.test.navigationcomponent.Constants.TEXT_FROM_FIRST_FRAGMENT
import com.test.navigationcomponent.Constants.TEXT_FROM_SECOND_FRAGMENT

class SecondFragment : Fragment(R.layout.fragment_second) {
    private lateinit var binding: FragmentSecondBinding
    private val args: SecondFragmentArgs by navArgs()
    private val dataModel: MyViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSecondBinding.bind(view)
        //get background and text
        //-----------------------
        //first decision
        //val color = requireArguments().getInt(COLOR)
        //val text = requireArguments().getString(TEXT_FROM_FIRST_FRAGMENT)
        //-----------------------
        //second decision
//        val data = SecondFragmentArgs.fromBundle(requireArguments())
        //-----------------------
        //third decision
        //We use args for the listening data
        //-----------------------
        //set background and text
//        binding.root.setBackgroundColor(args.color)
//        binding.textView.text = data.text
        //fourth decision
        dataModel.textForSecondFragment.observe(activity as LifecycleOwner) {
            binding.textView.text = it
        }
        dataModel.color.observe(activity as LifecycleOwner) {
            binding.root.setBackgroundColor(it)
        }

        //onClickListener
        binding.openSecondBoxButton.setOnClickListener {
            //first decision
            //publishResults(TEXT_FROM_SECOND_FRAGMENT, binding.editTextTextPersonName2.text.toString())
            //second decision
//            parentFragmentManager.setFragmentResult(
//                TEXT_FROM_SECOND_FRAGMENT,
//                bundleOf(TEXT_FROM_SECOND_FRAGMENT to binding.editTextTextPersonName2.text.toString()))

            //third decision
//            findNavController().previousBackStackEntry?.savedStateHandle?.set(
//                TEXT_FROM_SECOND_FRAGMENT, binding.editTextTextPersonName2.text.toString()
//            )
            //fourth decision
//            dataModel.textForFirstFragment.value = binding.editTextTextPersonName2.text.toString()
//            findNavController().popBackStack()
            val direction = SecondFragmentDirections.actionSecondFragmentToFirstFragment(binding.editTextTextPersonName2.text.toString())
            Navigation.findNavController(binding.root).navigate(direction)
        }
    }
}