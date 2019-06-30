package rtCamp;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;





public class Assignment1 {
	public static WebDriver driver;
	
	public static void browser_load(ChromeDriver driver)
    {
        new WebDriverWait(driver, 500).until(
          WebDriver -> ((JavascriptExecutor) WebDriver).executeScript("return document.readyState").equals("complete"));
    }
	
	public static void browserLaunchMethod() {
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void login_profile_method() throws InterruptedException, AWTException {
		
		browserLaunchMethod();
		driver.get("https://qa.rtcamp.net/activity/");
		String verify_title = driver.getTitle();   //Verify the Title
		Assert.assertEquals("Activity – rtCamp", verify_title);
		
		//Enter Invalid User Credentials
		WebElement userId = driver.findElement(By.id("bp-login-widget-user-login"));
		userId.clear();
		WebElement userPwd = driver.findElement(By.id("bp-login-widget-user-pass"));
		userId.sendKeys("iam123");		
		userPwd.clear();
		userPwd.sendKeys("Wrong@#$#@");	
		WebElement submitButton = driver.findElement(By.name("wp-submit"));
		submitButton.click();
		
		//ReLogin
		driver.get("https://qa.rtcamp.net/activity/");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//Enter the valid user id
		WebElement userIdValid = driver.findElement(By.id("bp-login-widget-user-login"));
		userIdValid.clear();
		userIdValid.sendKeys("demo");
		
		//Enter the valid password
		WebElement userPwdValid = driver.findElement(By.id("bp-login-widget-user-pass"));
		userPwdValid.clear();
		userPwdValid.sendKeys("demo");
		
		//Click the submit button
		WebElement submitButton1 = driver.findElement(By.name("wp-submit"));
		submitButton1.click();
		
		//Textarea Post
		WebElement textarea = driver.findElement(By.xpath("//textarea[@id=\"whats-new\"]"));
		textarea.click();
		textarea.sendKeys("WELCOME Post");
		
		//Select the Privacy Dropdown
		WebElement privacyDropdownList = driver.findElement(By.id("rtSelectPrivacy"));
		Select privacy_dd1=new Select(privacyDropdownList);
		List<WebElement> options1 = privacy_dd1.getOptions();
		for (WebElement eachOption : options1) {
			System.out.println(eachOption.getText());
		}
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		privacy_dd1.selectByIndex(0);
		
		//Click the Post Update Button
		WebElement postUpdateButton = driver.findElement(By.xpath("//input[@value='Post Update']"));
		postUpdateButton.click();
		
		//Hovering on User
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		WebElement userProfile = driver.findElement(By.cssSelector("li#wp-admin-bar-my-account > a"));
		Actions userAction1 = new Actions(driver);
		userAction1.moveToElement(userProfile).build().perform();
		Thread.sleep(1000);
		
		//Clicking Media Option
		driver.findElement(By.xpath("//li[@id='wp-admin-bar-my-account-media']")).click();
		
		//Media Gallery
		String verify_titleMedia = driver.getTitle();
		Assert.assertEquals("Media – demo – rtCamp", verify_titleMedia);
		
		//Click the Upload button
		driver.findElement(By.xpath("//span[@id='rtm_show_upload_ui']")).click();
		
		//Selecting the Privacy Dropdown
		WebElement privacyDropdownList2 = driver.findElement(By.id("rtSelectPrivacy"));
		Select privacy_dd2=new Select(privacyDropdownList2);
		List<WebElement> options2 = privacy_dd2.getOptions();
		for (WebElement eachOption : options2) {
			System.out.println(eachOption.getText());
		}
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		privacy_dd2.selectByIndex(0);
		
		//Creating the Album
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[@id='rtmedia-nav-item-albums']")).click();
		driver.findElement(By.xpath("//div[@class='clicker rtmedia-action-buttons']")).click();
		driver.findElement(By.xpath("//a[@href='#rtmedia-create-album-modal']")).click();
		driver.findElement(By.id("rtmedia_album_name")).sendKeys("TestAlbum");
		driver.findElement(By.id("rtmedia_album_description")).sendKeys("Random Images Test");
		driver.findElement(By.xpath("//button[text()='Create Album']")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[text()='×']")).click();
		
		//Select the Album
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath("(//span[@id='rtm_show_upload_ui'])[1]")).click();
		
		//Select files button click
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		WebElement selectFiles = driver.findElement(By.xpath("//*[@id=\"rtMedia-upload-button\"]"));
		selectFiles.click();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);;
		
		//First Image
		uploadFile("C:\\Users\\kevin\\Desktop\\rtCampTestData\\abc123.jpg");
		driver.findElement(By.className("start-media-upload")).click();	
		Thread.sleep(5000);//Must
		
		driver.findElement(By.xpath("(//li[@class = 'rtmedia-list-item'])[1]")).click();
		Thread.sleep(4000);//Must
		WebElement created_Album = driver.findElement(By.xpath("//div[@class='rtmedia-item-thumbnail']/img[1]"));
		created_Album.click();
		
		Thread.sleep(2000);//Must
		//driver.findElement(By.xpath("(//div[@class='rtmedia-item-thumbnail'])[1]")).click();
		driver.findElement(By.xpath("(//button[@type='submit']/span)[1]")).click();//Like or Unlike
		
		
		//Navigation to Profile
		driver.findElement(By.xpath("//button[text()='Edit']")).click();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.findElement(By.id("user-xprofile")).click();
		
		//Change Cover Image
		driver.findElement(By.id("change-cover-image")).click();
		driver.findElement(By.id("bp-browse-button")).click();
		
		Thread.sleep(1000);
		uploadFile("C:\\Users\\kevin\\Desktop\\rtCampTestData\\profilepic.jpg");
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		WebElement cover_update_msg = driver.findElement(By.id("bp-cover-image-feedback"));
		String Completion_Msg = cover_update_msg.getText();
		Assert.assertEquals("Your new cover image was uploaded successfully.", Completion_Msg);
		
		//To Delete the Album
		driver.get("https://qa.rtcamp.net/members/demo/media/album/");
		driver.findElement(By.xpath("(//div[@class='rtmedia-item-thumbnail'])[1]")).click();
		//driver.findElement(By.id("user-xprofile")).click();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.findElement(By.id("rtm-media-options-list")).click();
		driver.findElement(By.className("rtmedia-delete-album")).click();
		driver.switchTo().alert().accept();
	
	}
	public void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
	
		public void uploadFile(String fileLocation) {
		try {
		//Setting clipboard with file location
		setClipboardData(fileLocation);
		//native key strokes for CTRL, V and ENTER keys
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception exp) {
		exp.printStackTrace();
		}

		}	
	
	}
	
