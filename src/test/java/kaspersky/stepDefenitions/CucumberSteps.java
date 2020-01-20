package kaspersky.stepDefenitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import kaspersky.steps.CommonSteps;
import net.thucydides.core.annotations.Steps;
import org.apache.commons.lang3.time.DateUtils;
import utils.TestData;

import javax.mail.MessagingException;
import java.util.Date;

public class CucumberSteps {

    @Steps
    CommonSteps commonSteps;

    private static Date sentDate;

    @Given("User is on main page")
    public void user_is_on_main_page() {
        sentDate = DateUtils.addMinutes(new Date(), -1);
        commonSteps.openMainPage();
    }

    @And("he logs in")
    public void he_logs_in() {
        commonSteps.doLogin(TestData.getValue("kasperskyLogin2"), TestData.getValue("kasperskyPassword"));
    }

    @When("he goes to download page")
    public void he_goes_to_download_page() {
        commonSteps.goToDownloadPage();
    }

    @When("he goes to (.*) Tab")
    public void he_goes_to_Tab(String os) {
        commonSteps.goToSelectedTab(os);
    }

    @Then("he sees that product (.*) has description (.*)")
    public void he_sees_that_chosen_product_has_correct_description(String product, String description) {
        commonSteps.verifyProductDescription(product, description);
    }

    @When("he send (.*) app to himself")
    public void he_send_app_to_himself(String product) {
        commonSteps.sendAppToMyself(product);
    }

    @Then("^he receives email with subject (.*)$")
    public void he_receives_email_with_subject(String subject) throws MessagingException {
        commonSteps.verifyMessageSubject(subject, sentDate);
    }

    @Then("he sees message (.*) contains correct link (.*)")
    public void he_sees_message_contains_correct_link(String subject, String correctLink) {
        commonSteps.verifyMessageContainsCorrectLink(subject, sentDate, correctLink);
    }

    @Then("he logs out")
    public void he_logs_out() {
        commonSteps.logOut();
    }


}
