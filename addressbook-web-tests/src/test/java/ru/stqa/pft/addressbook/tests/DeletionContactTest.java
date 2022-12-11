package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class DeletionContactTest extends TestBase {

    @Test
    public void testDeletionContact() {

        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Tomas", "Anderson", "NEO", "MetaCortex", "312-555-0690", "test@test.com", "test1"));
        }
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectId();
        app.getContactHelper().selectDelete();
        app.getContactHelper().switchTo();
        app.getContactHelper().gotoLogOut();
    }


}
