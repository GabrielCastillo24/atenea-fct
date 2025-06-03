<template>
  <div class="mis-pedidos-container">
    <div class="header">
      <h2>Mis Pedidos</h2>
      <p v-if="totalElementos" class="total-info">
        {{ totalElementos }} pedido{{ totalElementos !== 1 ? 's' : '' }} encontrado{{ totalElementos !== 1 ? 's' : '' }}
      </p>
    </div>

    <!-- Loading state -->
    <div v-if="cargando" class="loading">
      <div class="spinner"></div>
      <p>Cargando pedidos...</p>
    </div>

    <!-- Error state -->
    <div v-else-if="error" class="error">
      <p>Error al cargar los pedidos: {{ error }}</p>
      <button @click="cargarPedidos" class="btn-retry">Reintentar</button>
    </div>

    <!-- Empty state -->
    <div v-else-if="!pedidos.length" class="empty-state">
      <div class="empty-icon">📦</div>
      <h3>No tienes pedidos aún</h3>
      <p>Cuando realices tu primer pedido, aparecerá aquí.</p>
    </div>

    <!-- Pedidos list -->
    <div v-else class="pedidos-list">
      <div 
        v-for="pedido in pedidos" 
        :key="pedido.id" 
        class="pedido-card"
      >
        <!-- Pedido header -->
        <div class="pedido-header">
          <div class="pedido-info">
            <h3>Pedido #{{ pedido.id.substring(0, 8) }}</h3>
            <p class="fecha">{{ formatearFecha(pedido.fechaPedido) }}</p>
          </div>
          <div class="pedido-status">
            <span :class="['status-badge', `status-${pedido.estado.toLowerCase()}`]">
              {{ pedido.estado }}
            </span>
          </div>
        </div>

        <!-- Pedido details -->
        <div class="pedido-details">
          <div class="detail-row">
            <span class="label">Dirección de envío:</span>
            <span>{{ pedido.direccionEnvio }}</span>
          </div>
          <div class="detail-row">
            <span class="label">Método de pago:</span>
            <span>{{ pedido.metodoPago }}</span>
          </div>
          <div class="detail-row">
            <span class="label">Total:</span>
            <span class="total">€{{ pedido.total.toFixed(2) }}</span>
          </div>
        </div>

        <!-- Productos -->
        <div class="productos-section">
          <h4>Productos ({{ pedido.productos.length }})</h4>
          <div class="productos-list">
            <div 
              v-for="item in pedido.productos" 
              :key="item.id" 
              class="producto-item"
            >
              <div class="producto-imagen">
                <img 
                  :src="item.producto.urlImg" 
                  :alt="item.producto.nombre"
                  @error="onImageError"
                />
              </div>
              <div class="producto-info">
                <h5>{{ item.producto.nombre }}</h5>
                <p class="producto-descripcion">{{ item.producto.descripcion }}</p>
                <div class="producto-precio">
                  <span class="cantidad">Cantidad: {{ item.cantidad }}</span>
                  <span class="precio">€{{ item.precioUnitario.toFixed(2) }}</span>
                </div>
              </div>
            </div>
          </div>
        </div>


      </div>
    </div>

    <!-- Paginación -->
    <div v-if="totalPaginas > 1" class="paginacion">
      <button 
        @click="cambiarPagina(paginaActual - 1)"
        :disabled="paginaActual <= 0"
        class="btn-pagina"
      >
        Anterior
      </button>
      
      <span class="pagina-info">
        Página {{ paginaActual + 1 }} de {{ totalPaginas }}
      </span>
      
      <button 
        @click="cambiarPagina(paginaActual + 1)"
        :disabled="paginaActual >= totalPaginas - 1"
        class="btn-pagina"
      >
        Siguiente
      </button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'MisPedidos',
  data() {
    return {
      pedidos: [],
      cargando: false,
      error: null,
      paginaActual: 0,
      totalPaginas: 0,
      totalElementos: 0,
      tamañoPagina: 20
    }
  },
  async mounted() {
    await this.cargarPedidos()
  },
  methods: {
    async cargarPedidos(pagina = 0) {
      this.cargando = true
      this.error = null
      
      try {
        const response = await axios.get('/api/pedido/mis-pedidos', {
          params: {
            page: pagina,
            size: this.tamañoPagina
          }
        })
        
        const data = response.data
        this.pedidos = data.content
        this.paginaActual = data.number
        this.totalPaginas = data.totalPages
        this.totalElementos = data.totalElements
        
      } catch (error) {
        console.error('Error al cargar pedidos:', error)
        this.error = error.response?.data?.message || 'Error al cargar los pedidos'
      } finally {
        this.cargando = false
      }
    },

    async cambiarPagina(nuevaPagina) {
      if (nuevaPagina >= 0 && nuevaPagina < this.totalPaginas) {
        await this.cargarPedidos(nuevaPagina)
      }
    },

    formatearFecha(fecha) {
      return new Date(fecha).toLocaleDateString('es-ES', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit'
      })
    },

    onImageError(event) {
      event.target.src = '/placeholder-image.jpg' // Imagen por defecto
    }
  }
}
</script>

