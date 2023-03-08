package ru.stqa.pft.rest;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import java.io.IOException;


    public class TestBase {

        public void skipIfNotFixed(int issueId) throws IOException {    // Метод для пропуска бага, если он ещё не исправлен
            if (isIssueOpen(issueId)) {
                throw new SkipException("Ignored because of issue " + issueId);
            }
        }

        private boolean isIssueOpen(int issueId) throws IOException {
            String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues/" + issueId + ".json")) // Получение информации о баг-репорте с заданным идентификатором
                    .returnContent().asString();
            JsonElement parsed = new JsonParser().parse(json);
            JsonObject parsedObject = parsed.getAsJsonObject();
            String status = parsedObject.get("issues").getAsJsonArray().get(0).getAsJsonObject().get("state_name").getAsString(); //ищем (парсим) в теле json- issue
            if (status.equals("Resolved")) {
                return false;
            }
            return true;
        }

        org.apache.http.client.fluent.Executor getExecutor() {          //метод для авторизации
            return Executor.newInstance().auth("b31e382ca8445202e66b03aaf31508a3", "");
        }

    }

