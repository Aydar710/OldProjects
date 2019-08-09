package com.example.advertpal.services

import com.example.advertpal.models.wallpost.DeletedPostResponse
import com.example.advertpal.models.wallpost.PostResponseWrapper
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface VkService {

    @GET("wall.post")
    fun makePost(
        @Query("owner_id") ownerId: String = "-183072058",
        @Query("message") message: String,
        @Query("access_token") access_token: String
    ): Single<PostResponseWrapper>

    @GET("wall.delete")
    fun deletePost(
        @Query("owner_id") ownerId: String = "-183072058",
        @Query("post_id") postId: String,
        @Query("access_token") access_token: String
    ): Single<DeletedPostResponse>
}
