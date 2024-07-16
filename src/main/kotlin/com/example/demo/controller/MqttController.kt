package com.example.demo.controller

import com.example.demo.service.MqttService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MqttController(
    private val mqttGateway: MqttService.MqttGateway
) {
    @GetMapping("/mqtt/ping")
    fun ping(){
        mqttGateway.sendToMqtt("Hello World!")
    }
}