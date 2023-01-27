package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreationContactTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    // Выполнение предусловия создания группы
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName(groupName));

    }
  }


  String groupName = "test1";
  @Test (enabled = false)
  public void testCreationContact() throws Exception {
    app.goTo().gotoHomePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Tomas").withLastname("Anderson").withNickname("NEO"). withCompany("MetaCortex").withTelephone("312-555-0690").withEmail("test@test.com").withGroup(groupName);
    app.contact().create(contact);
    assertThat(app.contact().count(),  equalTo(before.size() + 1));
    Contacts after = app.contact().all();


    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));


  }

}
