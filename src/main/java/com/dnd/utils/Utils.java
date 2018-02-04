package com.dnd.utils;

import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public final class Utils {

    private Utils() {
    }

    public static Array getEnumSqlArray(List<? extends Enum> list, DataSource dataSource) throws SQLException {
        if (!CollectionUtils.isEmpty(list)) {
            String[] array = list.stream().filter(a -> a != null).map(Enum::name).toArray(String[]::new);
            return dataSource.getConnection().createArrayOf("varchar", array);
        }
        return dataSource.getConnection().createArrayOf("varchar", new String[]{});
    }

    public static String getEnumValue(Enum value) {
        if (value != null) {
            return value.name();
        }
        return null;
    }

}
