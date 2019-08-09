package com.example.advertpal.ui

import android.os.Bundle
import androidx.work.*
import com.arellomobile.mvp.MvpAppCompatActivity
import com.example.advertpal.R
import com.example.advertpal.utils.POST_TEXT_KEY
import com.example.advertpal.utils.PostWorker
import com.example.advertpal.views.WorkActivityView
import kotlinx.android.synthetic.main.activity_work.*
import java.util.concurrent.TimeUnit

class WorkActivity : MvpAppCompatActivity(), WorkActivityView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work)

        /*val info = WorkManager.getInstance().getWorkInfosByTag("TAG")
        val list = info?.get()[0].outputData
        print("")*/

        btn_start_job.setOnClickListener {
            startWork()
        }


    }

    private fun startWork() {
        val constraints: Constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val data = Data.Builder()
            .putString(POST_TEXT_KEY, et_post_text.text.toString())
            .putInt("postPeriodic", Integer.parseInt(et_post_periodic.text.toString()))
            .build()

        val periodicPostWorkRequest = PeriodicWorkRequest
            .Builder(PostWorker::class.java, 1, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .setInputData(data)
            .addTag("TAG")
            .build()

        WorkManager.getInstance().enqueue(periodicPostWorkRequest)
    }
}
