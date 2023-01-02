package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ModificationContactTest  extends TestBase {

    @Test
    public void testModificationContact() {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
            app.getNavigationHelper().gotoHomePage();
        }
            if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Tomas", "Anderson", "NEO", "MetaCortex", "312-555-0690", "test@test.com", "test1"));
            }
            app.getNavigationHelper().gotoHomePage();
            app.getContactHelper().selectId();
            app.getContactHelper().selectEdit();
            app.getContactHelper().fillContactForm(new ContactData("Ярослав", "Сорокин", "9RuJl0", "MetaCortex", "312-555-0690", "test@test.com", null), false);
            app.getContactHelper().selectUpdate();
            app.getNavigationHelper().gotoHomePage();
            app.getContactHelper().gotoLogOut();


        }


}

