> problems
    
    org.springframework.beans.factory.BeanCreationException: 
    Error creating bean with name 'openApiSchemaPropertyBuilder': Lookup method resolution failed;
    nested exception is java.lang.IllegalStateException: 
    Failed to introspect Class [springfox.documentation.swagger.schema.OpenApiSchemaPropertyBuilder] from ClassLoader [sun.misc.Launcher$AppClassLoader@18b4aac2]
    
    
    org.springframework.beans.factory.BeanCreationException: 
    Error creating bean with name 'serviceModelToOpenApiMapperImpl': Lookup method resolution failed; 
    nested exception is java.lang.IllegalStateException: 
    
    遇到以上问题，缺少springboot web 依赖包
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    
> use
    
    访问：
        http://localhost:8092/swagger-ui/
        
    注解使用：
        @Api：用在controller类，描述API接口
        @ApiOperation：描述接口方法
        @ApiModel：描述对象
        @ApiModelProperty：描述对象属性
        @ApiImplicitParams：描述接口参数
        @ApiResponses：描述接口响应
        @ApiIgnore：忽略接口方法