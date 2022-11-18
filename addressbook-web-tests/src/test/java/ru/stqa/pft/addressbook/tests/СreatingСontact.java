package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class СreatingСontact extends TestBase {

  @Test
  public void testCreatingContact() throws Exception {

    gotoAddNew();
    fillFormContact(new ContactData("Tomas", "Anderson", "MetaCortex", "NEO", "312-555-0690", "test@test.com"));
    enterSave();
    goOut();
  }



}
