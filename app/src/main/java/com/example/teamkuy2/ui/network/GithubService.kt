package com.example.teamkuy2.ui.network

import com.example.teamkuy2.ui.model.ResponseDetailUser
import com.example.teamkuy2.ui.model.ResponseGithub
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @JvmSuppressWildcards
    @GET("users")
    suspend fun getUserGithub(): MutableList<ResponseGithub.ResponseGithubItem>
    @JvmSuppressWildcards
    @GET("users/{username}")
    suspend fun getDetailUserGithub(@Path("username") username: String): ResponseDetailUser
}