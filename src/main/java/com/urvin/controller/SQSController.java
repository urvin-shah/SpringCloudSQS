package com.urvin.controller;

import com.urvin.domain.MailMessage;
import com.urvin.queue.ObjectQueue;
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

    @Autowired
    ObjectQueue objectQueue;

    @PostMapping("/textMessage")
    public boolean sendTextMessage() {
        textQueue.sendTextMessage("Hello World!!!");
        return true;
    }

    @PostMapping("/objectMessage")
    public boolean sendObjectMessage() {
        objectQueue.sendMessage(new MailMessage("urvin","urvin_shah@gmail.com","s.s@gmail.com","Hello","Hello World from Urvin","ur@gmail.com"));
        return true;
    }
}
