package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

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
    public void testGroupDeletion() throws Exception {
        Groups before = app.db().groups(); // Проверка списка групп в БД до удаления
        GroupData deleteGroup = before.iterator().next();
        app.goTo().groupPage();
        app.group().delete(deleteGroup);
        assertThat(app.group().count(), equalTo(before.size() - 1));
        Groups after = app.db().groups(); // Проверка списка групп в БД после удаления
        assertThat(after, equalTo(before.withOut(deleteGroup)));

        }
}

