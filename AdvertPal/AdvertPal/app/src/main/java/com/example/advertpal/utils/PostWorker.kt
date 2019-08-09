package com.example.advertpal.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.advertpal.App
import com.example.advertpal.repositories.VkRepository
import javax.inject.Inject

class PostWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    @Inject
    lateinit var vkRepository: VkRepository

    @Inject
    lateinit var sPref: SharedPrefWrapper

    override fun doWork(): Result {
        App.component.inject(this)
        val postId = sPref.getPostId()

        if (postId != -1) {
            deletePost(postId)
            makePost(postId)
        } else {
            makePost(postId)
        }
        return Result.success()
    }

    @SuppressLint("CheckResult")
    private fun makePost(post_id: Int) {
        val text = inputData.getString(POST_TEXT_KEY)
        vkRepository.makePost("$text : $post_id", sPref.getToken())
            .subscribe({ postId ->
                sPref.savePostId(postId)
            }, {
                it.printStackTrace()
            })
    }

    @SuppressLint("CheckResult")
    private fun deletePost(postId: Int) {
        vkRepository.deletePost(postId, sPref.getToken())
            .subscribe({
            },{
                it.printStackTrace()
            })
    }

}