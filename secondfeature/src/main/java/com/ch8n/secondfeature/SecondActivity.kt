package com.ch8n.secondfeature

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_detail)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            setResult(RESPONSE_CODE, Intent().also {
                it.putExtra(RESPONSE_DATA, "\n>>Hi! its working...<<")
            })
            finish()
        }
    }

    companion object {
        const val RESPONSE_CODE = 1001
        const val RESPONSE_DATA = "Data"
    }
}