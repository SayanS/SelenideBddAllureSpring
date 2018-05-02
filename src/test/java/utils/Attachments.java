package utils;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.junit.ClassRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
@Component
public class Attachments {

    public String getSnapshotFolderPath() {
        File currentDirFile = new File("Screenshots");
        String path = currentDirFile.getAbsolutePath();
        return path;
    }

    public String takeScreenShot() throws IOException {
        System.out.println("Taking snapshot");
        File scrFile = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar cal = Calendar.getInstance();
        String snapshotFileName = "screenshot" + dateFormat.format(cal.getTime()) + ".png";
        String pathToSnapshot = getSnapshotFolderPath() + "/" + snapshotFileName;

        FileUtils.copyFile(scrFile, new File(pathToSnapshot));
        return snapshotFileName;

    }

    public byte[] embedScreenshotInReport() throws IOException {
        final byte[] screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
        return screenshot;
    }

    @ClassRule
    public TestWatcher screenshotOnFailure = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            makeScreenshotOnFailure();
        }

        @Attachment("Screenshot on failure")
        public byte[] makeScreenshotOnFailure() {
            System.out.println("Taking screenshot");
            return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
        }
    };

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("Scenario: " + scenario + ", failed taking snapshot");

        if (scenario.isFailed()) {
            // Take a screenshot if for failed scenario
            byte[] screenshot = null;
            try {
                screenshot = embedScreenshotInReport();
            } catch (IOException e) {
                e.printStackTrace();
            }
            scenario.embed(screenshot, "image/png");
        }
    }

    public void attachSnapshotToReport() {
        System.out.println("Add snapshot to report");
        Path content = null;
        String snapshotFileName = null;
        try {
            snapshotFileName = takeScreenShot();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        content = Paths.get(getSnapshotFolderPath() + "/" + snapshotFileName);
        try (InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment(snapshotFileName, is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
