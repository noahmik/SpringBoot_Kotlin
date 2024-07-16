package com.example.demo.controller

import com.example.demo.dto.MqttPublishRequest
import com.example.demo.model.Message
import com.example.demo.service.MessageRepository
import com.example.demo.service.MqttService
import com.example.demo.service.MessageService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class MqttController(
    private val mqttService: MqttService,
    private val mqttRepository: MessageRepository,
    private val messageService: MessageService
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
    @GetMapping("/api/messages")
    fun getMessagesByClientId(
        @RequestParam clientId: String
    ): ResponseEntity<List<Message>> {
        val messages = messageService.findByClientId(clientId)
        return ResponseEntity.ok(messages)
    }

    @PostMapping
    fun createMessage(@RequestBody message: Message): ResponseEntity<Message> {
        val savedMessage = messageService.save(message)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMessage)
    }
}
