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
    
    CommonFunctions com = new  CommonFunctions();
    
    
    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
    	WebDriver driver = com.getDriver();

        com.setDriverPath();
        com.disableChromeLocationAccess();
        
        driver.get("https://www.cleartrip.com/");
        driver.manage().window().maximize();
        PageFactory.initElements(driver, this);
        
        link_YourTrips.click();
        com.explicitWaitElementVisibility(btn_SignIn);
        btn_SignIn.click();
        com.explicitWaitElementVisibility(frame_signInPopUp);
        com.switchToFrame(frame_signInPopUp);
        com.explicitWaitElementVisibility(btn_FrameSignIn);
        btn_FrameSignIn.click();

        String errors = msg_ErrorSignIn.getText();
        Assert.assertTrue(errors.contains("There were errors in your submission"));
        driver.quit();
    }
    

}
