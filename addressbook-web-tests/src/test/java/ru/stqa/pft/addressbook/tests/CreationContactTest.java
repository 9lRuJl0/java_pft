package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class CreationContactTest extends TestBase {


  @Test
  public void testCreationContact() throws Exception {

    app.getContactHelper().gotoAddNew();
    if (! app.getContactHelper().isThereASelectGroups()) ;
    {
      app.getNavigationHelper().gotoGroupPage();
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
      app.getContactHelper().gotoAddNew();
    }

    app.getContactHelper().fillContactForm(new ContactData("Tomas", "Anderson", "NEO", "MetaCortex", "312-555-0690", "test@test.com", "test1"), true);

  }
}
