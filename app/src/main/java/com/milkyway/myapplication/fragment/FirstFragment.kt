package com.milkyway.myapplication.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.milkyway.myapplication.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            var randomNo = findRandomNumber()
            view.findViewById<TextView>(R.id.number).text = "Random number :$randomNo"
            val marks = arrayOf(80,85,60,90,70)
            for(item in marks)
                println(item)
        }
    }

    @SuppressLint("SetTextI18n")
    fun findRandomNumber(): Int {
        var diceRange: IntRange = 1..6
        var randomNumber = diceRange.random()
        println("Random number : $randomNumber")

        return randomNumber
    }
}