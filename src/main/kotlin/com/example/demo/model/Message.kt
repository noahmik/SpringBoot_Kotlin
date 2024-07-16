package com.example.demo.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime
import jakarta.persistence.*


@Entity
data class Message(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val clientId: String,

    val message: String,

    val createdAt: LocalDateTime = LocalDateTime.now()
)
