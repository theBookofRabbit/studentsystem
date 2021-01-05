package cn.xiao.studentsystem.Enitity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("管理员信息")
public class Manager {

    @ApiModelProperty("工号")
    public String num = "0";


    @ApiModelProperty("姓名")
    public String name = "0";


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