<style scoped>
.mis-pedidos-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.header {
  margin-bottom: 30px;
}

.header h2 {
  color: #333;
  margin-bottom: 10px;
}

.total-info {
  color: #666;
  font-size: 14px;
}

.loading {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 40px;
}

.spinner {
  width: 40px;
  height: 40px;
  border: 4px solid #f3f3f3;
  border-top: 4px solid #007bff;
  border-radius: 50%;
  animation: spin 1s linear infinite;
  margin-bottom: 20px;
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

.error {
  text-align: center;
  padding: 40px;
  color: #dc3545;
}

.btn-retry {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 10px;
}

.btn-retry:hover {
  background-color: #0056b3;
}


.empty-state {
  text-align: center;
  padding: 60px 20px;
}

.empty-icon {
  font-size: 4rem;
  margin-bottom: 20px;
}

.empty-state h3 {
  color: #333;
  margin-bottom: 10px;
}

.empty-state p {
  color: #666;
}


.pedidos-list {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.pedido-card {
  background: white;
  border-radius: 12px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  padding: 24px;
  transition: transform 0.2s, box-shadow 0.2s;
}

.pedido-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.15);
}

.pedido-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 20px;
  padding-bottom: 16px;
  border-bottom: 2px solid #f8f9fa;
}

.pedido-info h3 {
  color: #333;
  margin: 0 0 8px 0;
  font-size: 1.2rem;
}

.fecha {
  color: #666;
  font-size: 14px;
  margin: 0;
}

.status-badge {
  padding: 6px 12px;
  border-radius: 20px;
  font-size: 12px;
  font-weight: 600;
  text-transform: uppercase;
}

.status-pendiente {
  background-color: #fff3cd;
  color: #856404;
}

.status-completado {
  background-color: #d4edda;
  color: #155724;
}

.status-cancelado {
  background-color: #f8d7da;
  color: #721c24;
}


.pedido-details {
  margin-bottom: 20px;
}

.detail-row {
  display: flex;
  justify-content: space-between;
  margin-bottom: 8px;
  padding: 8px 0;
}

.label {
  font-weight: 600;
  color: #555;
}

.total {
  font-weight: 700;
  color: #D9AF62;
  font-size: 1.1rem;
}


.productos-section {
  margin-bottom: 20px;
}

.productos-section h4 {
  color: #333;
  margin-bottom: 16px;
  font-size: 1.1rem;
}

.productos-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.producto-item {
  display: flex;
  gap: 16px;
  padding: 12px;
  background-color: #f8f9fa;
  border-radius: 8px;
}

.producto-imagen {
  flex-shrink: 0;
}

.producto-imagen img {
  width: 80px;
  height: 80px;
  object-fit: cover;
  border-radius: 8px;
}

.producto-info {
  flex: 1;
}

.producto-info h5 {
  margin: 0 0 8px 0;
  color: #333;
  font-size: 1rem;
}

.producto-descripcion {
  color: #666;
  font-size: 14px;
  margin: 0 0 8px 0;
}

.producto-precio {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.cantidad {
  color: #666;
  font-size: 14px;
}

.precio {
  font-weight: 600;
  color: #D9AF62;
}

</style>