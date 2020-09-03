> 分类

    可以通过@EnableScheduling注解和@Scheduled注解实现定时任务，也可以通过SchedulingConfigurer接口来实现定时任务。
    但是这两种方式不能动态添加、删除、启动、停止任务。
    
    要实现动态增删启停定时任务功能，要集成Quartz框架。