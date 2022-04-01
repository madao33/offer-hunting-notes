package com.madao.fruit.dao.impl;

import com.madao.fruit.dao.FruitDAO;
import com.madao.fruit.pojo.Fruit;
import com.madao.myssm.basedao.BaseDao;

import java.util.List;

public class FruitDAOImpl extends BaseDao<Fruit> implements FruitDAO {
    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from t_fruit");
    }
}
