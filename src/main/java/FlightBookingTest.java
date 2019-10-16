import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@SuppressWarnings("restriction")
public class FlightBookingTest {
	
    @FindBy(id="OneWay") 
    WebElement radio_oneWay;
    
    @FindBy(id= "FromTag")
    WebElement text_OriginCity;
    
    @FindBy(id="ToTag")
    WebElement text_DestinationCity;
    
    @FindBy(id="ui-id-1")
    WebElement auto_searchResultOrigin;
    
    @FindBy(id="ui-id-2")
    WebElement auto_searchResultDestination;
    
    @FindBy(linkText = "All flights")
    WebElement text_AllFlights;

    @FindBy(id="DepartDate")
    WebElement text_DepartDate;
    
    @FindBy(xpath="//*[@id=\"ui-datepicker-div\"]/div[2]/table/tbody/tr[2]/td[4]/a")
    WebElement dateVal;
    
    @FindBy(id="SearchBtn")
    WebElement btn_Search;
    
    CommonFunctions com = new CommonFunctions();

    @Test
    public void testThatResultsAppearForAOneWayJourney() {

    	WebDriver driver = com.getDriver();
        com.setDriverPath();
        com.disableChromeLocationAccess();
        
        driver.get("https://www.cleartrip.com/");
        driver.manage().window().maximize();
        PageFactory.initElements(driver, this);
        
        com.explicitWaitElementVisibility(radio_oneWay);
        radio_oneWay.click();

        text_OriginCity.clear();
        text_OriginCity.sendKeys("Bangalore");

        //wait for the auto complete options to appear for the origin
        com.explicitWaitElementVisibility(auto_searchResultOrigin);
        List<WebElement> originOptions = auto_searchResultOrigin.findElements(By.tagName("li"));
        originOptions.get(0).click();

        text_DestinationCity.clear();
        text_DestinationCity.sendKeys("Delhi");

        //wait for the auto complete options to appear for the destination
        com.explicitWaitElementVisibility(auto_searchResultDestination);
        
        //select the first item from the destination auto complete list
        List<WebElement> destinationOptions = auto_searchResultDestination.findElements(By.tagName("li"));
        destinationOptions.get(0).click();
        
        com.explicitWaitElementVisibility(text_DepartDate);
        text_DepartDate.click();
        com.explicitWaitElementVisibility(dateVal);
        dateVal.click();

        //all fields filled in. Now click on search
        btn_Search.click();

        com.explicitWaitElementVisibility(text_AllFlights);
        //verify that result appears for the provided journey search
        Assert.assertTrue(text_AllFlights.getText().contains("All flights"));

        //close the browser
        driver.quit();

    }


}
