package com.test.navigationcomponent

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.test.navigationcomponent.Constants.COLOR
import com.test.navigationcomponent.Constants.TEXT_COLOR
import com.test.navigationcomponent.Constants.TEXT_FROM_FIRST_FRAGMENT
import com.test.navigationcomponent.Constants.TEXT_FROM_SECOND_FRAGMENT
import com.test.navigationcomponent.databinding.FragmentFirstBinding
import org.jetbrains.annotations.NotNull
import kotlin.properties.Delegates

class FirstFragment : Fragment(R.layout.fragment_first) {
    private lateinit var binding: FragmentFirstBinding
    private val dataModel: MyViewModel by activityViewModels()
//    private var color by Delegates.notNull<Int>()
//    private var colorName by Delegates.notNull<String>()
    private val args: FirstFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstBinding.bind(view)

        binding.openFirstYellowBoxButton.setOnClickListener {
            openSecondFragment(Color.rgb(255, 255, 200), "Yellow")
        }
        binding.openFirstGreenBoxButton.setOnClickListener {
            openSecondFragment(Color.rgb(200, 255, 200), "Green")
        }

        binding.textView2.text = args.text
        //first decision
        /*----------------------//
            listenResults<String>(TEXT_FROM_SECOND_FRAGMENT) {
            binding.textView2.text = it
        }
        //----------------------*/
        //second decision
//        parentFragmentManager.setFragmentResultListener(TEXT_FROM_SECOND_FRAGMENT, viewLifecycleOwner) { _, data ->
//            binding.textView2.text = data.getString(TEXT_FROM_SECOND_FRAGMENT)
//        }
        //third decision
//        val liveData = findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>(TEXT_FROM_SECOND_FRAGMENT)
//        liveData?.observe(viewLifecycleOwner) { text ->
//            if (text != null) binding.textView2.text = text
//            liveData.value = null // we do it for the value don't repeat more than one time
//        }
        //fourth decision
        dataModel.textForFirstFragment.observe(activity as LifecycleOwner) {
            binding.textView2.text = it
        }

    }

    private fun openSecondFragment(color: Int, colorName: String) {
        /* start first decision
        findNavController().navigate(
            R.id.action_firstFragment_to_secondFragment,
            bundleOf(
                COLOR to color,
                TEXT_COLOR to colorName,
                TEXT_FROM_FIRST_FRAGMENT to binding.editTextTextPersonName.text.toString()
            )
        )
        end first decision*/
        //start second decision
//        val text = binding.editTextTextPersonName.text.toString()
//        val direction = FirstFragmentDirections.actionFirstFragmentToSecondFragment(colorName, color, text)
//        findNavController().navigate(direction)
        //end second decision
        //start third decision
        dataModel.textForSecondFragment.value = binding.editTextTextPersonName.text.toString()
        dataModel.color.value = color
        dataModel.colorName.value = colorName
        val direction = FirstFragmentDirections.actionFirstFragmentToSecondFragment(colorName, color, dataModel.textForSecondFragment.value.toString())
        binding.editTextTextPersonName.setText("")
        findNavController().navigate(direction)
        findNavController().clearBackStack(R.id.action_secondFragment_to_firstFragment)
        //end third decision
    }

}