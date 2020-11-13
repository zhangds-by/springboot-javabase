> mybatis 升级版 plus

    封装好的 Service 和 Mapper 接口，提供了CRUD
    
    条件构造器 Wapper 方法构建条件，也可以进行原生sql进行拼接
    
    
> 依赖 mybatisplus子模块 需要注意的问题

    其他模块引用子模块，找不到数据库配置：
        需要在配置文件加上数据库配置
    
    注入对象的问题：
        需要项目增加注解扫描包 
        
    程序包com.zhangds.mybatisplus.service不存在：
        