package csec.vulnerable.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import csec.vulnerable.beans.UserInfo;

public class UserDTO {

    private int id;
    private String username;
    private UserInfo userInfo;
    private Collection<? extends GrantedAuthority> profiles;

    public UserDTO() {
    }

    

    public UserDTO(int id, String username, UserInfo userInfo, Collection<? extends GrantedAuthority> profiles) {
        this.id = id;
        this.username = username;
        this.userInfo = userInfo;
        this.profiles = profiles;
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



    public Collection<? extends GrantedAuthority> getProfiles() {
        return profiles;
    }



    public void setProfiles(Collection<? extends GrantedAuthority> profiles) {
        this.profiles = profiles;
    }


}

