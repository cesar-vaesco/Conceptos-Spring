package com.vaescode.springboot.di.app.models.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("miServicioComplejo")
@Primary // Esta anotación permite definir que bean tiene prioridad de inyección en el controller
public class MiServicioComplejo implements IServicio {


    @Override
    public String operacion() {
        return "Ejecutando algún proceso importante complicado...";
    }
}
