package com.mari.reservemystay.services.basic;

import com.mari.reservemystay.dao.CommonDataDao;
import com.mari.reservemystay.dao.CommonTypeDao;
import com.mari.reservemystay.domain.CommonData;
import com.mari.reservemystay.domain.CommonType;
import com.mari.reservemystay.model.basic.CommonDataModel;
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

        Optional<CommonType> commonTypeOptional = commonTypeDao.findById(model.getCommonTypeId());

        if (commonTypeOptional.isPresent()) {
            commonData.setCommonType(commonTypeOptional.get());
        } else {
            throw new IllegalArgumentException("invalid Common Type" + model.getCommonTypeId());
        }

        commonDataDao.save(commonData);
        return commonData.getId();
    }

}
