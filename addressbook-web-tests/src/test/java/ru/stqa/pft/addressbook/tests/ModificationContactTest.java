package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ModificationContactTest  extends TestBase {

    String groupName = "test1";
    @Test
    public void testModificationContact() {

        app.goTo().groupPage();
        if (! app.group().isThereAGroup(groupName)) {
            app.group().create(new GroupData().withName(groupName));
            app.goTo().gotoHomePage();
        }
            if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Tomas", "Anderson", "NEO", "MetaCortex", "312-555-0690", "test@test.com", groupName));
            }
            app.goTo().gotoHomePage();
            List<ContactData> before = app.getContactHelper().getContactList();
            app.getContactHelper().selectContact(before.size() - 1);
            app.getContactHelper().selectEdit();
            ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Yaroslav", "Sorokin", "9RuJl0", "MetaCortex", "312-555-0690", "test@test.com", null);
            app.getContactHelper().fillContactForm(contact, false);
            app.getContactHelper().selectUpdate();
            app.goTo().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);


        }


}

