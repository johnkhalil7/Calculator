package com.johnk.calculator

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.objecthunter.exp4j.ExpressionBuilder
import kotlinx.android.synthetic.main.fragment_bottom.*
import net.objecthunter.exp4j.Expression

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [bottom.newInstance] factory method to
 * create an instance of this fragment.
 */
class bottom : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var activityCallback: ButtonListener? = null

    interface ButtonListener {
        fun onButtonPress(digit: String)
        fun getResult()
        fun clearScreen()
        fun clearLastEntered()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            activityCallback = context as ButtonListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString()
                        + " must implement ButtonListener"
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button_0.setOnClickListener { digitClicked("0") } //0-9 buttons
        button_1.setOnClickListener { digitClicked("1") }
        button_2.setOnClickListener { digitClicked("2") }
        button_3.setOnClickListener { digitClicked("3") }
        button_4.setOnClickListener { digitClicked("4") }
        button_5.setOnClickListener { digitClicked("5") }
        button_6.setOnClickListener { digitClicked("6") }
        button_7.setOnClickListener { digitClicked("7") }
        button_8.setOnClickListener { digitClicked("8") }
        button_9.setOnClickListener { digitClicked("9") }

        button_dec.setOnClickListener { digitClicked(".") } //decimal button

        button_add.setOnClickListener { digitClicked("+") } //operator buttons
        button_sub.setOnClickListener { digitClicked("-") }
        button_mult.setOnClickListener { digitClicked("*") }
        button_div.setOnClickListener { digitClicked("/") }
        button_mod.setOnClickListener { digitClicked("%") }

        button_sqrt.setOnClickListener { digitClicked("^(1/2)") } //square root button

        button_equal.setOnClickListener { total() } //equals button

        button_c.setOnClickListener { clear() } //Clear button
        button_ce.setOnClickListener { clearEntry() } //clear entry button

    }

    private fun clearEntry() { //clear entry case
        activityCallback?.clearLastEntered()
    }

    private fun total() { //equal button case
        activityCallback?.getResult()
    }

    private fun digitClicked(digit: String) { //any digit or operator button case
        activityCallback?.onButtonPress(digit)
    }

    private fun clear(){ //clear button case
        activityCallback?.clearScreen()
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment bottom.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            bottom().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}