package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class DeletionContactTest extends TestBase {
    String groupName = "test2";
    @Test
    public void testDeletionContact() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup(groupName)) {
            app.getGroupHelper().createGroup(new GroupData(groupName, null, null));
            app.getNavigationHelper().gotoHomePage();
        } if (! app.getContactHelper().isThereAContact()) {
                app.getContactHelper().createContact(new ContactData("Tomas", "Anderson", "NEO", "MetaCortex", "312-555-0690", "test@test.com", groupName));
            }
            app.getNavigationHelper().gotoHomePage();
            app.getContactHelper().selectId();
            app.getContactHelper().selectDelete();
            app.getContactHelper().switchTo();
            app.getContactHelper().gotoLogOut();
    }
}









