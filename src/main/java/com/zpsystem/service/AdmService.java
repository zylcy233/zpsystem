package com.zpsystem.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface AdmService {
    boolean login(Map map);
    boolean changepwd(Map map);
}
