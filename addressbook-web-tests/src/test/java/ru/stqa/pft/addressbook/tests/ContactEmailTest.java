package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ContactEmailTest  extends TestBase {

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
            app.contact().create(new ContactData().withFirstname("Tomas").withLastname("Anderson")
                    .withNickname("NEO"). withCompany("MetaCortex").withTelephone("312-555-0690").withAddress("Wall street, house 1")
                    .withEmail("test@test.com").withEmail2("test@test2.com").withEmail3("test@test3.com").withGroup(groupName));
        }
    }


    @Test(enabled = false)

    public void testContactEmail() {
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        MatcherAssert.assertThat(contact.getAllemail(), CoreMatchers.equalTo(mergeEmail(contactInfoFromEditForm)));
        MatcherAssert.assertThat(contact.getAddress(), CoreMatchers.equalTo(contactInfoFromEditForm.getAddress()));
    }

    private String mergeEmail(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhoneTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleanedEmail(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");

    }
}



