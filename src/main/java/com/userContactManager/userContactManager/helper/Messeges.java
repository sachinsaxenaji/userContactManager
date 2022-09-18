package com.userContactManager.userContactManager.helper;

public class Messeges {

    private String contain;
    private String type;

    public Messeges(String contain, String type) {
        this.contain = contain;
        this.type = type;
    }

    public String getContain() {
        return contain;
    }

    public void setContain(String contain) {
        this.contain = contain;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
