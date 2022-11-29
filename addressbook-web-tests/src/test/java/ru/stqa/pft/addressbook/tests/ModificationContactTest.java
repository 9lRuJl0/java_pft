package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ModificationContactTest  extends TestBase {

    @Test
    public void testModificationContact() {

        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectId(By.id("3"));
        app.getContactHelper().selectEdit();
        app.getContactHelper().fillContactForm(new ContactData("Tomas", "Anderson", "NEO", "MetaCortex", "312-555-0690", "test@test.com"));
        app.getContactHelper().selectUpdate();
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().gotoLogOut();


    }


}
