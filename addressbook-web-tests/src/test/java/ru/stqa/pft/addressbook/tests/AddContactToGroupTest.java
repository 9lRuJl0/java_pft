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
        // Проверка контактов в бд
        Contacts contacts = app.db().contacts();
        // Выполнение предусловия создания группы
        if (app.db().groups().size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName(groupName));
        }
        // Выполнение предусловия создания контакта
//        if(app.db().contacts().size() == 0) {
//            app.contact().create(new ContactData().withFirstname("Tomas").withLastname("Anderson")
//                    .withNickname("NEO"). withCompany("MetaCortex").withTelephone("312-555-0690")
//                    .withEmail("test@test.com"));
        if (app.db().contacts().size() == 0 || contactGroup(contacts) == null) {
            app.contact().create(new ContactData()
                    .withFirstname("Elon")
                    .withLastname("Musk"), false);

            app.goTo().gotoHomePage();

        }
    }

    @Test

    public void testAddContactToGroup() {

        Contacts contacts = app.db().contacts();
        ContactData contactsAddGroup = contactGroup(contacts);
        GroupData addGroup = addGroupContact();
        Contacts before = contacts.withOut(contactsAddGroup);
        app.contact().selectContactById(contactsAddGroup.getId());
        app.contact().selectAdd(addGroup.name());
        ContactData updateContact = contactsAddGroup.inGroup(addGroup);
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withAdded(updateContact)));

    }

    public ContactData contactGroup(Contacts groupContact){
        for (ContactData contact : groupContact) {
            Set<GroupData> contactsGroups = contact.getGroups();
            int allGroups = app.db().groups().size();
            if (allGroups > contactsGroups.size()) {
                return contact;
            }
        }
        return null;

    }

    public GroupData addGroupContact() {
        Contacts contacts = app.db().contacts();
        Groups groups   = app.db().groups();
        Groups groupsAddContact = contactGroup(contacts).getGroups();
        //удаляем группы которые есть у контакта
        groups.removeAll(groupsAddContact);
        //группа для добавления
        GroupData groupAddСontact = groups.iterator().next();
        return groupAddСontact;
    }



}



