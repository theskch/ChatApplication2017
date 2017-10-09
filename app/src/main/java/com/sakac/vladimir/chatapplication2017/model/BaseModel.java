package com.sakac.vladimir.chatapplication2017.model;

import java.io.Serializable;

/**
 * Created by sakac on 5/7/2017.
 */

public abstract class BaseModel implements Serializable{

    private String id;

    public BaseModel(){
    }

    public BaseModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
