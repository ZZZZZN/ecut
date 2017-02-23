package com.krt.admin.system.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: 资源管理工具类
 * @author 殷帅
 * @date 2016年7月19日
 * @version 1.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Component
public class ResUtil {

	/**
	 * 获取模块树
	 * 
	 * @param parentList
	 * @param allList
	 * @return
	 */
	public List getTree(List parentList, List allList) {
		List list = new ArrayList();
		privilegeTree(parentList, allList, list);
		return list;
	}

	/**
	 * 递归遍历
	 * 
	 * @param parentList
	 * @param allList
	 * @param list
	 */
	public void privilegeTree(List parentList, List allList, List list) {
		for (int i = 0; i < parentList.size(); i++) {
			// 加父级
			Map parent = (Map) parentList.get(i);
			list.add(parent);
			// 获取子集
			List childrenList = getChildren(allList, parent);
			privilegeTree(childrenList, allList, list);
		}
	}

	/**
	 * 获取子集菜单
	 * 
	 * @param parentList
	 * @param parent
	 * @return
	 */
	public List getChildren(List parentList, Map parent) {
		List list = new ArrayList();
		for (int i = 0; i < parentList.size(); i++) {
			Map children = (Map) parentList.get(i);
			String c_pid = children.get("pid").toString();
			String p_id = parent.get("id").toString();
			if (c_pid.equals(p_id)) {
				list.add(children);
			}
		}
		return list;
	}
}
