package nl.postnl.pom.base;

import nl.postnl.pom.pages.StorePage;
import nl.postnl.pom.utils.ConfigLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,Duration.ofSeconds(15));
    }

    public void load(String endPoint){
        driver.get(ConfigLoader.getInstance().getBaseUrl() + endPoint);
    }

    public void waitForElementToDisappear(By overlay){
        List<WebElement> overlays = driver.findElements(overlay);
        System.out.println("Overlays Size " + overlays.size());
        if(overlays.size()>0){
            wait.until(ExpectedConditions
                    .invisibilityOfAllElements(overlays)
            );
            System.out.println("Overlays Invisible");
        }else{
            System.out.println("Overlays Not Found");
        }
    }

    public WebElement waitForElementToBeLocated(By element){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public WebElement waitForElementToBeClickable(By element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
//    public StorePage waitForPageToBeLoaded(String urlContains){
//        wait.until(ExpectedConditions.urlContains(urlContains));
//        return null;
//    }

}
