package com.ch8n.dynamic

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.splitcompat.SplitCompat
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory

abstract class SplitActivity : AppCompatActivity() {
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        // step 3 mark activity which need access to dynamic activity
        SplitCompat.installActivity(requireNotNull(newBase))
    }

    // step 8 create SplitInstallManager, it provides capability to install/remove dynamic module
    protected val splitManager: SplitInstallManager by lazy {
        SplitInstallManagerFactory.create(applicationContext)
    }
}