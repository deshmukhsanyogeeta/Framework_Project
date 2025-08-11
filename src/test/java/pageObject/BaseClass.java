package pageObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; // LogManager package
import org.apache.logging.log4j.Logger; //Logger package
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static ChromeDriver driver;
	public Logger logger;  // Log4j2
	public Properties p;
	
	//@SuppressWarnings("deprecation")
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setUp(String os,String br) throws IOException
	{
		//Loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		
		
		//Loggers
		logger=LogManager.getLogger(this.getClass());
		
		// Running project on Grid by remote browser [Step 10]
		/*if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capibilities=new DesiredCapabilities();
			
			//os setting
			if(os.equalsIgnoreCase("windows"))
			{
				capibilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capibilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No maching os");
				return;
			}
			
			// browser setting
			switch(br.toLowerCase())
			{
			case "chrome":capibilities.setBrowserName("chrome"); break;
			case "edge": capibilities.setBrowserName("MicrosoftEdge"); break;
			default: System.out.println("No matching browser"); return;
			}
			
			driver=(ChromeDriver) new RemoteWebDriver(new URL("http://192.168.0.3:4444/wd/hub"),capibilities);
		}
		
		// running program on local machine
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br)
			{
			case "chrome":driver=new ChromeDriver();break;
			//case "edge":driver=new EdgeDriver();break;
			default: System.out.println("Invalid browser.."); return;
			}
		}
		*/
		
		
		switch(br)
		{
		case "chrome":driver=new ChromeDriver();break;
		//case "edge":driver=new EdgeDriver();break;
		default: System.out.println("Invalid browser.."); return;
		}
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL")); // reading URL from property file 
		driver.manage().window().maximize();
	}
	
	public void closeBrowser()
	{
		driver.quit();
	}
	
	public String randomeString()
	{
		@SuppressWarnings("deprecation")
		String generatedstring=RandomStringUtils.randomAlphabetic(10);
		return generatedstring;
	}
	
	public String randomeNumber()
	{
		@SuppressWarnings("deprecation")
		String generatednumber=RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	
	@SuppressWarnings("deprecation")
	public String randomeAlphaNumeric()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		String generatednumber=RandomStringUtils.randomNumeric(5);
		return (generatedstring+"@"+generatednumber);
	}
	
	public String captureScreen(String tname) throws IOException {
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		TakesScreenshot takesScreenshot= driver;
		File sourceFile= takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath= System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timestamp + ".png";
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}
