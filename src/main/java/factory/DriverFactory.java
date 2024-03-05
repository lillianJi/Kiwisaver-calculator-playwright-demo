package factory;

import com.microsoft.playwright.*;
import utils.ConfigReader;

public class DriverFactory {
    public Browser browser;
    public static BrowserContext context;
    public static Page page;

    //For Parallel execution
    public static ThreadLocal<Page> threadLocalDriver = new ThreadLocal<>();
    public static ThreadLocal<BrowserContext> threadLocalContext = new ThreadLocal<>();

    /**
     * Launches Browser
     */
    public Page initDriver(String browserName) {
        BrowserType browserType = null;
        boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));
        switch (browserName) {
            case "firefox":
                browserType = Playwright.create().firefox();
                browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            case "chrome":
                browserType = Playwright.create().chromium();
                browser = browserType.launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headless));
                break;
            case "webkit":
                browserType = Playwright.create().webkit();
                browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
        }
        if (browserType == null) throw new IllegalArgumentException("Could not Launch Browser for type" + browserType);
        context = browser.newContext();
        //Below line is used to start the trace file
        context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(false));
        page = context.newPage();
        threadLocalDriver.set(page);
        threadLocalContext.set(context);
        return page;
    }

    /**
     *    Return Initialized Thread Local Driver
      */
    public static synchronized Page getPage() {
        return threadLocalDriver.get();
    }

    /**
     *  Return Initialized Thread Local Context
      */

    public static synchronized BrowserContext getContext() {
        return threadLocalContext.get();
    }

}
