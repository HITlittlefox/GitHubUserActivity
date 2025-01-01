package com.example.githubapi.requestHttp.retrofit

data class Post(
    val userId: Int,
    val id: Int? = null,
    val title: String,
    val body: String
)

data class Event(
    val id: String,
    val type: String,
    val actor: Actor,
    val repo: Repo,
    val payload: Payload,
    val created_at: String
)

data class Actor(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val url: String
)

data class Repo(
    val id: Int,
    val name: String,
    val url: String
)

data class Payload(
    val push_id: Long?,
    val size: Int?,
    val distinct_size: Int?,
    val ref: String?,
    val head: String?,
    val before: String?,
    val commits: List<Commit>?
)

data class Commit(
    val sha: String,
    val message: String,
    val author: CommitAuthor
)

data class CommitAuthor(
    val name: String,
    val email: String
)

