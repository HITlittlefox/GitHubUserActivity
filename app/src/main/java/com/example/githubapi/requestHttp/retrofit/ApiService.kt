package com.example.githubapi.requestHttp.retrofit

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // GET 请求：获取所有帖子
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    // POST 请求：创建新帖子
    @POST("posts")
    fun createPost(@Body post: Post): Call<Post>

    // GET 请求获取 GitHub 事件
    @GET("events")
    fun getGitHubEvents(
        @HeaderMap headers: Map<String, String>  // 动态传递请求头
    ): Call<List<Event>>
}
