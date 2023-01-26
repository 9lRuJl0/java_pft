package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

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
        app.contact().create(new ContactData().withFirstname("Tomas").withLastname("Anderson").withNickname("NEO"). withCompany("MetaCortex").withAddress("Wall street, house 1")
                .withHomePhone("312-555-0690").withMobilePhone("555555").withWorkPhone("1112233")
                .withEmail("test@test.com").withEmail2("test@test2.com").withEmail3("test@test3.com").withGroup(groupName));

    }

}

    @Test

    public void testContactPhones() {
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        MatcherAssert.assertThat(contact.getAllPhones(), CoreMatchers.equalTo(mergePhones(contactInfoFromEditForm)));
        MatcherAssert.assertThat(contact.getAddress(), CoreMatchers.equalTo(contactInfoFromEditForm.getAddress()));
        MatcherAssert.assertThat(contact.getAllemail(), CoreMatchers.equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> s != null && ! s.equals(""))
                //.map(ContactPhoneTest::cleaned)
                .collect(Collectors.joining("\n"));

    }



    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getFaxPhone())
                .stream().filter((s) -> s != null && ! s.equals(""))
                .map(ContactPhoneTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {

        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");

    }
}
