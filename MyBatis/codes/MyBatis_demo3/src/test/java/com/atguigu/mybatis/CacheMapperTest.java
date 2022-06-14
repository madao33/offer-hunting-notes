package com.atguigu.mybatis;

import com.atguigu.mybatis.mapper.CacheMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import sun.util.resources.cldr.zh.CalendarData_zh_Hans_CN;

public class CacheMapperTest {

    @Test
    public void testCache() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
        Emp emp1 = mapper.getEmpByEId(1);
        System.out.println(emp1);
//        sqlSession.clearCache();
        Emp emp2 = mapper.getEmpByEId(2);
        System.out.println(emp2);
    }
}
