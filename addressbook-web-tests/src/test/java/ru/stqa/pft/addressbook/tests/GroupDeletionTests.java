package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
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
        // Выполнение предусловия создания группы
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName(groupName));

        }
    }

    @Test
    public void testGroupDeletion() throws Exception {

        Groups before = app.group().all();
        GroupData deleteGroup = before.iterator().next();
        app.group().delete(deleteGroup);
        Groups after = app.group().all();
        Assert.assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.withOut(deleteGroup)));

        }



}

