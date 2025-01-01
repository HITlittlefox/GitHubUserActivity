package com.example.githubapi.requestHttp.retrofit

import com.example.githubapi.requestHttp.retrofit.RetrofitUtils.Companion.PRIVATE_TOKEN
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitUtils {

    companion object{
        // TODO 配置token
        const val PRIVATE_TOKEN = ""
    }

}


fun main() {
//    getRetrofitSample()
//    postRetrofitSample()
    getGithubEvent()
}

fun getRetrofitSample() {
    // 获取 Retrofit 实例
    val apiService = RetrofitClient.instance

    // GET 请求：获取所有帖子
    apiService.getPosts().enqueue(object : Callback<List<Post>> {
        override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
            if (response.isSuccessful) {
                println("GET Response: ${response.body()}")
            } else {
                println("GET Error: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<List<Post>>, t: Throwable) {
            println("GET Failure: ${t.message}")
        }
    })

}

fun postRetrofitSample() {
    // 获取 Retrofit 实例
    val apiService = RetrofitClient.instance

    // POST 请求：创建新帖子
    val newPost = Post(userId = 1, title = "New Title", body = "New Body")
    apiService.createPost(newPost).enqueue(object : Callback<Post> {
        override fun onResponse(call: Call<Post>, response: Response<Post>) {
            if (response.isSuccessful) {
                println("POST Response: ${response.body()}")
            } else {
                println("POST Error: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<Post>, t: Throwable) {
            println("POST Failure: ${t.message}")
        }
    })
}

fun getGithubEvent() {
    // 设置请求头
    val headers = mapOf(
        "Accept" to "application/vnd.github+json",
        "Authorization" to PRIVATE_TOKEN,
        "Content-Type" to "application/json",
        "X-GitHub-Api-Version" to "2022-11-28"
    )

    // 发起 GET 请求
    val call = RetrofitClient.instanceofGithub.getGitHubEvents(headers)

    // 异步执行请求
    call.enqueue(object : Callback<List<Event>> {
        override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
            if (response.isSuccessful) {
                // 输出返回的事件数据
                response.body()?.forEach { event ->
                    println("Event ID: ${event.id}")
                    println("Event Type: ${event.type}")
                    println("Created At: ${event.created_at}")
                    println("Actor: ${event.actor.login}")
                    println("Repo: ${event.repo.name}")
                    println("Event Payload:")

                    // 根据事件类型输出不同的信息
                    when (event.type) {
                        "PushEvent" -> {
                            // 输出推送事件的详细信息
                            event.payload.commits?.forEach { commit ->
                                println("Commit SHA: ${commit.sha}")
                                println("Commit Message: ${commit.message}")
                                println("Commit Author: ${commit.author.name} <${commit.author.email}>")
                            }
                        }
                        "PullRequestEvent" -> {
                            // 输出拉取请求事件的详细信息
                            println("Pull Request Action: ${event.payload.commits}")
                        }
                        else -> {
                            // 其他事件类型的处理
                            println("Event Payload is of type: ${event.payload::class.simpleName}")
                        }
                    }
                    println("------------------------------------------------------------")
                }
            } else {
                println("Request failed: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<List<Event>>, t: Throwable) {
            println("Request failed: ${t.message}")
        }
    })
}