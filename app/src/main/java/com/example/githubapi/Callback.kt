package com.example.githubapi

interface Callback<T> {
    fun success(t:T)
    fun failed(message:String)
}