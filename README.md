# Microservicio de Registro de Vehiculos

Microservicio Spring Boot con Lombok para registrar vehiculos y consultar su estado.

Tipos de transporte soportados:
- AUTO
- BICICLETA
- SCOOTER
- OTRO

Estados soportados:
- OCUPADO
- DISPONIBLE
- EN_USO

## Tecnologias

- Java 17 (compila y corre sobre JDK modernos)
- Spring Boot 3.3.4
- Lombok
- Maven

## Ejecutar el proyecto

```bash
mvn spring-boot:run
```

Aplicacion disponible en:

```text
http://localhost:8080
```

Swagger UI disponible en:

```text
http://localhost:8080/swagger-ui/index.html
```

OpenAPI JSON:

```text
http://localhost:8080/v3/api-docs
```

## Endpoints

### 1) Crear registro de vehiculo

`POST /api/vehiculos`

Ejemplo:

```bash
curl -X POST http://localhost:8080/api/vehiculos \
	-H "Content-Type: application/json" \
	-d '{
		"tipoTransporte": "AUTO",
		"estado": "DISPONIBLE",
		"patente": "AB123CD",
		"color": "Rojo",
		"marca": "Toyota",
		"modelo": "Corolla",
		"anio": 2022,
		"kilometraje": 15000
	}'
```

Tambien podes crear por ejemplo bicicleta:

```bash
curl -X POST http://localhost:8080/api/vehiculos \
	-H "Content-Type: application/json" \
	-d '{
		"tipoTransporte": "BICICLETA",
		"estado": "EN_USO",
		"color": "Negro",
		"marca": "Venzo",
		"modelo": "X1",
		"anio": 2024,
		"kilometraje": 350
	}'
```

### 2) Consultar estado por id

`GET /api/vehiculos/{id}/estado`

Ejemplo:

```bash
curl http://localhost:8080/api/vehiculos/7d57460d-ef57-4e1f-b2b5-536f98d8034a/estado
```

Respuesta esperada:

```json
{
	"id": "7d57460d-ef57-4e1f-b2b5-536f98d8034a",
	"estado": "DISPONIBLE"
}
```

### 3) Actualizar estado por id

`PATCH /api/vehiculos/{id}/estado`

Ejemplo:

```bash
curl -X PATCH http://localhost:8080/api/vehiculos/7d57460d-ef57-4e1f-b2b5-536f98d8034a/estado \
	-H "Content-Type: application/json" \
	-d '{
		"estado": "OCUPADO"
	}'
```

Respuesta esperada:

```json
{
	"id": "7d57460d-ef57-4e1f-b2b5-536f98d8034a",
	"estado": "OCUPADO"
}
```

