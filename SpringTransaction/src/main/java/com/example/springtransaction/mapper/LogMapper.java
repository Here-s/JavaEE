package com.example.springtransaction.mapper;

import com.example.springtransaction.model.LogInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {
    int add(LogInfo logInfo);
}
