package ru.stqa.pft.rest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Set;
import static org.testng.Assert.assertEquals;



public class RestTests {

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues = getIssues(); // новые баг репорты
        oldIssues.add(newIssue.withId(issueId)); //старые баг репорты
        assertEquals(newIssues, oldIssues); // сравнение баг репортов
    }

    private Set<Issue> getIssues() throws IOException {    //способ работы с http во fluent-стиле
        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues"); //ищем (парсим) в теле json- issue
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType()); //преобразовываем в нужный вид
    }

    private Executor getExecutor() { //метод для автороцизации
        return Executor.newInstance().auth("b31e382ca8445202e66b03aaf31508a3", "");
    }

    private int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json")
                        .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                                new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt(); // идентификатор созданного баг-репорта
    }
}

