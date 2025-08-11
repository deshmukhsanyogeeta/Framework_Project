package pageObject;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	ChromeDriver driver;
	
	public BasePage(ChromeDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
}
