package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class CreationContactTest extends TestBase {

  String groupName = "test3";
  @Test
  public void testCreationContact() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup(groupName)) {
      app.getGroupHelper().createGroup(new GroupData(groupName, null, null));
    }
    app.getContactHelper().gotoAddNew();
    app.getContactHelper().fillContactForm(new ContactData("Tomas", "Anderson", "NEO", "MetaCortex", "312-555-0690", "test@test.com", groupName), true);
    app.getContactHelper().gotoEnter();
  }
}
