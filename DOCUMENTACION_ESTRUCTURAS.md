# Documentación de Estructuras de Datos
## El Mapache Organizador - Sistema de Gestión de Tareas

---

## 📋 Índice
1. [Modelo de Datos](#modelo-de-datos)
2. [Estructuras Implementadas](#estructuras-implementadas)
3. [Operaciones Básicas](#operaciones-básicas)
4. [Integración con la Aplicación](#integración-con-la-aplicación)
5. [Pruebas](#pruebas)

---

## 1. Modelo de Datos

### Clase Principal: `Tarea`
**Ubicación:** `com.integradora.mapacheorganizador2.entity.Tarea`

**Atributos:**
- `id` (Long): Identificador único de la tarea
- `descripcion` (String): Descripción de la tarea
- `categoria` (String): "Personal" o "Trabajo"
- `prioridad` (String): "Alta", "Media" o "Baja"
- `completada` (boolean): Estado de la tarea
- `usuario` (Usuario): Usuario propietario de la tarea

**Cálculo de Prioridad Numérica:**
```
Prioridad Base:
- Alta = 3 puntos
- Media = 2 puntos
- Baja = 1 punto

Bonus por Categoría:
- Trabajo = +3 puntos
- Personal = +0 puntos

Ejemplos:
- Trabajo Alta = 3 + 3 = 6 (máxima prioridad)
- Personal Alta = 3 + 0 = 3
- Trabajo Media = 2 + 3 = 5
- Personal Media = 2 + 0 = 2
- Trabajo Baja = 1 + 3 = 4
- Personal Baja = 1 + 0 = 1 (mínima prioridad)
```

---

## 2. Estructuras Implementadas

### 2.1 Lista Doblemente Enlazada (`ListaTareas`)
**Ubicación:** `com.integradora.mapacheorganizador2.estructuras.ListaTareas`

**Descripción:** 
Lista doblemente enlazada que permite inserción ordenada por prioridad.

**Características:**
- Cada nodo tiene referencia al anterior y siguiente
- Mantiene referencias a cabeza y cola
- Inserción ordenada automática según prioridad

**Operaciones:**
- `agregar(Tarea)`: Agrega al final - O(1)
- `insertarOrdenado(Tarea)`: Inserta según prioridad - O(n)
- `eliminar(Long id)`: Elimina por ID - O(n)
- `isEmpty()`: Verifica si está vacía - O(1)
- `size()`: Retorna tamaño - O(1)
- `toList()`: Convierte a List<Tarea> - O(n)

**Uso en la aplicación:**
Organiza tareas por prioridad en la vista principal.

---

### 2.2 Pila (`PilaTareas`)
**Ubicación:** `com.integradora.mapacheorganizador2.estructuras.PilaTareas`

**Descripción:** 
Estructura LIFO (Last In, First Out) - El último en entrar es el primero en salir.

**Características:**
- Implementada con lista enlazada simple
- Acceso solo por el tope
- Ideal para historial o deshacer acciones

**Operaciones:**
- `push(Tarea)`: Agrega al tope - O(1)
- `pop()`: Remueve y retorna del tope - O(1)
- `peek()`: Consulta el tope sin remover - O(1)
- `isEmpty()`: Verifica si está vacía - O(1)
- `size()`: Retorna tamaño - O(1)
- `toList()`: Convierte a List<Tarea> - O(n)

**Uso en la aplicación:**
Muestra las tareas más recientes primero, útil para trabajar en lo último agregado.

---

### 2.3 Cola (`ColaTareas`)
**Ubicación:** `com.integradora.mapacheorganizador2.estructuras.ColaTareas`

**Descripción:** 
Estructura FIFO (First In, First Out) - El primero en entrar es el primero en salir.

**Características:**
- Implementada con lista enlazada simple
- Inserción por el final, extracción por el frente
- Ideal para procesar tareas en orden de llegada

**Operaciones:**
- `enqueue(Tarea)`: Agrega al final - O(1)
- `dequeue()`: Remueve y retorna del frente - O(1)
- `front()`: Consulta el frente sin remover - O(1)
- `isEmpty()`: Verifica si está vacía - O(1)
- `size()`: Retorna tamaño - O(1)
- `toList()`: Convierte a List<Tarea> - O(n)

**Uso en la aplicación:**
Muestra las tareas más antiguas primero, útil para completar en orden de llegada.

---

### 2.4 Árbol Binario de Búsqueda (`ArbolBinarioPrioridad`)
**Ubicación:** `com.integradora.mapacheorganizador2.estructuras.ArbolBinarioPrioridad`

**Descripción:** 
Árbol binario que organiza tareas según su prioridad numérica. Las tareas con menor prioridad van a la izquierda, las de mayor prioridad a la derecha.

**Características:**
- Cada nodo tiene máximo dos hijos (izquierdo y derecho)
- Organización automática por prioridad
- Permite múltiples tipos de recorridos
- Búsqueda eficiente

**Estructura del Nodo (`NodoArbol`):**
- `tarea`: Referencia a la tarea
- `prioridad`: Valor numérico para comparación
- `izquierdo`: Hijo izquierdo (menor prioridad)
- `derecho`: Hijo derecho (mayor prioridad)

**Operaciones:**

1. **Inserción** - `insertar(Tarea)` - O(log n) promedio, O(n) peor caso
   - Calcula la prioridad numérica de la tarea
   - Inserta recursivamente: menor a la izquierda, mayor a la derecha

2. **Búsqueda** - `buscar(Long id)` - O(n)
   - Busca una tarea por su ID recorriendo el árbol

3. **Eliminación** - `eliminar(Long id)` - O(log n) promedio
   - Caso 1: Nodo sin hijos → Se elimina directamente
   - Caso 2: Nodo con un hijo → Se reemplaza por su hijo
   - Caso 3: Nodo con dos hijos → Se reemplaza por el menor del subárbol derecho

4. **Recorridos:**
   - `recorridoInOrden()`: Izquierda → Raíz → Derecha (menor a mayor) - O(n)
   - `recorridoPreOrden()`: Raíz → Izquierda → Derecha - O(n)
   - `recorridoPostOrden()`: Izquierda → Derecha → Raíz - O(n)
   - `obtenerPorPrioridadDescendente()`: Derecha → Raíz → Izquierda (mayor a menor) - O(n)

5. **Utilidades:**
   - `altura()`: Calcula la altura del árbol - O(n)
   - `isEmpty()`: Verifica si está vacío - O(1)
   - `size()`: Retorna cantidad de nodos - O(1)

**Uso en la aplicación:**
- Organiza tareas por prioridad de forma jerárquica
- Permite visualizar tareas de mayor a menor prioridad o viceversa
- Útil para clasificación y búsqueda eficiente

**Ejemplo de Árbol:**
```
         Trabajo Media (5)
        /                  \
Personal Baja (1)      Trabajo Alta (6)
       \                    /
  Personal Media (2)  Personal Alta (3)
```

---

## 3. Operaciones Básicas

### Clase Auxiliar: `Nodo`
**Ubicación:** `com.integradora.mapacheorganizador2.estructuras.Nodo`

Nodo genérico usado por Lista, Pila y Cola:
- `tarea`: Referencia a la tarea
- `siguiente`: Siguiente nodo
- `anterior`: Nodo anterior (solo para lista doblemente enlazada)

---

## 4. Integración con la Aplicación

### Servicio: `GestorTareas`
**Ubicación:** `com.integradora.mapacheorganizador2.service.GestorTareas`

**Métodos:**

1. **`organizarPorPrioridad(List<Tarea>)`**
   - Usa: ListaTareas
   - Retorna: Tareas ordenadas por prioridad

2. **`organizarPorUltimasAgregadas(List<Tarea>)`**
   - Usa: PilaTareas
   - Retorna: Tareas más recientes primero (LIFO)

3. **`organizarPorPrimerasAgregadas(List<Tarea>)`**
   - Usa: ColaTareas
   - Retorna: Tareas más antiguas primero (FIFO)

4. **`organizarConArbolBinario(List<Tarea>)`**
   - Usa: ArbolBinarioPrioridad
   - Retorna: Tareas de mayor a menor prioridad

5. **`organizarConArbolBinarioAscendente(List<Tarea>)`**
   - Usa: ArbolBinarioPrioridad
   - Retorna: Tareas de menor a mayor prioridad

6. **`obtenerDescripcionPrioridad(Tarea)`**
   - Retorna: Descripción formateada con nivel de prioridad

### Controlador: `TareasController`
**Ubicación:** `com.integradora.mapacheorganizador2.controller.TareasController`

**Parámetros de ordenamiento:**
- `orden=prioridad` → Lista Priorizada
- `orden=pila` → Pila (LIFO)
- `orden=cola` → Cola (FIFO)
- `orden=arbol` → Árbol Binario (Mayor→Menor)
- `orden=arbol-asc` → Árbol Binario (Menor→Mayor)
- Sin parámetro → Orden normal (base de datos)

**Ejemplo de uso:**
```
http://localhost:8082/tareas?orden=arbol
http://localhost:8082/tareas?filtro=trabajo&orden=arbol
```

---

## 5. Pruebas

### Clase de Pruebas: `ArbolBinarioTest`
**Ubicación:** `src/test/java/com/integradora/mapacheorganizador2/ArbolBinarioTest.java`

**Pruebas implementadas:**

1. **Inserción de tareas**
   - Inserta 6 tareas con diferentes prioridades
   - Verifica tamaño y altura del árbol

2. **Búsqueda de tareas**
   - Busca tarea existente por ID
   - Busca tarea inexistente

3. **Recorridos del árbol**
   - InOrden (menor a mayor)
   - InOrden Inverso (mayor a menor)
   - PreOrden
   - PostOrden

4. **Eliminación de tareas**
   - Elimina una tarea
   - Verifica que el árbol se reorganiza correctamente

5. **Estado del árbol**
   - Verifica si está vacío
   - Consulta tamaño y altura

**Ejecutar pruebas:**
```bash
# Compilar
javac -cp "src/main/java" src/test/java/com/integradora/mapacheorganizador2/ArbolBinarioTest.java

# Ejecutar
java -cp "src/main/java;src/test/java" com.integradora.mapacheorganizador2.ArbolBinarioTest
```

---

## 📊 Comparación de Estructuras

| Estructura | Inserción | Búsqueda | Eliminación | Uso Principal |
|------------|-----------|----------|-------------|---------------|
| Lista | O(n) | O(n) | O(n) | Orden por prioridad |
| Pila | O(1) | O(n) | O(1) | Últimas tareas (LIFO) |
| Cola | O(1) | O(n) | O(1) | Primeras tareas (FIFO) |
| Árbol Binario | O(log n)* | O(n) | O(log n)* | Clasificación jerárquica |

*Promedio, peor caso O(n)

---

## 🎯 Ventajas de Cada Estructura

### Lista Priorizada
✅ Mantiene orden automático por prioridad
✅ Fácil de recorrer secuencialmente
✅ Inserción ordenada

### Pila (LIFO)
✅ Acceso rápido a lo más reciente
✅ Ideal para historial o deshacer
✅ Operaciones O(1)

### Cola (FIFO)
✅ Procesa en orden de llegada
✅ Justo para tareas pendientes
✅ Operaciones O(1)

### Árbol Binario
✅ Organización jerárquica natural
✅ Búsqueda más eficiente (promedio)
✅ Múltiples formas de recorrido
✅ Clasificación automática por prioridad
✅ Visualización clara de niveles de prioridad

---

## 📝 Notas Importantes

1. **No se utilizan clases de Java Collections**
   - Todas las estructuras están implementadas desde cero
   - Solo se usa `ArrayList` para retornar resultados (conversión final)

2. **Persistencia**
   - Las estructuras son temporales (en memoria)
   - Los datos se almacenan en MySQL
   - Las estructuras se reconstruyen en cada consulta

3. **Complejidad**
   - Las operaciones están optimizadas según el tipo de estructura
   - El árbol binario puede degenerar en lista si se insertan datos ordenados

4. **Extensibilidad**
   - Fácil agregar nuevas estructuras
   - El patrón permite integrar más algoritmos de ordenamiento

---

## 🚀 Cómo Usar en la Interfaz

1. **Acceder a la aplicación:** http://localhost:8082/tareas

2. **Agregar tareas** con diferentes prioridades y categorías

3. **Probar las estructuras** usando los botones:
   - 🎯 Lista Priorizada
   - 📚 Pila (LIFO)
   - 🚶 Cola (FIFO)
   - 🌳 Árbol (Mayor→Menor)
   - 🌲 Árbol (Menor→Mayor)

4. **Observar** cómo cada estructura organiza las tareas de forma diferente

---

**Desarrollado para:** Práctica de Estructuras de Datos
**Tecnologías:** Java, Spring Boot, MySQL, Thymeleaf
**Estructuras:** Lista, Pila, Cola, Árbol Binario (implementadas sin bibliotecas)
