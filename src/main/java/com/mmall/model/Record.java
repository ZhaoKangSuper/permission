package com.mmall.model;

public class Record {
    private Integer id;

    private Integer type;

    private String user1;

    private String title;

    private String pic;

    private String discription1;

    private String user2;

    private Integer action;

    private String discription2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1 == null ? null : user1.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getDiscription1() {
        return discription1;
    }

    public void setDiscription1(String discription1) {
        this.discription1 = discription1 == null ? null : discription1.trim();
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2 == null ? null : user2.trim();
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public String getDiscription2() {
        return discription2;
    }

    public void setDiscription2(String discription2) {
        this.discription2 = discription2 == null ? null : discription2.trim();
    }
}