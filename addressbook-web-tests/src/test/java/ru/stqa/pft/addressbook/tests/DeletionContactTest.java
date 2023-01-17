package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class DeletionContactTest extends TestBase {
    String groupName = "test1";
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
        List<ContactData> before = app.getContactHelper().getContactList();
        System.out.println(before.size());
            app.getContactHelper().selectContact(before.size() - 1);
            app.getContactHelper().selectDelete();
            app.getContactHelper().switchTo();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);


    }
}









