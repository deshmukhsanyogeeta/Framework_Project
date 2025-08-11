package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage
{
        
      public HomePage(ChromeDriver driver)
      {
    	  super(driver);
      }
      
   @FindBy(xpath="//span[normalize-space()='My Account']") WebElement linkMyaccount;
   @FindBy(xpath="//a[normalize-space()='Register']") WebElement linkRegistration;
   @FindBy(linkText="Login") WebElement linkLogin;
   
   public void clickMyAccount()
   {
	   linkMyaccount.click();
   }
   
   public void clickRegistration()
   {
	   linkRegistration.click();
   }
   
   public void clickLogin()
   {
	   linkLogin.click();
   }
   
   
	
}
