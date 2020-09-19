package com.ch8n.dynamic

import android.content.Intent
import android.os.Bundle
import android.os.Message
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ch8n.secondfeature.SecondActivity
import com.google.android.play.core.splitinstall.SplitInstallRequest

/*
    In this application,
    FirstActivity open SecondActivity from separate library module
    and return a message back from activity result
 */


class FirstActivity : SplitActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->

            // step 10 : install the module using split request.
            val moduleInstallRequest = SplitInstallRequest.newBuilder()
                .addModule(getString(R.string.gradle_second_feature))
                .build()

            splitManager.startInstall(moduleInstallRequest)
                .addOnSuccessListener {
                    toast("Module installed")
                }
                .addOnFailureListener {
                    toast("Module failed ${it.message}")
                }


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
            toast("Response from Preview Activity : $response")
            splitManager.deferredUninstall(listOf(getString(R.string.gradle_second_feature)))
        }
    }

    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT)
            .show()
    }

}