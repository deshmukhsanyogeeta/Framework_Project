package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AccountRegistrationPage;
import pageObject.BaseClass;
import pageObject.HomePage;

public class Tc001_AccountRegistrationTest extends BaseClass
{

	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		
		logger.info("*** starting Tc001_AccountRegistrationTest ***");  //Log4j
		
		try {
		//home page
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("** clicking on MyAccount **");
		hp.clickRegistration();
		logger.info("** clicking on Registration link **");
		
		//registration page 
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		logger.info("Providing customer details..");
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString() + "@gmail.com");
		regpage.setTelephone(randomeNumber());
		
		String password=randomeAlphaNumeric();
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating expected massage");
		String confmsg=regpage.getConfirmationMsg();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			logger.error("Test faild..");
			logger.debug("Debug logs..");
			Assert.fail();
		}
		
		logger.info("*** finished Tc001_AccountRegistrationTest ***");  //Log4j
		
		}
	

	
	
	
}
