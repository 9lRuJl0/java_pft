package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactInGroup extends TestBase {


    String groupName = "test1";
    @BeforeMethod

    public void ensurePreconditions() {
        Contacts contacts = app.db().contacts();
        if (contacts.size() == 0) {
            if (app.db().groups().size() == 0) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName(groupName));
            }
            app.contact().create(new ContactData()
                    .withFirstname("First")
                    .withLastname("Last")
                    .inGroup(app.db().groups().iterator().next()), true);
            app.goTo().gotoHomePage();
    }
        int contactDeletionGroup = app.db().contacts().iterator().next().getId();
        String groupName = app.db().groups().iterator().next().name();
        ContactData groupSize = new ContactData();
        if (groupSize.getGroups().size() == 0) {
            app.contact().selectContactById(contactDeletionGroup);
            app.contact().selectAdd(groupName);
        }
        }

    @Test

    public void testDeleteContactInGroup() {

        Contacts contacts = app.db().contacts();
        ContactData contactDeletionGroup = contacts.iterator().next();
        GroupData deletionGroup =  contactDeletionGroup.getGroups().iterator().next();
        app.goTo().gotoHomePage();
        app.contact().selectContactById(contactDeletionGroup.getId());
        app.contact().selectGroupToContact(deletionGroup.getId());
        app.contact().deletionToGroup();
        ContactData after = contactDeletionGroup;
        assertThat(after, equalTo(contactDeletionGroup.deletionToGroup(deletionGroup)));

    }

}
