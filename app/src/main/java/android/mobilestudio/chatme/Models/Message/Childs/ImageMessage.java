package android.mobilestudio.chatme.models.message.childs;

import android.mobilestudio.chatme.models.message.parent.Message;

/**
 * Created by pisoo on 7/31/2017.
 */

public class ImageMessage extends Message {

    private String ImageId;

    public String getImageId() {
        return ImageId;
    }

    public void setImageId(String imageId) {
        ImageId = imageId;
    }

    public void sentMessage() {

    }

    public void editMessage() {
    }

    public void deleteMessage() {
    }
}
