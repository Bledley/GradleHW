package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class RegPage extends BasePage{
    String URL = "https://www.facebook.com/";
    String name = "Johny";
    String surname = "Depp";
    String pass = "Qweasd123";
    //private WebDriver driver;

    public RegPage(WebDriver driver) {
        super(driver);
        //this.driver = driver;
        //PageFactory.initElements(driver,this);
    }



    public WebElement firstNameFld = driver.findElement(By.xpath("//*[@name='firstname']"));
    public WebElement lastNameFld = driver.findElement(By.xpath("//*[@name='lastname']"));
    public WebElement emailFld = driver.findElement(By.xpath("//*[@name='reg_email__']"));
    public WebElement repeatEmailFld = driver.findElement(By.xpath("//*[@name='reg_email_confirmation__']"));
    public WebElement passFld = driver.findElement(By.xpath("//*[@name='reg_passwd__']"));
    public WebElement yearDD = driver.findElement(By.name("birthday_year"));
    public WebElement maleRadioBtn = driver.findElement(By.xpath("(//*[@name='sex'])[2]"));
    public WebElement registerBtn = driver.findElement(By.xpath("//*[@name='websubmit']"));




    public void selectBirthdayYear(){
        Select selectYear = new Select (yearDD);
        selectYear.selectByValue("1997");
    }
    static String randomMailGen() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 8;
        for(int y = 0; y < length; y++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        String randomString = sb.toString();
        return randomString;
    }
    public String generatedMail = randomMailGen() + "@gmail.com";
}

