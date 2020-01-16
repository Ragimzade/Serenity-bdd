package kaspersky.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://my.kaspersky.com/")
public class MainPage extends BasePage {
    @FindBy(xpath = "//button[contains(.,'Sign in') and contains(@class,'btn')]")
    private WebElementFacade loginButton;

    @FindBy(xpath = "//button[contains(.,'Sign in') and contains(@class,'btn')]")
    private WebElementFacade login;

    @FindBy(css = "input.u-inputField__control[type='email']")
    private WebElementFacade emailField;

    @FindBy(xpath = "(//input[@type='password'])[1]")
    private WebElementFacade passwordField;

    @FindBy(xpath = "//button[@data-omniture-cta-name='Sign in']")
    private WebElementFacade signInButton;

    @FindBy(xpath = "//button[@class='w-notifications__bell js-notifications']")
    private WebElementFacade notificationButton;

    public void openLoginPopUp() {
        loginButton.waitUntilEnabled().waitUntilClickable().click();
    }

    public void login(String email, String password) {
        openLoginPopUp();
        emailField.type(email);
        passwordField.waitUntilClickable().type(password);
        signInButton.click();
    }

    public void assertPageIsOpened() {
        assertPageIsOpened(loginButton);
    }

    public boolean isSignedIn() {
        return notificationButton.isPresent();
    }

}
