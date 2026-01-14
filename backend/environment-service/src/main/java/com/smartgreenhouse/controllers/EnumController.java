package com.smartgreenhouse.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartgreenhouse.Enum.ParametreType;

@RestController
@RequestMapping("/api/enums")
public class EnumController {

    @GetMapping("/parametre-types")
    public ParametreType[] getParametreTypes() {
        return ParametreType.values();
    }
}
