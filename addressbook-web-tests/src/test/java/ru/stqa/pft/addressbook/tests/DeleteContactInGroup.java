package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactInGroup extends TestBase {


    String groupName = "test1";
    @BeforeMethod

    public void ensurePreconditions() {
        // Выполнение предусловия создания группы
        if (app.db().groups().size() == 0) {
        }
        app.goTo().groupPage();
        app.group().create(new GroupData().withName(groupName));
        // Выполнение предусловия создания контакта
        if(app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Tomas").withLastname("Anderson")
                    .withNickname("NEO"). withCompany("MetaCortex").withTelephone("312-555-0690")
                    .withEmail("test@test.com"));
        }
    }

    @Test

    public void testDeleteContactInGroup() {

        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        ContactData contactGroup = contacts.iterator().next();
        GroupData removeGroup = contactGroup.getGroups().iterator().next();
        ContactData before = app.contact().contactGroup(contacts);
        app.goTo().gotoHomePage();
        app.contact().selectGroupToContact(removeGroup.getId());
        app.contact().selectContactById(contactGroup.getId());
        app.contact().removeContactGroup();
        app.goTo().gotoHomePage();
        ContactData after = contactGroup.inGroup(removeGroup);
        assertThat(after, equalTo(contactGroup));
    }

}
