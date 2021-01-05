package cn.xiao.studentsystem.Mapper;

import cn.xiao.studentsystem.Enitity.Manager;
import cn.xiao.studentsystem.Enitity.Results;
import cn.xiao.studentsystem.Enitity.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerMapper {

    @Insert("insert into student (num,name,sex,grade,birthday,department,password) values (#{num},#{name},#{sex},#{grade},#{birthday},#{department},#{password})")
    public int ManagerAddStudent(Student student);

    @Insert("insert into results (studentnum,score,coursenum) values (#{studentnum},#{score},#{couresenum}")
    public int ManagerAddResult(Results results);

    @Select("select * from manager where num = #{num} and password = #{password}")
    public Manager SelectFromManager(Manager manager);

    @Select("select * from student")
    public List<Student> SelectAllStudent();

    @Update("update student set name = #{name},sex = #{sex},grade = #{grade},birthday = #{birthday},department = #{department},password = #{password} where num = #{num}")
    public int ManagerUpdateStudent(Student student);

    @Update("upadte results set score = #{score},coursenum = #{coursenum} where studentnum = #{studentnum}")
    public int ManagerUpdateResults(Results results);

    @Delete("delete from student where num = #{num}")
    public int ManagerDeleteStudent(Student student);

    @Delete("delete * from results where studentnum = #{studentnum}")
    public int ManagerDeleteResult(Results results);

}
