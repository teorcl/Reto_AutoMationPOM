package co.com.sofka.runner.panelLogin;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        publish = true,
        plugin = {"pretty", "html:target/cucumber-report/cucumber.html"},
        features = {"src/test/resources/features/webui/panel_login/panelLogin.feature"},
        glue = {"co.com.sofka.stepdefinition.panelLogin"},
        tags = "not @ignore"
)
public class PanelLoginCucumberTest {
}


