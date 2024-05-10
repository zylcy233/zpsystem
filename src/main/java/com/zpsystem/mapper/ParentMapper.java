package com.zpsystem.mapper;

import java.util.List;
import java.util.Map;

public interface ParentMapper {
    Map getOne(Map map);
    Map check(Map map);
    List getNew(Map map);
    boolean zhuce(Map map);
    boolean changepwd(Map map);
    boolean shoucang(Map map);
    boolean delshoucang(Map map);
    List selectadm(Map map);
    List select(Map map);
    List get(Map map);
    List getone(Map map);
    boolean insert(Map map);
    boolean delete(Object object);
    boolean update(Map map);
}
