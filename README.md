Arquitectura Microservicio (2023)

Herramientas y tecnologías utilizadas
• Java spring boot
• IDE de su preferencia
• Base de Datos Relacional
• Postman v9.13.2 (validador de API)

Json para Consumo de API's
*********** Clientes
{
    "clienteId": "CLI001",
    "nombre": "María López Test",
    "genero": "Femenino1",
    "edad": 281,
    "identificacion": "17234567903",
    "direccion": "Calle Falsa 4561",
    "telefono": "0998765431",
    "contrasenia": "12341",
    "estado": false
}

******** Cuentas
{
    "id": 7,
    "numeroCuenta": "1234567892",
    "tipoCuenta": "AHORROS",
    "saldoInicial": 5000.00,
    "clienteId": "CLI001"
}


******** Movimientos
{
    "id": 3,
  "numeroCuenta": "1234567893",
  "valor": -200.00,
  "tipoMovimiento": "RETIRO"
}  
