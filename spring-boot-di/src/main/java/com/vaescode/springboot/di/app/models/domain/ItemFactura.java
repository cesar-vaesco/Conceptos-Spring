package com.vaescode.springboot.di.app.models.domain;

import java.util.List;

public class ItemFactura {

    private Producto producto;
    private Integer cantidad;

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
