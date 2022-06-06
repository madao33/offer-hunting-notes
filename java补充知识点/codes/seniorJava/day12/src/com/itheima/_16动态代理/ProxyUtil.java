package com.itheima._16动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
    代理类：帮助我们做一个被代理的业务对象返回。
    java.lang.reflect.Proxy：这是 Java 动态代理机制的主类，
        它提供了一个静态方法来为一组接口的实现类动态地生成代理类及其对象。
        public static Object newProxyInstance(ClassLoader loader,
                Class[] interfaces, InvocationHandler h)
        参数一：类加载器:负责加载到时候做好的业务代理对象！
        参数二：被代理业务对象的全部实现的接口，以便代理对象可以知道要为哪些方法做代理。
 */
public class ProxyUtil {
    /**
     * 做一个被代理的业务对象返回!
     * @param obj
     * @return
     */
    public static <T> T getProxy(Object obj) {
        /**
         参数一：类加载器:负责加载到时候做好的业务代理对象！
         参数二：被代理业务对象的全部实现的接口，以便代理对象可以知道要为哪些方法做代理。
         参数三：代理真正的执行方法，也就是代理的处理逻辑！
         */
        return (T) Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
                        // proxy : 业务代理对象本身。用不到
                        // method: 代表当前正在被代理执行的方法！！
                        // params: 代表的是执行方法的参数，数组的形式!
                        long startTime = System.currentTimeMillis();

                        // 真正触发真实的方法执行
                        Object rs = method.invoke(obj,params);

                        long endTime = System.currentTimeMillis();
                        System.out.println(method.getName()+"方法耗时："+(endTime - startTime)/1000.0+"s");
                        return rs; // 返回方法执行的结果！！
                    }
                });
    }
}



