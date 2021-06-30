package dao.impl;

import dao.A15;
import dao.EmployeeMapper;
import dao.exception.DaoException;
import domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class A15Impl implements A15<Employee> {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public A15Impl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Employee> findAll()  {
        return jdbcTemplate.query("SELECT * FROM a15", new EmployeeMapper());
    }

    @Override
    public Employee findEntityByOperNumber(String operNumber)  {
        return jdbcTemplate.query("SELECT * FROM a15 WHERE oper_number=?", new Object[]{operNumber}, new BeanPropertyRowMapper<>(Employee.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public void delete(String operNumber) {
        jdbcTemplate.update("DELETE FROM a15 WHERE oper_number=?", operNumber);
    }

    @Override
    public void create(Employee employee)  {
        jdbcTemplate.update("INSERT INTO a15 VALUES( ?, ?, ? )",employee.getIo(), employee.getSurname(),
                employee.getOperNumber());
    }

    @Override
    public void update(Employee employee, String operNumber) {
        jdbcTemplate.update("UPDATE a15 SET sirname=?, io=? WHERE oper_number=?", employee.getSurname(),
                employee.getIo(),operNumber);
    }



    public  static  void  close(PreparedStatement ps) throws SQLException {
        if (ps != null) {
         ps.close();
        }
    }
    public  static void  close(Connection connection ) throws SQLException {
            if(connection!=null){
                connection.close();
        }
     }
    public  static  void  close(Statement st) throws SQLException {
        if (st != null) {
            st.close();
        }
    }

}
