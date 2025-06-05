package com.atenea.backend.dto;

import java.math.BigDecimal;
/**
 * DTO Record para mostrar estadísticas y resumen de pedidos
 */
public record ResumenPedidoDto(
        Long totalPedidos,
        Long pedidosPendientes,
        Long pedidosProcesando,
        Long pedidosEnviados,
        Long pedidosEntregados,
        Long pedidosCancelados,
        BigDecimal ventasTotales
) {}
