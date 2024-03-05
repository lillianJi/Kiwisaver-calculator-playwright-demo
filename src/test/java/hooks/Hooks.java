package hooks;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ConfigReader;
import java.nio.file.Paths;

public class Hooks {
    public DriverFactory driverFactory;
    public Page page;

    @Before
    public void launchBrowser() {
        //Fetching browser value from config file
        String browserName = ConfigReader.getProperty("browser");
        driverFactory = new DriverFactory();
        // Passing browser name to launch the browser
        page = driverFactory.initDriver(browserName);
    }

    //After runs in reverse order so order=1 will run first
    @After(order = 0)
    public void quitBrowser() {
        page.close();
    }

    @After(order = 1)
    public void takeScreenshotAndTrace(Scenario scenario) {
        if (scenario.isFailed()) {
            //Replace all space in scenario name with underscore
            String screenshotName = scenario.getName().replaceAll("", "_");
            byte[] sourcePath = page.screenshot();
            //Attach screenshot to report if scenario fails
            scenario.attach(sourcePath, "image/png", screenshotName);
            DriverFactory.context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("target/" + screenshotName + ".zip")));
        }
    }


}
