package com.example.demo.controller

import com.example.demo.dto.SaveMyModelRequest
import com.example.demo.model.MyModel
import com.example.demo.service.MyModelService
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.web.bind.annotation.*

@RestController
class MyController(
    val myModelService: MyModelService
) {
    @GetMapping("/hello")
    fun hello(): String {
        return "Hello World!"
    }

    @PostMapping("/my-model")
    fun saveMyModel(
        @RequestBody
        request: SaveMyModelRequest
    ): MyModel {
        return myModelService.save(
            MyModel(
                name = request.name
            )
        )
    }


}