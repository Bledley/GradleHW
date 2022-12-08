import Pages.HomePage;
import Pages.RegPage;
import Util.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static Util.Constants.pass;

public class HomeWorkTestNG {
    protected RegPage regPage;
    protected HomePage homePage;
    private WebDriver chromeDriver;
    private WebDriver ffDriver;
    protected Constants constants;



    public WebDriver getDriver(String browser) {
        if (browser.equalsIgnoreCase("firefox")) {

            if (ffDriver == null) {
                System.setProperty("webdriver.gecko.driver", "/Users/artem/IdeaProjects/Third_lesson_java/sources/geckodriver");
                ffDriver = new FirefoxDriver();
                ffDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            }
            return ffDriver;
        } else if (browser.equalsIgnoreCase("chrome")) {
            if (chromeDriver == null) {
                System.setProperty("webdriver.chrome.driver", "/Users/artem/IdeaProjects/Third_lesson_java/sources/chromedriver");
                chromeDriver = new ChromeDriver();
                chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            }
            return chromeDriver;
        } else return null;
    }





    @Parameters("browser")
    @BeforeTest
    public void setUp (String browser) {
        getDriver(browser).get(constants.URL);
    }


    @Parameters("browser")
    @Test(priority = 1)
    public void openRegPage(String browser) throws InterruptedException {
        homePage = new HomePage(getDriver(browser));
        Thread.sleep(3000);
        //homePage.btnContinue.click();// for EU internet (allow cookies)
        homePage.openRegPageBtn.click();
        Thread.sleep(3000);
    }

    @Parameters("browser")
    @Test(priority = 2)
    public void enterAllRegData(String browser) {
        regPage = new RegPage(getDriver(browser)); //lazy initalization
        regPage.firstNameFld.sendKeys(constants.name);
        regPage.lastNameFld.sendKeys(constants.surname);
        regPage.emailFld.sendKeys(regPage.generatedMail);
        regPage.repeatEmailFld.sendKeys(regPage.generatedMail);
        regPage.passFld.sendKeys(constants.pass);
        regPage.selectBirthdayYear();
        regPage.maleRadioBtn.click();

    }

    @Parameters("browser")
    @Test(priority = 3)
    public void checkEnteredData() {
        Assert.assertEquals(regPage.firstNameFld.getAttribute("value"), constants.name);
        Assert.assertEquals(regPage.lastNameFld.getAttribute("value"), constants.surname);
        Assert.assertEquals(regPage.emailFld.getAttribute("value"), regPage.generatedMail);
        Assert.assertEquals(regPage.repeatEmailFld.getAttribute("value"), regPage.generatedMail);
        Assert.assertEquals(regPage.passFld.getAttribute("value"), pass);
        Assert.assertEquals(regPage.yearDD.getAttribute("value"), "1997");
        Assert.assertEquals(regPage.maleRadioBtn.isSelected(), true);
    }

    @Parameters("browser")
    @Test(priority = 4)
    public void checkRegisterBtn(){
        Assert.assertEquals(regPage.registerBtn.isDisplayed(), true);
    }



    @Parameters("browser")
    @AfterTest
    public void closeBrowser(String browser){
        getDriver(browser).quit();
    }
}