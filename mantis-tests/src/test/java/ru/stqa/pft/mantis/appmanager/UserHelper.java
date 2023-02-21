package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class UserHelper extends HelperBase {

    private WebDriver wd;
    public UserHelper(ApplicationManager app) {
        super(app);
    }
    public void loginAdministrator() {
        wd.get(app.getProperty("web.baseUrl") + "login.php");
        type(By.name("username"), "administrator");
        type(By.name("password"), "root");
        click(By.xpath("//input[@value='Login']"));
    }
    private void goToManage() {

        click(By.xpath("//div[@id='sidebar']/ul/li[6]/a/i"));
    }
    private void goToManageUsers() {

        click(By.linkText("Manage Users"));
    }
    private void selectUser() {
        click(By.xpath("//tr[2]/td/a"));
    }
    public void resetPassword () {
        click(By.xpath("//input[@value='Reset Password']"));
    }
    public void goToUserPage() {
        goToManage();
        goToManageUsers();
        selectUser();

    }



}
