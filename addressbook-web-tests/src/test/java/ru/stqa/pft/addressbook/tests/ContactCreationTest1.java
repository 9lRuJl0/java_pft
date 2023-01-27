package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest1 extends TestBase {

    @Test
    public void testCreationContact1() {

        app.goTo().gotoHomePage();
        app.contact().gotoAddNew();
        app.contact().fillContactForm(
                new ContactData().withFirstname("Bill").withLastname("Gates").withGroup("test1"), true);
        app.contact().gotoEnter();
        app.contact().gotoHomePage();


    }


}
