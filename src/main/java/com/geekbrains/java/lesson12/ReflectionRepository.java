package com.geekbrains.java.lesson12;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReflectionRepository<T> {
    private Class<T> myClass;
    private String tableName;
    private static Connection connection;
    private static Statement stmt;

    public ReflectionRepository(Class<T> myClass) {
        this.myClass = myClass;
        this.tableName =  myClass.getAnnotation(DbTable.class).name();
    }

    public void save(T object) throws SQLException, IllegalAccessException {
        Class c = object.getClass();
        if (!c.isAnnotationPresent(DbTable.class)) {
            throw new RuntimeException("Unable to save objects for class " + c.getSimpleName());
        }
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("INSERT INTO ");
        queryBuilder.append(((DbTable) c.getAnnotation(DbTable.class)).name());
        queryBuilder.append(" (");
        Field[] fields = c.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(DbColumn.class)) {
                queryBuilder
                        .append(f.getName())
                        .append(", ");
            }
        }
        queryBuilder.setLength(queryBuilder.length() - 2);
        queryBuilder.append(") VALUES (");
        for (Field f : fields) {
            if (f.isAnnotationPresent(DbColumn.class)) {
                queryBuilder.append("?, ");
            }
        }
        queryBuilder.setLength(queryBuilder.length() - 2);
        queryBuilder.append(");");
        PreparedStatement ps = connection.prepareStatement(queryBuilder.toString());
        int index = 1;
        for (Field f : fields) {
            if (f.isAnnotationPresent(DbColumn.class)) {
                ps.setObject(index, f.get(object));
                index++;
            }
        }
        ps.executeUpdate();
    }

    public void connect() throws Exception {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:mydb.db");
        stmt = connection.createStatement();
    }

    public void disconnect() {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public T getById(int id) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT * FROM " + tableName + " WHERE id = " + id + ";");
        ResultSet rs = stmt.executeQuery(queryBuilder.toString());
        return constructObj(rs);
    }

    public T constructObj(ResultSet rs) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException, NoSuchFieldException {
        Field field;
        T o = myClass.getConstructor().newInstance();
        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            field = myClass.getDeclaredField(rsmd.getColumnName(i));
            if (rsmd.getColumnType(i) == 4) {
                field.set(o, rs.getInt(i));
            } else {
                field.set(o, rs.getString(i));
            }
        }
        return o;
    }

    public List<T> getAll() throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT * FROM " + tableName + ";");
        ResultSet rs = stmt.executeQuery(queryBuilder.toString());
        List<T> objects = new ArrayList<>();
        while (rs.next()) {
            objects.add(constructObj(rs));
        }
        return objects;
    }

    public void deleteById(int id) throws SQLException {
        stmt.executeUpdate("DELETE FROM " + tableName + " WHERE id = " + id + ";");
    }

    public void deleteAll() throws SQLException {
        stmt.executeUpdate("DELETE FROM " + tableName + ";");
    }
}
