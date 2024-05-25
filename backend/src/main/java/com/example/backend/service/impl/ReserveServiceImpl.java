package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.mapper.ExamBmMapper;
import com.example.backend.pojo.ExamBm;
import com.example.backend.pojo.Reserve;
import com.example.backend.service.ReserveService;
import com.example.backend.mapper.ReserveMapper;
import com.example.backend.service.utils.CarTypeNum;
import com.example.backend.service.utils.HourNum;
import com.example.backend.service.utils.SubNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
* @author 86199
* @description 针对表【reserve】的数据库操作Service实现
* @createDate 2024-05-14 17:44:43
*/
@Service
public class ReserveServiceImpl extends ServiceImpl<ReserveMapper, Reserve>
    implements ReserveService{
    @Autowired
    ReserveMapper reserveMapper;
    @Autowired
    ExamBmMapper examBmMapper;
    @Override
    public List<Reserve> findReserveByUser(Map<String, String> data) {
        reserveMapper.deleteOldReservations();
        return reserveMapper.findReserveByUser(data);
    }

    @Override
    public Map<String, String> insertReserve(Map<String,String> data) {
        String uId = data.get("uId");
        String subId = data.get("subId");
        String staTime = data.get("staTime");
        String enTime = data.get("enTime");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date1 = null;
        Date date2 = null;

        try {
            date1 = dateFormat.parse(staTime);
            date2 = dateFormat.parse(enTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Reserve reserve = new Reserve(null,Integer.parseInt(subId),Integer.parseInt(uId),date1,date2,null);
        reserveMapper.insert(reserve);

        Map<String,String> map = new HashMap<>();
        map.put("error_info","success");
        return map;
    }

    @Override
    public Map<String, String> deleteReserve(Map<String,String> data) {
        Map<String,String> map = new HashMap<>();
        int t = reserveMapper.deleteById(Integer.parseInt(data.get("resId")));
        if(t == 1)
        {
            map.put("error_info","success");
            return map;
        }
        map.put("error_info","error");
        return map;
    }

    @Override
    public List<Reserve> getPro() {
        List<ExamBm> examBms = examBmMapper.findPro();
        List<Reserve> reserveList = reserveMapper.getTotalHours();
        List<Reserve> reserves = new ArrayList<>();
        for(ExamBm i : examBms)
        {
            if(i.getSubId() == null || (i.getSubId() == 1 && (i.getBmScore() == null || i.getBmScore() < 90)))
            {
                Reserve reserve = new Reserve();
                reserve.setSubId(1);
                reserve.setUId(i.getUId());
                reserves.add(reserve);
            }else if((i.getSubId() == 1 && i.getBmScore() >= 90) || (i.getSubId() == 2 &&
                    (i.getBmScore() == null ||i.getBmScore() < 80 ))){
                Reserve reserve = new Reserve();
                reserve.setSubId(2);
                reserve.setUId(i.getUId());
                reserve.setTotalHours(getHour(reserveList,2,i.getUId()));
                reserves.add(reserve);
            } else if ((i.getSubId() == 2 && i.getBmScore() >= 80) || (i.getSubId() == 3 &&
                    (i.getBmScore() == null || i.getBmScore() < 80))) {
                Reserve reserve = new Reserve();
                reserve.setSubId(3);
                reserve.setUId(i.getUId());
                reserve.setTotalHours(getHour(reserveList,3,i.getUId()));
                reserves.add(reserve);
            }else {
                Reserve reserve = new Reserve();
                reserve.setSubId(4);
                reserve.setUId(i.getUId());
                reserves.add(reserve);
            }
        }
        return reserves;
    }

    public Double getHour(List<Reserve> reserveList, Integer subId,Integer uId){
        for(Reserve reserve : reserveList)
        {
            if(reserve.getUId().equals(uId) && reserve.getSubId().equals(subId))
            {
                return reserve.getTotalHours();
            }
        }
        return null;
    }

    @Override
    public List<HourNum> getEveryHourNum() {
        return reserveMapper.getEveryHourNum();
    }

    @Override
    public List<SubNum> getSubIdNum() {
        return reserveMapper.getSubIdNum();
    }

    @Override
    public List<CarTypeNum> getCarTypeNum() {
        return reserveMapper.getCarTypeNum();
    }

    @Override
    public List<HourNum> getPreNum() {
        return reserveMapper.getPreNum();
    }
}




