# El Mapache Organizador

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

## Notas
- La tabla `usuarios` se crea automáticamente
- Si tienes contraseña en MySQL, actualiza `application.properties`