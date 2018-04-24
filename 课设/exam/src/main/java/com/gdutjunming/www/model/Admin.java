package com.gdutjunming.www.model;

/**
 * @author 郑俊铭
 * Date: 2017/11/19
 * Time: 16:13
 * No struggle, talent how to match the willfulness.
 * Description: 管理员实体类
 */
public class Admin {
    /**
     * 管理员ID
     */
    private Integer id;
    /**
     * 管理员登录用户名
     */
    private String username;
    /**
     * 管理员登录密码
     */
    private String password;

    public Admin() {

    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Admin)) {
            return false;
        }

        Admin admin = (Admin) o;

        return (getUsername() != null ? getUsername().equals(admin.getUsername()) : admin.getUsername() == null) && (getPassword() != null ? getPassword().equals(admin.getPassword()) : admin.getPassword() == null);
    }

}
