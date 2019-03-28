package com.aolan.b365.mvp.model.entity;

public class User {

    /**
     * id : 28
     * telephone : 13632824345
     * nickname : user
     * avatar : http://b365-api.io/resources/avatar.png
     * gender : 1
     * height : null
     * birth : null
     * created_at : 2018-07-27 10:17:59
     * updated_at : 2018-07-27 10:17:59
     */

    private int id;
    private String telephone;
    private String nickname;
    private String avatar;
    private int gender;
    private String height;
    private String birth;
    private String created_at;
    private String updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Object getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Object getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
