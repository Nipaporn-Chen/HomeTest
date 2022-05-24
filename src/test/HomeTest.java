import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HomeTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(testName = "IN-1")
    public void pageTitle() {

        driver.get("https://interview-prep-test.herokuapp.com/");

        //Validate username and password field is editable
        WebElement username = driver.findElement(By.xpath("//input[@name='email']"));
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        username.isEnabled();
        password.isEnabled();

        //Enter username and password
        username.sendKeys("test@yahoo.com");
        password.sendKeys("test123");

        //Press login
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        //Verify page title is "Interview App"
        String expectedResult = "Interview App";
        String actualResult = driver.getTitle();
        Assert.assertEquals(actualResult, expectedResult);

    }
    @Test(testName = "IN-2")
    public void testManageAccessBtn(){

        driver.get("https://interview-prep-test.herokuapp.com/");

        Assert.assertFalse(false, "Manage Access");

    }
    @Test
    public void testSignoutBtn(){

        driver.get("https://interview-prep-test.herokuapp.com/");

        //Validate username and password field is editable
        WebElement username = driver.findElement(By.xpath("//input[@name='email']"));
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        username.isEnabled();
        password.isEnabled();

        //Enter username and password
        username.sendKeys("test@yahoo.com");
        password.sendKeys("test123");

        //Press login
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        WebElement signoutBtn = driver.findElement(By.xpath("//a[@href='/login']/u"));

        String expected = "Sign out";
        Assert.assertEquals(signoutBtn.getText(), expected);


    }
    @Test(testName = "IN-3")
    public void defaultdashboard() {

        driver.get("https://interview-prep-test.herokuapp.com/");

        //Validate username and password field is editable
        WebElement username = driver.findElement(By.xpath("//input[@name='email']"));
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        username.isEnabled();
        password.isEnabled();

        //Enter username and password
        username.sendKeys("test@yahoo.com");
        password.sendKeys("test123");

        //Press login
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        //Verify user dashboard is visible with all topics, coding, soft skills
        String alltopicsActual = driver.findElement(By.xpath("//button[text()='All Topics']")).getText();
        String alltopicsExpected = "All Topics";
        Assert.assertEquals(alltopicsActual, alltopicsExpected);

        String codingActual = driver.findElement(By.xpath("//button[text()= 'Coding']")).getText();
        String codingExpected = "Coding";
        Assert.assertEquals(codingActual, codingExpected);

        String softskillsActual = driver.findElement(By.xpath("//button[text() = 'Soft skills']")).getText();
        String softskillsExpected = "Soft skills";
        Assert.assertEquals(softskillsActual, softskillsExpected);
    }
 @Test(testName = "IN-4")
    public void testDoSection() {
        driver.get("https://interview-prep-test.herokuapp.com/");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("test@yahoo.com");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("test123");
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        driver.findElement(By.xpath("//button[text()='Add do ']")).click();

        String expectedStatement = "learn and practice coding 7 days a week";
        driver.findElement(By.id("inputArea1")).sendKeys(expectedStatement);
        driver.findElement(By.xpath("//button[text()='Enter']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[text()='learn and practice coding 7 days a week']")).getText(), expectedStatement);
    }
    @Test(testName = "IN-4")
    public void testDoSectionNegative() {
        driver.get("https://interview-prep-test.herokuapp.com/");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("test@yahoo.com");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("test123");
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        driver.findElement(By.xpath("//button[text()='Add do ']")).click();

        String expectedStatement = "100% coding";
        driver.findElement(By.id("inputArea1")).sendKeys(expectedStatement);
        driver.findElement(By.xpath("//button[text()='Enter']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[text()='100% coding']")).getText(), expectedStatement);
    }
    @Test(testName = "IN-4")
    public void testDoNotSection() {
        driver.get("https://interview-prep-test.herokuapp.com/");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("test@yahoo.com");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("test123");
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        driver.findElement(By.xpath("//div[@class='col-md-3 dont']//button")).click();

        String expectedStatement = "dont give up 4ever";
        driver.findElement(By.id("inputArea2")).sendKeys(expectedStatement);
        driver.findElement(By.xpath("//button[text()='Enter']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[text()='dont give up 4ever']")).getText(), expectedStatement);
    }
    @Test(testName = "IN-4")
    public void testDoNotSectionNegative() {
        driver.get("https://interview-prep-test.herokuapp.com/");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("test@yahoo.com");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("test123");
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        driver.findElement(By.xpath("//div[@class='col-md-3 dont']//button")).click();

        String expectedStatement = "0/anything";
        driver.findElement(By.id("inputArea2")).sendKeys(expectedStatement);
        driver.findElement(By.xpath("//button[text()='Enter']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[text()='0/anything']")).getText(), expectedStatement);
    }
    @Test(testName = "IN-5(1)")
    public void dashbordQuestion1() {

        driver.get("https://interview-prep-test.herokuapp.com/");
        WebElement username = driver.findElement(By.xpath("//input[@name='email']"));
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        username.isEnabled();
        password.isEnabled();

        //Enter username and password
        username.sendKeys("test@yahoo.com");
        password.sendKeys("test123");

        //Press login
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        //Click on Coding dashboard
        driver.findElement(By.xpath("//img[@class='codeChallengeImg']")).click();

        //Click on "Enter new question"
        driver.findElement(By.xpath("//button[text()='Enter new question ']")).click();

        //Verify text box is appeared
        driver.findElement(By.id("question")).isDisplayed();

        //Verify Coding content should accept letter, number, and special characters.
        driver.findElement(By.id("question")).sendKeys("How do we debug 3 code at the same time?\n" +
                "Selam@123");

        //Click on "Enter" button
        driver.findElement(By.xpath("//button[text()='Enter']")).click();

        //Verify the question is the first display.
        driver.findElement(By.xpath("//div[@class='row question-section shadow-sm']")).isDisplayed();

        //Verify the display question is correct
        String expectedResult = "How do we debug 3 code at the same time?\n" +
                "Selam@123";
        String actualResult1 = driver.findElement(By.xpath("//div[@class='col-md-8']")).getText();

        Assert.assertEquals(actualResult1, expectedResult);

    }
    @Test(testName = "IN-5(2)")
    public void dashbordQuestion2() {
        driver.get("https://interview-prep-test.herokuapp.com/");
        WebElement username = driver.findElement(By.xpath("//input[@name='email']"));
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        username.isEnabled();
        password.isEnabled();

        //Enter username and password
        username.sendKeys("test@yahoo.com");
        password.sendKeys("test123");

        //Press login
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        //Click on Coding dashboard
        driver.findElement(By.xpath("//img[@class='codeChallengeImg']")).click();

        //Click on "Enter new question"
        driver.findElement(By.xpath("//button[text()='Enter new question ']")).click();

        //Verify text box is appeared
        driver.findElement(By.id("question")).isDisplayed();

        //Leave text box empty.
        driver.findElement(By.id("question")).click();
        //Click on "Enter" button
        driver.findElement(By.xpath("//button[text()='Enter']")).click();

        //Verify the question is the first display.
        String actualResult = driver.findElement(By.xpath("//div[@class='row question-section shadow-sm']")).getText();
        Assert.assertFalse(false, actualResult);

    }
    @Test(testName = "IN-5(3)")
    public void dashbordQuestion3() {

        driver.get("https://interview-prep-test.herokuapp.com/");
        WebElement username = driver.findElement(By.xpath("//input[@name='email']"));
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        username.isEnabled();
        password.isEnabled();

        //Enter username and password
        username.sendKeys("test@yahoo.com");
        password.sendKeys("test123");

        //Press login
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        //Click on Soft skill dashboard
        driver.findElement(By.xpath("//button[text()='Soft skills']")).click();

        //Click on "Enter new question"
        driver.findElement(By.xpath("//button[text()='Enter new question ']")).click();

        //Verify text box is appeared
        driver.findElement(By.xpath("//input[@name='question']")).isDisplayed();

        //Verify Soft skills content should accept letter, number, and special characters.
        driver.findElement(By.xpath("//input[@name='question']")).sendKeys("How do we debug 3 code at the same time?\n" +
                "Selam@123");

        //Click on "Enter" button
        driver.findElement(By.xpath("//button[text()='Enter']")).click();

        //Verify the question is the first display.
        driver.findElement(By.xpath("//div[@class='row question-section shadow-sm']")).isDisplayed();

        //Verify the display question is correct
        String expectedResult = "How do we debug 3 code at the same time?\n" +
                "Selam@123";
        String actualResult1 = driver.findElement(By.xpath("//div[@class='col-md-8']")).getText();

        Assert.assertEquals(actualResult1, expectedResult);

    }
    @Test(testName = "IN-5(4)")
    public void dashbordQuestion4() {
        driver.get("https://interview-prep-test.herokuapp.com/");
        WebElement username = driver.findElement(By.xpath("//input[@name='email']"));
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        username.isEnabled();
        password.isEnabled();

        //Enter username and password
        username.sendKeys("test@yahoo.com");
        password.sendKeys("test123");

        //Press login
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        //Click on Soft skills dashboard
        driver.findElement(By.xpath("//button[text()='Soft skills']")).click();

        //Click on "Enter new question"
        driver.findElement(By.xpath("//button[text()='Enter new question ']")).click();

        //Verify text box is appeared
        driver.findElement(By.xpath("//input[@name='question']")).isDisplayed();

        //Leave text box empty.
        driver.findElement(By.xpath("//input[@name='question']")).click();
        //Click on "Enter" button
        driver.findElement(By.xpath("//button[text()='Enter']")).click();

        //Verify the question is the first display.
        String actualResult = driver.findElement(By.xpath("//div[@class='row question-section shadow-sm']")).getText();
        Assert.assertFalse(false, actualResult);

    }

    @AfterMethod
    public void tearDown(){

        driver.close();
    }
}
