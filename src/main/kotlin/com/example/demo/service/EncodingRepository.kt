package com.example.demo.repository

import com.example.demo.model.EncodingModel
import org.springframework.data.jpa.repository.JpaRepository

interface EncodingRepository : JpaRepository<EncodingModel, Long> {
    fun findByEncodedUrl(encodedUrl: String): EncodingModel?
}
