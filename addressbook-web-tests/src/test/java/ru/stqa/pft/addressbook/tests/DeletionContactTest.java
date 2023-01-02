package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class DeletionContactTest extends TestBase {

    @Test
    public void testDeletionContact() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
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






