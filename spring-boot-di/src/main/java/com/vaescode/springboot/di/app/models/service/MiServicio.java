package com.vaescode.springboot.di.app.models.service;

import org.springframework.stereotype.Service;

@Service
public class MiServicio {

    public String operacion(){
        return "Ejecutando algún proceso importante...";
    }

}
