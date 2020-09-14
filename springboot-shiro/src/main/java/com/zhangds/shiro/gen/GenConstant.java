package com.zhangds.shiro.gen;

public class GenConstant {

    //输出配置
    public static final String OUTPUT_DIR = "D://code";
    public static final String AUTHOR = "zhangds";

    //连接数据库配置
    public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "root";
    public static final String URL = "jdbc:mysql://192.168.25.128:3306/springboot?useUnicode=true&characterEncoding=UTF-8&useSSL=false";

    //策略配置
    public static final String[] TABLE_PREFIX = {""};
    public static final String[] TABLE_INCLUDE = {"log", "permission", "role", "user", "role_permission", "user_role"};

    //包配置
    public static final String PARENT = "com.zhangds.shiro";
    public static final String CONTROLLER = "controller";
    public static final String SERVICE = "service";
    public static final String SERVICE_IMPL = "service.impl";
    public static final String MAPPER = "mapper";
    public static final String ENTITY = "entities";
    public static final String XML = "mapper.xml";

}
