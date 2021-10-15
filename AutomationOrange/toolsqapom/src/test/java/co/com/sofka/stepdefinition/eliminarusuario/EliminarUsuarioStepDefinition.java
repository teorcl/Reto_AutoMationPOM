package co.com.sofka.stepdefinition.eliminarusuario;

import co.com.sofka.model.panelLogin.PanelLoginModel;
import co.com.sofka.page.panelLogin.PanelLogin;
import co.com.sofka.stepdefinition.panelLogin.PanelLoginStepDefinition;
import co.com.sofka.stepdefinition.setup.WebUI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static co.com.sofka.util.Seconds.TEN_SECONDS;


public class EliminarUsuarioStepDefinition extends WebUI {

    private static final Logger LOGGER = Logger.getLogger(PanelLoginStepDefinition.class);
    private PanelLoginModel panelLoginModel;
    private PanelLogin pLogin;

    private static final String ASSERTION_EXCEPTION_MESSAGE = "Los valores suministrados no son los esperados.";

    /***=======================================================================================================*/
    //=========================================================================================================
    @Given("que el admin se encuentra autenticado en el sitio de orangeHRM")
    public void queElAdminSeEncuentraAutenticadoEnElSitioDeOrangeHRM() {
        try{
            generalSetUp();
            dataCorrectConfiguration();
            pLogin = new PanelLogin(driver, panelLoginModel,TEN_SECONDS.getValue());
            pLogin.llenarLogin();
        } catch (Exception exception){
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }

    @When("el admin quiere eliminar a un usuario")
    public void elAdminQuiereEliminarAUnUsuario() {
        try {
            pLogin.userToDelete();
        } catch (Exception exception){
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }

    @Then("el sistema deberia eliminarlo de la tabla.")
    public void elSistemaDeberiaEliminarloDeLaTabla() {
        try {
            Assertions.assertEquals(
                    expected(),
                    pLogin.getAssertionTable(),
                    ASSERTION_EXCEPTION_MESSAGE
            );

            quitDriver();
        } catch (Exception exception){
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }
    //=========================================================================================================
    /**========================================================================================================**/


    private void dataCorrectConfiguration(){
        panelLoginModel = new PanelLoginModel();
        panelLoginModel.setUser("Admin");
        panelLoginModel.setPassword("admin123");
    }

    private String expected(){
        return panelLoginModel.getEmployeeName().getValue();
    }

}
