package com.example.githubapi.requestHttp.httpURLConnection

import com.example.githubapi.requestHttp.retrofit.RetrofitUtils.Companion.PRIVATE_TOKEN
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

/**
 * 不使用 OkHttp，而是直接使用 Kotlin 的标准库 HttpURLConnection 来实现相同的功能。以下是基于 HttpURLConnection 的实现方法。
 */
fun sendHttpRequest(
    url: String,
    method: String = "GET",
    headers: Map<String, String> = emptyMap(),
    body: String? = null
): String? {
    val connection = URL(url).openConnection() as HttpURLConnection
    return try {
        // 设置请求方法
        connection.requestMethod = method

        // 设置请求头
        headers.forEach { (key, value) ->
            connection.setRequestProperty(key, value)
        }

        // 如果是 POST 或其他需要发送请求体的方法
        if (body != null && (method == "POST" || method == "PUT")) {
            connection.doOutput = true
            OutputStreamWriter(connection.outputStream).use { writer ->
                writer.write(body)
            }
        }

        // 检查响应状态码
        if (connection.responseCode !in 200..299) {
            throw Exception("HTTP $method Request failed: ${connection.responseCode}")
        }

        // 读取响应内容
        BufferedReader(InputStreamReader(connection.inputStream)).use { reader ->
            reader.readText()
        }
    } finally {
        connection.disconnect()
    }
}

fun getSample() {
    val url = "https://jsonplaceholder.typicode.com/posts/"
    val headers = mapOf(
        "Accept" to "application/json"
    )

    try {
        val response = sendHttpRequest(url, method = "GET", headers = headers)
        println("GET Response: $response")
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }


}

fun postSample() {
    val url = "https://jsonplaceholder.typicode.com/posts"
    val jsonBody = """
        {
            "title": "foo",
            "body": "bar",
            "userId": 1
        }
    """.trimIndent()
    val headers = mapOf(
        "Content-Type" to "application/json", "Accept" to "application/json"
    )

    try {
        val response = sendHttpRequest(url, method = "POST", headers = headers, body = jsonBody)
        println("POST Response: $response")
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}

fun getGithubAPI() {

    val url = "https://api.github.com/events"
    val headers = mapOf(
        "Accept" to "application/vnd.github+json",
        "Authorization" to PRIVATE_TOKEN,
        "X-GitHub-Api-Version" to "2022-11-28"
    )

    try {
        val response = sendHttpRequest(url, method = "GET", headers = headers)
        println("GET Response: $response")
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}

fun main() {
    getSample()
    postSample()
    getGithubAPI()
}
