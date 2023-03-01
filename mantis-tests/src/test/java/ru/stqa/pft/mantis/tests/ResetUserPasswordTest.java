package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;



public class ResetUserPasswordTest extends TestBase {


    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }



    @Test
    public void testResetUserPassword() throws MessagingException, IOException {


        String password = "VzlomPentagona";
        app.userHelper().loginAdministrator();
        app.userHelper().goToUserPage();
        String user = app.getUserName();
        String email = String.format("%s@localhost.localadmin", user);
        app.userHelper().resetPassword();

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 60000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        Assert.assertTrue(app.newSession().login(user, password));

    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().findFirst().get();                                              //.filter((m) -> m.to.equals(email)).
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();

    }

}
