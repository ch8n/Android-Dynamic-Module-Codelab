package com.ch8n.githubtrends

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.ch8n.secondfeature.SecondActivity

/*
    In this application,
    FirstActivity open SecondActivity from separate library module
    and return a message back from activity result
 */

class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            startActivityForResult(
                Intent(this, SecondActivity::class.java),
                SecondActivity.RESPONSE_CODE
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == SecondActivity.RESPONSE_CODE) {
            val response = data?.getStringExtra(SecondActivity.RESPONSE_DATA) ?: ""
            Toast.makeText(this, "Response from Preview Activity : $response", Toast.LENGTH_SHORT)
                .show()
        }
    }

}