package com.example.demo.service

import com.example.demo.model.MyModel
import org.springframework.stereotype.Service

@Service
class MyModelService(val myModelRepository: MyModelRepository) {
    fun save(myModel: MyModel): MyModel
        = myModelRepository.save(myModel)
}