package com.zhengqi.wiki03.req;

public class UserQueryReq extends PageReq{
   private String loginName;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserQueryReq{");
        sb.append("loginName='").append(loginName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}