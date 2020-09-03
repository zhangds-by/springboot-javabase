> AopUtils
    
    //获取对象的真实类型
    public static Class<?> getTargetClass(Object candidate) {
        org.springframework.util.Assert.notNull(candidate, "Candidate object must not be null");
        Class<?> result = null;
        if (candidate instanceof TargetClassAware) { // SpringAOP代理类去实现的接口 TargetClassAware接口
            result = ((TargetClassAware)candidate).getTargetClass();
        }
        if (result == null) { //cglib就是通过继承来完成动态代理的，代理对象的父类即为真实对象类型；
                              //JDK完成的代理，只需要得到本身类型即可，因为这就是接口的类型；
            result = isCglibProxy(candidate) ? candidate.getClass().getSuperclass() : candidate.getClass();
        }
        return result;
    }

    //从代理对象上的一个方法，找到真实对象上对应的方法
    Method getMostSpecificMethod(Method method, Class<?> targetClass)
    
> StringUtils

    字符串判断操作
    
    字符串处理
    
    文件路径名称操作
    
    字符串和子串操作
    
    字符串和数组之间的基本操作  
    
> ClassUtils 代理和类加载方法

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            //获取当前线程的context class loader
            cl = Thread.currentThread().getContextClassLoader();
        }catch (Throwable ex) {
        }
        if (cl == null) {
            // 如果没有context loader，使用当前类的类加载器；
            cl = ClassUtils.class.getClassLoader();
            if (cl == null) {
                // 如果当前类加载器无法获取，获得bootstrap ClassLoader
                try {
                    cl = ClassLoader.getSystemClassLoader();
                } catch (Throwable ex) {
                }
            }
        }
        return cl;
    }
    
    bootstrap class loader：主要负责main方法启动的时候，加载JAVA_HOME/lib下的jar包；
    extension class loader：主要负责加载JAVA_HOME/ext/lib下的jar包；
    system class loader：主要负责加载classpath下的jar包或者类；
  