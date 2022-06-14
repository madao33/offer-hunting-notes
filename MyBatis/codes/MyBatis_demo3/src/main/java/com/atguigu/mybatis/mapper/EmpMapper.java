package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper {

    /**
     * 查询所有的员工信息
     */
    List<Emp> getAllEmp();

    /**
     * 查询员工以及员工说对应的部门信息
     * @param eid
     * @return
     */

    Emp getEmpAndDept(@Param("eid") Integer eid);

    /**
     * 通过分步查询查询员工以及员工所对应的部门信息
     */
    Emp getEmpAndDeptByStepOne(@Param("eid") Integer eid);

    /**
     * 通过分步查询，查询部门及对应的所有员工信息
     * 分步查询第二步：根据部门id查询部门中的所有员工
     * @param did
     * @return java.util.List<com.atguigu.mybatis.pojo.Emp>
     * @date 2022/2/27 22:10
     */
    List<Emp> getDeptAndEmpByStepTwo(@Param("did") Integer did);

}
