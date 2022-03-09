package com.vaescode.springboot.di.app;

import com.vaescode.springboot.di.app.models.service.IServicio;
import com.vaescode.springboot.di.app.models.service.MiServicio;
import com.vaescode.springboot.di.app.models.service.MiServicioComplejo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean("miServicioAppConfig")
    public IServicio registrarMiServicio(){
        return new MiServicio();
    }
    @Bean("miServicioComplejo")
    public IServicio registrarMiServicioComplejo(){
        return new MiServicioComplejo();
    }
}
