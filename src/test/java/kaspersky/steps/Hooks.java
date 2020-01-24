package kaspersky.steps;

import browser.Browser;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
    @Before
    public void before() {
        Browser.getDriver();
    }

    @After
    public void after() {
        Browser.quit();
    }
}
