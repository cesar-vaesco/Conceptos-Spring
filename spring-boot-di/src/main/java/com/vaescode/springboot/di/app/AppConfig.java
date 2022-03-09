package com.vaescode.springboot.di.app;

import com.vaescode.springboot.di.app.models.domain.ItemFactura;
import com.vaescode.springboot.di.app.models.domain.Producto;
import com.vaescode.springboot.di.app.models.service.IServicio;
import com.vaescode.springboot.di.app.models.service.MiServicio;
import com.vaescode.springboot.di.app.models.service.MiServicioComplejo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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

    @Bean("itemsFacturaOficina")
    @Primary
    public List<ItemFactura> registrarItemsOficina() {

        Producto producto1 = new Producto("Camara Sony",100);
        Producto producto2 = new Producto("Bicicleta 26",150);
        Producto producto3 = new Producto("Tv Sony",500);
        Producto producto4 = new Producto("Pc Lg",4500);
        Producto producto5 = new Producto("Mouse Logitech",1100);

        ItemFactura linea1 = new ItemFactura(producto1,2);
        ItemFactura linea2 = new ItemFactura(producto2,5);
        ItemFactura linea3 = new ItemFactura(producto3,2);
        ItemFactura linea4 = new ItemFactura(producto4,5);
        ItemFactura linea5 = new ItemFactura(producto5,6);

        return Arrays.asList(linea1,linea2, linea3,linea4,linea5);
    }
}
