package me.anisimov.agency.util;

import me.anisimov.agency.domain.BaseEntity;
import me.anisimov.agency.persistance.DAO;
import me.anisimov.agency.util.annotation.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

@Component
public class PersistenceUtil {
    @Autowired
    DAO dao;

    public <T extends BaseEntity> String buildSqlInsert(T entity) throws SQLException {
        Class<?> entityClass = entity.getClass();
        if (!entityClass.isAnnotationPresent(Table.class)) {
            throw new RuntimeException("Provided object should be an entity");
        }
        Map<String, Object> params;
        params = entity.toMap();
        String sql = sqlInsertBlank(entityClass, params);
        return sqlSubstitution(sql, params);
    }
    public <T extends BaseEntity> String buildSqlUpdate(T entity) throws SQLException {
        Class<?> entityClass = entity.getClass();
        if (!entityClass.isAnnotationPresent(Table.class)) {
            throw new RuntimeException("Provided object should be an entity");
        }
        Map<String, Object> params;
        params = entity.toMap();
        Long id = entity.getId();
        String sql = sqlUpdateBlank(entityClass, params, id);
        return sqlSubstitution(sql, params);
    }

    public String sqlUpdateBlank(Class<?> entityClass, Map<String, Object> params,Long id) {
        String sql ="Update ";
        String prefix="";
        StringBuilder fullSql =new StringBuilder(sql);

        Table tableAnnotation = entityClass.getAnnotation(Table.class);
        String tableName = tableAnnotation.name();
        fullSql.append(tableName).append(" set ");

        for(String paramName: params.keySet()){
            Object paramValue = params.get(paramName);
            if (paramValue != null) {
                fullSql.append(prefix);
                prefix = ",";
                fullSql.append(paramName).append("=?");
            }
        }
        fullSql.append(" where id=").append(id);
        return fullSql.toString();
    }

    public String sqlInsertBlank(Class<?> entityClass, Map<String, Object> params) {
        String prefix = "";
        String pr = "";
        String sqlStart = "Insert into ";
        String sqlEnd = ") Values (";
        StringBuilder fullSql = new StringBuilder(sqlStart);
        StringBuilder sqlQuestionMark = new StringBuilder(sqlEnd);

        Table tableAnnotation = entityClass.getAnnotation(Table.class);
        String tableName = tableAnnotation.name();
        fullSql.append(tableName).append("(");

        for (String paramName : params.keySet()) {
            Object paramValue = params.get(paramName);
            if (paramValue != null) {
                fullSql.append(prefix);
                sqlQuestionMark.append(pr);
                prefix = ",";
                fullSql.append(paramName);
                pr = ",";
                sqlQuestionMark.append("?");
            }
        }
        sqlQuestionMark.append(");");
        fullSql.append(sqlQuestionMark);
        return fullSql.toString();
    }

    public String sqlSubstitution(String sql, Map<String, Object> params) throws SQLException {
        int paramNumber = 1;
        PreparedStatement ps = dao.getConnection().prepareStatement(sql);
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
//    public Map<String,Object> toMap(Object data) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        Map<String, Object> params = new HashMap<>();
//        Class<?> entityClass = data.getClass();
//        Field[] fields = entityClass.getDeclaredFields();
//        for (int i = 0; i < fields.length ; i++) {
//            Field field = fields[i];
//            if (field.isAnnotationPresent(Column.class)){
//                Column annotation = field.getAnnotation(Column.class);
//                String columnName = annotation.name();
//                String fieldName = field.getName();
//                String accessorMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
//                Method method = entityClass.getDeclaredMethod(accessorMethodName);
//                Object invokeResult = method.invoke(data);
//                params.put(columnName,invokeResult);
//            }
//        }
//        return params;
//    }


