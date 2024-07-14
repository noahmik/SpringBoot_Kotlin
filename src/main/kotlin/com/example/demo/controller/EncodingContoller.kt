package com.example.demo.controller

import com.example.demo.dto.EncodingModelRequest
import com.example.demo.dto.EncodingModelResponse
import com.example.demo.model.EncodingModel
import com.example.demo.model.MyModel
import com.example.demo.service.EncodingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
class EncodingController(@Autowired val encodingService: EncodingService) {

    @PostMapping("/api/short-urls")
    fun saveEncoding(
        @RequestBody request: EncodingModelRequest
    ): EncodingModelResponse {
        val savedModel = encodingService.save(request)
        return EncodingModelResponse(
            originalUrl = savedModel.originalUrl,
            encodedUrl = "http://localhost:8080/short-url/${savedModel.encodedUrl}"
        )
    }

    @GetMapping("/api/short-urls")
    fun GetEncoding(): List<EncodingModel>
            = encodingService.findAll()

    @GetMapping("/short-url/{encoded}")
    fun getOriginalUrl(@PathVariable encoded: String): ResponseEntity<Void> {
        val originalUrl = encodingService.findOriginalUrl(encoded)
        return if (originalUrl != null) {
            ResponseEntity.status(HttpStatus.FOUND).location(URI(originalUrl)).build()
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
}
