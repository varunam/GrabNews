package com.grab.news.app.repository;

/**
 * Created by varun.am on 2019-05-25
 */
public class Source {
    
    private String id, name;
    
    public Source(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
