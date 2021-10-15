
Feature: Como Empleado administrativo
  necesito eliminar un usuario de la plataforma de  orangeHRM
  que haya terminado su contrato


  Scenario: eliminar un usuario de la plataforma.
    Given que el admin se encuentra autenticado en el sitio de orangeHRM
    When el admin quiere eliminar a un usuario
    Then el sistema deberia eliminarlo de la tabla.