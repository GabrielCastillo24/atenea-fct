package com.atenea.backend.api.dto;

public class CarritoDTO {
    private Long idUsuario;
    private Long idProducto;
    private Integer cantidadProducto;

    // Constructor vacío
    public CarritoDTO() {}

    // Constructor con parámetros
    public CarritoDTO(Long idUsuario, Long idProducto, Integer cantidadProducto) {
        this.idUsuario = idUsuario;
        this.idProducto = idProducto;
        this.cantidadProducto = cantidadProducto;
    }

    // Getters y Setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    // toString
    @Override
    public String toString() {
        return "CarritoDTO{" +
                "idUsuario=" + idUsuario +
                ", idProducto=" + idProducto +
                ", cantidadProducto=" + cantidadProducto +
                '}';
    }
}