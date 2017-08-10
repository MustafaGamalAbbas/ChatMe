package android.mobilestudio.chatme.models.message.parent;

/**
 * Created by pisoo on 7/31/2017.
 */

public class Message {
    private Boolean it_is_myMessage;
    private String content;

    public void sentMessage() {
    }

    public void editMessage() {
    }

    public void deleteMessage() {
    }

    public Boolean getIt_is_myMessage() {
        return it_is_myMessage;
    }

    public void setIt_is_myMessage(Boolean it_is_myMessage) {
        this.it_is_myMessage = it_is_myMessage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
