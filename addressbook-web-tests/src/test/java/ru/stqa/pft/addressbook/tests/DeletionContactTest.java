package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class DeletionContactTest extends TestBase {

    @Test
    public void testDeletionContact() {
        app.getContactHelper().selectId(By.id("3"));
        app.getContactHelper().selectDelete();
        app.getContactHelper().switchTo();
        app.getContactHelper().gotoLogOut();
    }


}
