package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ModificationContactTest  extends TestBase {

    String groupName = "test1";

    @BeforeMethod
    public void ensurePreconditions() {

        // Проверка групп в базе данных
        if (app.db().groups().size() == 0) {
            // Выполнение предусловия создания группы
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(groupName));
            // Выполнение предусловия создания контакта
            app.goTo().gotoHomePage();
        }
            if (app.db().contacts().size() == 0)  {
                app.contact().create(new ContactData().withFirstname("Tomas").withLastname("Anderson")
                        .withNickname("NEO").withCompany("MetaCortex").withTelephone("312-555-0690")
                        .withEmail("test@test.com").withGroup(groupName));

            }
        }

   @Test

        public void testModificationContact() {
            app.goTo().gotoHomePage();
            Contacts before = app.db().contacts(); //Проверка списка контактов до модификации в бд
            ContactData modifiedContact = before.iterator().next();
            ContactData contact = new ContactData().
                    withId(modifiedContact.getId()).withFirstname("Yaroslav").withLastname("Sorokin").withNickname("NEO").withCompany("MetaCortex").withTelephone("312-555-0690").withEmail("test@test.com");
            app.contact().modify(contact);
            app.goTo().gotoHomePage();
            assertThat(app.contact().count(), equalTo(before.size()));
            Contacts after = app.db().contacts(); //Проверка списка контактов после модификации в бд
            assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));

        }
}


