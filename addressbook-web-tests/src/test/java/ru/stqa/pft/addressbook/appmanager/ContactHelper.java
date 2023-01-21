package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver wd) {

        super(wd);
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

    public void gotoAddNew() {

        click(By.linkText("add new"));
    }

    public void selectDelete() {

        click(By.xpath("//input[@value='Delete']"));
    }

    public void switchTo() {
        wd.switchTo().alert().accept();

    }

    public void selectEdit() {

        click(By.xpath("//img[@alt='Edit']"));
    }

    public void selectUpdate() {

        click(By.name("update"));

    }

    public void selectContact(int index) {
        if (wd.findElements(By.name("selected[]")).size() >= index) {
            wd.findElements(By.name("selected[]")).get(index).click();
        }

    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
        // wd.findElement(By.xpath("input[value='" + id + "']")).click();
    }

    public void gotoHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    public void create(ContactData contact) {
        gotoAddNew();
        fillContactForm(contact, true);
        gotoEnter();
        contactCache = null;
        gotoHomePage();

    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        selectEdit();
        fillContactForm(contact, false);
        selectUpdate();
        contactCache = null;
        gotoHomePage();
    }

    public void delete(int index) {
        selectContact(index);
        selectDelete();
        switchTo();

    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        selectDelete();
        contactCache = null;
        switchTo();
    }


    public boolean isThereAContact() {

        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }


    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastname));
        }
        return new Contacts(contactCache);
    }

}











