package com.adanac.demo.anno;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectUtil {
	private static String getTableName(Class<?> bean) {
		String name = null;
		// 判断是否有Table注解
		if (bean.isAnnotationPresent(Table.class)) {
			// 获取注解对象
			Table table = bean.getAnnotation(Table.class);
			name = table.name();
		}
		return name;
	}

	private static List<NameAndType> getColumns(Class<?> bean) {
		List<NameAndType> columns = new ArrayList<NameAndType>();
		Field[] fields = bean.getDeclaredFields();
		if (fields != null) {
			// 分析Bean中的变量是否需要生成sql字段
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				if (field.isAnnotationPresent(Column.class)) {
					// 生成sql字段的名
					Column column = field.getAnnotation(Column.class);
					String name = column.name();
					// 生成sql字段的类型
					String type = null;
					if (int.class.isAssignableFrom(field.getType())
							|| Integer.class.isAssignableFrom(field.getType())) {
						type = "int";
					} else if (String.class.isAssignableFrom(field.getType())) {
						type = "varchar";
					} else {
						throw new RuntimeException("unspported type=" + field.getType().getSimpleName());
					}
					columns.add(new NameAndType(type, name));

				}

			}
		}
		return columns;
	}

	public static String createTable(Class<?> bean) {
		String tableName = getTableName(bean);
		List<NameAndType> columns = getColumns(bean);
		if (tableName != null && !tableName.equals("") && !columns.isEmpty()) {
			StringBuilder createTableSql = new StringBuilder("create table ");
			// 加表名
			createTableSql.append(tableName);
			createTableSql.append("(");

			// 加表中字段
			for (int i = 0; i < columns.size(); i++) {
				NameAndType column = columns.get(i);
				createTableSql.append(column.name);
				createTableSql.append(" ");
				createTableSql.append(column.type);
				createTableSql.append("(");
				createTableSql.append(column.type == "varchar" ? "255" : "20");
				createTableSql.append(")");
				// 追加下一个字段定义前需要添加逗号
				if (i != columns.size() - 1) {
					createTableSql.append(",");
				}
			}
			createTableSql.append(")");
			return createTableSql.toString();
		}

		return null;
	}
}
