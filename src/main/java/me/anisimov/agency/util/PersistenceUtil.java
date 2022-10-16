package me.anisimov.agency.util;

import me.anisimov.agency.persistance.DAO;
import me.anisimov.agency.util.annotation.Column;
import me.anisimov.agency.util.annotation.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Component
public class PersistenceUtil {
    @Autowired
    DAO dao;

    public <T> String buildSqlInsert(T object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException {
        Class<?> entityClass = object.getClass();
        if (!entityClass.isAnnotationPresent(Table.class)) {
            throw new RuntimeException("Provided object should be an entity");
        }
        String sql = "Insert into ";
        StringBuilder st = new StringBuilder(sql);
        Map<String, Object> params = new HashMap<>();
        int paramNumber = 1;
        String prefix = "";
        String pr = "";
        String columnName = "";

        Table tableAnnotation = entityClass.getAnnotation(Table.class);
        String tableName = tableAnnotation.name();
        st.append(tableName).append("(");

        Field[] fields = entityClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (field.isAnnotationPresent(Column.class)) {
                Column annotation = field.getAnnotation(Column.class);
                columnName = annotation.name();
                String fieldName = field.getName();
                String accessorMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method method = entityClass.getDeclaredMethod(accessorMethodName);
                Object invokeResult = method.invoke(object);
                params.put(columnName, invokeResult);
            }
        }
        for (String paramName : params.keySet()) {
            Object paramValue = params.get(paramName);
            if (paramValue != null) {
                st.append(prefix);
                prefix = ",";
                st.append(paramName);
            }
        }
        st.append(") Values (");
        for (String paramName : params.keySet()) {
            Object paramValue = params.get(paramName);
            if (paramValue != null) {
                st.append(pr);
                pr = ",";
                st.append("?");
            }
        }
        st.append(");");
        PreparedStatement ps = dao.getConnection().prepareStatement(String.valueOf(st));
        for (String paramName : params.keySet()) {
            Object paramValue = params.get(paramName);
            if (paramValue != null) {
                if (paramValue instanceof Long) {
                    ps.setLong(paramNumber, (Long) paramValue);
                } else if (paramValue instanceof Byte) {
                    ps.setByte(paramNumber, (Byte) paramValue);
                } else if (paramValue instanceof Integer) {
                    ps.setInt(paramNumber, (Integer) paramValue);
                } else {
                    ps.setString(paramNumber, String.valueOf(paramValue));
                }
                paramNumber++;
            }
        }
        return ps.toString();
    }

}
