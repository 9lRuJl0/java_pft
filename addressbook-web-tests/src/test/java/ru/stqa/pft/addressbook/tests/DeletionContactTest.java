package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class DeletionContactTest extends TestBase {

    @Test
    public void testDeletionContact() {
        app.getContactHelper().selectId();
        app.getContactHelper().selectDelete();
        app.getContactHelper().switchTo();
        app.getContactHelper().gotoLogOut();
    }


}
