package com.itheima._12自定义泛型接口;

public class TeacherData implements Data<Teacher> {
    @Override
    public void add(Teacher stu) {
        System.out.println("添加老师！");
    }

    @Override
    public void delete(Teacher stu) {
        System.out.println("删除老师！");
    }

    @Override
    public void update(Teacher stu) {

    }

    @Override
    public Teacher query(int id) {
        return null;
    }
}
