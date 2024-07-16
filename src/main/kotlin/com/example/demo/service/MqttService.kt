package com.example.demo.service

import org.springframework.integration.annotation.MessagingGateway
import org.springframework.integration.mqtt.support.MqttHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

@Service
class MqttService(private val mqttOutboundChannel: MessageChannel) {

    @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
    interface MqttGateway {
        fun sendToMqtt(data: String)
    }

    fun publish(clientId: String, message: String) {
        val modifiedMessage = "[$clientId]: $message"
        val mqttMessage: Message<String> = MessageBuilder.withPayload(modifiedMessage)
            .setHeader(MqttHeaders.TOPIC, "bssm")
            .build()
        mqttOutboundChannel.send(mqttMessage)
    }
}
