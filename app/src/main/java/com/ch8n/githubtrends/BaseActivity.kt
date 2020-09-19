package com.ch8n.githubtrends

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.splitcompat.SplitCompat

class BaseActivity : AppCompatActivity() {
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        // step 3 mark activity which need access to dynamic activity
        SplitCompat.installActivity(requireNotNull(newBase))
    }
}