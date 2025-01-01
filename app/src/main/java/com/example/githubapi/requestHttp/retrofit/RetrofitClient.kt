package com.example.githubapi.requestHttp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val instance: ApiService by lazy {
        Retrofit.Builder().baseUrl(BASE_URL) // 设置 Base URL
            .addConverterFactory(GsonConverterFactory.create()) // 使用 Gson 解析 JSON
            .build().create(ApiService::class.java) // 创建 ApiService 实例
    }

    private const val BASE_URL_GITHUB = "https://api.github.com/"

    val instanceofGithub: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_GITHUB)  // 设置 GitHub API 基础 URL
            .addConverterFactory(GsonConverterFactory.create())  // 使用 Gson 转换器
            .build()
            .create(ApiService::class.java)  // 创建 API 服务接口
    }
}
