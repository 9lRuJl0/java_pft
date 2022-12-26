package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
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

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("company"), contactData.getCompany());
        type(By.name("home"), contactData.getTelephone());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

        public void type(By locator, String text) {
            wd.findElement(locator).click();
            wd.findElement(locator).clear();
            wd.findElement(locator).sendKeys(text);
        }

        public void gotoAddNew () {

            click(By.linkText("add new"));
        }

        public void selectDelete () {

            click(By.xpath("//input[@value='Delete']"));
        }

        public void switchTo () {
            wd.switchTo().alert().accept();

        }

        public void selectEdit () {

            click(By.xpath("//img[@alt='Edit']"));
        }

        public void selectUpdate () {

            click(By.name("update"));

        }

        public void selectId () {

            click(By.xpath("//td/input"));

        }

    public void createContact(ContactData contact) {
        gotoAddNew();
        fillContactForm(contact, true);
        gotoEnter();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//td/input"));
    }



    public boolean isThereASelectGroups() {
        click((By.name("new_group")));
        return isElementPresent((By.name("test1")));
    }
}





