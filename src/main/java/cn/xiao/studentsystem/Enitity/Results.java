package cn.xiao.studentsystem.Enitity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("成绩表")
public class Results {

    @ApiModelProperty("学生学号")
    public String studentnum = "0";


    @ApiModelProperty("学生成绩")
    public String score = "0";


    @ApiModelProperty("课程号")
    public String coursenum = "0";


    public String getStudentnum() {
        return studentnum;
    }

    public void setStudentnum(String studentnum) {
        this.studentnum = studentnum;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCoursenum() {
        return coursenum;
    }

    public void setCoursenum(String coursenum) {
        this.coursenum = coursenum;
    }
}
