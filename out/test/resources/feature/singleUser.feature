Feature: Usuarios
  Como administrador de la pagina web reqres
  necesito validar el listado de usuarios y un unico usuario
  para poder contactar los usuarios mas frecuentes



  Background:
    Given el administrador está en la pagina web de reqres y quiere enumerar la informacion de un usuario

  Scenario: unico usuario
    When el administrador ingresa a la base de datos y escoge el usuario "2"
    Then el sistema imprime en pantalla la respuesta exitosa y los datos del usuario

  Scenario: usuario inexistente
    When el administrador ingresa al sistema y escoge el usuario "15"
    Then el sistema debe mostrar en pantalla usuario no encontrado