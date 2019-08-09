package com.example.advertpal.repositories

import com.example.advertpal.services.VkService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class VkRepository(private val vkService: VkService) {

    fun makePost(message: String, token: String): Single<Int> =
        vkService.makePost(message = message, access_token = token)
            .subscribeOn(Schedulers.io())
            .map {
                it.response?.postId
            }

    fun deletePost(postId: Int, token: String): Single<Int> =
        vkService.deletePost(postId = postId.toString(), access_token = token)
            .subscribeOn(Schedulers.io())
            .map {
                it.response
            }
}
