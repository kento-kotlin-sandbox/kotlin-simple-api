package com.example.simpleapi.controller

import com.example.simpleapi.data.Message
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat
import java.util.*


@RestController
@RequestMapping("/messages")
class SimpleController {

    @GetMapping
    fun getMessages() : List<Message> {
        return listOf(
                Message(
                        UUID.randomUUID().toString(),
                        "First Message",
                        "This is a 1st message on ${getDate()}."
                ),
                Message(
                        UUID.randomUUID().toString(),
                        "second Message",
                        "this is a 2nd message on ${getDate()}."
                )
        )
    }

    @PutMapping
    fun insertMessage(@RequestBody message: Message) : Message {
        message.id = UUID.randomUUID().toString()
        return message
    }

    @PostMapping
    fun updateMessage(@RequestBody message: Message) : Message {
        message.title += "UPDATED TITLE:${getDate()}"
        message.message += "UPDATED MESSAGE:${getDate()}"
        return message
    }

    @DeleteMapping(value = ["/{id}"])
    fun deleteMessage(@PathVariable(name = "id") id: String): Boolean {
        return true
    }

    private fun getDate() : String {
        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        return simpleDateFormat.format(Date())
    }
}
