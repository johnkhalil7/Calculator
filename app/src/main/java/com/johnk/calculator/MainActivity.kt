package com.johnk.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder

var alg = StringBuilder() //global varaible to store expression

class MainActivity : FragmentActivity(), bottom.ButtonListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onButtonPress(digit: String) {
        addToExpression(digit) //whenever a digit or operator is pressed, will be added to expression
    }

    override fun getResult() {
        var total = alg.toString() //convert stringbuilder to string and assign it to a new variable
        var e = ExpressionBuilder(total).build() //create an ExpressionBuilder variable and pass in the string
        var doubleTotal = e.evaluate() //evaluate
        var stringTotal = doubleTotal.toString() //convert to string
        displayTotal(stringTotal) //send to display total function
    }

    override fun clearScreen() {
        val displayFragment = supportFragmentManager.findFragmentById(R.id.top_frag) as top
        alg.setLength(0) //reset stringbuilder
        var empty = alg.toString() //convert to string
        displayFragment.changeTextProperties(empty) //display a clear screen
    }

    override fun clearLastEntered() {
        var entry = alg.toString() //convert to string
        if((entry.contains('+') || entry.contains('-') || entry.contains('*') || entry.contains('/') || entry.contains('%'))==false)
        {
            entry = "" //special case where user is clearing the first entry before any operator is entered

        }
        else {
            while (entry.last() == '0' || entry.last() == '1' || entry.last() == '2' || entry.last() == '3' || entry.last() == '4' || entry.last() == '5' || entry.last() == '6'
                    || entry.last() == '7' || entry.last() == '8' || entry.last() == '9') {

                entry = entry.substring(0, entry.length - 1) //while the expression after an operator still contains any digit, delete the last digit

            }
        }
        alg.replace(0, alg.length, entry) //update stringbuilder

        val displayFragment = supportFragmentManager.findFragmentById(R.id.top_frag) as top
        displayFragment.changeTextProperties(entry) //display using top fragment
    }

    private fun displayTotal(stringTotal: String) {
        val displayFragment = supportFragmentManager.findFragmentById(R.id.top_frag) as top
        displayFragment.changeTextProperties(stringTotal) //display using top fragment
        alg.setLength(0) //reset stringbuilder
    }

    private fun addToExpression(digit: String) {

       if(alg.isNullOrEmpty()) //if expression starts blank
       {
               alg.append(digit) //add to the end of the expression
               var stringDigit = alg.toString() //convert stringbuilder to string
               val displayFragment = supportFragmentManager.findFragmentById(R.id.top_frag) as top
               displayFragment.changeTextProperties(stringDigit) //display it to top fragment
        }
       else if((alg.last() == '+' || alg.last() == '-'|| alg.last() == '*' || alg.last() == '/' || alg.last() == '%') && (digit == "+" ||digit == "-" || digit == "*" || digit == "/" || digit == "%") ) {
           var stringDigit = alg.toString() //if the last character is an operator and you want to add another operator back-to-back
           stringDigit = stringDigit.substring(0,stringDigit.length-1) //delete the operator initially at the end of the string
           alg.replace(0, alg.length, stringDigit) //update stringbuilder
           alg.append(digit) //add the new operator to end of stringbuilder

           stringDigit = alg.toString() //convert to string
           val displayFragment = supportFragmentManager.findFragmentById(R.id.top_frag) as top
           displayFragment.changeTextProperties(stringDigit) //display it using top fragment
       }
        else{ //for any other case
           alg.append(digit)
           var stringDigit = alg.toString()
           val displayFragment = supportFragmentManager.findFragmentById(R.id.top_frag) as top
           displayFragment.changeTextProperties(stringDigit)
       }
    }


}
