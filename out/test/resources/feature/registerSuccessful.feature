Feature: Registro Usuario
  como usuario del sistema
  quiero registrarme
  para usar los servicios del sistema

  Scenario: registro existoso
    Given que el usuario se encuentra en el sitio web
    When el usuario envie la solicitud de registro con el correo "eve.holt@reqres.in" y la contraseña "pistol"
    Then el sistema debe mostrar respuesta exitosa con un token de respuesta

  Scenario: Registro no exitoso
    Given que el usuario no permitido del sitio web intenta registrarse
    When el usuario realiza la solicitud con el correo "clau.fer@reqres.in" y la contraseña "lhssysgge"
    Then el sistema debe mostrar solicitud fallida y un mensaje de error