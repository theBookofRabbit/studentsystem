package cn.xiao.studentsystem.Controller;

import cn.xiao.studentsystem.Enitity.Results;
import cn.xiao.studentsystem.Enitity.Student;
import cn.xiao.studentsystem.Mapper.StudentMapper;
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

@Api(tags = "学生操作")
@RestController
@RequestMapping("/student")
@Service
public class StudentController {
    @Autowired
    StudentMapper studentMapper;

    @ApiOperation(notes = "学生查询个人信息",value = "输入学号、密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num",value = "学生学号",required = true,paramType = "query",dataTypeClass = String.class,example = "3119001111"),
            @ApiImplicitParam(name = "password",value = "学生密码",required = true,paramType = "query",dataTypeClass = String.class,example = "cccccccccc")
    })
    @PostMapping("/message/quire")
    public Student StudentQuire(
            @Param("num")String num,
            @Param("password")String password
    ){
        Student student = new Student();
        student.setNum(num);
        student.setPassword(password);
        if(num == null || num.equals("") || password == null || password.equals("")){
            throw new ServiceException(student, ServiceExceptionEnum.STUDENT_MESSAGE_NOT_COMPLIE);
        }
        return studentMapper.SelectMessage(student);
    }

    @ApiOperation(notes = "学生查询个人某门课程成绩",value = "输入学号、密码、课程号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num",value = "学生学号",required = true,paramType = "query",dataTypeClass = String.class,example = "3119001111"),
            @ApiImplicitParam(name = "password",value = "学生密码",required = true,paramType = "query",dataTypeClass = String.class,example = "cccccccccc"),
            @ApiImplicitParam(name = "coursenum",value = "课程号",required = true,paramType = "query",dataTypeClass = String.class,example = "1123")
    })
    @PostMapping("/result/quire")
    public Results ResultQuire(
            @Param("num")String num,
            @Param("password")String password,
            @Param("coursenum")String coursenum
    ){
        Results result = new Results();
        result.setStudentnum(num);
        result.setCoursenum(coursenum);

        Student student = new Student();
        student.setNum(num);
        student.setPassword(password);
        if(num == null || num.equals("") || password == null || password.equals("") || coursenum == null || coursenum.equals("")){
            throw new ServiceException(result, ServiceExceptionEnum.STUDENT_MESSAGE_NOT_COMPLIE);
        }
        //核验学号密码
        if(studentMapper.SelectMessage(student) == null){
            throw new ServiceException(result,ServiceExceptionEnum.STUDENT_NUM_NOT_EXIST);
        }
        //查询成绩
        return studentMapper.SelectResult(result);
    }

    @ApiOperation(notes = "学生新增个人选课信息",value = "输入学号、密码、课程号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num",value = "学生学号",required = true,paramType = "query",dataTypeClass = String.class,example = "3119001111"),
            @ApiImplicitParam(name = "password",value = "学生密码",required = true,paramType = "query",dataTypeClass = String.class,example = "cccccccccc"),
            @ApiImplicitParam(name = "coursenum",value = "课程号",required = true,paramType = "query",dataTypeClass = String.class,example = "233")
    })
    @PostMapping("/result/insert")
    public Results CourseInsertOrUpdate(
            @Param("num")String num,
            @Param("password")String password,
            @Param("coursenum")String coursenum
    ){
        Results result = new Results();
        result.setStudentnum(num);
        result.setCoursenum(coursenum);
        result.setScore("还未考试");//选课还没有考试

        Student student = new Student();
        student.setNum(num);
        student.setPassword(password);
        if(num == null || num.equals("") || password == null || password.equals("") || coursenum == null || coursenum.equals("")){
            throw new ServiceException(result, ServiceExceptionEnum.STUDENT_MESSAGE_NOT_COMPLIE);
        }
        //核验学号密码
        if(studentMapper.SelectMessage(student) == null){
            throw new ServiceException(result,ServiceExceptionEnum.STUDENT_NUM_NOT_EXIST);
        }
        //新增选修情况（新增记录到没有成绩的成绩表）
        studentMapper.ResultInsert(result);
        return result;
    }


}
