package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactPhoneTest  extends TestBase {

    String groupName = "test1";
    @BeforeMethod

    public void ensurePreconditions() {
        // Выполнение предусловия создания группы
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

    @Test

    public void testContactPhones() {
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    }
}
