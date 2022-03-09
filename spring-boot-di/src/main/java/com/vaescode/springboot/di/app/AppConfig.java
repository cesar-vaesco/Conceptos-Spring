package com.vaescode.springboot.di.app;

import com.vaescode.springboot.di.app.models.domain.ItemFactura;
import com.vaescode.springboot.di.app.models.domain.Producto;
import com.vaescode.springboot.di.app.models.service.IServicio;
import com.vaescode.springboot.di.app.models.service.MiServicio;
import com.vaescode.springboot.di.app.models.service.MiServicioComplejo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean("miServicioAppConfig")
    public IServicio registrarMiServicio() {
        return new MiServicio();
    }

    @Bean("miServicioComplejo")
    public IServicio registrarMiServicioComplejo() {
        return new MiServicioComplejo();
    }

    @Bean("itemsFactura")
    public List<ItemFactura> registrarItems() {

        Producto producto1 = new Producto("Camara Sony",100);
        Producto producto2 = new Producto("Bicicleta 26",150);

        ItemFactura linea1 = new ItemFactura(producto1,2);
        ItemFactura linea2 = new ItemFactura(producto2,5);

        return Arrays.asList(linea1,linea2);
    }
}
