package cn.xiao.studentsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@MapperScan(basePackages = "cn/xiao/studentsystem/Mapper")//扫描mapper文件夹
@SpringBootApplication
public class application implements CommandLineRunner {
    @Autowired
    DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(application.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("[run][获得连接：{}]"+conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
