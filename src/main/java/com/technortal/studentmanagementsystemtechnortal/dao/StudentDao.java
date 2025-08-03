package com.technortal.studentmanagementsystemtechnortal.dao;

import com.technortal.studentmanagementsystemtechnortal.db.DBConfig;
import com.technortal.studentmanagementsystemtechnortal.entity.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

//DAO data access object
public class StudentDao {

    private JdbcTemplate jdbcTemplate = DBConfig.getJdbcTemplate();

    //select
    public List<Student> getAllStudents() {
        return jdbcTemplate.query(
                "select * from student",new BeanPropertyRowMapper<>(Student.class));
    }


    //insert
    public void insert(Student student) {
        jdbcTemplate.update("insert into student (name,age,address,create_time) values(?,?,?,now())",
                student.getName(),student.getAge(),student.getAddress());
    }

    //update
    public void updateStudent(Student student) {
        jdbcTemplate.update(
                "update student set name = ?, age = ?, address = ? where id = ?",
                student.getName(), student.getAge(), student.getAddress(), student.getId()
        );
    }

    //delete
    public void delete(int id) {
        jdbcTemplate.update("delete from student where id = ?",id);
    }



}
