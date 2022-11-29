package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    public WebDriver wd;

    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;

    private NavigationHelper navigationHelper;

    private GroupHelper groupHelper;

    public void init() {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/group.php?selected%5B%5D=13&selected%5B%5D=9&selected%5B%5D=8&selected%5B%5D=10&selected%5B%5D=11&selected%5B%5D=14&delete=Delete+group%28s%29");
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("Admin", "secret");
        contactHelper = new ContactHelper(wd);
    }



    public void login() {
      wd.get("http://localhost/addressbook/");
      wd.findElement(By.name("user")).click();
      wd.findElement(By.name("user")).sendKeys("Admin");
      wd.findElement(By.id("LoginForm")).click();
      wd.findElement(By.name("pass")).click();
      wd.findElement(By.name("pass")).sendKeys("secret");
      wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    public void stop() {
        wd.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }
}
