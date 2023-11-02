package com.example.teamkuy2.ui.network

import com.example.teamkuy2.ui.model.ResponseGithub
import retrofit2.http.GET

interface GithubService {
    @JvmSuppressWildcards
    @GET("users")
    suspend fun getUserGithub(): MutableList<ResponseGithub.ResponseGithubItem>
}