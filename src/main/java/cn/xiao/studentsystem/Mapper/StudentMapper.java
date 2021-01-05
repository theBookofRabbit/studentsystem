package cn.xiao.studentsystem.Mapper;

import cn.xiao.studentsystem.Enitity.Results;
import cn.xiao.studentsystem.Enitity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper {

    @Select("select * from student where num = #{num} and password = #{password}")
    public Student SelectMessage(Student student);

    @Select("select * from results where studentnum = #{studentnum} and coursenum = #{coursenum}")
    public Results SelectResult(Results result);

    @Insert("insert into results (studentnum,score,coursenum) values (#{studentnum},#{score},#{coursenum})")
    public int ResultInsert(Results results);


}
