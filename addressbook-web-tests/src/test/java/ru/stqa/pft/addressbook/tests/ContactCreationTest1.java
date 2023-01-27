package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.io.File;

public class ContactCreationTest1 extends TestBase {

    @Test
    public void testCreationContact1() {

        app.goTo().gotoHomePage();
        app.contact().gotoAddNew();
        File photo = new File("C:/Users/yaro-//Documents/GitHub/java_pft/addressbook-web-tests/src/tests/resources/hacker-man.png");
        app.contact().fillContactForm(
                new ContactData().withFirstname("Bill").withLastname("Gates").withPhoto(photo), true);
        app.contact().gotoEnter();
        app.contact().gotoHomePage();


    }

    @Test (enabled = false)
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("C:/Users/yaro-/Documents/GitHub/java_pft/addressbook-web-tests/src/tests/resources/hacker-man.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }

}
