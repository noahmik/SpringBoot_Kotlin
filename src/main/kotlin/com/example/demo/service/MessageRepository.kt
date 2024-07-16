package com.example.demo.service

import com.example.demo.model.Message
import org.springframework.data.jpa.repository.JpaRepository

interface MessageRepository : JpaRepository<Message, Long> {
    fun findByClientId(clientId: String): List<Message>
    fun save(message: Message):Message
}
