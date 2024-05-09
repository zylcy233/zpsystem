package com.zpsystem.mapper;

import java.util.List;
import java.util.Map;

public interface ParentMapper {
    Map getOne(Map map);
    boolean zhuce(Map map);
    boolean changepwd(Map map);
    List selectadm(Map map);
    List select(Map map);
    List get(Map map);
    List getone(Map map);
    boolean insert(Map map);
    boolean delete(Object object);
    boolean update(Map map);
}
