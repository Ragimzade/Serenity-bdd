package kaspersky.pages;

import kaspersky.enums.NavigationMenuTabs;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class LoggedInMainPage extends BasePage {

    private static final String NAVIGATION_MENU = "//ul[@class='main-menu js-priority-menu is-priority']//li//a[contains(@href,'%s')]";

    @FindBy(xpath = "//button[@class='w-notifications__bell js-notifications']")
    private WebElementFacade notificationButton;

    @FindBy(xpath = "//span[@class=\"menu-item__text a-direction-ltr\"]")
    private WebElementFacade accountButton;

    @FindBy(xpath = "(//span[@class='w-accountMenu__text'])[3]")
    private WebElementFacade signOutButton;

    public void goToDownloadPage() {
        navigate(NavigationMenuTabs.Downloads);
    }

    private void navigate(NavigationMenuTabs tab) {
        $(String.format(NAVIGATION_MENU, tab)).click();
    }

    public void logOut() {
       accountButton.click();
       signOutButton.click();
    }

    public void assertPageIsOpened() {
      assertPageIsOpened(notificationButton);
    }
}
