package com.itheima._01单元测试;

import org.junit.*;

public class UserServiceTest {
    // @Before：用来修饰实例方法，该方法会在每一个测试方法执行之前执行一次。
    @Before
    public void before(){
        System.out.println("===before===");
    }
    // @After：用来修饰实例方法，该方法会在每一个测试方法执行之后执行一次。
    @After
    public void after(){
        System.out.println("===after===");
    }

    // @BeforeClass：用来静态修饰方法，该方法会在所有测试方法之前只执行一次。
    @BeforeClass
    public static void beforeClass(){
        System.out.println("===beforeClass===");
    }

    // @AfterClass：用来静态修饰方法，该方法会在所有测试方法之后只执行一次。
    @AfterClass
    public static void afterClass(){
        System.out.println("===afterClass===");
    }


    /**
     * 测试方法的要求：
     *  1.必须public修饰
     *  2.没有返回值没有参数
     *  3. 必须使注解@Test修饰
     */
    @Test
    public void testLogin(){
        UserService userService = new UserService();
        String rs = userService.login("admin","123456");
        // 断言预期结果的正确性。
        /**
         * 参数一：测试失败的提示信息。
         * 参数二：期望值。
         * 参数三：实际值
         */
        // public static void assertEquals(String message, Object expected, Object actual)
        Assert.assertEquals("登录业务功能方法有错误，请检查！","success",rs);
    }

    @Test
    public void testChu(){
        UserService userService = new UserService();
        userService.chu(10 , 0);
    }
}



