package com.db.mock.comon;

/**
 * @author: caozm
 * @create: 2022-06-18 11:01
 * @Version 1.0
 * @description:
 **/
public class MapperSqlType {

    public static Class<?> map(int jdbcType) throws Exception {
        switch (jdbcType) {
            case java.sql.Types.BIT:
            case java.sql.Types.BOOLEAN:
                return java.lang.Boolean.class;
            case java.sql.Types.TINYINT:
            case java.sql.Types.SMALLINT:
            case java.sql.Types.INTEGER:
                return java.lang.Integer.class;
            case java.sql.Types.BIGINT:
                return java.lang.Long.class;
            case java.sql.Types.FLOAT:
            case java.sql.Types.DOUBLE:
                return java.lang.Double.class;
            case java.sql.Types.REAL:
                return java.lang.Float.class;
            case java.sql.Types.NUMERIC:
            case java.sql.Types.DECIMAL:
                return java.math.BigDecimal.class;
            case java.sql.Types.CHAR:
            case java.sql.Types.VARCHAR:
            case java.sql.Types.LONGVARCHAR:
                return java.lang.String.class;
            case java.sql.Types.DATE:
                return java.sql.Date.class;
            case java.sql.Types.TIME:
                return java.sql.Time.class;
            case java.sql.Types.TIMESTAMP:
                return java.sql.Timestamp.class;
            case java.sql.Types.STRUCT:
                return java.sql.Struct.class;
            case java.sql.Types.ARRAY:
                return java.sql.Array.class;
            case java.sql.Types.BLOB:
                return java.sql.Blob.class;
            case java.sql.Types.CLOB:
                return java.sql.Clob.class;
            case java.sql.Types.REF:
                return java.sql.Ref.class;
            case java.sql.Types.DATALINK:
                return java.net.URL.class;
            case java.sql.Types.ROWID:
                return java.sql.RowId.class;
            case java.sql.Types.NULL:
            case java.sql.Types.OTHER:
            case java.sql.Types.JAVA_OBJECT:
            case java.sql.Types.DISTINCT:
            case java.sql.Types.BINARY:
            case java.sql.Types.VARBINARY:
            case java.sql.Types.LONGVARBINARY:
            default:
                throw new Exception("invalid or unmapped SQL type (" + jdbcType
                        + ")");
        }
    }
}