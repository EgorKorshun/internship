package dao.impl;

import com.informix.asf.Connection;
import dao.BaseDao;
import dao.exception.DaoException;
import domain.Employee;
import testDB.ConnectionToDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static testDB.Testing.io;

public class DaoImpl implements BaseDao<Employee> {
    private static DaoImpl instance;
    DaoImpl(){}


    public static DaoImpl getInstance(){
        if(instance==null) {
            instance = new DaoImpl();
        }
        return instance;
    }


    @Override
    public List<Employee> findAll() throws DaoException, SQLException, ClassNotFoundException {
        List<Employee> resultCollection = new ArrayList<>();
         java.sql.Connection connection = ConnectionToDB.databaseConnection();



        try (PreparedStatement statement = connection.prepareStatement("select sirname, io,oper_phone from ratsg:a15 where oper_user = USER")){
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setSurname(resultSet.getString("sirname"));
                employee.setIo(resultSet.getString("io"));
                employee.setOperNumber(resultSet.getString("oper_phone"));
                resultCollection.add(employee);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
        return resultCollection;
    }

    @Override
    public Optional<Employee> findEntityById(long id) throws DaoException, SQLException, ClassNotFoundException {
        java.sql.Connection connection = ConnectionToDB.databaseConnection();

        Employee employee = null;

        try(PreparedStatement statement = connection.prepareStatement("select sirname, io,oper_phone from ratsg:a15 where oper_user = USER")) {
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (io == resultSet.getString("io")) {
                    employee = new Employee();
                    employee.setSurname(resultSet.getString("sirname"));
                    employee.setIo(resultSet.getString("io"));
                    employee.setOperNumber(resultSet.getString("oper_phone"));;

                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
        return Optional.ofNullable(employee);
    }

//запрос составить
    @Override
    public boolean delete(long id) throws DaoException, SQLException, ClassNotFoundException {
        boolean result = true;
        java.sql.Connection connection = ConnectionToDB.databaseConnection();

        try (PreparedStatement statement= connection.prepareStatement("select sirname, io,oper_phone from ratsg:a15 where oper_user = USER" )){
            statement.setLong(1,id);
            statement.execute();
        } catch (SQLException e) {
            result = false;

            throw new DaoException(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
        return result;
    }

    @Override
    public boolean create(Employee employee) throws DaoException, SQLException, ClassNotFoundException {
        boolean result = true;
        java.sql.Connection connection = ConnectionToDB.databaseConnection();
        Statement statement;

        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO employee\n" +
                    "(sirname, io, oper_phone)\n" +
                    "values ('"+employee.getSurname()+"','"+employee.getIo()+"' ,'"+ employee.getOperNumber()+"');" );

            ResultSet resultSet = statement.executeQuery("SELECT employee.io, employee.operPhone\n" +
                    "FROM ratsg:a15");
            while (resultSet.next()) {
                if(resultSet.getString("sirname").equals(employee.getSurname())) {
                    String tempAccountId = resultSet.getString("sirname");
                    statement.executeUpdate("INSERT INTO employee\n" +
                            "(account_id)\n" +
                            "values ('" + resultSet.getString("io") + "'); ");
                    ResultSet resultSet1 = statement.executeQuery("SELECT employee.io, employee.operPhone\n" +
                            "FROM ratsg:a15");
                    while (resultSet1.next()){
                        if(resultSet1.getString("a15_io")==tempAccountId){
                            employee.setOperNumber(resultSet1.getString("oper_phone"));
                            break;
                        }
                    }
                    break;
                }
            }
        } catch (SQLException e) {
            result = false;
            throw new DaoException(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
        return result;
    }

    @Override
    public boolean update(Employee employee) throws DaoException, SQLException, ClassNotFoundException {
        boolean result = true;
        java.sql.Connection connection = ConnectionToDB.databaseConnection();
        try(PreparedStatement statement = connection.prepareStatement("UPDATE ratsg:a15\n" +
                "SET io = ?, " +
                "sirname = ?, " +
                "oper_number = ? \n" +
                "FROM employee\n" +
                "where oper_user = USER")) {
            statement.setString(1,employee.getSurname());
            statement.setString(2,employee.getIo());
            statement.setString(3,employee.getOperNumber());
            statement.execute();
        } catch (SQLException e) {
            result = false;
            throw new DaoException(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
        return result;
    }


}
