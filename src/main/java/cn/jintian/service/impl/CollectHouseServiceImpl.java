package cn.jintian.service.impl;

import cn.jintian.dao.impl.CollectHouseDaoImpl;
import cn.jintian.service.ICollectHouseService;

public class CollectHouseServiceImpl implements ICollectHouseService {
    public String setUserLike(int userName, String houseName) {
        CollectHouseDaoImpl collectHouseDao = new CollectHouseDaoImpl();
        int count = -1;
        int housetype = collectHouseDao.getHouse(houseName);
        if (housetype == 1) {
            count = collectHouseDao.setOldCollect(houseName, userName);
        }else if (housetype == 2){
            count = collectHouseDao.setNewCollect(houseName,userName);
        }else {
            count = collectHouseDao.setRendCollect(houseName,userName);
        }
        if (count > 0){
         return "收藏成功";
        }else{
            return "收藏失败";
        }
    }
}
