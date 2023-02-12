package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.util.Set;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class AddContactToGroupTest extends TestBase {

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

    public void testAddContactToGroup() {

        Contacts contacts = app.db().contacts();
        ContactData contactGroup = contactGroup(contacts);
        GroupData addGroup = noGroupContact();
        ContactData before = contactGroup.inGroup(addGroup);
        app.goTo().gotoHomePage();
        app.contact().selectContactById(contactGroup.getId());
        app.contact().selectAdd(addGroup.getName());
        app.goTo().gotoHomePage();
        ContactData after = contactGroup.inGroup(addGroup);
        assertThat(after, equalTo(before));


    }

    public GroupData noGroupContact() {
        Contacts contacts = app.db().contacts();
        Groups groupContact = contactGroup(contacts).getGroups();
        Groups listGroups = app.db().groups();
        listGroups.removeAll(groupContact);
        GroupData group = listGroups.iterator().next();

        return group;
    }

    public ContactData contactGroup(Contacts contacts) {
        for (ContactData contact : contacts) {
            Set<GroupData> ContactGroup = contact.getGroups();
            int listGroups = app.db().groups().size();
            if (listGroups > ContactGroup.size()) {
                return contact;
            }
        }
        return null;

     }
}



