package utils;

import base.IBaseEntity;


import java.sql.*;
import java.util.*;

public class DBUtils implements IBaseEntity {

    private static final String URL = "jdbc:mysql://localhost:3306/database";
    private static final String USER = "root";
    private static final String PASSWORD = "9802357s";


    public static Connection getConnection(final String url, final String user, final String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static Connection getConnection() throws SQLException {
        return getConnection(URL, USER, PASSWORD);
    }

    private static List<Map<String, Object>> extractFromResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<Map<String, Object>> resultSet = new ArrayList<>();
        while (rs.next()) {
            HashMap<String, Object> row = new HashMap<>(columnCount);
            for (int i = 1; i <= columnCount; ++i) {
                row.put(metaData.getColumnName(i), rs.getObject(i));
            }
            resultSet.add(row);
        }
        return resultSet;
    }

    public static List<Map<String, Object>> getQueryMysql(String query) {
        try (Statement st = getConnection().createStatement();
             ResultSet rs = st.executeQuery(query)) {

            return extractFromResultSet(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("Sql query error", ex);
        }
    }

    public static List<Map<String, Object>> getParametrizedQueryMysql(String query, String... params) {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement(query);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            ResultSet resultSet = ps.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> map = new HashMap<>();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i + 1);
                    Object columnValue = resultSet.getObject(columnName);
                    if (columnValue == null) {
                        columnValue = "";
                    }
                    map.put(columnName, columnValue);
                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}

