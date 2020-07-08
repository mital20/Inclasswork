package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class Pratical
{

     static WebDriver driver;

    public static void sleep1(int n)
    {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }}

    public static void waituntilElementIsClickable(By by, int time)
    {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }
    public static void clickOnElement(By by)
    {
        driver.findElement(by).click();
    }
    public static void typetext(By by, String text)
    {
        driver.findElement(by).sendKeys(text);
    }
    public static void selectByVisbleTextFromDropDownByVisibleText(By by, String text)
    {
        Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(text);
    }

    public static void selectFromDropDownByIndex(By by, int n)
    {
        Select select = new Select(driver.findElement(by));
        select.selectByIndex(n);
    }
    public static void selectfromDropDownByValue(By by, String value)
    {
        Select select = new Select(driver.findElement(by));
        select.selectByValue(value);
    }
    public static long timestamp()
    {
        return (System.currentTimeMillis());
    }
@BeforeMethod
 public static void setBrowser()
 {
     System.setProperty("webdriver.chrome.driver","C:\\soft\\chromedriver.exe" );
     driver = new ChromeDriver();
     driver.manage().window().maximize();
     driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
     driver.get("https://demo.nopcommerce.com");

 }
 @AfterMethod
 public  static  void setcloseBrowser()
 {
     driver.close();

 }
 @Test
    public void userShouldBeResisterSuccessfully()
    {
        clickOnElement(By.xpath("//a[@class=\"ico-register\"]"));// click on register button
        clickOnElement(By.xpath("//input[@id=\"gender-male\"]")); //click on male
        typetext(By.xpath("//input[@id=\"FirstName\"]"),"Mital");// enter first name
        typetext(By.xpath("//input[@id=\"LastName\"]"),"sharma");// enter surname
        // select date from drop down
        selectByVisbleTextFromDropDownByVisibleText(By.xpath("//select[@name=\"DateOfBirthDay\"]"),"20");
        // select month from drop down
        selectFromDropDownByIndex(By.xpath("//select[@name=\"DateOfBirthMonth\"]"),2);
        // select a year drop down
        selectfromDropDownByValue(By.xpath("//select[@name=\"DateOfBirthYear\"]"),"1985");
        // enter yout email address
        driver.findElement(By.xpath("//input[@id=\"Email\"]")).sendKeys("mital+"+timestamp()+"@gmail.com");

        typetext(By.xpath("//input[@id=\"Company\"]"),"Abcltd"); // enter company name
        clickOnElement(By.xpath("//input[@type=\"checkbox\"]")); // unclick on checkbox
        typetext(By.xpath("//input[@id=\"Password\"]"),"mum123");// enter your password
        typetext(By.xpath("//input[@id=\"ConfirmPassword\"]"),"mum123"); // confirm your password
        clickOnElement(By.xpath("//input[@id=\"register-button\"]")); // click on register button


        String expected = "Your registration completed"; // expected result
        String actucl = driver.findElement(By.xpath("//div[@class=\"result\"]")).getText();// actucl result from website
        Assert.assertEquals(actucl,expected, "text do not match");// comparing two texts
        System.out.println(actucl);// checking the output
    }

@Test

public void registerUserShouldBeAbleToReferAProductToAFriendSuccessfully()
{
    clickOnElement(By.xpath("//a[@class=\"ico-register\"]"));// click on register button
    clickOnElement(By.xpath("//input[@id=\"gender-male\"]")); //click on male
    typetext(By.xpath("//input[@id=\"FirstName\"]"),"Mital");// enter first name
    typetext(By.xpath("//input[@id=\"LastName\"]"),"sharma");// enter surname
    // select date from drop down
    selectByVisbleTextFromDropDownByVisibleText(By.xpath("//select[@name=\"DateOfBirthDay\"]"),"20");
    // select month from drop down
    selectFromDropDownByIndex(By.xpath("//select[@name=\"DateOfBirthMonth\"]"),2);
    // select a year drop down
    selectfromDropDownByValue(By.xpath("//select[@name=\"DateOfBirthYear\"]"),"1985");
    // enter your email address
    driver.findElement(By.xpath("//input[@id=\"Email\"]")).sendKeys("mital+"+timestamp()+"@gmail.com");

    typetext(By.xpath("//input[@id=\"Company\"]"),"Abcltd"); // enter company name
    clickOnElement(By.xpath("//input[@type=\"checkbox\"]")); // unclick on checkbox
    typetext(By.xpath("//input[@id=\"Password\"]"),"mum123");// enter your password
    typetext(By.xpath("//input[@id=\"ConfirmPassword\"]"),"mum123"); // confirm your password
    clickOnElement(By.xpath("//input[@id=\"register-button\"]")); // click on register button

    clickOnElement(By.xpath("//ul[@class=\"top-menu notmobile\"]/li/a[@href=\"/computers\"]")); // click on computre
    clickOnElement(By.xpath("//ul[@class=\"sublist\"]/li[1]/a"));// click on desktop
    clickOnElement(By.xpath("//h2/a[text()=\"Build your own computer\"]")); // click on computer product
    clickOnElement(By.xpath("//input[@value=\"Email a friend\"]")); // click on email a friend
    typetext(By.xpath("//input[@id=\"FriendEmail\"]"),"sharmajigna+"+timestamp()+"@gmail.com"); // enter friend email details

    //writing a message for a friend
    typetext(By.xpath("//textarea[@id=\"PersonalMessage\"]"),"suggesting you this computer as per our conversation");
    clickOnElement(By.xpath("//input[@name=\"send-email\"]"));// click on send button
    String expected = "Your message has been sent."; // show your expected text
    String actual = driver.findElement(By.xpath("//div[@class=\"result\"]")).getText();// get actual text from website
    Assert.assertEquals(actual,expected,"both text do not match");// comparing both text
    System.out.println(actual);// printing both text
}
@Test
    public void UserShouldBeAbleToAddProductToBasketSuccessfully()
{
    clickOnElement(By.xpath("//div[@class=\"header-menu\"]/ul[1]/li[5]/a"));// click on books

    //getting text to check if correct product been selected
    String expected1 = driver.findElement(By.xpath("//div/div/div[1]/div/div[2]/h2/a[text()=\"Fahrenheit 451 by Ray Bradbury\"]")).getText();
    // click on add to cart button for first book
    clickOnElement(By.xpath("//div[@class=\"product-grid\"]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/input[1]"));


   // sleep1(2000);
    //getting text to check if correct product been selected
    String expected2 = driver.findElement(By.xpath("//div/div/div[2]/div/div[2]/h2/a[text()=\"First Prize Pies\"]")).getText();
    //sleep1(2000);
    // click on add to cart button for second book
    clickOnElement(By.xpath("//div[@class=\"product-grid\"]/div[1]/div[2]/div[1]/div[2]/div[3]/div[2]/input[1]"));


    // click on shopping cart at the top
    clickOnElement(By.xpath("//a/span[@class=\"cart-label\"]"));

    // getting text from shopping cart to compare
    String actual1 = driver.findElement(By.xpath("//td/a[text()=\"Fahrenheit 451 by Ray Bradbury\"]")).getText();
    // comparing first book
    Assert.assertEquals(actual1,expected1,"both message do not match");// comparing first book
    System.out.println(actual1);// checking output 




    // getting text from shopping cart to compare
    String actual2 = driver.findElement(By.xpath("//tbody/tr[2]/td[4]/a[@class=\"product-name\"]")).getText();
    // comparing second book
    Assert.assertEquals(actual2,expected2,"it not same book in te shopping cart");
    // checking the ouput
    System.out.println(actual2);



}







}
