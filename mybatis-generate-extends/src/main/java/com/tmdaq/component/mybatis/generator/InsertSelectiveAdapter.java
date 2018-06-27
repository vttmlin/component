package com.tmdaq.component.mybatis.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * 自增长主键 添加元素
 */
public class InsertSelectiveAdapter extends PluginAdapter {
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element,
                                                         IntrospectedTable introspectedTable) {
        if (introspectedTable != null && introspectedTable.getPrimaryKeyColumns() != null && !introspectedTable.getPrimaryKeyColumns().isEmpty()) {
            List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();
            for (IntrospectedColumn primaryKeyColumn : primaryKeyColumns) {
                if (primaryKeyColumn.isAutoIncrement()) {
                    element.addAttribute(new Attribute("keyColumn", primaryKeyColumn.getActualColumnName()));
                    element.addAttribute(new Attribute("keyProperty", primaryKeyColumn.getJavaProperty()));
                    element.addAttribute(new Attribute("useGeneratedKeys", "true"));
                }
            }
        }
        return true;
    }
}
