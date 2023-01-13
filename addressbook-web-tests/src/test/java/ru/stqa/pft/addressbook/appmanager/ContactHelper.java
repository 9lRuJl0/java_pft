package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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

        public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
        }

    public void createContact(ContactData contact) {
        gotoAddNew();
        fillContactForm(contact, true);
        gotoEnter();

    }

    public boolean isThereAContact() {

        return isElementPresent(By.name("selected[]"));
    }

    String groupName = "test1";

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements ) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstName = wd.findElement(By.xpath("input[@name='firstname']")).getText();
            String lastname = wd.findElement(By.xpath("input[@name='lastname']")).getText();
            String nickname = wd.findElement(By.xpath("input[@name='nickname']")).getText();
            String company = wd.findElement(By.xpath("input[@name='company']")).getText();
            String telephone = wd.findElement(By.xpath("input[@name='telephone']")).getText();
            String email = wd.findElement(By.xpath("input[@name='email']")).getText();
            ContactData сontact = new ContactData(id, firstName, lastname, nickname, company, telephone, email, groupName);
            contacts.add(сontact);
        }
        return  contacts;
    }


    public int getContactCount() {
       return wd.findElements(By.name("selected[]")).size();
    }
}










