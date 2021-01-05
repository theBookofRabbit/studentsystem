package cn.xiao.studentsystem.Enitity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("学生个人信息")
public class Student {
    @ApiModelProperty("学号")
    public String num = "0";

    @ApiModelProperty("名字")
    public String name = "0";


    @ApiModelProperty("性别")
    public String sex = "保密";


    @ApiModelProperty("年级")
    public String grade = "未知年级";


    @ApiModelProperty("出生年月")
    public String birthday = "0";


    @ApiModelProperty("系号")
    public String department = "0";


    @ApiModelProperty("密码")
    public String password = "0";

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
