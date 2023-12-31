package com.example.teamkuy2.ui.detaill

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teamkuy2.ui.home.Result
import com.example.teamkuy2.ui.network.ApiClient
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailViewModel() : ViewModel(){
    val resultDetailUser = MutableLiveData<Result>()
    fun getDetailUser(username : String){
        viewModelScope.launch {
            flow{
                val response = ApiClient
                    .githubService
                    .getDetailUserGithub2(username)

                emit(response)
            }.onStart {
                    resultDetailUser.value = Result.Loading(true)
            }.onCompletion {
                    resultDetailUser.value = Result.Loading(false)
            }.catch {
                    it.printStackTrace()
                    resultDetailUser.value = Result.Error(it)
            }.collect{
                    resultDetailUser.value = Result.Success(it)
            }
        }
    }
}