package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    private static final Logger log =
            LogManager.getLogger(ScreenshotUtils.class);

    public static String takeScreenshot(WebDriver driver, String testName) {

        if (driver == null) {
            log.error("Driver is null. Screenshot cannot be captured for test: {}", testName);
            return null;
        }

        String timestamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String screenshotPath =
                "test-output/screenshots/" + testName + "_" + timestamp + ".png";

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(screenshotPath);

            FileUtils.copyFile(source, destination);

            log.info("Screenshot captured successfully: {}", screenshotPath);

        } catch (IOException e) {
            log.error("Failed to capture screenshot for test: {}", testName, e);
        }

        return screenshotPath;
    }
}
