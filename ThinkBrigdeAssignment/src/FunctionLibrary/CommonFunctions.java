package FunctionLibrary;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

public class CommonFunctions {
	static WebDriver driver;
	public CommonFunctions(WebDriver driver){
		
		this.driver	= driver;
	}
	
	
	/***************************************************************************************************
	** Function Description	: Function is used to wait for the page to load completely
	** Input Parameters		: None
	** Output Parameters	: None
	***************************************************************************************************/
	public static void fnWaitForPageToBeReady() 
	{
			
		try
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
		    //This loop will rotate for 400 times to check If page Is ready after every 1 second.
		    //You can replace your if you wants to Increase or decrease wait time.
		    for (int i=0; i<150; i++)
		    {
		    	Thread.sleep(1000);
		    	if (js.executeScript("return document.readyState").toString().equals("complete"))
		    	{
		    		break;
		    	}
		    }
		}
		catch(Exception e)
		{	
		}
	 }
	
	/***************************************************************************************************
	** Function Description	: Function is used to get WebElement based on provided locater
	** Input Parameters		: loctrType - Locator Type, loctrValue - Locator Value
	** Output Parameters	: WebElement 
	***************************************************************************************************/
	public WebElement fnGetWebElement(String loctrType,String loctrValue)
	{	
		fnWaitForPageToBeReady();		//Wait for page to load completely
		WebElement elm = null;
		try
		{
			switch(loctrType.toUpperCase().trim())
			{
				case "XPATH":
					elm=driver.findElement(By.xpath(loctrValue));
					break;
				case "CSSSELECTOR":
					elm=driver.findElement(By.cssSelector(loctrValue));
					break;
				case "ID":
					elm=driver.findElement(By.id(loctrValue));
					break;
				case "CLASSNAME":
					elm=driver.findElement(By.className(loctrValue));
					break;
				case "LINKTEXT":
					elm=driver.findElement(By.linkText(loctrValue));
					break;
				case "NAME":
					elm=driver.findElement(By.name(loctrValue));
					break;
				case "PARTIALLINKTEXT":
					elm=driver.findElement(By.partialLinkText(loctrValue));
					break;
				case "TAGNAME":
					elm=driver.findElement(By.tagName(loctrValue));
					break;
				default:
					System.out.println("LOGS: Locator type '"+loctrType+"' is not valid");
					break;
			}
		}
		catch(Exception e)
		{
			System.out.println("LOGS: Unable to Get WebElement: "+loctrType+"="+loctrValue);
		}
		return elm;
	}
	
	
	/***************************************************************************************************
	** Function Description	: Function is used to verify that element is displayed
	** Input Parameters		: loctrType - Locator Type, loctrValue - Locator Value
	** Output Parameters	: true or false
	***************************************************************************************************/
	public boolean fnWebElementDisplayed(String loctrType,String loctrValue)
	{
		boolean flag = false;
		WebElement elm = fnGetWebElement(loctrType,loctrValue);
		try
		{
			if(elm!=null)
			{
				if(elm.isDisplayed())
				{
					new Actions(driver).moveToElement(elm).perform();
					((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'",elm);	//Highlight
					Thread.sleep(100);
					((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'",elm);	//Remove Highlight
					flag=true;
				}
			}
		}catch(Exception e){
			System.out.println("LOGS: Unable to display WebElement: "+loctrType+"="+loctrValue);
		}
		return flag;
	}
	
	/***************************************************************************************************
	** Function Description	: Function is used to get text of WebElement
	** Input Parameters		: loctrType - Locator Type, loctrValue - Locator Value
	** Output Parameters	: Text of WebElement
	***************************************************************************************************/
	public String fnWebElementGetText(String loctrType,String loctrValue)
	{		
		String text = "";
		WebElement elm = fnGetWebElement(loctrType,loctrValue);
		try
		{
			if(elm!=null)
			{
				text = elm.getText().trim();
			}
		}
		catch(Exception e)
		{
			System.out.println("LOGS: Unable to Get Text of WebElement: "+loctrType+"="+loctrValue);
		}
		return text;
	}
	
	/***************************************************************************************************
	** Function Description	: Function is used to click on WebElement based on provided locater
	** Input Parameters		: loctrType - Locator Type, loctrValue - Locator Value
	** Output Parameters	: true or false
	***************************************************************************************************/
	public boolean fnWebElementClick(String loctrType,String loctrValue)
	{		
		boolean flag = true;
		WebElement elm = fnGetWebElement(loctrType,loctrValue);
		try
		{
			if(elm!=null)
			{
				((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'",elm);	//Highlight
				elm.click();
				flag=true;
			}
		}
		catch(Exception e)
		{
			System.out.println("LOGS: Unable to Click on WebElement: "+loctrType+"="+loctrValue);
		}
		fnWaitForPageToBeReady();
		return flag;
	}
	
	/***************************************************************************************************
	 ** Function Description : Function is used to Enter Text in WebElement Input
	 * Parameters : loctrType - Locator Type, loctrValue - Locator Value, value -
	 * Value Output Parameters : True or False
	 ***************************************************************************************************/
	public boolean fnWebElementEnterText(String loctrType, String loctrValue, String value) {
		boolean flag = false;
		WebElement elm = fnGetWebElement(loctrType, loctrValue);
		try {
			if (elm != null) {
				((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", elm); // Highlight
				elm.clear();
				elm.click();
				elm.sendKeys(value);
				((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", elm); // Remove Highlight
				flag = true;
			}
		} catch (Exception e) {
			System.out.println("LOGS: Unable to Enter Value in WebElement: " + loctrType + "=" + loctrValue);
		}
		return flag;
	}
	
	 
	 /***************************************************************************************************
		 ** Function Description : Function is used to get WebElement based on provided
		 * locater Input Parameters : loctrType - Locator Type, loctrValue - Locator
		 * Value Output Parameters : List<WebElement>
		 ***************************************************************************************************/
		public List<WebElement> fnGetWebElementList(String loctrType, String loctrValue) {
			fnWaitForPageToBeReady(); // Wait for page to load completely
			List<WebElement> elm = null;
			try {
				switch (loctrType.toUpperCase().trim()) {
				case "XPATH":
					elm = driver.findElements(By.xpath(loctrValue));
					break;
				case "CSSSELECTOR":
					elm = driver.findElements(By.cssSelector(loctrValue));
					break;
				case "ID":
					elm = driver.findElements(By.id(loctrValue));
					break;
				case "CLASSNAME":
					elm = driver.findElements(By.className(loctrValue));
					break;
				case "LINKTEXT":
					elm = driver.findElements(By.linkText(loctrValue));
					break;
				case "NAME":
					elm = driver.findElements(By.name(loctrValue));
					break;
				case "PARTIALLINKTEXT":
					elm = driver.findElements(By.partialLinkText(loctrValue));
					break;
				case "TAGNAME":
					elm = driver.findElements(By.tagName(loctrValue));
					break;
				default:
					System.out.println("LOGS: Locator type '" + loctrType + "' is not valid");
					break;
				}
			} catch (Exception e) {
				System.out.println("LOGS: Unable to Get WebElement List with: " + loctrType + "=" + loctrValue);
			}
			return elm;
		}
		
		/***************************************************************************************************
		 ** Function Description : Function is used to click on WebElement based on
		 * provided locater Input Parameters : elm - WebElement Output Parameters : true
		 * or false
		 ***************************************************************************************************/
		public static boolean fnWebElementClick(WebElement elm) {
			boolean flag = false;
			try {
				if (elm != null) {
					((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", elm);
					elm.click();
					flag = true;
				}
			} catch (Exception e) {
				System.out.println("LOGS: Unable to Click on WebElement: " + elm.getText());
			}
			fnWaitForPageToBeReady();
			return flag;
		}

		/***************************************************************************************************
		 ** Function Description : Function is used to Sleep a tread for provided time
		 ** Input Parameters : timeInSec - Time to wait in seconds Output Parameters :
		 * None
		 ***************************************************************************************************/
		public static void fnWait(int timeInSec) {
			try {
				System.out.println("LOGS: Waiting for [" + timeInSec + "] seconds");
				Thread.sleep(timeInSec * 1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


	 


	 
	 
}
