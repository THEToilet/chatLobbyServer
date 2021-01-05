package jp.ac.shibaura.it.ie.test.data;

import java.util.LinkedHashMap;
import java.util.Map;

public class MessageMap {
    private Map<String, MessageTest> messageList = new LinkedHashMap<String, MessageTest>();

    public LinkedHashMap<String, MessageTest> getMessageList() {
        return (LinkedHashMap<String, MessageTest>) messageList;
    }

    public void setMessageList(String messageId, MessageTest messageTest) {
        this.messageList.put(messageId, messageTest);
    }
}
