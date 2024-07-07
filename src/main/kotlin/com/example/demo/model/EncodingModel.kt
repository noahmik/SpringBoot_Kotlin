package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "endcoding")
data class EncodingModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var originalUrl: String,
    var encodedUrl: String
)