package services;

import javax.inject.Inject;

import play.mvc.*;
import play.libs.ws.*;
import java.util.concurrent.CompletionStage;
import java.util.Arrays;
// import play.libs.Json;
// import com.fasterxml.jackson.databind.JsonNode;

public class SoapClient  { //implements WSBodyReadables, WSBodyWritables
    
    private final WSClient ws;
   

    private final String baseSoapUrl = "https://api.payamak-panel.com/post/";
    private final String _sendOp = "send.asmx";
    private final String _receiveOp = "receive.asmx";
    private final String _contactsOp = "contacts.asmx";
    private final String _actionsOp = "actions.asmx";
    private final String _scheduleOp = "schedule.asmx";
    private final String _ticketsOp = "tickets.asmx";
    private final String _usersOp = "users.asmx";
    private final String _voiceOp = "voice.asmx";

    private String username, password;

    
    public SoapClient(final WSClient ws) {
        this.ws = ws;
    }

    public void initCred(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public CompletionStage<WSResponse> SendSimpleSMS2(String to, String from, String msg, boolean flash) {
      	String _func = "SendSimpleSMS2";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + to + "</to><from>" + from + "</from><text>" + msg + "</text><isflash>" + Boolean.toString(flash) + "</isflash></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> SendSimpleSMS(String[] to, String from, String msg, Boolean flash) {

        String _to = "<string>" + String.join("</string><string>", to) + "</string>";

        String _func = "SendSimpleSMS";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + _to + "</to><from>" + from + "</from><text>" + msg + "</text><isflash>" + Boolean.toString(flash) + "</isflash></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> SendSms(String[] to, String from, String msg, boolean flash, String udh, long[] recid) {
        
        String _to = "<string>" + String.join("</string><string>", to) + "</string>";
        String _recid = "<long>" + String.join("</long><long>", Arrays.toString(recid)) + "</long>";

        String _func = "SendSms";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + _to + "</to><from>" + from + "</from><text>" + msg + "</text><isflash>" + Boolean.toString(flash) + "</isflash><udh>" + udh + "</udh><recId>" + _recid + "</recId></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> SendWithDomain(String to, String from, String msg, boolean flash, String domain) {
       
        String _func = "SendWithDomain";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + to + "</to><from>" + from + "</from><text>" + msg + "</text><isflash>" + Boolean.toString(flash) + "</isflash><domainName>" + domain + "</domainName></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> SendByBaseNumber(String[] text, String to, int bodyId) {
        
        String _text = "<string>" + String.join("</string><string>", text) + "</string>";

        String _func = "SendByBaseNumber";
      String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><text>" + _text + "</text><to>" + to + "</to><bodyId>" + String.valueOf(bodyId) + "</bodyId></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> SendByBaseNumber2(String text, String to, int bodyId) {
        
        String _func = "SendByBaseNumber2";
      String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><text>" + text + "</text><to>" + to + "</to><bodyId>" + String.valueOf(bodyId) + "</bodyId></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> getMessages(int location, String from, int index, int count) {

        String _func = "getMessages";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><from>" + from + "</from><index>" + String.valueOf(index) + "</index><count>" + String.valueOf(count) + "</count></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetSmsPrice(int irancellCount, int mtnCount, String from, String text) {

        String _func = "GetSmsPrice";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><irancellCount>" + String.valueOf(irancellCount) + "</irancellCount><mtnCount>" + String.valueOf(mtnCount) + "</mtnCount><from>"+ from +"</from><text>"+ text +"</text></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetMultiDelivery2(String recId) {

        String _func = "GetMultiDelivery2";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><recId>" + recId + "</recId></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetMultiDelivery(String recId) {

        String _func = "GetMultiDelivery";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><recId>" + recId + "</recId></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetInboxCount(boolean isRead) {

        String _func = "GetInboxCount";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><isRead>" + Boolean.toString(isRead) + "</isRead></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetDelivery2(String recId) {

        String _func = "GetDelivery2";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><recId>" + recId + "</recId></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetDelivery(String recId) {

        String _func = "GetDelivery";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><recId>" + recId + "</recId></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetDeliveries3(String[] recIds) {

        String _recids = "<string>" + String.join("</string><string>", recIds) + "</string>";

        String _func = "GetDeliveries3";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><recId>" + _recids + "</recId></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
 	public CompletionStage<WSResponse> GetDeliveries2(String recId) {

        String _func = "GetDeliveries2";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><recId>" + recId + "</recId></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetDeliveries(long[] recIds) {

        String _recids = "<long>" + String.join("</long><long>", Arrays.toString(recIds)) + "</long>";

        String _func = "GetDeliveries";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><recId>" + _recids + "</recId></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetCredit() {

        String _func = "GetCredit";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }

    

    //Receive API Operations
    public CompletionStage<WSResponse> RemoveMessages2(int location, String msgIds) {

        String _func = "RemoveMessages2";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><msgIds>" + msgIds + "</msgIds></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    //use Received or Sent or Removed or Deleted for location in the next method
    public CompletionStage<WSResponse> RemoveMessages(String location, String msgIds) {

        String _func = "RemoveMessages";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + location + "</location><msgIds>" + msgIds + "</msgIds></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetUsersMessagesByDate(int location, String from, int index, int count, String dateFrom, String dateTo) {

        String _func = "GetUsersMessagesByDate";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><from>" + from + "</from><index>" + String.valueOf(index) + "</index><count>" + String.valueOf(count) +"</count><dateFrom>" + dateFrom + "</dateFrom><dateTo>" + dateTo + "</dateTo></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetOutBoxCount() {

        String _func = "GetOutBoxCount";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetMessagesWithChangeIsRead(int location, String from, int index, int count, boolean isRead, boolean changeIsRead) {

       String _func = "GetMessagesWithChangeIsRead";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><from>" + from + "</from><index>" + String.valueOf(index) + "</index><count>" + String.valueOf(count) +"</count><isRead>" + Boolean.toString(isRead) + "</isRead><changeIsRead>" + Boolean.toString(changeIsRead) + "</changeIsRead></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetMessagesReceptions(int msgId, int fromRows) {

        String _func = "GetMessagesReceptions";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><msgId>" + String.valueOf(msgId) + "</msgId><fromRows>" + String.valueOf(fromRows) + "</fromRows></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetMessagesFilterByDate(int location, String from, int index, int count, String dateFrom, String dateTo, boolean isRead) {

        String _func = "GetMessagesFilterByDate";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><from>" + from + "</from><index>" + String.valueOf(index) + "</index><count>" + String.valueOf(count) + "</count><dateFrom>" + dateFrom + "</dateFrom><dateTo>" + dateTo + "</dateTo><isRead>" + Boolean.toString(isRead) + "</isRead></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetMessagesByDate(int location, String from, int index, int count, String dateFrom, String dateTo) {

        String _func = "GetMessagesByDate";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><from>" + from + "</from><index>" + String.valueOf(index) + "</index><count>" + String.valueOf(count) + "</count><dateFrom>" + dateFrom + "</dateFrom><dateTo>" + dateTo + "</dateTo></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetMessagesAfterIDJson(int location, String from, int count, int msgId) {

        String _func = "GetMessagesAfterIDJson";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><from>" + from + "</from><count>" + String.valueOf(count) + "</count><msgId>" + String.valueOf(msgId) + "</msgId></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetMessagesAfterID(int location, String from, int count, int msgId) {

        String _func = "GetMessagesAfterID";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><from>" + from + "</from><count>" + String.valueOf(count) + "</count><msgId>" + String.valueOf(msgId) + "</msgId></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetMessages(int location, String from, int index, int count) {

        String _func = "GetMessages";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) +"</location><from>" + from + "</from><index>" + String.valueOf(index) + "</index><count>" + String.valueOf(count) + "</count></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetMessageStr(int location, String from, int index, int count) {

        String _func = "GetMessageStr";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) +"</location><from>" + from + "</from><index>" + String.valueOf(index) + "</index><count>" + String.valueOf(count) + "</count></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> ChangeMessageIsRead(String msgIds) {

        String _func = "ChangeMessageIsRead";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><msgIds>" + msgIds + "</msgIds></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }

    //Users API Operations
    public CompletionStage<WSResponse> AddPayment(String name, String family, String bankName, String code, double amount, String cardNumber) {

        String _func = "AddPayment";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><name>"+name+"</name><family>"+family+"</family><bankName>"+bankName+"</bankName><code>"+code+"</code><amount>"+String.valueOf(amount)+"</amount><cardNumber>"+cardNumber+"</cardNumber></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> AddUser(int productId, String descriptions, String mobileNumber, String emailAddress, String nationalCode, String name, String family, String corporation, String phone, String fax, String address, String postalCode, String certificateNumber) {
 
        String _func = "AddUser";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><productId>" + String.valueOf(productId) + "</productId><descriptions>"+descriptions+"</descriptions><mobileNumber>"+mobileNumber+"</mobileNumber><emailAddress>"+emailAddress+"</emailAddress><nationalCode>"+nationalCode+"</nationalCode><name>"+name+"</name><family>"+family+"</family><corporation>"+corporation+"</corporation><phone>"+phone+"</phone><fax>"+fax+"</fax><address>"+address+"</address><postalCode>"+postalCode+"</postalCode><certificateNumber>"+certificateNumber+"</certificateNumber></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> AddUserComplete(int productId, String descriptions, String mobileNumber, String emailAddress, String nationalCode, String name, String family, String corporation, String phone, String fax, String address, String postalCode, String certificateNumber, int country, int province, int city, String howFindUs, String commercialCode, String saleId, String recommanderId) {
 
        String _func = "AddUserComplete";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><productId>" + String.valueOf(productId) + "</productId><descriptions>"+descriptions+"</descriptions><mobileNumber>"+mobileNumber+"</mobileNumber><emailAddress>"+emailAddress+"</emailAddress><nationalCode>"+nationalCode+"</nationalCode><name>"+name+"</name><family>"+family+"</family><corporation>"+corporation+"</corporation><phone>"+phone+"</phone><fax>"+fax+"</fax><address>"+address+"</address><postalCode>"+postalCode+"</postalCode><certificateNumber>"+certificateNumber+"</certificateNumber><country>"+String.valueOf(country)+"</country><province>"+String.valueOf(province)+"</province><city>"+String.valueOf(city)+"</city><howFindUs>"+howFindUs+"</howFindUs><commercialCode>"+commercialCode+"</commercialCode><saleId>"+saleId+"</saleId><recommanderId>"+recommanderId+"</recommanderId></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> AddUserWithLocation(int productId, String descriptions, String mobileNumber, String emailAddress, String nationalCode, String name, String family, String corporation, String phone, String fax, String address, String postalCode, String certificateNumber, int country, int province, int city) {
 
        String _func = "AddUserWithLocation";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><productId>" + String.valueOf(productId) + "</productId><descriptions>"+descriptions+"</descriptions><mobileNumber>"+mobileNumber+"</mobileNumber><emailAddress>"+emailAddress+"</emailAddress><nationalCode>"+nationalCode+"</nationalCode><name>"+name+"</name><family>"+family+"</family><corporation>"+corporation+"</corporation><phone>"+phone+"</phone><fax>"+fax+"</fax><address>"+address+"</address><postalCode>"+postalCode+"</postalCode><certificateNumber>"+certificateNumber+"</certificateNumber><country>"+String.valueOf(country)+"</country><province>"+String.valueOf(province)+"</province><city>"+String.valueOf(city)+"</city></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> AddUserWithMobileNumber(int productId, String mobileNumber, String firstName, String lastName, String email) {

        String _func = "AddUserWithMobileNumber";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><productId>"+String.valueOf(productId)+"</productId><mobileNumber>"+mobileNumber+"</mobileNumber><firstName>"+firstName+"</firstName><lastName>"+lastName+"</lastName><email>"+email+"</email></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> AddUserWithUserNameAndPass(String targetUserName, String targetUserPassword, int productId, String descriptions, String mobileNumber, String emailAddress, String nationalCode, String name, String family, String corporation, String phone, String fax, String address, String postalCode, String certificateNumber) {

        String _func = "AddUserWithUserNameAndPass";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><targetUserName>"+targetUserName+"</targetUserName><targetUserPassword>"+targetUserPassword+"</targetUserPassword><productId>"+String.valueOf(productId)+"</productId><descriptions>"+descriptions+"</descriptions><mobileNumber>"+mobileNumber+"</mobileNumber><emailAddress>"+emailAddress+"</emailAddress><nationalCode>"+nationalCode+"</nationalCode><name>"+name+"</name><family>"+family+"</family><corporation>"+corporation+"</corporation><phone>"+phone+"</phone><fax>"+fax+"</fax><address>"+address+"</address><postalCode>"+postalCode+"</postalCode><certificateNumber>"+certificateNumber+"</certificateNumber></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> AuthenticateUser() {

        String _func = "AuthenticateUser";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> ChangeUserCredit(double amount, String description, String targetUsername, boolean GetTax) {

        String _func = "ChangeUserCredit";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><amount>" + String.valueOf(amount) + "</amount><description>"+description+"</description><targetUsername>"+targetUsername+"</targetUsername><GetTax>"+Boolean.toString(GetTax)+"</GetTax></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> ChangeUserCredit2(String amount, String description, String targetUsername, boolean GetTax) {

        String _func = "ChangeUserCredit2";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><amount>" + amount + "</amount><description>"+description+"</description><targetUsername>"+targetUsername+"</targetUsername><GetTax>"+Boolean.toString(GetTax)+"</GetTax></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> ChangeUserCreditBySeccondPass(String ausername, String seccondPassword, double amount, String description, String targetUsername, boolean GetTax) {

        String _func = "ChangeUserCreditBySeccondPass";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>"+ausername+"</username><seccondPassword>"+seccondPassword+"</seccondPassword><amount>"+String.valueOf(amount)+"</amount><description>"+description+"</description><targetUsername>"+targetUsername+"</targetUsername><GetTax>"+Boolean.toString(GetTax)+"</GetTax></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }

    public CompletionStage<WSResponse> DeductUserCredit(String ausername, String apassword, double amount, String description) {

        String _func = "DeductUserCredit";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>"+ausername+"</username><password>"+apassword+"</password><amount>"+String.valueOf(amount)+"</amount><description>"+description+"</description></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }

    public CompletionStage<WSResponse> ForgotPassword(String mobileNumber, String emailAddress, String targetUsername) {

        String _func = "ForgotPassword";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><mobileNumber>"+mobileNumber+"</mobileNumber><emailAddress>"+emailAddress+"</emailAddress><targetUsername>"+targetUsername+"</targetUsername></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetCities(int provinceId) {

        String _func = "GetCities";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><provinceId>" + String.valueOf(provinceId) + "</provinceId></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetEnExpireDate() {

       String _func = "GetEnExpireDate";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetExpireDate() {

        String _func = "GetExpireDate";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetProvinces() {

        String _func = "GetProvinces";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetUserBasePrice(String targetUsername) {

        String _func = "GetUserBasePrice";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><targetUsername>" + targetUsername + "</targetUsername></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetUserByUserID(String pass, int userId) {

        String _func = "GetUserByUserID";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><pass>"+pass+"</pass><userId>"+String.valueOf(userId)+"</userId></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetUserCredit(String targetUsername) {

        String _func = "GetUserCredit";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><targetUsername>"+targetUsername+"</targetUsername></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetUserCreditBySecondPass(String secondPassword, String targetUsername) {

        String _func = "GetUserCreditBySecondPass";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><secondPassword>"+secondPassword+"</secondPassword><targetUsername>"+targetUsername+"</targetUsername></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetUserDetails(String targetUsername) {
        String _func = "GetUserDetails";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><targetUsername>"+targetUsername+"</targetUsername></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetUserIsExist(String targetUsername) {

        String _func = "GetUserIsExist";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><targetUsername>"+targetUsername+"</targetUsername></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetUserNumbers() {

        String _func = "GetUserNumbers";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetUserTransactions(String targetUsername, String creditType, String dateFrom, String dateTo, String keyword) {

        String _func = "GetUserTransactions";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><targetUsername>"+targetUsername+"</targetUsername><creditType>"+creditType+"</creditType><dateFrom>"+dateFrom+"</dateFrom><dateTo>"+dateTo+"</dateTo><keyword>"+keyword+"</keyword></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetUserWallet() {

        String _func = "GetUserWallet";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetUserWalletTransaction(String dateFrom, String dateTo, int count, int startIndex, String payType, String PayLoc) {

        String _func = "GetUserWalletTransaction";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><dateFrom>" + dateFrom + "</dateFrom><dateTo>" + dateTo + "</dateTo><count>" + String.valueOf(count) + "</count><startIndex>" + String.valueOf(startIndex) + "</startIndex><payType>" + payType + "</payType><payLoc>" + PayLoc + "</payLoc></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetUsers() {

        String _func = "GetUsers";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> HasFilter(String text) {

        String _func = "HasFilter";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><text>" + text + "</text></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> RemoveUser(String targetUsername) {

        String _func = "RemoveUser";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><targetUsername>" +targetUsername + "</targetUsername></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> RevivalUser(String targetUsername) {

        String _func = "RevivalUser";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><targetUserName>" + targetUsername + "</targetUserName></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }

    //Contact API Operations
    public CompletionStage<WSResponse> AddContact(String groupIds, String firstname, String lastname, String nickname, String corporation, String mobilenumber, String phone, String fax, String birthdate, String email, int gender, int province, int city, String address, String postalCode, String additionaldate, String additionaltext, String descriptions) {

        String _func = "AddContact";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><groupIds>" + groupIds + "</groupIds><firstname>" + firstname + "</firstname><lastname>" + lastname + "</lastname><nickname>" + nickname + "</nickname><corporation>" + corporation + "</corporation><mobilenumber>" + mobilenumber + "</mobilenumber><phone>" + phone + "</phone><fax>" + fax + "</fax><birthdate>" + birthdate + "</birthdate><email>" + email + "</email><gender>" + String.valueOf(gender) + "</gender><province>" + String.valueOf(province) + "</province><city>" + String.valueOf(city) + "</city><address>" + address + "</address><postalCode>" + postalCode + "</postalCode><additionaldate>" + additionaldate + "</additionaldate><additionaltext>" + additionaltext + "</additionaltext><descriptions>" + descriptions + "</descriptions></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> AddContactEvents(int contactId, String eventName, int eventType, String eventDate) {

        String _func = "AddContactEvents";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><contactId>" + String.valueOf(contactId) + "</contactId><eventName>" + eventName + "</eventName><eventDate>" + eventDate + "</eventDate><eventType>" + String.valueOf(eventType) + "</eventType></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> AddGroup(String groupName, String Descriptions, boolean showToChilds) {

        String _func = "AddGroup";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><groupName>" + groupName + "</groupName><Descriptions>" + Descriptions + "</Descriptions><showToChilds>" + Boolean.toString(showToChilds) + "</showToChilds></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> ChangeContact(int contactId, String mobilenumber, String firstname, String lastname, String nickname, String corporation, String phone, String fax, String email, int gender, int province, int city, String address, String postalCode, String additionaltext, String descriptions, int contactStatus) {

        String _func = "ChangeContact";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><contactId>" + String.valueOf(contactId) + "</contactId><mobilenumber>" + mobilenumber + "</mobilenumber><firstname>" + firstname + "</firstname><lastName>" + lastname + "</lastname><nickname>" + nickname + "</nickname><corporation>" + corporation + "</corporation><phone>" + phone + "</phone><fax>" + fax + "</fax><email>" + email + "</email><gender>" + String.valueOf(gender) + "</gender><province>" + String.valueOf(province) + "</province><city>" + String.valueOf(city) + "</city><address>" + address + "</address><postalCode>" + postalCode + "</postalCode><additionaltext>" + additionaltext + "</additionaltext><descriptions>" + descriptions + "</descriptions><contactStatus>" + String.valueOf(contactStatus) + "</contactStatus></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> ChangeGroup(int groupId, String groupName, String Descriptions, boolean showToChilds, int groupStatus) {

        String _func = "ChangeGroup";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><groupId>" + String.valueOf(groupId) + "</groupId><groupName>" + groupName + "</groupName><Descriptions>" + Descriptions + "</Descriptions><showToChilds>" + Boolean.toString(showToChilds) + "</showToChilds><groupStatus>" + String.valueOf(groupStatus) + "</groupStatus></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> CheckMobileExistInContact(String mobileNumber) {

        String _func = "CheckMobileExistInContact";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><mobileNumber>" + mobileNumber + "</mobileNumber></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetContactEvents(int contactId) {

        String _func = "GetContactEvents";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><contactId>" + String.valueOf(contactId) + "</contactId></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetContacts(int groupId, String keyword, int from, int count) {

        String _func = "GetContacts";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><groupId>" + String.valueOf(groupId) + "</groupId><keyword>" + keyword + "</keyword><from>" + String.valueOf(from) + "</from><count>" + String.valueOf(count) + "</count></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetContactsByID(int contactId, int status) {

        String _func = "GetContactsByID";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><contactId>" + String.valueOf(contactId) + "</contactId><status>" + String.valueOf(status) + "</status></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetGroups() {

        String _func = "GetGroups";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> MergeGroups(int originGroupId, int destinationGroupId, boolean deleteOriginGroup) {

        String _func = "MergeGroups";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><originGroupId>" + String.valueOf(originGroupId) + "</originGroupId><destinationGroupId>" + String.valueOf(destinationGroupId) + "</destinationGroupId><deleteOriginGroup>" + Boolean.toString(deleteOriginGroup) + "</deleteOriginGroup></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> RemoveContact(String mobilenumber) {
 
        String _func = "RemoveContact";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><mobilenumber>" + mobilenumber + "</mobilenumber></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> RemoveContactByContactID(int contactId) {

        String _func = "RemoveContactByContactID";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><contactId>" + String.valueOf(contactId) + "</contactId></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> RemoveGroup(int groupId) {

        String _func = "RemoveGroup";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><groupId>" + String.valueOf(groupId) + "</groupId></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }

    

    //ACtions API Operations
    public CompletionStage<WSResponse> AddBranch(String branchName, int owner) {

        String _func = "AddBranch";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><branchName>" + branchName + "</branchName><owner>" + String.valueOf(owner) + "</owner></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> AddBulk(String from, int branch, int bulkType, String title, String message, String rangeFrom, String rangeTo, String DateToSend, int requestCount, int rowFrom) {

        String _func = "AddBulk";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><from>" + from + "</from><branch>" +String.valueOf(branch) + "</branch><bulkType>" + String.valueOf(bulkType) + "</bulkType><title>" + title + "</title><message>" + message + "</message><rangeFrom>" + rangeFrom + "</rangeFrom><rangeTo>" + rangeTo + "</rangeTo><DateToSend>" + DateToSend + "</DateToSend><requestCount>" + String.valueOf(requestCount) + "</requestCount><rowFrom>" + String.valueOf(rowFrom) + "</rowFrom></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> AddBulk2(String from, int branch, int bulkType, String title, String message, String rangeFrom, String rangeTo, String DateToSend, int requestCount, int rowFrom) {

        String _func = "AddBulk2";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><from>" + from + "</from><branch>" +String.valueOf(branch) + "</branch><bulkType>" + String.valueOf(bulkType) + "</bulkType><title>" + title + "</title><message>" + message + "</message><rangeFrom>" + rangeFrom + "</rangeFrom><rangeTo>" + rangeTo + "</rangeTo><DateToSend>" + DateToSend + "</DateToSend><requestCount>" + String.valueOf(requestCount) + "</requestCount><rowFrom>" + String.valueOf(rowFrom) + "</rowFrom></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> AddNewBulk(String from, int branch, int bulkType, String title, String message, String rangeFrom, String rangeTo, String DateToSend, int requestCount, int rowFrom) {

        String _func = "AddNewBulk";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><from>" + from + "</from><branch>" +String.valueOf(branch) + "</branch><bulkType>" + String.valueOf(bulkType) + "</bulkType><title>" + title + "</title><message>" + message + "</message><rangeFrom>" + rangeFrom + "</rangeFrom><rangeTo>" + rangeTo + "</rangeTo><DateToSend>" + DateToSend + "</DateToSend><requestCount>" + String.valueOf(requestCount) + "</requestCount><rowFrom>" + String.valueOf(rowFrom) + "</rowFrom></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> AddNumber(int branchId, String[] mobileNumbers) {

        String _mobileNumbers = "<string>" + String.join("</string><string>", mobileNumbers) + "</string>";

        String _func = "AddNumber";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><branchId>" + String.valueOf(branchId) + "</branchId><mobileNumbers>" + _mobileNumbers + "</mobileNumbers></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetBranchs(int owner) {

        String _func = "GetBranchs";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><owner>" + String.valueOf(owner) + "</owner></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetBulk() {

        String _func = "GetBulk";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetBulkCount(int branch, String rangeFrom, String rangeTo) {

        String _func = "GetBulkCount";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><branch>" + String.valueOf(branch) + "</branch><rangeFrom>" + rangeFrom + "</rangeFrom><rangeTo>" + rangeTo + "</rangeTo></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetBulkReceptions(int bulkId, int fromRows) {

        String _func = "GetBulkReceptions";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><bulkId>" + String.valueOf(bulkId) + "</bulkId><fromRows>" + String.valueOf(fromRows) + "</fromRows></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetBulkStatus(int bulkId, int sent, int failed, int status) {

        String _func = "GetBulkStatus";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><bulkId>" + String.valueOf(bulkId) + "</bulkId><sent>" + String.valueOf(sent) + "</sent><failed>" + String.valueOf(failed) + "</failed><status>" + String.valueOf(status) + "</status></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetMessagesReceptions(long msgId, int fromRows) {

        String _func = "GetMessagesReceptions";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><msgId>" + String.valueOf(msgId) + "</msgId><fromRows>" + String.valueOf(fromRows) + "</fromRows></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetMobileCount(int branch, String rangeFrom, String rangeTo) {

        String _func = "GetMobileCount";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><branch>"+ String.valueOf(branch) + "</branch><rangeFrom>" + rangeFrom + "</rangeFrom><rangeTo>" + rangeTo + "</rangeTo></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetSendBulk() {

        String _func = "GetSendBulk";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetTodaySent() {

        String _func = "GetTodaySent";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetTotalSent() {

        String _func = "GetTotalSent";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> RemoveBranch(int branchId) {

        String _func = "RemoveBranch";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><branchId>" + String.valueOf(branchId) + "</branchId></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }

    public CompletionStage<WSResponse> RemoveBulk(int bulkId) {

        String _func = "RemoveBulk";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><bulkId>" + String.valueOf(bulkId) + "</bulkId></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> SendMultipleSMS(String[] to, String from, String[] text, boolean isflash, String udh, long[] recId, String status) {

		String _to = "<string>" + String.join("</string><string>", to) + "</string>";
		String _text = "<string>" + String.join("</string><string>", text) + "</string>";
		String _recId = "<long>" + String.join("</long><long>", Arrays.toString(recId)) + "</long>";

        String _func = "SendMultipleSMS";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + _to + "</to><from>" + from + "</from><text>" + _text + "</text><isflash>" + Boolean.toString(isflash) + "</isflash><udh>" + udh + "</udh><recId>" + _recId + "</recId><status>" + status + "</status></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> SendMultipleSMS2(String[] to, String[] from, String[] text, boolean isflash, String udh, long[] recId, String status) {

		String _to = "<string>" + String.join("</string><string>", to) + "</string>";
		String _text = "<string>" + String.join("</string><string>", text) + "</string>";
		String _from = "<string>" + String.join("</string><string>", from) + "</string>";
		String _recId = "<long>" + String.join("</long><long>", Arrays.toString(recId)) + "</long>";

        String _func = "SendMultipleSMS2";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + _to + "</to><from>" + _from + "</from><text>" + _text + "</text><isflash>" + Boolean.toString(isflash) + "</isflash><udh>" + udh + "</udh><recId>" + _recId + "</recId><status>" + status + "</status></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> UpdateBulkDelivery(int bulkId) {

        String _func = "UpdateBulkDelivery";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><bulkId>" + String.valueOf(bulkId) + "</bulkId></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }

    

    //Schedule API Operations
    public CompletionStage<WSResponse> AddMultipleSchedule(String[] to, String from, String[] text, boolean isflash, String[] scheduleDateTime, String period) {

		String _to = "<string>" + String.join("</string><string>", to) + "</string>";
		String _text = "<string>" + String.join("</string><string>", text) + "</string>";
		String _schDates = "<dateTime>" + String.join("</dateTime><dateTime>", scheduleDateTime) + "</dateTime>";

        String _func = "AddMultipleSchedule";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>"+_to+"</to><from>" + from + "</from><text>" + _text + "</text><isflash>" + Boolean.toString(isflash) + "</isflash><scheduleDateTime>" + _schDates + "</scheduleDateTime><period>" + period + "</period></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _scheduleOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> AddNewUsance(String to, String from, String text, boolean isflash, String scheduleStartDateTime, int countrepeat, String scheduleEndDateTime, String periodType) {

        String _func = "AddNewUsance";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + to + "</to><from>" + from + "</from><text>" + text + "</text><isflash>" + Boolean.toString(isflash) + "</isflash><scheduleStartDateTime>" +scheduleStartDateTime + "</scheduleStartDateTime><countrepeat>" + String.valueOf(countrepeat) + "</countrepeat><periodType>" + periodType + "</periodType></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _scheduleOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> AddSchedule(String to, String from, String text, boolean isflash, String scheduleDateTime, String period) {

        String _func = "AddSchedule";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + to + "</to><from>" + from + "</from><text>" + text + "</text><isflash>" + Boolean.toString(isflash) + "</isflash><scheduleDateTime>" + scheduleDateTime + "</scheduleDateTime><period>" + period + "</period></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _scheduleOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> AddUsance(String to, String from, String text, boolean isflash, String scheduleStartDateTime, int repeatAfterDays, String scheduleEndDateTime) {

        String _func = "AddUsance";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + to + "</to><from>" + from + "</from><text>" + text + "</text><isflash>" + Boolean.toString(isflash) + "</isflash><scheduleStartDateTime>" + scheduleStartDateTime + "</scheduleStartDateTime><repeatAfterDays>" + String.valueOf(repeatAfterDays) + "</repeatAfterDays><scheduleEndDateTime>" + scheduleEndDateTime + "</scheduleEndDateTime></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _scheduleOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> GetScheduleStatus(int scheduleId) {

        String _func = "GetScheduleStatus";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><scheduleId>" + String.valueOf(scheduleId) + "</scheduleId></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _scheduleOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> RemoveSchedule(int scheduleId) {

        String _func = "RemoveSchedule";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><scheduleId>" + String.valueOf(scheduleId) + "</scheduleId></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _scheduleOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
    public CompletionStage<WSResponse> RemoveScheduleList(int[] scheduleIdList) {

		String _list = "<int>" + String.join("</int><int>", Arrays.toString(scheduleIdList)) + "</int>";

        String _func = "RemoveScheduleList";
    	String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><scheduleIdList>" + _list + "</scheduleIdList></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _scheduleOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
	}

    // Voice API Operations
    public CompletionStage<WSResponse> GetSendSMSWithSpeechTextStatus(int recId) {

        String _func = "GetSendSMSWithSpeechTextStatus";
        String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><recId>" + String.valueOf(recId) + "</recId></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _voiceOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }

    public CompletionStage<WSResponse> SendBulkSpeechText(String title, String body, String receivers, String DateToSend, int repeatCount) {

        String _func = "SendBulkSpeechText";
        String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><title>" + title + "</title><body>" + body + "</body><receivers>" + receivers + "</receivers><DateToSend>" + DateToSend + "</DateToSend><repeatCount>" + String.valueOf(repeatCount) + "</repeatCount></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _voiceOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }

    public CompletionStage<WSResponse> SendBulkVoiceSMS(String title, int voiceFileId, String receivers, String DateToSend, int repeatCount) {

        String _func = "SendBulkVoiceSMS";
        String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><title>" + title + "</title><voiceFileId>" + String.valueOf(voiceFileId) + "</voiceFileId><receivers>" + receivers + "</receivers><DateToSend>" + DateToSend + "</DateToSend><repeatCount>" + String.valueOf(repeatCount) + "</repeatCount></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _voiceOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }

    public CompletionStage<WSResponse> SendSMSWithSpeechText(String smsBody, String speechBody, String from, String to) {

        String _func = "SendSMSWithSpeechText";
        String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><smsBody>" + smsBody + "</smsBody><speechBody>" + speechBody + "</speechBody><from>" + from + "</from><to>" + to + "</to></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _voiceOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }

    public CompletionStage<WSResponse> SendSMSWithSpeechTextBySchduleDate(String smsBody, String speechBody, String from, String to, String scheduleDate) {

        String _func = "SendSMSWithSpeechTextBySchduleDate";
        String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><smsBody>" + smsBody + "</smsBody><speechBody>" + speechBody + "</speechBody><from>" + from + "</from><to>" + to + "</to><scheduleDate>" + scheduleDate + "</scheduleDate></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _voiceOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }

    public CompletionStage<WSResponse> UploadVoiceFile(String title, String base64StringFile) {

        String _func = "UploadVoiceFile";
        String wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><title>" + title + "</title><base64StringFile>" + base64StringFile + "</base64StringFile></"+ _func +"></soap:Body></soap:Envelope>";
     
        return ws.url(baseSoapUrl + _voiceOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq);
    }
   
}
