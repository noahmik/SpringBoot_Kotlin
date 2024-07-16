package com.example.demo.dto

data class MqttPublishRequest(
    val clientId: String,
    val message: String
)