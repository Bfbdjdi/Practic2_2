package oaosalty;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("\nБелин Георгий Алексеевич, РИБО-01-22, Вариант 1\n");

        /*String server = "https://www.mirea.ru/schedule/";
        HTTPRunnable hTTPRunnable = new HTTPRunnable(server, null);
        Thread th = new Thread(hTTPRunnable);
        th.start();

        try
        {
            th.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
           try {
               FileWriter fw = new FileWriter("C:\\Users\\googn\\Desktop\\resp.html");
               fw.write(hTTPRunnable.getResponseBody());
               fw.close();
               System.out.println("Success save response from server: " + server);
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
        }*/

        String server = "https://android-for-students.ru";
        String serverPath = "/materials/practical/hello.php";
        HashMap<String, String> map = new HashMap();
        map.put("name", "Belin");
        map.put("group", "RIBO-01-22");
        HTTPRunnable httpRunnable = new HTTPRunnable(server + serverPath, map);
        Thread th = new Thread(httpRunnable);
        th.start();
        try
        {
            th.join();
        }
        catch (InterruptedException ex) {}
        finally {
            try {
                System.out.println(httpRunnable.getResponseBody());

                JSONObject jSONObject = new JSONObject(httpRunnable.getResponseBody());
                int result = jSONObject.getInt("result_code");
                System.out.println("Result: " + result);
                System.out.println("Type: " + jSONObject.getString("message_type"));
                System.out.println("Text: " + jSONObject.getString("message_text"));

                switch (result) {
                    case 1:
                        JSONArray jSONArray = jSONObject.getJSONArray("task_list");
                        System.out.println("Task list: ");

                        for (int i = 0; i < jSONArray.length(); i++) {
                            System.out.println((i + 1) + ") " + jSONArray.get(i));
                        }
                        break;

                    case 0:
                        //ошибка

                    default:
                        break;
                }
            }
            catch (JSONException err){ System.out.println("Полученные данные не совпадают с шаблоном пакетов для получения. Последние не могут быть отображены."); }
        }
    }
}
