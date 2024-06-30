package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "my_model")
class MyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    var name: String = ""

    // 추가적인 초기화 로직 등
}
