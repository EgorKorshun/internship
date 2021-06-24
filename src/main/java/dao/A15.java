package dao;

import dao.exception.DaoException;
import domain.Employee;

import java.sql.SQLException;
import java.util.List;

public interface A15<T>{
    List<T> findAll() throws DaoException, SQLException, ClassNotFoundException;
    Employee findEntityByOperNumber(String operNumber) throws DaoException, SQLException, ClassNotFoundException;
    void delete(String operNumber) throws DaoException, SQLException, ClassNotFoundException;
    void create(T t) throws DaoException, SQLException, ClassNotFoundException;
    void update(T t, String operNumber) throws DaoException, SQLException, ClassNotFoundException;
}
