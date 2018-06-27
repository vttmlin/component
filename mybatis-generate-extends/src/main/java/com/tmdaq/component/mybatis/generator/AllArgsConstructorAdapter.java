package com.tmdaq.component.mybatis.generator;

import lombok.AllArgsConstructor;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

@AllArgsConstructor
public class AllArgsConstructorAdapter extends PluginAdapter {
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {
        topLevelClass.addAnnotation("@AllArgsConstructor");
        topLevelClass.addImportedType(new FullyQualifiedJavaType("lombok.AllArgsConstructor"));
        return true;
    }
}
