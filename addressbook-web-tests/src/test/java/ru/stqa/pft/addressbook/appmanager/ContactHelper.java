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
            String allphones = element.findElement(By.xpath(".//td[6]")).getText();
            String allemail = element.findElement(By.xpath(".//td[5]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastname)
                    .withHomePhone(allphones).withMobilePhone(allphones).withWorkPhone(allphones).withAllemail(allemail));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstmane = wd.findElement(By.name("firstmane")).getAttribute("value");
        String lastmane = wd.findElement(By.name("lastmane")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");

        wd.navigate().back();
        return new ContactData().withId(contact.getId())
                .withLastname(lastmane).withFirstname(firstmane)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withAdress(address).withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }
    //wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
    //wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
    //wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();



}











