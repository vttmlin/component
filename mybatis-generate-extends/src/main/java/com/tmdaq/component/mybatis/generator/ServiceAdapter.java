package com.tmdaq.component.mybatis.generator;

import freemarker.template.Template;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceAdapter extends PluginAdapter {
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String targetPackage = introspectedTable.getContext().getJavaModelGeneratorConfiguration().getTargetPackage();
        String targetProject = introspectedTable.getContext().getJavaClientGeneratorConfiguration().getTargetProject();
        String domainObjectName = introspectedTable.getTableConfiguration().getDomainObjectName();
        Map<String, String> map = new HashMap<>();
        targetPackage = targetPackage.substring(0, targetPackage.lastIndexOf('.'));
        String targetPackageFile = targetPackage.replace(".", "/");
        map.put("targetPackage", targetPackage);
        map.put("bean", domainObjectName.substring(0, 1).toLowerCase() + domainObjectName.substring(1));
        map.put("Bean", domainObjectName);
        try {
            Template t = new Template(null, new InputStreamReader(this.getClass().getResourceAsStream("/Service.ftl")), null);
            String format = String.format("%s%s%s/%s/service/%sService.java", System.getProperty("user.dir"), File.separator, targetProject, targetPackageFile, domainObjectName);
            Writer writer = new FileWriter(format);
            t.process(map, writer);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
