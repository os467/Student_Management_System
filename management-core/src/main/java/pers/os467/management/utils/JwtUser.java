package pers.os467.management.utils;

public class JwtUser {
    //用户号
    private Integer uid;
    //用户名
    private String username;
    //用户角色号
    private Integer rid;
    //用户角色
    private String role;

    public JwtUser() {
    }

    public JwtUser(Integer uid, String username, Integer rid, String role) {
        this.uid = uid;
        this.username = username;
        this.rid = rid;
        this.role = role;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
