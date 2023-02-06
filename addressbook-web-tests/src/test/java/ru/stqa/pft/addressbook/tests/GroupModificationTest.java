package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTest extends TestBase {
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

        public void testGroupModification() {


            Groups before = app.group().all();
            GroupData modifiedGroup = before.iterator().next();
            GroupData group = new GroupData().
                    withId(modifiedGroup.getId()).withName(groupName).withHeader("test2").withFooter("test3");
            app.goTo().groupPage();
            app.group().modify(group);
            assertThat(app.group().count(), equalTo(before.size()));
            Groups after = app.group().all();
            assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));
        }
}



