<template>
    
  <div class="contenedor-carrito" v-if="carrito">
    <h1>Tu Carrito</h1>
    <div
      class="producto-carrito"
      v-for="producto in carrito.productos"
      :key="producto.id"
    >
      <img
        :src="producto.urlImg"
        alt="Imagen del producto"
        class="imagen-producto"
      />
      <div class="detalle-producto">
        <h2 class="nombre-producto">{{ producto.nombre }}</h2>
        <p class="precio-producto">Precio: {{ producto.precioUnitario }} €</p>
        <div class="cantidad-producto">
          <span>Cantidad:</span>
          <button class="cantidad" @click="cambiarCantidad(producto,-1)">-</button>
          <span class="numero-cantidad">{{ producto.cantidad }}</span>
          <button class="cantidad" @click="cambiarCantidad(producto,+1)">+</button>
        </div>
      </div>
     <button class="boton-carrito" @click="quitarProducto(producto.idProducto)">Quitar</button>
    </div>
    <button v-if="carrito.productos && carrito.productos.length > 0" class="boton-carrito" @click="quitarTodo">Quitar todo</button>
    <div class="resumen-carrito">
      <p>Total productos: {{ carrito.totalItems }}</p>
      <p>Total a pagar: {{ carrito.total }} €</p>
    </div>

    <!-- Sección de método de pago -->
    <div class="metodo-pago" v-if="carrito.productos && carrito.productos.length > 0">
      <h3>Método de pago</h3>
      <div class="opciones-pago">
        <label class="opcion-pago">
          <input type="radio" value="TARJETA" v-model="metodoPago" />
          <span>Tarjeta de crédito/débito</span>
        </label>
        <label class="opcion-pago">
          <input type="radio" value="EFECTIVO" v-model="metodoPago" />
          <span>Efectivo</span>
        </label>
        <label class="opcion-pago">
          <input type="radio" value="TRANSFERENCIA" v-model="metodoPago" />
          <span>Transferencia bancaria</span>
        </label>
      </div>
    </div>

    <button 
      class="boton-carrito boton-pedido" 
      @click="hacerPedido"
      :disabled="!metodoPago || !carrito.productos || carrito.productos.length === 0 || procesandoPedido"
      v-if="carrito.productos && carrito.productos.length > 0"
    >
      <span v-if="procesandoPedido">Procesando pedido...</span>
      <span v-else>Hacer pedido</span>
    </button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      carrito: null,
      metodoPago: '',
      procesandoPedido: false
    };
  },
  mounted() {
    this.obtenerCarrito();
  },
  methods: {
    async obtenerCarrito() {
      try {
        const response = await axios.get("api/carrito", {
        });
        this.carrito = response.data;
        console.log("Carrito cargado:", this.carrito);
      } catch (error) {
        console.error("Error al obtener el carrito:", error.response?.data || error);
      }
    },
    async quitarProducto(idProducto) {
        console.log("Intentando quitar producto con ID:", idProducto);
        try {
            const response = await axios.delete(`/api/carrito/quitar-producto/${idProducto}`, {
            headers: {
                'Content-Type': 'application/json'
            }
            });
            console.log("Producto eliminado:", response.data);
            this.obtenerCarrito(); 
            return response.data;
        } catch (error) {
            console.error("Error al quitar el producto:", error.response?.data || error);
        }
    },
    async quitarTodo() {
        try {
        const response = await axios.delete('/api/carrito/vaciar');
        console.log("Carrito vaciado:", response.data);
        this.obtenerCarrito();
        } catch (error) {
        console.error("Error al vaciar el carrito:", error.response?.data || error);
    }
    },
    async cambiarCantidad(producto, delta) {
      const nuevaCantidad = producto.cantidad + delta;

      if (nuevaCantidad < 1) return; 

      try {
        const dto = { cantidad: nuevaCantidad };
        const response = await axios.put(`/api/carrito/producto/${producto.idProducto}/cantidad`,
          dto,
          { headers: { "Content-Type": "application/json" } }
        );
        this.carrito = response.data; 
      } catch (error) {
        console.error("Error al actualizar la cantidad:", error.response?.data || error);
      }
    },
    async hacerPedido(){
      // Validar que se haya seleccionado un método de pago
      if (!this.metodoPago || this.metodoPago.trim() === '') {
        alert('Debe seleccionar un método de pago');
        return;
      }
      // Validar que el carrito no esté vacío
      if (!this.carrito.productos || this.carrito.productos.length === 0) {
        alert('El carrito está vacío');
        return;
      }
      this.procesandoPedido = true;
      try {
        // Preparar los datos según CrearPedidoDto
        const pedidoData = {
          metodoPago: this.metodoPago
        };
        const response = await axios.post('/api/pedido/crear', pedidoData, {
          headers: {
            'Content-Type': 'application/json'
          }
        });
        console.log("Pedido realizado exitosamente:", response.data);
        alert('¡Pedido realizado con éxito!');
        // Limpiar el formulario
        this.metodoPago = '';
        // Actualizar el carrito (debería estar vacío ahora)
        this.obtenerCarrito();
      } catch (error) {
        console.error("Error al hacer el pedido:", error);
        if (error.response?.status === 400) {
          alert('Error en los datos del pedido. Verifique la información.');
        } else if (error.response?.status === 500) {
          alert('Error interno del servidor. Inténtelo más tarde.');
        } else {
          alert('Error al hacer el pedido. Verifique su conexión.');
        }
      } finally {
        this.procesandoPedido = false;
      }
    }
  }
};
</script>

