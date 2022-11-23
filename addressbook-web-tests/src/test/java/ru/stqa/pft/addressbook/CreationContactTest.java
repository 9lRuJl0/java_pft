package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class CreationContactTest extends TestBase {



  @Test
  public void testCreationContact() throws Exception {

    gotoAddNew();
    fillContactForm(new ContactData("Tomas", "Anderson", "NEO", "MetaCortex", "312-555-0690", "test@test.com"));
    gotoEnter();
    gotoHomePage();
    gotoLogOut();
  }

}
