package cn.jintian.service.impl;

import cn.jintian.dao.impl.IndexOldHouseDaoImpl;
import cn.jintian.pojo.Old_H;
import cn.jintian.service.IIndexOldHourseService;
import cn.jintian.util.OldHousePage;

import java.util.ArrayList;
import java.util.List;

public class IndexOldHouseServiceImpl implements IIndexOldHourseService {

	public OldHousePage getOldHourse(int index, int itemsPerPage) {
		IndexOldHouseDaoImpl iod = new IndexOldHouseDaoImpl();
		Thread thread = new Thread(iod);
		thread.start();
		OldHousePage ohp = new OldHousePage();
		ohp.setHourseTotal(iod.getOldHourseCount());
		List<Old_H> oldHourse = iod.getOldHourse(1,3);
//		for (Old_H old_h : oldHourse) {
//			System.out.println(old_h.getOld_h_decoration() + old_h.getOld_h_show() + "----------------");
//		}

		ohp.setItemsPerPage(itemsPerPage);
		if (index > ohp.getTotalPages()) {
			ohp.setIndex(ohp.getTotalPages());
		}else if(index < 1){
			ohp.setIndex(1);
		}else{
			ohp.setIndex(index);
		}
		ohp.setHourse(iod.getOldHourse(ohp.getIndex(), ohp.getItemsPerPage()));
		return ohp;
	}

	public List getImgInfo(int index, int itemsPerPage) {
		IndexOldHouseDaoImpl iod = new IndexOldHouseDaoImpl();
		Thread thread = new Thread(iod);
		thread.start();
		OldHousePage ohp = new OldHousePage();
		ohp.setHourseTotal(iod.getOldHourseCount());
		ohp.setItemsPerPage(itemsPerPage);
		if (index > ohp.getTotalPages()) {
			ohp.setIndex(ohp.getTotalPages());
		}else if(index < 1){
			ohp.setIndex(1);
		}else{
			ohp.setIndex(index);
		}
		List<Old_H> hourseImg = iod.getOldHourseImg(ohp.getIndex(), ohp.getItemsPerPage());
		List<String> list = new ArrayList<String>();
		for (Old_H old_h : hourseImg) {
			list.add(old_h.getOld_h_img());
		}
		return list;
	}

}
