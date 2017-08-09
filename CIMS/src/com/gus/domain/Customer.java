package com.gus.domain;

import com.gus.exception.MsgException;

import java.io.Serializable;
import java.sql.Date;

public class Customer implements Serializable {
    private int id;
    private String name;
    private String gender;
    private Date birthday;
    private String cellphone;
    private String email;
    private String preference;
    private String type;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void checkValues() throws MsgException {
        if (null == name || "".equals(name)) {
            throw new MsgException("客户姓名不能为空");
        }
        if (null == gender || "".equals(gender)) {
            throw new MsgException("性别不能为空");
        }
        if (null == birthday || "".equals(birthday)) {
            throw new MsgException("出生年月不能为空");
        }
        if (null == cellphone || "".equals(cellphone)) {
            throw new MsgException("手机号码不能为空");
        }
        if (null == email || "".equals(email)) {
            throw new MsgException("电子邮箱不能为空");
        }
        if (null == preference || "".equals(preference)) {
            throw new MsgException("客户爱好不能为空");
        }
        if (null == type || "".equals(type)) {
            throw new MsgException("客户类型不能为空");
        }
        if (null == description || "".equals(description)) {
            throw new MsgException("描述信息不能为空");
        }
//        使用正则表达式检验邮箱格式
//        xxx@xxx.xxx.xxx
        if (!email.matches("^\\w+@\\w+(\\.\\w+)+$")) {
            throw new MsgException("邮箱格式不正确");
        }


    }
}
