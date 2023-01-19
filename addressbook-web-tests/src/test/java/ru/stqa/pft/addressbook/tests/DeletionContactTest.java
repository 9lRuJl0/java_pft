package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class DeletionContactTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (! app.group().isThereAGroup(groupName)) {
            app.group().create(new GroupData().withName(groupName));
            app.goTo().gotoHomePage();
        }
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Tomas").withLastname("Anderson").withNickname("NEO"). withCompany("MetaCortex").withTelephone("312-555-0690").withEmail("test@test.com").withGroup(groupName));

        }

    }
    String groupName = "test1";
    @Test
    public void testDeletionContact() {
        app.goTo().gotoHomePage();
        Contacts before = app.contact().all();
        ContactData deleteContact = before.iterator().next();
        app.contact().delete(deleteContact);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size() - 1);

        assertThat(after, equalTo(before.withOut(deleteContact)));



    }


}









