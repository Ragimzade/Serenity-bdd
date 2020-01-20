package browser;

import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;

import static browser.Browser.getDriver;


public class DriverManager implements DriverSource {

    @Override
    public WebDriver newDriver() {
        return getDriver();
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }
}

