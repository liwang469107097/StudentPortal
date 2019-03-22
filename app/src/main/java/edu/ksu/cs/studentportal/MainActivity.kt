package edu.ksu.cs.studentportal

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun launchProfessionalProgramActivity(view: View){
        val intent = Intent(this, ProfessionalProgramActivity::class.java)
        startActivity(intent)
    }
}
