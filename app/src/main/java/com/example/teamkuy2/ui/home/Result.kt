package com.example.teamkuy2.ui.home

sealed class Result {
    data class Success<out T>(val dataa: T) : Result()
    data class Error(val exception: Throwable) : Result()
    data class Loading(val isLoading: Boolean) : Result()
}