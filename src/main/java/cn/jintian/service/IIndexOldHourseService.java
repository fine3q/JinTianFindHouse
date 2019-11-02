package cn.jintian.service;
import cn.jintian.util.OldHousePage;

import java.util.List;

public interface IIndexOldHourseService {
	OldHousePage getOldHourse(int index, int itemsPerPage);
	public List getImgInfo(int index, int itemsPerPage);
}
