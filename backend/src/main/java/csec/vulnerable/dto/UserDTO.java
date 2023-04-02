package csec.vulnerable.dto;

import csec.vulnerable.beans.UserInfo;

public class UserDTO {

    private int id;
    private String username;
    private UserInfo userInfo;

    public UserDTO() {
    }

    public UserDTO(int id, String username, UserInfo userInfo) {
        this.id = id;
        this.username = username;
        this.userInfo = userInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}

