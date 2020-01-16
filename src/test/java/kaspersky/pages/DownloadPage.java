package kaspersky.pages;

import base.IBaseEntity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;

public class DownloadPage extends BasePage {

    private static final String OS_TAB_BUTTONS = "//div[@class='u-osTile__title' and contains(.,'{0}')]";

    @FindBy(xpath = "(//iframe[@title='recaptcha challenge'])[2]")
    private WebElementFacade iframeBlock;

    @FindBy(xpath = "//h2[@data-at-selector='downloadBlockTrialAppsTitle']")
    private WebElementFacade downloadHeader;

    @FindBy(xpath = "//button[@data-at-selector='installerSendSelfBtn']")
    private WebElementFacade sendButton;

    @FindBy(xpath = "//button[contains(text(),'OK')]")
    private WebElementFacade okButton;

    @FindBy(xpath = "(//iframe[@title='recaptcha challenge'])[2]")
    private WebElementFacade iframe;

    @FindBy(xpath = "//*[@id='portal-menu']//span[text()='Письма']")
    private WebElementFacade lettersToolbarButton;

    private static final int TIMEOUT_IN_SECONDS = 120;
    private static final int DELAY_IN_MILLIS = 1000;


    public void sendAppToMySelf(String product) {
        sendProductToMySelf(product);
        sendButton.click();
        clickOkButtonIfPresent();
        waitForCaptchaValidation();
    }

    private void clickOkButtonIfPresent() {
        if (okButton.isPresent()) {
            okButton.click();
        }
    }

    public void goToSelectedOsTab(String os) {
        findBy(OS_TAB_BUTTONS, os).click();
    }

    private void waitForCaptchaValidation() {
        if (iframeBlock.isPresent()) {
            log.info("waiting for absent of iframe");
            IBaseEntity.getDelay(TIMEOUT_IN_SECONDS, DELAY_IN_MILLIS).until(() -> !iframeBlock.isVisible());
            sendButton.click();
            clickOkButtonIfPresent();
        }
    }

    private WebElementFacade getProductBlockByName(String text) {
        List<WebElementFacade> webElements = findAll(
                By.xpath("//div[@class='w-downloadProgramCard a-padding-x-sm']//child::div/div/div/div[2]"));
        return webElements
                .stream()
                .filter(webElement -> Objects.equals(webElement.getText(), text))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No WebElement found containing " + text));
    }

    private String getProductDescription(String productName) {
        WebElement product = getProductBlockByName(productName)
                .find((By.xpath("./ancestor::div[@class='w-downloadProgramCard a-padding-x-sm']//div[@data-at-selector='serviceShortDescription']")));
        return product.getText();
    }

    public void sendProductToMySelf(String productName) {
        WebElement product = getProductBlockByName(productName).find(
                (By.xpath(".//ancestor::div[@class='w-downloadProgramCard a-padding-x-sm']//button/div[2]")));
        product.click();
    }

    public boolean isProductHasCorrectDescription(String productName, String correctDescription) {
        String description = getProductDescription(productName);
        if (description.equals(correctDescription)) {
            log.info("Descriptions match");
            return true;
        } else {
            log.info(String.format("Found description: '%s'; expected description: '%s' ", description, correctDescription));
            return false;
        }
    }

    public void assertPageIsOpened() {
        assertPageIsOpened(downloadHeader);
    }
}
