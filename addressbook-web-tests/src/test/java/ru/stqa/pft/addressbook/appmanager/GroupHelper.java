package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {

        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
        click(By.linkText("Logout"));
        wd.findElement(By.name("user")).clear();
      wd.findElement(By.name("user")).sendKeys("Admin");
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(groupData, By.name("group_name"));
        type(groupData, By.name("group_header"));
        type(groupData, By.name("group_footer"));
    }

    private void type(GroupData groupData, By locator) {
        type(locator, groupData.getHeader());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroup() {
        click(By.name("selected[]"));
    }
}
