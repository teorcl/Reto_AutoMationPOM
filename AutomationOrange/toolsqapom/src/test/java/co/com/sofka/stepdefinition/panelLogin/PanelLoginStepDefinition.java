package co.com.sofka.stepdefinition.panelLogin;

import co.com.sofka.model.panelLogin.PanelLoginModel;
import co.com.sofka.page.panelLogin.PanelLogin;
import co.com.sofka.stepdefinition.setup.WebUI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;


import static co.com.sofka.util.Seconds.TEN_SECONDS;
import static co.com.sofka.util.Numbers.*;

public class PanelLoginStepDefinition extends WebUI {
    private static final Logger LOGGER = Logger.getLogger(PanelLoginStepDefinition.class);
    private PanelLoginModel panelLoginModel;
    private PanelLogin pLogin;

    private static final String ASSERTION_EXCEPTION_MESSAGE = "Los valores suministrados no son los esperados.";

    //=========================================================================//
    //=========================================================================//
    /**+++++++++++Credenciales-Correctas+++++++++++++++**/
    @Given("quiero iniciar sesion como admin")
    public void quieroIniciarSesionComoAdmin() {
        try {
            generalSetUp();
        } catch (Exception exception) {
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }

    @When("cuando ingreso las credenciales correctas")
    public void cuandoIngresoLasCredencialesCorrectas() {
        try {
            selectScenario(NUMBER0.getValue());
            pLogin = new PanelLogin(driver, panelLoginModel, TEN_SECONDS.getValue());
            pLogin.llenarLogin();
        } catch (Exception exception) {
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }

    @Then("debería poder iniciar la sesion.")
    public void deberíaPoderIniciarLaSesion() {
        try {
            Assertions.assertTrue(
                    pLogin.isDisplayed(pLogin.getAssertionLoginExitoso()),
                    ASSERTION_EXCEPTION_MESSAGE
            );
            quitDriver();
        } catch (Exception exception) {
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }

    //=========================================================================//
    //=========================================================================//
    /**+++++++++++-------------Contraseña-incorrectas---------+++++++++++++++**/
    @When("ingreso una password incorrecta")
    public void ingresoUnaPasswordIncorrecta() {
        try {
            selectScenario(NUMBER1.getValue());
            pLogin = new PanelLogin(driver, panelLoginModel, TEN_SECONDS.getValue());
            pLogin.llenarLogin();
        } catch (Exception exception) {
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }

    @Then("debería mostrar un mensaje credenciales invalidas")
    public void deberíaMostrarUnMensajeCredencialesInvalidas() {
        try {
            Assertions.assertEquals(
                    expected(NUMBER1.getValue()),
                    pLogin.getAssertionLoginFail(),
                    ASSERTION_EXCEPTION_MESSAGE
            );
            quitDriver();
        } catch (Exception exception) {
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }

    //=========================================================================//
    //=========================================================================//
    /**+++++++++++-------------Usuario-no-valido---------+++++++++++++++**/
    @When("ingreso un usuario no valido")
    public void ingresoUnUsuarioNoValido() {
        try {
            selectScenario(NUMBER2.getValue());
            pLogin = new PanelLogin(driver, panelLoginModel, TEN_SECONDS.getValue());
            pLogin.llenarLogin();
        } catch (Exception exception) {
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }

    @Then("deberia fallar el login por usuario.")
    public void deberiaFallarElLoginPorUsuario() {
        try {
            Assertions.assertEquals(
                    expected(NUMBER2.getValue()),
                    pLogin.getAssertionLoginFail(),
                    ASSERTION_EXCEPTION_MESSAGE
            );
            quitDriver();
        } catch (Exception exception) {
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }


    //=========================================================================//
    //=========================================================================//
    /**+++++++++++-------------sin-credenciales---------+++++++++++++++**/
    @When("no se ingresan credenciales")
    public void noSeIngresanCredenciales() {
        try {
            selectScenario(NUMBER3.getValue());
            pLogin = new PanelLogin(driver, panelLoginModel, TEN_SECONDS.getValue());
            pLogin.llenarLogin();
        } catch (Exception exception) {
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }

    @Then("deberia fallar el login por campos vacios")
    public void deberiaFallarElLoginPorCamposVacios() {
        try {
            Assertions.assertEquals(
                    expected(NUMBER3.getValue()),
                    pLogin.getAssertionLoginFail(),
                    ASSERTION_EXCEPTION_MESSAGE
            );
            quitDriver();
        } catch (Exception exception) {
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }


    private void selectScenario(int select) {
        switch (select) {
            case 0:
                panelLoginModel = new PanelLoginModel();
                panelLoginModel.setUser("Admin");
                panelLoginModel.setPassword("admin123");
                break;
            case 1:
                panelLoginModel = new PanelLoginModel();
                panelLoginModel.setUser("Admin");
                panelLoginModel.setPassword("ad33123");
                break;
            case 2:
                panelLoginModel = new PanelLoginModel();
                panelLoginModel.setUser("aDministrador");
                panelLoginModel.setPassword("admin123");
                break;
            case 3:
                panelLoginModel = new PanelLoginModel();
                panelLoginModel.setUser("");
                panelLoginModel.setPassword("");
                break;


        }

    }


    private String expected(int casos) {
        switch (casos) {
            case 0:
                return "Welcome Paul";
            case 1:
                return "Invalid credentials";

            case 2:
                return "Invalid credentials";

            case 3:
                return "Username cannot be empty";

            default:
                return "Default";
        }
    }

}
