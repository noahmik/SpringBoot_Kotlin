package com.example.demo.dto

data class EncodingModelRequest(
    val originalUrl: String
)

data class EncodingModelResponse(
    val originalUrl: String,
    val encodedUrl: String
)
