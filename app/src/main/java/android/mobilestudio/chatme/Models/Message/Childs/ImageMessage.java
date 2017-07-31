package android.mobilestudio.chatme.Models.Message.Childs;

import android.mobilestudio.chatme.Models.Message.Parent.Message;

/**
 * Created by pisoo on 7/31/2017.
 */

public class ImageMessage extends Message {

    private String ImageId  ;

    public String getImageId() {
        return ImageId;
    }

    public void setImageId(String imageId) {
        ImageId = imageId;
    }

    public void sentMessage(){

    }
    public void editMessage(){
    }
    public void deleteMessage(){
    }
}
