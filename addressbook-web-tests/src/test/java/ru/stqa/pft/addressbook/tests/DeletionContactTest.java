package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeletionContactTest extends TestBase {


    String groupName = "test1";

    @BeforeMethod
    public void ensurePreconditions() {
        // Проверка групп в базе данных
        if (app.db().groups().size() == 0) {
            // Выполнение предусловия создания группы
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(groupName));

    }
        //app.goTo().gotoHomePage();
       // if (app.contact().all().size() == 0)

        if(app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Tomas").withLastname("Anderson")
                    .withNickname("NEO"). withCompany("MetaCortex").withTelephone("312-555-0690")
                    .withEmail("test@test.com").withGroup(groupName));

        }

    }

    @Test
    public void testDeletionContact() {

        //Contacts before = app.contact().all();

        Contacts before = app.db().contacts();

        ContactData deleteContact = before.iterator().next(); //Проверка списка контактов до удаления в бд
        app.contact().delete(deleteContact);
        assertThat(app.contact().count(),  equalTo(before.size() - 1));

        //Contacts after = app.contact().all();

        Contacts after = app.db().contacts(); //Проверка списка контактов после удаления в бд
        assertThat(after, equalTo(before.withOut(deleteContact)));



    }


}









