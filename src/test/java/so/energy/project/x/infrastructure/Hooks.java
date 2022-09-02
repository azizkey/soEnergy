package so.energy.project.x.infrastructure;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Data;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

@Data
public class Hooks {
    private static WebDriver driver;
    private final String browser;

    public Hooks() {
        this.browser = System.getProperty("browser", "chrome-headless");
    }

    public WebDriver get() {
        return Hooks.driver;
    }

    @Before
    public void init() {
        switch (browser) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("['start-maximized']");

                Hooks.driver = new ChromeDriver(chromeOptions);
            }
            case "chrome-headless" -> {  // invisible chrome, you will not gonna see chrome opening at the background, it is not getting  memory or RAM as regular chrome
                WebDriverManager.chromedriver().setup();
                Hooks.driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                Hooks.driver = new FirefoxDriver();
                Hooks.driver.manage().window().maximize();
            }
            case "firefox-headless" -> {  // invisible chrome, you will not gonna see chrome opening at the background,
                WebDriverManager.firefoxdriver().setup();
                Hooks.driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
            }
            case "ie" -> {
                if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                    throw new WebDriverException("Your OS doesn't support Internet Explorer");
                WebDriverManager.iedriver().setup();
                Hooks.driver = new InternetExplorerDriver();
            }
            case "edge" -> {
                if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                    throw new WebDriverException("Your OS doesn't support Edge");
                WebDriverManager.edgedriver().setup();
                Hooks.driver = new EdgeDriver();
            }
            case "safari" -> {
                if (!System.getProperty("os.name").toLowerCase().contains("mac"))
                    throw new WebDriverException("Your OS doesn't support Safari");
                WebDriverManager.getInstance(SafariDriver.class).setup();
                Hooks.driver = new SafariDriver();
            }
            default -> {
                Hooks.driver = null;
            }
        }

        if (Hooks.driver != null) Hooks.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @After
    public void quitDriver(Scenario scenario) throws InterruptedException {
        if (scenario.isFailed()) {
            saveScreenshotsForScenario(scenario);
        }

        Hooks.driver.quit();
    }

    private void saveScreenshotsForScenario(final Scenario scenario) {
        final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "failure-%s.png".formatted(scenario.getName()));
    }
}
