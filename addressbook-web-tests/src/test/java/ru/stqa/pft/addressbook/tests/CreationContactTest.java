package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class CreationContactTest extends TestBase {



  @Test
  public void testCreationContact() throws Exception {

    app.getContactHelper().gotoAddNew();
    app.getContactHelper().fillContactForm(new ContactData("Tomas", "Anderson", "NEO", "MetaCortex", "312-555-0690", "test@test.com"));
    app.getContactHelper().gotoEnter();
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().gotoLogOut();
  }

}
