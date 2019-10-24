package cn.jintian.dao;

public interface ICollectHouseDao {
    int setNewCollect(String houseId, int userId);
    int setOldCollect(String houseId, int userId);
    int setRendCollect(String houseId, int userId);
    int getHouse(String houseName);
}
