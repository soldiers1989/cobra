package com.ihome.service;


import com.ihome.pojo.SysConfig;

import java.util.List;

public interface SysConfigService {

    List<SysConfig> select(SysConfig sysConfig);

     SysConfig selectByPrimaryKey(SysConfig sysConfig);

     int updateByPrimaryKeySelective(SysConfig sysConfig);

     int updateByPrime(SysConfig sysConfig);


}