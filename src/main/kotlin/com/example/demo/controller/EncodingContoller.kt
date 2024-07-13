package com.example.demo.controller

import com.example.demo.dto.EncodingModelRequest
import com.example.demo.dto.EncodingModelResponse
import com.example.demo.model.EncodingModel
import com.example.demo.model.MyModel
import com.example.demo.service.EncodingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

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
}
