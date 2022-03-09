package com.vaescode.springboot.di.app.models.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component("miServicioSimple")
//@Primary // Esta anotación permite definir que bean tiene prioridad de inyección en el controller
public class MiServicio implements IServicio {


    @Override
    public String operacion() {
        return "Ejecutando algún proceso importante simple usando una interfaz...";
    }
}
