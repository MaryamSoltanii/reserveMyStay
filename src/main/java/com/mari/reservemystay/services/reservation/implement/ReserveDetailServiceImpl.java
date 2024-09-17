package com.mari.reservemystay.services.reservation.implement;

import com.mari.reservemystay.dao.PersonDao;
import com.mari.reservemystay.dao.ReserveDao;
import com.mari.reservemystay.dao.ReserveDetailDao;
import com.mari.reservemystay.domain.ReserveDetail;
import com.mari.reservemystay.exception.BusinessException;
import com.mari.reservemystay.model.reservation.implement.ReserveDetailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mari.reservemystay.exception.BusinessException.RED_PRS_NOT_FOUND;
import static com.mari.reservemystay.exception.BusinessException.RED_RES_NOT_FOUND;

@Service
@Transactional
public class ReserveDetailServiceImpl implements ReserveDetailService{
    @Autowired
    private ReserveDetailDao reserveDetailDao;

    @Autowired
    private ReserveDao reserveDao;

    @Autowired
    private PersonDao personDao;

    public Long save(ReserveDetailModel model){
        ReserveDetail reserveDetail = new ReserveDetail();
        var reserveId = reserveDao.findById(model.getReserveId()).orElseThrow(()-> new BusinessException(RED_RES_NOT_FOUND));
        var personId = personDao.findById(model.getPersonId()).orElseThrow(()-> new BusinessException(RED_PRS_NOT_FOUND));
        /*get list from ui and check prs
        if person is valid I save reserveDetailDao, if not save person
         */
        reserveDetailDao.save(reserveDetail);
        return reserveDetail.getId();

    }
}
