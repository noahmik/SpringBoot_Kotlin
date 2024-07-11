package com.example.demo.service

import com.example.demo.model.EncodingModel
import org.springframework.data.jpa.repository.JpaRepository

interface EncodingRepository : JpaRepository<EncodingModel, Long>