package cn.jintian.dao.impl;

import cn.jintian.dao.BaseDao;
import cn.jintian.dao.ICollectHouseDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CollectHouseDaoImpl implements ICollectHouseDao{
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    int count = -1;
    public int setNewCollect(String houseId, int userId) {
        String sql = "insert into userlike(userlike_new_h_id,userlike_users_id) values(?,?)";
        try {
            preparedStatement = BaseDao.getConn().prepareStatement(sql);
            preparedStatement.setString(1,houseId);
            preparedStatement.setInt(2,userId);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int setOldCollect(String houseId, int userId) {
        String sql = "insert into userlike(userlike_old_h_id,userlike_users_id) values(?,?)";
        try {
            preparedStatement = BaseDao.getConn().prepareStatement(sql);
            preparedStatement.setString(1,houseId);
            preparedStatement.setInt(2,userId);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int setRendCollect(String houseId, int userId) {
        String sql = "insert into userlike(userlike_rent_id,userlike_users_id) values(?,?)";
        try {
            preparedStatement = BaseDao.getConn().prepareStatement(sql);
            preparedStatement.setString(1, houseId);
            preparedStatement.setInt(2, userId);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    public int getHouse(String houseName) {
        int houseId = Integer.parseInt(houseName);
        int houseType = -1;
        String sql = "select h_liketype from houseinfo where h_house_id = ?";
        try {
            preparedStatement.setInt(1,houseId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                houseType = resultSet.getInt("h_liketype");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return houseType;
    }
}
