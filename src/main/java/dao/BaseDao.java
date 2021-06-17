package dao;

import dao.exception.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BaseDao<T>{
    List<T> findAll() throws DaoException, SQLException, ClassNotFoundException;
    Optional<T> findEntityById(long id) throws DaoException, SQLException, ClassNotFoundException;
    boolean delete(long id) throws DaoException, SQLException, ClassNotFoundException;
    boolean create(T t) throws DaoException, SQLException, ClassNotFoundException;
    boolean update(T t) throws DaoException, SQLException, ClassNotFoundException;
}
