Feature: Inicio de sesión
  Yo como usuario admin quiero
  iniciar sesión en el sitio de OrangeHRM

  Background:
    Given quiero iniciar sesion como admin

  Scenario: Inicio de sesion exitoso
    When cuando ingreso las credenciales correctas
    Then debería poder iniciar la sesion.

  Scenario: Inicio de sesion con el password invalido
    When ingreso una password incorrecta
    Then debería mostrar un mensaje credenciales invalidas

  Scenario: Inicio  de sesión donde el usuario es inválido.
    When ingreso un usuario no valido
    Then deberia fallar el login por usuario.

  Scenario: Inicio  de sesión donde no se introducen credenciales.
    When no se ingresan credenciales
    Then deberia fallar el login por campos vacios