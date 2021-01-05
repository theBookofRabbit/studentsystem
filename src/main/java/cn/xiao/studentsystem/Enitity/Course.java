package cn.xiao.studentsystem.Enitity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("课程信息")
public class Course {

    @ApiModelProperty("课程号")
    public String num = "0";


    @ApiModelProperty("课程名字")
    public String name = "0";


    @ApiModelProperty("学分")
    public String credit = "0";


    @ApiModelProperty("上课时间")
    public String coursetime = "0";


    @ApiModelProperty("选课人数")
    public String personsnum = "0";


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

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCoursetime() {
        return coursetime;
    }

    public void setCoursetime(String coursetime) {
        this.coursetime = coursetime;
    }

    public String getPersonsnum() {
        return personsnum;
    }

    public void setPersonsnum(String personsnum) {
        this.personsnum = personsnum;
    }
}
