package edu.ksu.cs.studentportal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView

class ProfessionalProgramActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_professional_program)
        calculateCumulativeGPA()

        val onItemSelect = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                calculateCumulativeGPA()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                calculateCumulativeGPA()
            }
        }

        findViewById<Spinner>(R.id.spinnerGradeCIS115).onItemSelectedListener = onItemSelect
        findViewById<Spinner>(R.id.spinnerGradeCIS200).onItemSelectedListener = onItemSelect
        findViewById<Spinner>(R.id.spinnerGradeCIS300).onItemSelectedListener = onItemSelect
        findViewById<Spinner>(R.id.spinnerGradeCIS301).onItemSelectedListener = onItemSelect
        findViewById<Spinner>(R.id.spinnerGradeECE241).onItemSelectedListener = onItemSelect
        findViewById<Spinner>(R.id.spinnerGradeMATH220).onItemSelectedListener = onItemSelect
        findViewById<Spinner>(R.id.spinnerGradeMATH221).onItemSelectedListener = onItemSelect
    }

    fun calculateCumulativeGPA(){
        var numerator = 0.0
        var denominator = 0.0
        var lessThanC = false

        fun retrieveGPAComponent(spinnerID : Int, creditHours : Int){
            val spinner = findViewById<Spinner>(spinnerID)
            when(spinner.selectedItem.toString()){
                "A" -> {numerator += 4* creditHours; denominator += creditHours}
                "B" -> {numerator += 3* creditHours; denominator += creditHours}
                "C" -> {numerator += 2* creditHours; denominator += creditHours}
                "D" -> {numerator += creditHours; denominator += creditHours; lessThanC = true}
                "F" -> {denominator += creditHours; lessThanC = true}
            }
        }

        retrieveGPAComponent(R.id.spinnerGradeCIS115, creditHours = 3)
        retrieveGPAComponent(R.id.spinnerGradeCIS200, creditHours = 4)
        retrieveGPAComponent(R.id.spinnerGradeCIS300, creditHours = 3)
        retrieveGPAComponent(R.id.spinnerGradeCIS301, creditHours = 3)
        retrieveGPAComponent(R.id.spinnerGradeECE241, creditHours = 3)
        retrieveGPAComponent(R.id.spinnerGradeMATH220, creditHours = 4)
        retrieveGPAComponent(R.id.spinnerGradeMATH221, creditHours = 4)

        val gpaTextView = findViewById<TextView>(R.id.textViewCumulativeGPA)
        val cumulativeGPA = if(denominator == 0.0) "n/a" else (numerator/denominator).toString()
        var message = "Cumulative GPA: $cumulativeGPA"
        if (lessThanC) message += "\nYou must have a C or better in all courses!"

        if (denominator !== 0.0 && numerator /denominator < 3.2) message += "\nYou must have a 3.2 cumulative GPA!"

        gpaTextView.text = message
    }
}
