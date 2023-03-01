package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;



public class UserHelper extends HelperBase {

   // private WebDriver wd;
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
        click(By.xpath("//a[contains(text(),'Manage')]"));
    }
    private void goToManageUser() {
        click(By.xpath("//a[contains(text(),'Manage Users')]"));
    }
    private void selectUser() {
        click(By.xpath("//tr[4]/td/a"));
    }
    public void resetPassword () {
        click(By.cssSelector("input[value='Reset Password']"));
    }
    public void goToUserPage() {
        goToManage();
        goToManageUser();
        selectUser();

    }





}
