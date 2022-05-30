package com.itheima._12自定义泛型接口;
// 操作学生数据
public class StudentData implements Data<Student> {
    @Override
    public void add(Student stu) {
        System.out.println("添加学生！");
    }

    @Override
    public void delete(Student stu) {
        System.out.println("删除学生！");
    }

    @Override
    public void update(Student stu) {

    }

    @Override
    public Student query(int id) {
        return null;
    }
}
