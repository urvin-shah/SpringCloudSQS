package com.urvin.controller;

import com.urvin.queue.TextQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/send")
public class SQSController {

    @Autowired
    TextQueue textQueue;

    @PostMapping("/textMessage")
    public boolean sendTextMessage() {
        textQueue.send("Hello World!!!");
        return true;
    }
}
