package com.assignment.crudmicroservice.crudmicroservice.dao;

import com.assignment.crudmicroservice.crudmicroservice.list.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setId(rs.getLong("id"));
            employee.setName(rs.getString("name"));
            employee.setAddress(rs.getString("address"));
            return employee;
        }
    }

    public List<Employee> findAll() {
        return jdbcTemplate.query("select * from employee", new EmployeeRowMapper());
    }

    public Employee findById(long id) {
        return jdbcTemplate.queryForObject("select * from employee where id=?", new Object[]{
                        id
                },
                new BeanPropertyRowMapper<Employee>(Employee.class));
    }

    public int deleteById(long id) {
        return jdbcTemplate.update("delete from employee where id=?", new Object[]{
                id
        });
    }

    public int insert(Employee employee) {
        return jdbcTemplate.update("insert into employee (id, name, address) " + "values(?,  ?, ?)",
                new Object[]{
                        employee.getId(), employee.getName(), employee.getAddress()
                });
    }

    public int update(Employee employee) {
        return jdbcTemplate.update("update employee " + " set name = ?, address = ? " + " where id = ?",
                new Object[]{
                        employee.getName(), employee.getAddress(), employee.getId()
                });
    }
}
