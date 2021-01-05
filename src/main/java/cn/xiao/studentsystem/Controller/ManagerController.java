package cn.xiao.studentsystem.Controller;


import cn.xiao.studentsystem.Enitity.Manager;
import cn.xiao.studentsystem.Enitity.Results;
import cn.xiao.studentsystem.Enitity.Student;
import cn.xiao.studentsystem.Mapper.ManagerMapper;
import cn.xiao.studentsystem.Utils.ServiceException;
import cn.xiao.studentsystem.Utils.ServiceExceptionEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Service
@RequestMapping("/manger")
@Api(tags = "管理员（教师）操作")
public class ManagerController {
    @Autowired
    ManagerMapper managerMapper;

    @PostMapping("/message/insert")
    @ApiOperation(notes = "管理员（教师）登陆成功后新增学生信息",value = "输入管理员（教师）工号、密码、学生信息（学号，姓名，性别，年级，出生年月，系号，默认初始密码）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num",value = "教师工号",required = true,paramType = "query", dataTypeClass = String.class, example = "111111111"),
            @ApiImplicitParam(name = "password",value = "教师密码",required = true,paramType = "query", dataTypeClass = String.class, example = "aaaaaaaa"),
            @ApiImplicitParam(name = "studentnum",value = "学生学号",required = true,paramType = "query", dataTypeClass = String.class, example = "3119005555"),
            @ApiImplicitParam(name = "studentname",value = "学生名字",required = true,paramType = "query", dataTypeClass = String.class, example = "王魈"),
            @ApiImplicitParam(name = "studentsex",value = "学生性别",required = true,paramType = "query", dataTypeClass = String.class, example = "男"),
            @ApiImplicitParam(name = "studentgrade",value = "学生年级",required = true,paramType = "query", dataTypeClass = String.class, example = "2018级"),
            @ApiImplicitParam(name = "studentbirthday",value = "学生出生年月",required = true,paramType = "query", dataTypeClass = String.class, example = "2000年01月01日"),
            @ApiImplicitParam(name = "studentdepartment",value = "学生系号",required = true,paramType = "query", dataTypeClass = String.class, example = "1123"),
            @ApiImplicitParam(name = "studnetpassword",value = "学生默认初始密码",required = true,paramType = "query", dataTypeClass = String.class, example = "bbbbbbb")
    })
    public Student ManagerAdd(
            @Param("num")String num,
            @Param("password")String password,
            @Param("studentnum")String studentnum,
            @Param("studentname")String studentname,
            @Param("studentsex")String studentsex,
            @Param("studentgrade")String studentgrade,
            @Param("studentbirthday")String studentbirthday,
            @Param("studentdepartment")String studentdepartment,
            @Param("studnetpassword")String studnetpassword
    ){
        Student student = new Student();
        student.setBirthday(studentbirthday);
        student.setDepartment(studentdepartment);
        student.setGrade(studentgrade);
        student.setName(studentname);
        student.setNum(studentnum);
        student.setPassword(studnetpassword);
        student.setSex(studentsex);

        Manager manager = new Manager();
        manager.setNum(num);
        manager.setPassword(password);
        if(managerMapper.SelectFromManager(manager) == null){
            throw new ServiceException(student, ServiceExceptionEnum.MANAGER_NUM_OR_PASSWORD_WORING);
        }
        if(studentnum == null || studentnum.equals("") || studentname == null || studentname.equals("") ||studentsex == null || studentsex.equals("") ||studentgrade == null || studentgrade.equals("") ||studentbirthday == null || studentbirthday.equals("") ||studentdepartment == null || studentdepartment.equals("") ||studnetpassword == null || studnetpassword.equals("")){
            throw new ServiceException(student,ServiceExceptionEnum.STUDENT_MESSAGE_NOT_COMPLIE);
        }
        managerMapper.ManagerAddStudent(student);
        return student;
    }


    @ApiOperation(notes = "管理员（教师）登陆成功后修改某一个学生信息",value = "输入管理员（教师）工号、密码、要修改的学生信息（学号，姓名，性别，年级，出生年月，系号，学生密码），注意调用此接口前摇先调用管理员对学生信息的查询接口获得相应学生的全部信息然后修改，不能少任何一项信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num",value = "教师工号",required = true,paramType = "query", dataTypeClass = String.class, example = "111111111"),
            @ApiImplicitParam(name = "password",value = "教师密码",required = true,paramType = "query", dataTypeClass = String.class, example = "aaaaaaaa"),
            @ApiImplicitParam(name = "studentnum",value = "学生学号",required = true,paramType = "query", dataTypeClass = String.class, example = "3119005555"),
            @ApiImplicitParam(name = "studentname",value = "学生名字",required = true,paramType = "query", dataTypeClass = String.class, example = "王魈"),
            @ApiImplicitParam(name = "studentsex",value = "学生性别",required = true,paramType = "query", dataTypeClass = String.class, example = "男"),
            @ApiImplicitParam(name = "studentgrade",value = "学生年级",required = true,paramType = "query", dataTypeClass = String.class, example = "2018级"),
            @ApiImplicitParam(name = "studentbirthday",value = "学生出生年月",required = true,paramType = "query", dataTypeClass = String.class, example = "2000年01月01日"),
            @ApiImplicitParam(name = "studentdepartment",value = "学生系号",required = true,paramType = "query", dataTypeClass = String.class, example = "1123"),
            @ApiImplicitParam(name = "studnetpassword",value = "学生密码",required = true,paramType = "query", dataTypeClass = String.class, example = "bbbbbbb")
    })
    @PostMapping("/message/update")
    public Student ManagerUpdate(
            @Param("num")String num,
            @Param("password")String password,
            @Param("studentnum")String studentnum,
            @Param("studentname")String studentname,
            @Param("studentsex")String studentsex,
            @Param("studentgrade")String studentgrade,
            @Param("studentbirthday")String studentbirthday,
            @Param("studentdepartment")String studentdepartment,
            @Param("studnetpassword")String studnetpassword
    ){
        Student student = new Student();
        student.setBirthday(studentbirthday);
        student.setDepartment(studentdepartment);
        student.setGrade(studentgrade);
        student.setName(studentname);
        student.setNum(studentnum);
        student.setPassword(studnetpassword);
        student.setSex(studentsex);

        Manager manager = new Manager();
        manager.setNum(num);
        manager.setPassword(password);
        if(managerMapper.SelectFromManager(manager) == null){
            throw new ServiceException(student, ServiceExceptionEnum.MANAGER_NUM_OR_PASSWORD_WORING);
        }
        if(studentnum == null || studentnum.equals("") || studentname == null || studentname.equals("") ||studentsex == null || studentsex.equals("") ||studentgrade == null || studentgrade.equals("") ||studentbirthday == null || studentbirthday.equals("") ||studentdepartment == null || studentdepartment.equals("") ||studnetpassword == null || studnetpassword.equals("")){
            throw new ServiceException(student,ServiceExceptionEnum.STUDENT_MESSAGE_NOT_COMPLIE);
        }
        managerMapper.ManagerUpdateStudent(student);
        return student;
    }


    @ApiOperation("管理员删除特定学生的记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num",value = "教师工号",required = true,paramType = "query", dataTypeClass = String.class, example = "111111111"),
            @ApiImplicitParam(name = "password",value = "教师密码",required = true,paramType = "query", dataTypeClass = String.class, example = "aaaaaaaa"),
            @ApiImplicitParam(name = "studentnum",value = "要删除的学生学号",required = true,paramType = "query", dataTypeClass = String.class, example = "3119005555")
    })
    @PostMapping("/message/delete")
    public String ManagerDelete(
            @Param("num")String num,
            @Param("password")String password,
            @Param("studentnum")String studentnum
    ){
        Manager manager = new Manager();
        manager.setNum(num);
        manager.setPassword(password);
        if(managerMapper.SelectFromManager(manager) == null){
            throw new ServiceException("管理员工号或密码错误", ServiceExceptionEnum.MANAGER_NUM_OR_PASSWORD_WORING);
        }

        Student student = new Student();
        student.setNum(studentnum);
        if(managerMapper.ManagerDeleteStudent(student) == 0){
            throw new ServiceException("学生学号不存在",ServiceExceptionEnum.STUDENT_NUM_NOT_EXIST);
        }
        else {
            return "删除成功";
        }
    }


    @ApiOperation(notes = "管理员（教师）登陆成功后查询所有学生信息",value = "输入管理员（教师）工号、密码，获得所有学生所有信息，方便删除和修改（此处就不考虑实用性啦，反正是课设嘛~）0v0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num",value = "教师工号",required = true,paramType = "query", dataTypeClass = String.class, example = "111111111"),
            @ApiImplicitParam(name = "password",value = "教师密码",required = true,paramType = "query", dataTypeClass = String.class, example = "aaaaaaaa")
    })
    @PostMapping("/message/quire")
    public List<Student> ManagerQuire(
            @Param("num")String num,
            @Param("password")String password
    ){
        List<Student> studentList = new ArrayList<>();

        Manager manager = new Manager();
        manager.setNum(num);
        manager.setPassword(password);
        if(managerMapper.SelectFromManager(manager) == null){
            throw new ServiceException(studentList, ServiceExceptionEnum.MANAGER_NUM_OR_PASSWORD_WORING);
        }
        studentList = managerMapper.SelectAllStudent();
        return studentList;
    }

    //==============管理员对成绩的操作=====================
    @ApiOperation(notes = "管理添加学生成绩",value = "输入管理员工号、密码、学号、成绩、课程号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num",value = "教师工号",required = true,paramType = "query", dataTypeClass = String.class, example = "111111111"),
            @ApiImplicitParam(name = "password",value = "教师密码",required = true,paramType = "query", dataTypeClass = String.class, example = "aaaaaaaa"),
            @ApiImplicitParam(name = "studentnum",value = "学生学号",required = true,paramType = "query", dataTypeClass = String.class, example = "3119005555"),
            @ApiImplicitParam(name = "score",value = "学生成绩",required = true,paramType = "query", dataTypeClass = String.class, example = "100分"),
            @ApiImplicitParam(name = "coursenum",value = "课程号",required = true,paramType = "query", dataTypeClass = String.class, example = "1123")
    })
    @PostMapping("/result/insert")
    public Results ManagerAddResult(
            @Param("num")String num,
            @Param("password")String password,
            @Param("studentnum")String studentnum,
            @Param("score")String score,
            @Param("coursenum")String coursenum
    ){
        Results results = new Results();
        results.setCoursenum(coursenum);
        results.setScore(score);
        results.setStudentnum(studentnum);

        Manager manager = new Manager();
        manager.setNum(num);
        manager.setPassword(password);
        if(managerMapper.SelectFromManager(manager) == null){
            throw new ServiceException(results, ServiceExceptionEnum.MANAGER_NUM_OR_PASSWORD_WORING);
        }
        if(studentnum == null || studentnum.equals("") || score == null || score.equals("") ||coursenum == null || coursenum.equals("")){
            throw new ServiceException(results,ServiceExceptionEnum.RESULT_NOT_COMPLIE);
        }
        managerMapper.ManagerAddResult(results);//必然成功，除非sql语句写错或者出现服务器死机、并发等问题
        return results;
    }


    @ApiOperation(notes = "管理修改学生成绩",value = "输入管理员工号、密码、学号、成绩、课程号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num",value = "教师工号",required = true,paramType = "query", dataTypeClass = String.class, example = "111111111"),
            @ApiImplicitParam(name = "password",value = "教师密码",required = true,paramType = "query", dataTypeClass = String.class, example = "aaaaaaaa"),
            @ApiImplicitParam(name = "studentnum",value = "学生学号",required = true,paramType = "query", dataTypeClass = String.class, example = "3119005555"),
            @ApiImplicitParam(name = "score",value = "学生成绩",required = true,paramType = "query", dataTypeClass = String.class, example = "100分"),
            @ApiImplicitParam(name = "coursenum",value = "课程号",required = true,paramType = "query", dataTypeClass = String.class, example = "1123")
    })
    @PostMapping("/result/update")
    public Results ManagerUpdateResult(
            @Param("num")String num,
            @Param("password")String password,
            @Param("studentnum")String studentnum,
            @Param("score")String score,
            @Param("coursenum")String coursenum
    ){
        Results results = new Results();
        results.setCoursenum(coursenum);
        results.setScore(score);
        results.setStudentnum(studentnum);

        Manager manager = new Manager();
        manager.setNum(num);
        manager.setPassword(password);
        if(managerMapper.SelectFromManager(manager) == null){
            throw new ServiceException(results, ServiceExceptionEnum.MANAGER_NUM_OR_PASSWORD_WORING);
        }
        if(studentnum == null || studentnum.equals("") || score == null || score.equals("") ||coursenum == null || coursenum.equals("")){
            throw new ServiceException(results,ServiceExceptionEnum.RESULT_NOT_COMPLIE);
        }
        if(managerMapper.ManagerUpdateResults(results) == 0){
            throw new ServiceException(results,ServiceExceptionEnum.STUDENT_NUM_NOT_EXIST);
        }
        else {
            return results;
        }

    }

    @ApiOperation(notes = "管理员删除学生某一课程成绩记录（学号，成绩，课程号）",value = "输入管理员工号、密码、学号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num",value = "教师工号",required = true,paramType = "query", dataTypeClass = String.class, example = "111111111"),
            @ApiImplicitParam(name = "password",value = "教师密码",required = true,paramType = "query", dataTypeClass = String.class, example = "aaaaaaaa"),
            @ApiImplicitParam(name = "studentnum",value = "学生学号",required = true,paramType = "query", dataTypeClass = String.class, example = "3119005555")
    })
    @PostMapping("/result/delete")
    public String ManagerAddResult(
            @Param("num")String num,
            @Param("password")String password,
            @Param("studentnum")String studentnum
    ){
        Results results = new Results();
        results.setStudentnum(studentnum);

        Manager manager = new Manager();
        manager.setNum(num);
        manager.setPassword(password);
        if(managerMapper.SelectFromManager(manager) == null){
            throw new ServiceException("管理员工号或密码错误", ServiceExceptionEnum.MANAGER_NUM_OR_PASSWORD_WORING);
        }
        if(studentnum == null || studentnum.equals("")){
            throw new ServiceException("学号/成绩/课程号不能为空",ServiceExceptionEnum.RESULT_NOT_COMPLIE);
        }
        if(managerMapper.ManagerDeleteResult(results) == 0){
            throw new ServiceException("学号不存在",ServiceExceptionEnum.STUDENT_NUM_NOT_EXIST);
        }
        return "删除成功";
    }

    // =========管理员对课程的操作=======================
    //无。

}
