package dao;

import domain.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        Employee employee = new Employee();
        employee.setSurname(resultSet.getString("sirname"));
        employee.setIo(resultSet.getString("io"));
        employee.setOperNumber(resultSet.getString("oper_phone"));
        return employee;
    }
}
