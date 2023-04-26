# ProyectoTecnicasAvanzadas

**Link para abrir el swagger:**

"http://localhost:8080/swagger-ui/index.html#/"

**Rutas y explicación AirportController:**

1.Para crear un nuevo aeropuerto, selecciona la opción "POST" y luego ingresa la siguiente URL en la barra de direcciones de Postman: "http://localhost:8080/api/airports". Asegúrate de seleccionar la pestaña "Body", luego selecciona la opción "raw" y en la caja de texto ingresa un objeto JSON con la información necesaria para crear un nuevo aeropuerto, por ejemplo:

```json
{
   
    "iataCode": "LAX",
    "name": "Los Angeles International Airport"
}
```

2.Para obtener un aeropuerto específico, selecciona la opción "GET" y luego ingresa la siguiente URL en la barra de direcciones de Postman: "http://localhost:8080/api/airports/{id}". Reemplaza "{id}" con el ID del aeropuerto que deseas obtener. Haz clic en el botón "Send" para enviar la solicitud GET.

3.Para eliminar un aeropuerto específico, selecciona la opción "DELETE" y luego ingresa la siguiente URL en la barra de direcciones de Postman: "http://localhost:8080/api/airports/{id}". Reemplaza "{id}" con el ID del aeropuerto que deseas eliminar. Haz clic en el botón "Send" para enviar la solicitud DELETE.

4.Para obtener un aeropuerto por su código IATA, selecciona la opción "GET" y luego ingresa la siguiente URL en la barra de direcciones de Postman: "http://localhost:8080/api/airports/iata/{iataCode}". Reemplaza "{iataCode}" con el código IATA del aeropuerto que deseas obtener. Haz clic en el botón "Send" para enviar la solicitud GET.

**Rutas y explicación FlightController:**

1.Para la ruta @PostMapping: Crear una solicitud de tipo POST y en el cuerpo (Body) de la solicitud, ingresar un objeto JSON con los datos de un vuelo, en el formato:

```json
{
"flightNumber": "AB123",
"departureDate": "2023-05-01",
"departureAirport": {
"id": 1
},
"arrivalDate": "2023-05-01",
"arrivalAirport": {
"id": 2
}
}
```

2.Para la ruta @GetMapping("/{id}"): Crear una solicitud de tipo GET y en la barra de dirección, agregar el ID del vuelo que se desea obtener. Por ejemplo, si se desea obtener el vuelo con ID 1, la URL quedaría así: "http://localhost:8080/api/flights/1".

3.Para la ruta @DeleteMapping("/{id}"): Crear una solicitud de tipo DELETE y en la barra de dirección, agregar el ID del vuelo que se desea eliminar. Por ejemplo, si se desea eliminar el vuelo con ID 1, la URL quedaría así: "http://localhost:8080/api/flights/1".

4.Para la ruta @GetMapping("/search"): Crear una solicitud de tipo GET y en la barra de dirección, agregar los parámetros de búsqueda (departureAirportIataCode, arrivalAirportIataCode y departureDate) en la URL. Por ejemplo, si se desea buscar vuelos con salida del aeropuerto con código IATA "MEX", llegada al aeropuerto con código IATA "JFK" y fecha de salida el 1 de mayo de 2023, la URL quedaría así: "http://localhost:8080/api/flights/search?departureAirportIataCode=MEX&arrivalAirportIataCode=JFK&departureDate=2023-05-01".

**Rutas y explicación PassengerController:**

1. Para utilizar la ruta createPassenger, haz clic en la pestaña "Body" y selecciona "raw" y "JSON" en el menú desplegable. A continuación, introduce los datos de la entidad Passenger en formato JSON. Ejemplo:

```json
{
  "firstName": "Juan",
  "lastName": "Pérez",
  "email": "juan.perez@example.com"
}
```

2.Para utilizar la ruta getPassenger, selecciona "GET" en la lista desplegable de métodos HTTP y escribe la URL "http://localhost:8080/api/passengers/{id}" en la barra de direcciones de Postman, donde {id} es el identificador del pasajero que deseas obtener. Ejemplo: "http://localhost:8080/api/passengers/1"

3.Para utilizar la ruta deletePassenger, selecciona "DELETE" en la lista desplegable de métodos HTTP y escribe la URL "http://localhost:8080/api/passengers/{id}" en la barra de direcciones de Postman, donde {id} es el identificador del pasajero que deseas eliminar. Ejemplo: "http://localhost:8080/api/passengers/1".

**Rutas y explicación ReservationController:**

1.Para crear una nueva reserva, use la ruta POST "/api/reservations". Deberá enviar una solicitud POST con un cuerpo de solicitud JSON que contenga los detalles de la reserva. En Postman, seleccione el método POST y agregue la URL "http://localhost:8080/api/reservations". En la pestaña "Body", seleccione "raw" y elija "JSON" en el menú desplegable. Luego, ingrese los detalles de la reserva en formato JSON y haga clic en "Send" para enviar la solicitud, de esta manera:

```json
{
    "passenger": {
        "id": 1,
        "name": "Juan",
        "lastname": "Perez",
        "email": "juan.perez@email.com"
    },
    "flight": {
        "id": 1,
        "flightNumber": "AA123",
        "departureDate": "2023-05-01T10:00:00Z",
        "arrivalDate": "2023-05-01T12:00:00Z",
        "departureAirport": "JFK",
        "arrivalAirport": "LAX"
    }
}

```

2.Para recuperar una reserva existente por su ID, use la ruta GET "/api/reservations/{id}". Deberá enviar una solicitud GET con el ID de la reserva en la URL. En Postman, seleccione el método GET y agregue la URL "http://localhost:8080/api/reservations/{id}", donde "{id}" es el ID de la reserva que desea recuperar. Haga clic en "Send" para enviar la solicitud.

3.Para eliminar una reserva existente por su ID, use la ruta DELETE "/api/reservations/{id}". Deberá enviar una solicitud DELETE con el ID de la reserva en la URL. En Postman, seleccione el método DELETE y agregue la URL "http://localhost:8080/api/reservations/{id}", donde "{id}" es el ID de la reserva que desea eliminar. Haga clic en "Send" para enviar la solicitud.
