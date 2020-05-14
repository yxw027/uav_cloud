package com.ccssoft.cloudmessagemachine.service.impl;

import com.ccssoft.cloudmessagemachine.mina.comon.ComonUtils;
import com.ccssoft.cloudmessagemachine.service.BoxService;
import org.springframework.stereotype.Service;

/**
 * @author moriarty
 * @date 2020/5/14 14:57
 */
@Service
public class BoxServiceImpl implements BoxService {
    @Override
    public String restartBox(Long id) {
        return ComonUtils.restartBox(id);
    }
}
