//package dao.impl;
//
//import dao.A15;
//import dao.exception.DaoException;
//import domain.Employee;
//import testDB.ConnectionToDB;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//
////ВСЕ SQL ЗАПРОСЫ ИЗМЕНИТЬ
////в ркпоз класть интерфейс а15, туда же вс. реализа а15.импл
//
//public class APIA15Impl implements A15<Employee> {
//    private final String USER = "prog724";
//
//    private static APIA15Impl instance;
//    APIA15Impl(){}
//
//
//    public static APIA15Impl getInstance(){
//        if(instance==null) {
//            instance = new APIA15Impl();
//        }
//        return instance;
//    }
//
//
//    @Override
//    public List<Employee> findAll() throws DaoException, SQLException, ClassNotFoundException {
//        List<Employee> resultCollection = new ArrayList<>();
//         java.sql.Connection connection = ConnectionToDB.databaseConnection();
//
//        try (PreparedStatement statement = connection.prepareStatement("select sirname, io,oper_phone from ratsg:a15 ")){
//            final ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()){
//                Employee employee = new Employee();
//                employee.setSurname(resultSet.getString("sirname"));
//                employee.setIo(resultSet.getString("io"));
//                employee.setOperNumber(resultSet.getString("oper_phone"));
//                resultCollection.add(employee);
//            }
//        } catch (SQLException e) {
//            throw new DaoException(e);
//        }
//        finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//            }
//        }
//        return resultCollection;
//    }
//
//    @Override
//    public Employee findEntityByOperNumber(String io) throws DaoException, SQLException, ClassNotFoundException {
//        java.sql.Connection connection = ConnectionToDB.databaseConnection();
//
//        Employee employee = null;
//
//        try(PreparedStatement preparedStatement = connection.prepareStatement("select  io from ratsg:a15 where oper_user = ?")) {
//            preparedStatement.setString(1, USER);
//            final ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                    employee = new Employee();
//                    employee.setSurname(resultSet.getString("sirname"));
//                    employee.setIo(resultSet.getString("io"));
//                    employee.setOperNumber(resultSet.getString("oper_phone"));;
//
//                }
//            }
//        finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//            }
//        }
//        return Optional.ofNullable(employee);
//    }
////Spring boot
//
//
////запрос составить
//    @Override
//    public void delete(String operNumber) throws DaoException, SQLException, ClassNotFoundException {
//        boolean result = true;
//        java.sql.Connection connection = ConnectionToDB.databaseConnection();
//
//        try (PreparedStatement statement= connection.prepareStatement("select sirname, io,oper_phone from ratsg:a15 where oper_user = USER" )){
//            statement.setString(1,USER);
//            statement.execute();
//        } catch (SQLException e) {
//            result = false;
//
//            throw new DaoException(e);
//        }
//        finally {
//            try {
//                connection.close();//////////
//            } catch (SQLException e) {
//            }
//        }
//        return result;
//    }
//    public void delete(int id) throws SQLException, ClassNotFoundException {
//        java.sql.Connection connection = ConnectionToDB.databaseConnection();
//        PreparedStatement preparedStatement =
//                null;
//        try {
//            preparedStatement = connection.prepareStatement("DELETE FROM a15 WHERE oper_user=?");
//
//            preparedStatement.setString(1,USER );
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public void create(Employee employee) throws DaoException, SQLException, ClassNotFoundException {
//        boolean result = true;
//        java.sql.Connection connection = ConnectionToDB.databaseConnection();
//        Statement statement;
//
//        try {
//            statement = connection.createStatement();
//            statement.executeUpdate("INSERT INTO employee\n" +
//                    "(sirname, io, oper_phone)\n" +
//                    "values ('"+employee.getSurname()+"','"+employee.getIo()+"' ,'"+ employee.getOperNumber()+"');" );
//
//            ResultSet resultSet = statement.executeQuery("SELECT employee.io, employee.operPhone\n" +
//                    "FROM ratsg:a15");
//            while (resultSet.next()) {
//                if(resultSet.getString("sirname").equals(employee.getSurname())) {
//                    String tempAccountId = resultSet.getString("sirname");
//                    statement.executeUpdate("INSERT INTO employee\n" +
//                            "(account_id)\n" +
//                            "values ('" + resultSet.getString("io") + "'); ");
//                    ResultSet resultSet1 = statement.executeQuery("SELECT employee.io, employee.operPhone\n" +
//                            "FROM ratsg:a15");
//                    while (resultSet1.next()){
//                        if(resultSet1.getString("a15_io")==tempAccountId){
//                            employee.setOperNumber(resultSet1.getString("oper_phone"));
//                            break;
//                        }
//                    }
//                    break;
//                }
//            }
//        } catch (SQLException e) {
//            result = false;
//            throw new DaoException(e);
//        }
//        finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//            }
//        }
//        return result;
//    }
//
//    @Override
//    public boolean update(Employee employee) throws DaoException, SQLException, ClassNotFoundException {
//        boolean result = true;
//        java.sql.Connection connection = ConnectionToDB.databaseConnection();
//        try(PreparedStatement statement = connection.prepareStatement("UPDATE ratsg:a15\n" +
//                "SET io = ?, " +
//                "sirname = ?, " +
//                "oper_number = ? \n" +
//                "FROM employee\n" +
//                "where oper_user = USER")) {
//            statement.setString(1,employee.getSurname());
//            statement.setString(2,employee.getIo());
//            statement.setString(3,employee.getOperNumber());
//            statement.execute();
//        } catch (SQLException e) {
//            result = false;
//            throw new DaoException(e);
//        }
//        finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//            }
//        }
//        return result;
//    }
//
//
//}
