import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest {
    CommonFunctions com = new CommonFunctions();

    @FindBy(linkText = "Hotels")
    WebElement hotelLink;

    @FindBy(id = "Tags")
    WebElement localityTextBox;
    
    @FindBy(id="ui-id-1")
    WebElement auto_searchHotelSelect;
    
    @FindBy(id = "SearchHotelsButton")
    WebElement searchButton;
    
    @FindBy(id="CheckInDate")
    WebElement checkInDate;
    
    @FindBy(id="CheckOutDate")
    WebElement checkOutDate;

    @FindBy(xpath="//*[@id=\"ui-datepicker-div\"]/div[2]/table/tbody/tr[2]/td[4]/a")
    WebElement fromDateVal;
    
    @FindBy(xpath="//*[@id=\"ui-datepicker-div\"]/div[2]/table/tbody/tr[3]/td[4]/a")
    WebElement toDateVal;
    
    @FindBy(id = "travellersOnhome")
    WebElement travellerSelection;
    
    @FindBy(xpath="//*[@id=\"area\"]/section/div/div[2]/div[5]/section/div[1]/div/div[1]/span")
    WebElement searchResultLocation; 

    @Test
    public void shouldBeAbleToSearchForHotels() {
        com.setDriverPath();
        WebDriver driver= com.getDriver();
        PageFactory.initElements(driver, this);
        
        driver.get("https://www.cleartrip.com/");
        hotelLink.click();
        
        //Search Locality
        localityTextBox.sendKeys("Indiranagar");
        com.explicitWaitElementVisibility(auto_searchHotelSelect);
        auto_searchHotelSelect.click();
        
        //Select Checkin date
        checkInDate.click();
        fromDateVal.click();
        
        //Selecet checkOut date
        checkOutDate.click();
        toDateVal.click();

        //Select no of travellers & room
        com.explicitWaitElementVisibility(travellerSelection);
        com.selectDropDownByValue(travellerSelection, "2");
        
        //Search hotels
        searchButton.click();
        
        com.explicitWaitElementVisibility(searchResultLocation);
        searchResultLocation.getText().contains("Showing properties by distance");

        driver.quit();

    }

}
