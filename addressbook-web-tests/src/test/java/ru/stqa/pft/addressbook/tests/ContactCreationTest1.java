package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;


public class ContactCreationTest1 extends TestBase {


    String groupName = "test1";

    @BeforeMethod
    public void ensurePreconditions() {
        // Проверка групп в базе данных
        if (app.db().groups().size() == 0) {
            // Выполнение предусловия создания группы
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(groupName));
        }
    }

    @Test
    public void testCreationContact1() {
        Groups groups = app.db().groups();
        File photo = new File("src/test/resources/hacker-man.png");
        ContactData newContact = new ContactData().withFirstname("Bill").withLastname("Gates").withPhoto(photo)
                .inGroup(groups.iterator().next());
        app.goTo().gotoHomePage();
        app.contact().gotoAddNew();
        app.contact().fillContactForm(newContact, true);
        app.contact().gotoEnter();
        app.contact().gotoHomePage();


    }

    @Test(enabled = false)
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/hacker-man.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }

}
