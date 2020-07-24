package org.mybatis.generator.plugins;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.enums.Annotation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LombokPlugin extends PluginAdapter {

    public boolean validate(List<String> list) {
        return true;
    }

    /**
     * 修改实体类
     *
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        classAnnotation(topLevelClass, null);
        Set<FullyQualifiedJavaType> set = new HashSet<>();
        set.add(new FullyQualifiedJavaType(Annotation.ApiModel.getClazz()));
        set.add(new FullyQualifiedJavaType(Annotation.DATA.getClazz()));
        set.add(new FullyQualifiedJavaType(Annotation.Builder.getClazz()));
        set.add(new FullyQualifiedJavaType(Annotation.Accessors.getClazz()));
        set.add(new FullyQualifiedJavaType(Annotation.NoArgsConstructor.getClazz()));
        set.add(new FullyQualifiedJavaType(Annotation.AllArgsConstructor.getClazz()));
        topLevelClass.addImportedTypes(set);

        topLevelClass.addAnnotation(Annotation.DATA.getAnnotation());
        topLevelClass.addAnnotation(Annotation.Builder.getAnnotation());
        topLevelClass.addAnnotation(Annotation.Accessors.getAnnotation());
        topLevelClass.addAnnotation(Annotation.NoArgsConstructor.getAnnotation());
        topLevelClass.addAnnotation(Annotation.AllArgsConstructor.getAnnotation());
        topLevelClass.addAnnotation(Annotation.ApiModel.getAnnotation() + "(value=\"" + topLevelClass.getType() + "\",description=\"" + introspectedTable.getRemarks() + "\")");
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    /**
     * 修改mapper接口
     *
     * @param interfaces
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean clientGenerated(Interface interfaces, IntrospectedTable introspectedTable) {
        interfaceAnnotation(interfaces, null);
        Set<FullyQualifiedJavaType> set = new HashSet<>();
        set.add(new FullyQualifiedJavaType(Annotation.Mapper.getClazz()));
        interfaces.addImportedTypes(set);
        interfaces.addAnnotation(Annotation.Mapper.getAnnotation() + "()");
        return super.clientGenerated(interfaces, introspectedTable);
    }

    /**
     * 实体类字段
     *
     * @param field
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return
     */
    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        // 生成注释
        fieldAnnotation(field, introspectedColumn.getRemarks());
        // 生成注释结束


//        // 追加ApiModelProperty注解
//        topLevelClass.addImportedType(new FullyQualifiedJavaType(Annotation.ApiModelProperty.getClazz()));
//        field.addAnnotation(Annotation.ApiModelProperty.getAnnotation() + "(value=\""+ introspectedColumn.getRemarks() + "\",name=\"" +introspectedColumn.getJavaProperty()+"\")");
//
//        // 追加日期格式化注解
//        if (introspectedColumn.getJdbcTypeName().equals("TIMESTAMP")) {
//            field.addAnnotation(Annotation.JsonFormat.getAnnotation() + "(pattern = \"yyyy-MM-dd\",timezone=\"GMT+8\")");
//            topLevelClass.addImportedType(new FullyQualifiedJavaType(Annotation.JsonFormat.getClazz()));
//        }
//        // tinyint数据（Byte）转换成（Integer）类型
//        String a = field.getType().getShortName();
//        if ("Byte".equals(a)) {
//            field.setType(new FullyQualifiedJavaType("java.lang.Integer"));
//        }
        return super.modelFieldGenerated(field, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
    }

    /**
     * get方法 false 不生成
     *
     * @param method
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return
     */
    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    /**
     * get方法 false 不生成
     *
     * @param method
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return
     */
    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    /**
     * mapper接口方法
     * DeleteByPrimaryKey方法
     *
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @return
     */
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        methodAnnotation(method, "根据主键删除数据");
        return super.clientDeleteByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable);
    }


    /**
     * mapper接口方法
     * Insert方法
     *
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @return
     */
    public boolean clientInsertMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        methodAnnotation(method, "插入数据库记录（不建议使用）");
        return super.clientInsertMethodGenerated(method, interfaze, introspectedTable);
    }


    /**
     * mapper接口方法
     * InsertSelective方法
     *
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @return
     */
    public boolean clientInsertSelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        methodAnnotation(method, "插入数据库记录（建议使用）");
        return super.clientInsertSelectiveMethodGenerated(method, interfaze, introspectedTable);
    }


    /**
     * mapper接口方法
     * SelectByPrimaryKey方法
     *
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @return
     */
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        methodAnnotation(method, "根据主键id查询");
        return super.clientSelectByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable);
    }


    /**
     * mapper接口方法
     * UpdateByPrimaryKeySelective方法
     *
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @return
     */
    public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        methodAnnotation(method, "修改数据(推荐使用)");
        return super.clientUpdateByPrimaryKeySelectiveMethodGenerated(method, interfaze, introspectedTable);
    }


    /**
     * mapper接口方法
     * UUpdateByPrimaryKey方法
     *
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @return
     */
    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        methodAnnotation(method, "修改数据");
        return super.clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(method, interfaze, introspectedTable);
    }

    /**
     * 方法注释生成
     *
     * @param method
     * @param explain
     */
    public static void methodAnnotation(Method method, String explain) {
        // 生成注释
        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(explain);
        method.addJavaDocLine(sb.toString());
        Parameter param = method.getParameters().get(0);
        sb.setLength(0);
        sb.append(" * @param ");
        sb.append(param.getName());
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine(" */");
        // 生成注释结束
    }


    /**
     * 属性注释生成
     *
     * @param field
     * @param explain
     */
    public static void fieldAnnotation(Field field, String explain) {
        // 生成注释
        StringBuilder sb = new StringBuilder();
        field.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(explain);
        field.addJavaDocLine(sb.toString());
        field.addJavaDocLine(" */");
        // 生成注释结束
    }

    /**
     * 类注释生成
     *
     * @param topLevelClass
     * @param explain
     */
    public static void classAnnotation(TopLevelClass topLevelClass, String explain) {
        // 生成注释
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine("* @author Alexpy");
        topLevelClass.addJavaDocLine("* @Date " + dateStr(new Date()));
        topLevelClass.addJavaDocLine("*/");
        // 生成注释结束
    }


    /**
     * 接口注释生成
     *
     * @param interfaze
     * @param explain
     */
    public static void interfaceAnnotation(Interface interfaze, String explain) {
        // 生成注释
        interfaze.addJavaDocLine("/**");
        interfaze.addJavaDocLine("* @author Alexpy");
        interfaze.addJavaDocLine("* @Date " + dateStr(new Date()));
        interfaze.addJavaDocLine("*/");
        // 生成注释结束
    }

    private static String dateStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(date);
    }

}
