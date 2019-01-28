package org.yiuman.verydog.system.vm;

import javax.validation.constraints.NotNull;

/**
 * @author Yiuman
 * @date 2018/9/5
 */
public class UserLoginVM {

    /**
     * 登录名，可为用户名或电话
     */
    @NotNull
    private String login;

    @NotNull
    private String password;

    private boolean rememberMe;

    public UserLoginVM() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
