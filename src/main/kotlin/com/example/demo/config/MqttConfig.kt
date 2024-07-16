package com.example.demo.config

import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory
import org.springframework.integration.mqtt.core.MqttPahoClientFactory
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.MessageHandler

@Configuration //서비스 시작과 동시에 얘 먼저 시작
class MqttConfig {
    private val brokerUrl = "tcp://localhost:1883"
    private val clientId = "mqtt-rok"
    private val topic = "bssm" // 대화주제

    @Bean //다른 객체에서 참조 가능
    fun mqttPahoClientFactory(): MqttPahoClientFactory {
        val options = MqttConnectOptions().apply{
            serverURIs = arrayOf(brokerUrl)
        }
        return DefaultMqttPahoClientFactory().apply {
            connectionOptions = options
        }
    }
    @Bean
    fun mqttOutboundChannel(): MessageChannel {
        return DirectChannel()
    }
    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    fun mqttOutbound(): MessageHandler {
        val handler = MqttPahoMessageHandler(clientId, mqttPahoClientFactory()).apply {
            setAsync(true)
            setDefaultTopic(topic)
        }
        return handler
    }
}