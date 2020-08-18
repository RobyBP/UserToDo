package com.robybp.usertodo.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.robybp.usertodo.R
import com.robybp.usertodo.views.fragments.LogInFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_activity_main, LogInFragment()).commit()
        }
    }
}