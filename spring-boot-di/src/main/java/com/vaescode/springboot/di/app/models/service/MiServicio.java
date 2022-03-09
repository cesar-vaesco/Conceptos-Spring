package com.vaescode.springboot.di.app.models.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("miServicioSimple")
public class MiServicio implements IServicio {


    @Override
    public String operacion() {
        return "Ejecutando algún proceso importante usando una interfaz...";
    }
}
