package nl.postnl.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import nl.postnl.pom.constants.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    public WebDriver initializeDriver(String browser){
        WebDriver driver;
        switch(BrowserType.valueOf(browser)){
            case CHROME:
                WebDriverManager.chromedriver().cachePath("driver").setup();
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().cachePath("driver").setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalStateException("Invalid browser name: "+ browser);
        }
        driver.manage().window().maximize();
        return driver;
    }
}
