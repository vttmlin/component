package com.tmdaq.component.mybatis.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.ArrayList;
import java.util.List;

public class ListPageAdapter extends PluginAdapter {
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze,
                                   TopLevelClass topLevelClass,
                                   IntrospectedTable introspectedTable) {
        addMethod(interfaze, introspectedTable);
        return true;
    }

    private void addMethod(Interface interfaze, IntrospectedTable introspectedTable) {
        String objectName = introspectedTable.getTableConfiguration().getDomainObjectName();
        Method method = new Method();
        method.addParameter(new Parameter(new FullyQualifiedJavaType(String.format("PageVo<%s>", objectName)), "param"));
        method.setReturnType(new FullyQualifiedJavaType(String.format("List<%s>", objectName)));
        method.setName(String.format("listPage%sBean", objectName));
        interfaze.addMethod(method);
        interfaze.addImportedType(new FullyQualifiedJavaType("com.tmdaq.common.PageVo"));
        interfaze.addImportedType(new FullyQualifiedJavaType("java.util.List"));
    }


    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        String targetPackage = introspectedTable.getContext().getJavaModelGeneratorConfiguration().getTargetPackage();
        String objectName = introspectedTable.getTableConfiguration().getDomainObjectName();
        XmlElement element = new XmlElement("select");
        element.addAttribute(new Attribute("id", String.format("listPage%sBean", objectName)));
        element.addAttribute(new Attribute("resultType", targetPackage + "." + objectName));
        element.addElement(new TextElement(String.format("select <include refid=\"Base_Column_List\"/> from %s where 1=1", introspectedTable.getTableConfiguration().getTableName())));
        for (IntrospectedColumn introspectedColumn : introspectedTable.getBaseColumns()) {
            addElement(introspectedColumn, element);
        }
        for (IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns()) {
            addElement(introspectedColumn, element);
        }
        document.getRootElement().addElement(element);
        return true;
    }

    private void addElement(IntrospectedColumn introspectedColumn, XmlElement element) {
        XmlElement ifElement = new XmlElement("if");
        String shortName = introspectedColumn.getFullyQualifiedJavaType().getShortName();
        String javaProperty = introspectedColumn.getJavaProperty();
        String jdbcTypeName = introspectedColumn.getJdbcTypeName();
        String actualColumnName = introspectedColumn.getActualColumnName();
        if ("String".equals(shortName)) {
            ifElement.addAttribute(new Attribute("test", String.format("query.%s !=null and query.%s != null", javaProperty, javaProperty)));
            ifElement.addElement(new TextElement(String.format("and %s = #{query.%s,jdbcType=%s}", actualColumnName, javaProperty, jdbcTypeName)));
            element.addElement(ifElement);
        } else if ("Date".equals(shortName)) {
            ifElement.addAttribute(new Attribute("test", String.format("query.%s !=null", javaProperty + "Start")));
            ifElement.addElement(new TextElement(String.format("and %s >= #{query.%s,jdbcType=%s}", actualColumnName, javaProperty, jdbcTypeName)));
            element.addElement(ifElement);
            XmlElement ifElementEnd = new XmlElement("if");
            ifElementEnd.addAttribute(new Attribute("test", String.format("query.%s !=null", javaProperty + "End")));
            ifElementEnd.addElement(new TextElement(String.format("and %s &lt;= #{query.%s,jdbcType=%s}", actualColumnName, javaProperty, jdbcTypeName)));
            element.addElement(ifElementEnd);
        } else {
            ifElement.addAttribute(new Attribute("test", String.format("query.%s !=null", javaProperty)));
            ifElement.addElement(new TextElement(String.format("%s = #{query.%s,jdbcType=%s}", actualColumnName, javaProperty, jdbcTypeName)));
            element.addElement(ifElement);
        }
    }

    /**
     *
     * */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {
        List<Field> addField = new ArrayList<>();
        for (Field field : topLevelClass.getFields()) {
            if ("Date".equals(field.getType().getShortName())) {
                Field fieldStart = new Field();
                fieldStart.setName(field.getName() + "Start");
                fieldStart.setType(new FullyQualifiedJavaType("String"));
                fieldStart.setVisibility(JavaVisibility.PRIVATE);
                addField.add(fieldStart);
                Field fieldEnd = new Field();
                fieldEnd.setName(field.getName() + "End");
                fieldEnd.setVisibility(JavaVisibility.PRIVATE);
                fieldEnd.setType(new FullyQualifiedJavaType("String"));
                addField.add(fieldEnd);
            }
            field.addJavaDocLine("/**");
            field.addJavaDocLine(String.format(" *  length=%s", null));
            field.addJavaDocLine(" * */");
        }
        for (Field field : addField) {
            topLevelClass.addField(field);
        }
        return true;
    }
}
