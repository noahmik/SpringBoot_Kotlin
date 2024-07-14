package com.example.demo.service

import com.example.demo.dto.EncodingModelRequest
import com.example.demo.model.EncodingModel
import com.example.demo.model.MyModel
import com.example.demo.repository.EncodingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class EncodingService(@Autowired val encodingRepository: EncodingRepository) {

    fun encodeUrl(originalUrl: String): String {
        // 단순한 인코딩 로직 (예: UUID를 사용한 단축 URL 생성)
        return UUID.randomUUID().toString().substring(0, 8)
    }

    fun save(request: EncodingModelRequest): EncodingModel {
        val encodedUrl = encodeUrl(request.originalUrl)
        val encodingModel = EncodingModel(originalUrl = request.originalUrl, encodedUrl = encodedUrl)
        return encodingRepository.save(encodingModel)
    }

    fun findAll(): List<EncodingModel>
            = encodingRepository.findAll()

    fun findOriginalUrl(encoded: String): String? {
        return encodingRepository.findByEncodedUrl(encoded)?.originalUrl
    }

}
