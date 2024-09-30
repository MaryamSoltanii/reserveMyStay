package com.mari.reservemystay.serviceImpl.basic;

import com.mari.reservemystay.dao.CommonDataDao;
import com.mari.reservemystay.dao.CommonTypeDao;
import com.mari.reservemystay.domain.CommonData;
import com.mari.reservemystay.domain.CommonType;
import com.mari.reservemystay.exception.BusinessException;
import com.mari.reservemystay.model.basic.CommonDataModel;
import com.mari.reservemystay.services.basic.CommonDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CommonDataServiceImpl implements CommonDataService {

    @Autowired
    private CommonDataDao commonDataDao;

    @Autowired
    private CommonTypeDao commonTypeDao;

    @Override
    public Long save(CommonDataModel model) {
        CommonData commonData = new CommonData();
        commonData.setCode(model.getCode());
        commonData.setName(model.getName());
        Long commonTypeId = commonTypeDao.findById(model.getCommonTypeId()).orElseThrow(() -> new BusinessException("invalid Common Type" + model.getCommonTypeId())).getId();
        commonData.setCommonType(commonTypeId);
        commonDataDao.save(commonData);
        return commonData.getId();
    }

}
