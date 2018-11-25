package com.gabi.backend.bikeparkend.controller;

import com.gabi.backend.bikeparkend.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/bikepark")
public class BikeParkController {

    @Autowired
    private GenericService userService;

    //TODO
}
