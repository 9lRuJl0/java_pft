package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {

        super(wd);
    }

    public void gotoLogOut() {

        click(By.linkText("Logout"));
    }


    public void gotoEnter() {

       click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("company"), contactData.getCompany());
        type(By.name("home"), contactData.getTelephone());
        type(By.name("email"), contactData.getEmail());
    }

    public void type(By locator, String text) {
        wd.findElement(locator).click();
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    public void gotoAddNew() {

        click(By.linkText("add new"));
    }

    public void selectId() {
        click(By.linkText("id"));
    }

    public void selectDelete() {
        click(By.linkText("delete"));
    }

    public void switchTo() {
        wd.switchTo().alert().accept();

    }

    public void selectEdit() {
        click(By.linkText("edit"));
    }

    public void selectUpdate() {
        click(By.linkText("update"));

    }
}
