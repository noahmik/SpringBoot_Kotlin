package com.example.demo.controller

import com.example.demo.dto.MqttPublishRequest
import com.example.demo.service.MqttService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MqttController(
    private val mqttService: MqttService
) {
    @GetMapping("/mqtt/ping")
    fun ping() {
        mqttService.publish("server", "Hello World!")
    }

    @PostMapping("/mqtt/publish")
    fun publishMessage(@RequestBody request: MqttPublishRequest): ResponseEntity<String> {
        return try {
            mqttService.publish(request.clientId, request.message)
            ResponseEntity.ok("MQTT 메시지 '[${request.clientId}]: ${request.message}' 전송 완료")
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("MQTT 메시지 전송 실패: ${ex.message}")
        }
    }
}
