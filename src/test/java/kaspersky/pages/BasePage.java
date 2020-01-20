package kaspersky.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import base.IBaseEntity;


public class BasePage extends PageObject implements IBaseEntity {

    protected void assertPageIsOpened(WebElementFacade element) {
        if (element.waitUntilPresent().isPresent()) {
            log.info(String.format("Page '%s' is opened", getClass().getName()));
        } else {
            throw new AssertionError(element + " not found");
        }
    }
}
