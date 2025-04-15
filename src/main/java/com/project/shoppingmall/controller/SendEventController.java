package com.project.shoppingmall.controller;

import com.project.shoppingmall.service.SendEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/send")
public class SendEventController {

    private final SendEventService sendEventService;

    @GetMapping("/logs")
    public ResponseEntity<Object> sendAll() {
        return new ResponseEntity<>(sendEventService.sendAll(), HttpStatus.OK);
    }
}
