import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.javafx.PlatformUtil;

@SuppressWarnings("restriction")
public class SignInTest {
	

    @FindBy(id="modal_window") 
    WebElement frame_signInPopUp;
    
    @FindBy(linkText= "Your trips")
    WebElement link_YourTrips;
    
    @FindBy(id="SignIn")
    WebElement btn_SignIn;
    
    @FindBy(id="signInButton")
    WebElement btn_FrameSignIn;
    
    @FindBy(id="errors1")
    WebElement msg_ErrorSignIn;
    
    WebDriver driver = new ChromeDriver();
    
    
    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

        setDriverPath();
        
        driver.get("https://www.cleartrip.com/");
        driver.manage().window().maximize();
        PageFactory.initElements(driver, this);
        
        link_YourTrips.click();
        explicitWaitElementVisibility(btn_SignIn);
        btn_SignIn.click();
        explicitWaitElementVisibility(frame_signInPopUp);
        switchToFrame(frame_signInPopUp);
        explicitWaitElementVisibility(btn_FrameSignIn);
        btn_FrameSignIn.click();

        String errors = msg_ErrorSignIn.getText();
        Assert.assertTrue(errors.contains("There were errors in your submission"));
        driver.quit();
    }
    

	private void explicitWaitElementVisibility(WebElement element) {
    	WebDriverWait wait = new WebDriverWait(driver, 10000);
    	wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    private void switchToFrame(WebElement frame) {
    	driver.switchTo().frame(frame);
    }

	private void setDriverPath() {
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


}
