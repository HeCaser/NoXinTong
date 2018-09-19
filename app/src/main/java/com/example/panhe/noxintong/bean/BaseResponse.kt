package com.example.panhe.noxintong.bean

data class BaseResponse<T>(
        val data: T,
        val errCode: Int,
        val message: String
)