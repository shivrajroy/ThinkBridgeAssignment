package TestScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


public class DriverReturn {
	
	/********************************************************************************************************
	 ** Function Description : Function is used to return the driver as selected by user in the excel sheet
	 * displayed Input Parameters : String - browserName, String - URL
	 * Value Output Parameters : WebDriver - driver
	 ********************************************************************************************************/
 		static WebDriver createInstance(String browserName,String URL) throws Throwable {
		WebDriver driver = null;
		
		if(browserName.toLowerCase().contains("chrome")){
			System.setProperty("webdriver.chrome.driver", "../ThinkBrigdeAssignment/src/Utils/chromedriver.exe");
			//DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			//capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			//driver = new ChromeDriver(capabilities);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(URL);
			return driver;
		}
		if(browserName.toLowerCase().contains("edge")){
			System.setProperty("webdriver.edge.driver", "../ThinkBrigdeAssignment/src/Utils/msedgedriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.edge();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new EdgeDriver(capabilities);
			driver.manage().window().maximize();
			driver.get(URL);
			return driver;
		}
		return driver;
   }
 

}
