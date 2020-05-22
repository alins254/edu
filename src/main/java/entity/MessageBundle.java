package entity;

public class MessageBundle {
    public String message;
    public Object object;
    public Object extraObject;

    public MessageBundle(String message, Object object){
        this.message = message;
        this.object = object;
    }

    public void setExtraObject(Object extraObject) {
        this.extraObject = extraObject;
    }
}
