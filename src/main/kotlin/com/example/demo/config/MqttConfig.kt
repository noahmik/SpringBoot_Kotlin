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

@Configuration
class MqttConfig {
    private val brokerUrl = "tcp://localhost:1883"
    private val clientId = "mqtt-rok"
    private val topic = "bssm"

    @Bean
    fun mqttPahoClientFactory(): MqttPahoClientFactory {
        val options = MqttConnectOptions().apply {
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
        return MqttPahoMessageHandler(clientId, mqttPahoClientFactory()).apply {
            setAsync(true)
            setDefaultTopic(topic)
        }
    }
}
