package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    protected WebDriver wd;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private String browser;

    private ContactHelper contactHelper;


    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if (Objects.equals(browser, BrowserType.FIREFOX)) {
            wd = new FirefoxDriver(new FirefoxOptions().setBinary("C:/Program Files/Mozilla Firefox/firefox.exe"));
        } else if (Objects.equals(browser, BrowserType.CHROME)) {
            wd = new ChromeDriver();
        } else if (Objects.equals(browser, BrowserType.IE))  {
            wd = new InternetExplorerDriver();
        }


        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/group.php?new=New+group");
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.Login("Admin", "secret");
        contactHelper.gotoAddNew();
    }



    public void stop() {
        wd.quit();
    }

    public void login(String username, String password) {
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.id("LoginForm")).click();
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    public void fillFormContact(ContactData contactData) {
        enterName(contactData.getFirstname());
        enterLastName(contactData.getLastname());
        enterCompany(contactData.getCompany());
        enterNickname(contactData.getNickname());
        enterTelephone(contactData.getTelephone());
        enterEmail(contactData.getEmail());
    }

    public void goOut() {
        wd.findElement(By.linkText("home")).click();
        wd.findElement(By.linkText("Logout")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys("Admin");
    }

    public void enterSave() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void enterEmail(String email) {
        wd.findElement(By.name("email")).click();
        wd.findElement(By.name("email")).clear();
        wd.findElement(By.name("email")).sendKeys(email);
    }

    public void enterTelephone(String telephone) {
        wd.findElement(By.name("home")).click();
        wd.findElement(By.name("home")).clear();
        wd.findElement(By.name("home")).sendKeys(telephone);
    }

    public void enterNickname(String nickname) {
        wd.findElement(By.name("nickname")).click();
        wd.findElement(By.name("nickname")).clear();
        wd.findElement(By.name("nickname")).sendKeys(nickname);
    }

    public void enterCompany(String company) {
        wd.findElement(By.name("company")).click();
        wd.findElement(By.name("company")).clear();
        wd.findElement(By.name("company")).sendKeys(company);
    }

    public void enterLastName(String lastname) {
        wd.findElement(By.name("lastname")).click();
        wd.findElement(By.name("lastname")).clear();
        wd.findElement(By.name("lastname")).sendKeys(lastname);
    }

    public void enterName(String firstname) {
        wd.findElement(By.name("firstname")).click();
        wd.findElement(By.name("firstname")).clear();
        wd.findElement(By.name("firstname")).sendKeys(firstname);
    }

    public void gotoAddNew() {
        wd.findElement(By.linkText("add new")).click();
    }

//    private boolean isElementPresent(By by) {
//      try {
//        wd.findElement(by);
//        return true;
//      } catch (NoSuchElementException e) {
//        return false;
//      }
//    }


    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }


    public ContactHelper getContactHelper() {return contactHelper; }

    private boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
