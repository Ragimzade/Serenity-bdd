package kaspersky.tests;

import kaspersky.steps.CommonSteps;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    @Managed(uniqueSession = true)
    WebDriver driver;

    @Steps
    CommonSteps commonSteps;
}
