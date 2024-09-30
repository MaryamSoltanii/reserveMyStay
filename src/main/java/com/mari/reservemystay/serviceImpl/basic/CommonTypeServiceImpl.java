package com.mari.reservemystay.serviceImpl.basic;

import com.mari.reservemystay.dao.CommonTypeDao;
import com.mari.reservemystay.domain.CommonType;
import com.mari.reservemystay.model.basic.CommonTypeModel;
import com.mari.reservemystay.services.basic.CommonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommonTypeServiceImpl implements CommonTypeService {

    @Autowired
    private CommonTypeDao commonTypeDao;

    @Override
    public Long save(CommonTypeModel model) {
        CommonType commonType = new CommonType();
        commonType.setName(model.getName());
        commonType.setCode(model.getCode());

        commonTypeDao.save(commonType);
        return commonType.getId();

    }
}
