package com.atguigu.mybatis;

import com.atguigu.mybatis.mapper.DynamicSQLMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DynamicSQLMapperTest {

    @Test
    public void testGetEmpByCondition() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> emps = mapper.getEmpByCondition(new Emp(null, "", null, "", ""));
        System.out.println(emps);
    }

    @Test
    public void testGetEmpByChoose() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> emp = mapper.getEmpByChoose(new Emp(null, "", null, "", ""));
        System.out.println(emp);
    }

    @Test
    public void testdeleteMoreByArray() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        int emp = mapper.deleteMoreByArray(new Integer[]{5, 6, 7});
        System.out.println(emp);
    }

    @Test
    public void testInsertMoreByList() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        Emp emp1 = new Emp(null,"a",1,"男","123@321.com");
        Emp emp2 = new Emp(null,"b",1,"男","123@321.com");
        Emp emp3 = new Emp(null,"c",1,"男","123@321.com");
        List<Emp> emps = Arrays.asList(emp1, emp2, emp3);
        int result = mapper.insertMoreByList(emps);
        System.out.println(result);
    }
}
