# El Mapache Organizador

## Sistema de Gestión de Tareas con Estructuras de Datos

Aplicación web donde los usuarios pueden agregar, organizar y completar tareas personales o laborales según su prioridad y estado, utilizando estructuras de datos implementadas desde cero.

## 📚 Estructuras de Datos Implementadas

### ✅ 1. Lista Doblemente Enlazada
- Almacenamiento y ordenamiento por prioridad
- Operaciones: agregar, eliminar, buscar, mostrar

### ✅ 2. Pila (LIFO)
- Historial de tareas más recientes
- Operaciones: push, pop, peek

### ✅ 3. Cola (FIFO)
- Gestión de tareas en orden de llegada
- Operaciones: enqueue, dequeue, front

### ✅ 4. Árbol Binario de Búsqueda
- Clasificación jerárquica por prioridad
- Operaciones: insertar, eliminar, buscar, recorridos (InOrden, PreOrden, PostOrden)
- Recorridos especiales: Mayor→Menor y Menor→Mayor prioridad

**Nota:** Todas las estructuras están implementadas sin usar clases de Java Collections.

## Requisitos para ejecutar el proyecto

### 1. Instalar XAMPP
- Descargar desde: https://www.apachefriends.org/
- Instalar y ejecutar Apache y MySQL

### 2. La base de datos se crea automáticamente
- No necesitas crear nada manualmente
- Spring Boot creará `mapache_organizador` automáticamente

### 3. Ejecutar el proyecto
```bash
./mvnw spring-boot:run
```

### 4. Acceder a la aplicación
- URL: http://localhost:8082
- Registro: http://localhost:8082/register
- Login: http://localhost:8082/login

## Configuración de MySQL
- Host: localhost
- Puerto: 3306
- Base de datos: mapache_organizador
- Usuario: root
- Contraseña: (vacía)

## 🎯 Funcionalidades

- ✅ Registro y login de usuarios
- ✅ Agregar tareas con descripción, categoría y prioridad
- ✅ Marcar tareas como completadas
- ✅ Eliminar tareas
- ✅ Filtrar por: Todas, Pendientes, Completadas, Personales, Trabajo
- ✅ Ordenar con diferentes estructuras de datos:
  - 🎯 Lista Priorizada
  - 📚 Pila (LIFO)
  - 🚶 Cola (FIFO)
  - 🌳 Árbol Binario (Mayor→Menor)
  - 🌲 Árbol Binario (Menor→Mayor)
- ✅ Estadísticas en tiempo real

## 📊 Sistema de Prioridades

```
Prioridad Numérica:
- Trabajo Alta = 6 (máxima)
- Trabajo Media = 5
- Trabajo Baja = 4
- Personal Alta = 3
- Personal Media = 2
- Personal Baja = 1 (mínima)
```

## 📖 Documentación

Para más detalles sobre las estructuras de datos implementadas, consulta:
- [DOCUMENTACION_ESTRUCTURAS.md](DOCUMENTACION_ESTRUCTURAS.md)

## 🧪 Pruebas

Pruebas del Árbol Binario:
```bash
cd src/test/java/com/integradora/mapacheorganizador2
java ArbolBinarioTest.java
```

## Notas
- La tabla `usuarios` y `tareas` se crean automáticamente
- Si tienes contraseña en MySQL, actualiza `application.properties`
- Todas las estructuras de datos están implementadas sin usar Java Collections