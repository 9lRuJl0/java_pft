package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ModificationContactTest  extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (! app.group().isThereAGroup(groupName)) {
            app.group().create(new GroupData().withName(groupName));
        }
        // Выполнение предусловия создания контакта
        app.goTo().gotoHomePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Tomas").withLastname("Anderson").withNickname("NEO"). withCompany("MetaCortex").withTelephone("312-555-0690").withEmail("test@test.com").withGroup(groupName));

        }

    }
    String groupName = "test1";
    @Test
    public void testModificationContact() {
        app.goTo().gotoHomePage();
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().
                withId(modifiedContact.getId()).withFirstname("Yaroslav").withLastname("Sorokin").withNickname("NEO"). withCompany("MetaCortex").withTelephone("312-555-0690").withEmail("test@test.com");
        app.contact().modify(contact);
        assertThat(app.contact().count(),  equalTo(before.size()));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));

        }




}

