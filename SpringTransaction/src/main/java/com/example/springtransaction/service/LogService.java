package com.example.springtransaction.service;

import com.example.springtransaction.mapper.LogMapper;
import com.example.springtransaction.model.LogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class LogService {

    @Autowired
    private LogMapper logMapper;

    @Transactional(propagation = Propagation.NESTED)
    public int add(LogInfo logInfo) {
        int result = logMapper.add(logInfo);;
        System.out.println("添加日志结果：" + result);
        try {
            int num = 10/0;
        } catch (NumberFormatException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return logMapper.add(logInfo);
    }
}
