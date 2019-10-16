import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.javafx.PlatformUtil;

@SuppressWarnings("restriction")
public class CommonFunctions {
	
	WebDriver driver = new ChromeDriver();
	DesiredCapabilities caps = new DesiredCapabilities();
	ChromeOptions options = new ChromeOptions();
	
	//getDriver function
	public WebDriver getDriver() {
		return driver;
	}
	
	//explicitly wait function
	public void explicitWaitElementVisibility(WebElement element) {
    	WebDriverWait wait = new WebDriverWait(driver, 10000);
    	wait.until(ExpectedConditions.visibilityOf(element));
    }
    
	//Frame Switch
    public void switchToFrame(WebElement frame) {
    	driver.switchTo().frame(frame);
    }
    
    //setting driver path
    public void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }

    //check if element present
    public boolean isElementPresent(WebElement text_searchSummary) {
        try {
            driver.findElement((By) text_searchSummary);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    
    //disable geolocation access 
    public void disableChromeLocationAccess() {

    	Map<String, Object> prefs = new HashMap<String, Object>();
    	prefs.put("profile.default_content_settings.popups", 0);
    	prefs.put("profile.default_content_setting_values.notifications", 2);

    	options.setExperimentalOption("prefs", prefs);
    	caps.setCapability(ChromeOptions.CAPABILITY, options);
    }

    //Select from dropdown by Value
    public void selectDropDownByValue(WebElement dropDownObject, String valueToSelect) {
    	Select travellers = new Select(dropDownObject);
    	travellers.selectByValue(valueToSelect);
    	
    	
    }
    
    
}
