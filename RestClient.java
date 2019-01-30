package services;

import javax.inject.Inject;

import play.mvc.*;
import play.libs.ws.*;
import java.util.concurrent.CompletionStage;

import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;

public class RestClient  { //implements WSBodyReadables, WSBodyWritables
    
    private final WSClient ws;
   

    private final String baseRestUrl = "https://rest.payamak-panel.com/api/SendSMS/";
    private String username, password;

    
    public RestClient(final WSClient ws) {
        this.ws = ws;
    }

    public void initCred(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public CompletionStage<WSResponse> SendSMS(String to, String from, String text, boolean flash) {
       
       JsonNode json = Json.newObject()
                    .put("username", username)
                    .put("password", password)
                    .put("to", to)
                    .put("from", from)
                    .put("text", text)
                    .put("isFlash", Boolean.toString(flash));

        return ws.url(baseRestUrl + "SendSMS").addHeader("Content-Type", "application/json")
            .post(json);
            //.thenApply(response ->
            //ok("result: " + response.getBody())//.asJson().findValues("full_name")
        //);    
    }

    public CompletionStage<WSResponse> SendByBaseNumber(String text, String to, int bodyId) {
       
       JsonNode json = Json.newObject()
                    .put("username", username)
                    .put("password", password)
                    .put("text", text)
                    .put("to", to)
                    .put("bodyId", String.valueOf(bodyId));

        return ws.url(baseRestUrl + "BaseServiceNumber").addHeader("Content-Type", "application/json")
            .post(json);
    }
    
    public CompletionStage<WSResponse> GetDeliveries2(long recId) {
       
       JsonNode json = Json.newObject()
                    .put("username", username)
                    .put("password", password)
                    .put("recId", String.valueOf(recId));

        return ws.url(baseRestUrl + "GetDeliveries2").addHeader("Content-Type", "application/json")
            .post(json);
    }

    public CompletionStage<WSResponse> GetMessages(int location, String from, String index, boolean count) {
       
       JsonNode json = Json.newObject()
                    .put("username", username)
                    .put("password", password)
                    .put("location", String.valueOf(location))
                    .put("from", from)
                    .put("index", index)
                    .put("count", Boolean.toString(count));

        return ws.url(baseRestUrl + "GetMessages").addHeader("Content-Type", "application/json")
            .post(json);
    }

    public CompletionStage<WSResponse> GetCredit() {
       
       JsonNode json = Json.newObject()
                    .put("username", username)
                    .put("password", password);;

        return ws.url(baseRestUrl + "GetCredit").addHeader("Content-Type", "application/json")
            .post(json);
    }

    public CompletionStage<WSResponse> GetBasePrice(long recId) {
       
       JsonNode json = Json.newObject()
                    .put("username", username)
                    .put("password", password);

        return ws.url(baseRestUrl + "GetBasePrice").addHeader("Content-Type", "application/json")
            .post(json);
    }


     public CompletionStage<WSResponse> GetUserNumbers(long recId) {
       
       JsonNode json = Json.newObject()
                    .put("username", username)
                    .put("password", password);

        return ws.url(baseRestUrl + "GetUserNumbers").addHeader("Content-Type", "application/json")
            .post(json);
    }

   
}
