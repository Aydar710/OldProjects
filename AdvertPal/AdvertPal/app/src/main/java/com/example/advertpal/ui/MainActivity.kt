package com.example.advertpal.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.advertpal.utils.SharedPrefWrapper
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope
import com.vk.api.sdk.utils.VKUtils
import java.util.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.advertpal.R.layout.activity_main)

        val fingerprints = VKUtils.getCertificateFingerprint(this, this.packageName)
        print(Arrays.toString(fingerprints))
        VK.login(this, arrayListOf(VKScope.WALL, VKScope.GROUPS))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object : VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                // User passed authorization
                val sPref = SharedPrefWrapper(this@MainActivity)
                sPref.saveToken(token.accessToken)
                val intent = Intent(this@MainActivity, WorkActivity::class.java)
                startActivity(intent)
            }

            override fun onLoginFailed(errorCode: Int) {
                // User didn't pass authorization
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
