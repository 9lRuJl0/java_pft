package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties properties;
    private WebDriver wd;
    private String browser;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;
    private JamesHelper jamesHelper ;

    private UserHelper userHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();

    }
    // Запуск браузера
    public void init() throws IOException {

        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    }
    // Остановка браузера
    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }
    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
         return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null) {
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }


    public WebDriver getDriver() {
        if(wd == null) {
            if (Objects.equals(browser, BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();
            } else if (Objects.equals(browser, BrowserType.CHROME)) {
                wd = new ChromeDriver();
            } else if (Objects.equals(browser, BrowserType.EDGE)) {
                wd = new EdgeDriver();
            }
            wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
        }
            return  wd;
    }

    public  FtpHelper ftp() {
        if (ftp == null) {
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public MailHelper mail() {
     if (mailHelper == null) {
         mailHelper = new MailHelper(this);
     }
     return mailHelper;
    }

    public JamesHelper james() {
        if (jamesHelper == null) {
            jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }

    public UserHelper user() {
        if (userHelper == null) {
            userHelper = new UserHelper(this);
        }
        return userHelper;
    }

    public String getUsersName() {
        String users = wd.findElement(By.name("username")).getAttribute("value");
        return users;
    }
}