package com.example.demo.service

import com.example.demo.model.Message
import org.springframework.stereotype.Service

@Service
class MessageService(private val messageRepository: MessageRepository) {

    fun findByClientId(clientId: String): List<Message> {
        return messageRepository.findByClientId(clientId)
    }

    fun save(message: Message): Message {
        return messageRepository.save(message)
    }
}
