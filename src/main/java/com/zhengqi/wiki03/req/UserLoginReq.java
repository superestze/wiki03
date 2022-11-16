package com.zhengqi.wiki03.req;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class UserLoginReq {

    @NotNull(message = "login name cannot be null")
    private String loginName;
    @NotNull(message = "password cannot be null")
    @Length(min = 6, max = 20, message = "password must be 6~20")
    private String password;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", loginName=").append(loginName);
        sb.append(", password=").append(password);
        sb.append("]");
        return sb.toString();
    }
}