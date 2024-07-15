package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "my_model")
data class MyModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long=0,
    var name: String
)
