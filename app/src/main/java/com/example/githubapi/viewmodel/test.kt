package com.example.githubapi.viewmodel

import com.example.githubapi.requestHttp.retrofit.RetrofitUtils.Companion.PRIVATE_TOKEN
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun main() {
//    var te=Test()
//    te.t({ API.getProductList2(10,10)},10)
//

    val BASE_URL = "https://api.douban.com/v2/movie/"
    val retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    println(retrofit)

    getMsgByGet()
}

/**
 * java 调用 Curl的方法
 * @param cmds
 * @return
 */
fun execCurl(cmds: Array<String?>): String? {
    val process = ProcessBuilder(*cmds)
    val p: Process
    try {
        p = process.start()
        val reader: java.io.BufferedReader =
            java.io.BufferedReader(java.io.InputStreamReader(p.inputStream))
        val builder: java.lang.StringBuilder = java.lang.StringBuilder()
        var line: String?
        while ((reader.readLine().also { line = it }) != null) {
            builder.append(line)
            builder.append(System.lineSeparator())
        }
        return builder.toString()
    } catch (e: java.io.IOException) {
        print("get thread error: $e")
    }
    return null
}



fun getToken() {
    val url =
        "https://api.github.com/events/" //这个为url连接，随便写了个连接，一般接口会先调用接口获取token，然后拼接token，post发送请求
    val cmds = arrayOf<String?>("curl", "X", "GET", "-k", "-i", "" + url + "")
    val sb = StringBuffer()
    for (i in cmds.indices) {
        sb.append(cmds[i] + " ")
    }
    println(sb)
    val responseMsg = execCurl(cmds)
    println(responseMsg)
}


fun getMsgByGet() {
    val jsonStr = "{'content':'testtest','creator':'creator''name':'lili'}" //这里为发送的json串
    val url = "https://api.github.com/events" //这里为连接发送的url
    val accessToken = PRIVATE_TOKEN
        //这一步这个token为上面get获取到的，有些简单的接口不需要获取这个
    val cmds = arrayOf<String?>(
        "curl",
        "-L",
        "-H",
        "\"Accept: application/vnd.github+json\"",
        "-H",
        "\"Authorization: Bearer $accessToken\"",
        "-H",
        "\"Content-Type:application/json\"",
        "-H",
        "\"X-GitHub-Api-Version: 2022-11-28\"",
        "-X",
        "GET",
//        "-k",
        "-i",
        "" + url + "",
//        "-d",
//        "" + jsonStr + ""
    ) //超级注意：这个拼接json串的时候不要拼接双引号或者单引号，这个是踩坑提示，因为这个改了好久
    val sb = StringBuffer()
    for (i in cmds.indices) {
        sb.append(cmds[i] + " ")
    }
    println(sb) //这个是测试中调试输出你的发送命令航
    println(execCurl(cmds))
}

fun getMsgByPost() {
    val jsonStr = "{'content':'testtest','creator':'creator''name':'lili'}" //这里为发送的json串
    val url = "https://www.baidu.com/" //这里为连接发送的url
    val accessToken = "123456" //这一步这个token为上面get获取到的，有些简单的接口不需要获取这个
    val cmds = arrayOf<String?>(
        "curl",
        "-H",
        "access-token:{{$accessToken}}",
        "-H",
        "Content-Type:application/json",
        "-X",
        "POST",
        "-k",
        "-i",
        "" + url + "",
        "-d",
        "" + jsonStr + ""
    ) //超级注意：这个拼接json串的时候不要拼接双引号或者单引号，这个是踩坑提示，因为这个改了好久
    val sb = StringBuffer()
    for (i in cmds.indices) {
        sb.append(cmds[i] + " ")
    }
    println(sb) //这个是测试中调试输出你的发送命令航
    println(execCurl(cmds))
}
