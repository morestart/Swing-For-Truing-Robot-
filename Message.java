package CourseProject;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;

import java.io.IOException;
import java.util.UUID;

class Message {
    private String content;
//    private String code;

    void Run(String info) throws Exception {
        OkHttpClient client = new OkHttpClient();
        String url = "http://www.tuling123.com/openapi/api";
        String apiKey = "8532d61cc1a846c7b2af7e0842e1473d";

        GenerateUUID uuid = new GenerateUUID();
        String uid = uuid.getUUID();
        RequestBody formBody = new FormBody.Builder()
                .add("key", apiKey)
                .add("info", info)
                .add("userid", uid)
                .build();
        /* System.out.println(((FormBody) formBody).value(1)); */
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        assert response.body() != null;
        String s = response.body().string();

        /* System.out.println(s); */
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(s).getAsJsonObject();
        /* System.out.println(object.get("text")); */
        content = object.getAsJsonObject().get("text").getAsString();
//        code = object.getAsJsonObject().get("code").getAsString();
        /*
        System.out.println(code);
        System.out.println(content);
        */
    }

    String getContent() {
        return content;
    }

//    String getCode() {
//        return code;
//    }
//
//    String getS() {
//        return s;
//    }
}

class GenerateUUID {
    String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
