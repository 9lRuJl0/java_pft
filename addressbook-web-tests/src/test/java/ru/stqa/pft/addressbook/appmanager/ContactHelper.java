package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends ApplicationManager {

    public ContactHelper(WebDriver wd) {
        this.wd = wd;
      //super(wd);
    }
    public void gotoAddNew() {
        wd.findElement(By.linkText("add new")).click();
    }
}
