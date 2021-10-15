package co.com.sofka.page.panelLogin;

import co.com.sofka.model.panelLogin.PanelLoginModel;
import co.com.sofka.page.common.CommonActionsOnPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class PanelLogin extends CommonActionsOnPages{
    private static final Logger LOGGER = Logger.getLogger(PanelLogin.class);
    private PanelLoginModel orangeLoginModel;
    private static final String MODEL_NULL_MESSAGE = "El modelo del formulario es nulo.";


    private final By user = By.id("txtUsername");
    private final By passLocator = By.id("txtPassword");
    private final By btnLogin = By.id("btnLogin");

    private final By adminMenuLocator = By.id("menu_admin_viewAdminModule");
    private final By searchEmployeeName = By.id("searchSystemUser_employeeName_empName");//Debo quitarlo
    private final By btnSearch = By.id("searchBtn");//Debo quitarlo
    private final By ohrmList_chkSelectRecord = By.xpath("//*[@id=\"ohrmList_chkSelectRecord_10\"]");
    private final By btnDeleteLocator = By.id("btnDelete");
    private final By dialogDeleteBtn = By.id("dialogDeleteBtn");//Confirmacion de eliminar


    //For Assertions test case.
    private final By assertionLoginFail = By.id("spanMessage");
    private final By assertionLoginExitoso = By.id("welcome");
    private final By assertionTable = By.xpath("//*[@id=\"resultTable\"]");



    public PanelLogin(WebDriver driver, PanelLoginModel orangeLogin) {
        super(driver);
        this.orangeLoginModel = orangeLogin;
    }

    public PanelLogin(WebDriver driver, PanelLoginModel orangeLogin, int secondsForExplicitWait) {
        super(driver, secondsForExplicitWait);
        if(orangeLogin == null)
            LOGGER.warn(MODEL_NULL_MESSAGE);
        this.orangeLoginModel= orangeLogin;
    }

    public void llenarLogin() throws IOException {
        try{
            scrollTo(user);
            withExplicitWaitClear(user);
            withExplicitWaitTypeInto(user,orangeLoginModel.getUser());

            scrollTo(passLocator);
            withExplicitWaitClear(passLocator);
            withExplicitWaitTypeInto(passLocator,orangeLoginModel.getPassword());

            scrollTo(btnLogin);
            withExplicitWaitClickOn(btnLogin);
        }
        catch (Exception exception){
            LOGGER.warn(exception.getMessage());
        }
    }


    public void userToDelete()throws IOException{
        try{
            scrollTo(adminMenuLocator);
            withExplicitWaitClickOn(adminMenuLocator);

            scrollTo(ohrmList_chkSelectRecord);
            withExplicitWaitClickOn(ohrmList_chkSelectRecord);

            scrollTo(btnDeleteLocator);
            withExplicitWaitClickOn(btnDeleteLocator);

            scrollTo(dialogDeleteBtn);
            withExplicitWaitClickOn(dialogDeleteBtn);

        } catch (Exception exception){
            LOGGER.warn(exception.getMessage());
        }
    }


    public By getAssertionLoginExitoso() {
        return assertionLoginExitoso;
    }

    public String getAssertionLoginFail() {
        return getText(assertionLoginFail).trim();
    }

    public String getAssertionTable(){
        return getText(assertionTable).trim();
    }
}
