package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage
{
      public MyAccountPage(ChromeDriver driver)
      {
    	  super(driver);
      }
      
      @FindBy(xpath="//h2[text()='My Account']") WebElement msgHeading; 
      @FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement lnkLogout; // added in step 6 [data driven testing]
      
      //Actions
      public boolean isMyAccountPageExists()
      {
    	  try {
    	  return (msgHeading.isDisplayed());
    	  }
    	  catch(Exception e)
    	  {
    		  return false;
    	  }
      }
      
      public void linkLogout()
      {
    	  lnkLogout.click();
      }
}