<style scoped>
.contenedor-carrito {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
}

.producto-carrito {
  display: flex;
  gap: 20px;
  border-bottom: 1px solid #ddd;
  padding: 20px 0;
  align-items: center;
}

.imagen-producto {
  width: 100px;
  height: auto;
  object-fit: contain;
}

.detalle-producto {
  flex: 1;
}

.nombre-producto {
  font-size: 18px;
  font-weight: bold;
  margin: 0 0 10px 0;
}

.precio-producto {
  color: #b12704;
  font-weight: bold;
  margin: 5px 0;
}

.cantidad-producto {
  display: flex;
  align-items: center;
  gap: 10px;
  margin: 10px 0;
  font-size: 14px;
}

.numero-cantidad {
  font-weight: bold;
}

.resumen-carrito {
  text-align: right;
  margin-top: 30px;
  border-top: 2px solid #eee;
  padding-top: 20px;
  font-size: 16px;
}

.resumen-carrito p {
  margin: 5px 0;
}

/* Estilos para la sección de método de pago */
.metodo-pago {
  margin: 30px 0;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #f9f9f9;
}

.metodo-pago h3 {
  margin-top: 0;
  color: #6b5648;
}

.opciones-pago {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.opcion-pago {
  display: flex;
  align-items: center;
  cursor: pointer;
  padding: 8px;
  border-radius: 4px;
  transition: background-color 0.2s;
}

.opcion-pago:hover {
  background-color: #e9e9e9;
}

.opcion-pago input[type="radio"] {
  margin-right: 10px;
}

.boton-carrito {
  background-color: #6b5648;
  border: 1px solid #6b5648;
  padding: 10px 20px;
  font-weight: bold;
  cursor: pointer;
  border-radius: 10px;
  margin-top: 20px;
  transition: background-color 0.3s;
  color: #D9AF62;
}

.boton-pedido {
  width: 100%;
  padding: 15px 20px;
  font-size: 16px;
}

.boton-carrito:disabled {
  background-color: #ccc;
  border-color: #ccc;
  cursor: not-allowed;
  color: #666;
}

.cantidad{
  width: 20px;
  height: 20px;
  background-color:gray;
  border: 1px solid black;
  font-weight: bold;
  cursor: pointer;
  border-radius: 100px;
  transition: background-color 0.3s;
  color: white
}

button:hover:not(:disabled) {
  background-color: #4a3f35;
}
</style>