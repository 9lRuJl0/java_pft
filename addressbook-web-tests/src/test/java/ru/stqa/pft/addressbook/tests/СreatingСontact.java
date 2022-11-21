package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

public class СreatingСontact extends TestBase {

  @Test
  public void testCreatingContact() throws Exception {

    ApplicationManager app = new ApplicationManager();

    app.gotoAddNew();
    app.fillFormContact(new ContactData("Tomas", "Anderson", "MetaCortex", "NEO", "312-555-0690", "test@test.com"));
    app.enterSave();
    app.goOut();
  }



}
