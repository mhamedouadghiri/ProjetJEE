package com.mhamed.ProjetJEE.data;

import java.sql.Connection;

public abstract class BaseDatasource<T> implements BaseDAO<T> {

    protected static Connection connection;

    protected BaseDatasource() {
        connection = JDBCConnection.getConnection();
    }
}
