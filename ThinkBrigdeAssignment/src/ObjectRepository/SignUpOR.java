package ObjectRepository;

public interface  SignUpOR 
	{
	
	public static final String name= "//input[@id='name']";
	public static final String orgName= "//input[@id='orgName']";
	public static final String email= "//input[@id='singUpEmail']";
	public static final String agreeCheckBox = "//span[contains(text(),'I agree to the')]";
	public static final String signUpBtn = "//button[contains(text(),'Get Started')]";
	public static final String language = "//div[@id='language']/div/span";
	public static final String search = "(//input[@type='search'])[2]";
	public static final String dutchLang = "//span[contains(text(),'Dutch')]";
	public static final String emailNotification = "//span[contains(text(),'A welcome email has been sent. Please check your email.')]";
	
	}
