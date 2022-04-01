package com.madao.fruit.dao;

import com.madao.fruit.pojo.Fruit;
import java.util.List;

public interface FruitDAO {
    // 获取所有库存列表信息
    List<Fruit> getFruitList();
}
