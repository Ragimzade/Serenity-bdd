package kaspersky.tests;


import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)

@CucumberOptions(
        plugin = {"pretty"},
        features = {"src/test/resources/send_an_email.feature"}
)
public class CucumberTestSuite {

}