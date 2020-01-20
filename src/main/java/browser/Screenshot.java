package browser;



import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import base.IBaseEntity;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import utils.DateUtil;

public class Screenshot implements IBaseEntity {


    public static void takeScreenshot(WebDriver driver) {
        Path screenPath = Paths.get("screenshots_from_tests", DateUtil.getTimeStamp().concat(".png"));
        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screenshotPng = new File(screenPath.toString());
            FileUtils.copyFile(screenshotFile, screenshotPng);
            log.info(String.format("Saving screenshot '%s' ", screenshotPng.getName()));
        } catch (IOException ex) {
            log.error("There was a problem while trying to make screenshot ", ex);
        }
    }

}
