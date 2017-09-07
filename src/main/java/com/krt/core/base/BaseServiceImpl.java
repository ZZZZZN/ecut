package com.krt.core.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.krt.core.bean.DataTable;
import com.krt.core.util.ShiroUtil;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 殷帅
 * @version 1.0
 * @Description: 公共抽取服务层的实现
 * @date 2016年7月16日
 */
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    protected final Logger logger = Logger.getLogger(this.getClass());

    public abstract BaseMapper<T> getMapper();

    /**
     * 插入
     *
     * @param entity
     * @return
     */
    public void insert(T entity) throws Exception {
        Map currentUser = ShiroUtil.getCurrentUser();
        if (currentUser != null) {
            entity.setInserter(Integer.valueOf(currentUser.get("id") + ""));
        }
        entity.setInsertTime(new Date());
        getMapper().insert(entity);
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    public void delete(Integer id) throws Exception {
        getMapper().delete(id);
    }

    /**
     * 更新
     *
     * @param entity
     * @throws Exception
     */
    public void update(T entity) throws Exception {
        Map currentUser = ShiroUtil.getCurrentUser();
        if (currentUser != null) {
            entity.setUpdater(Integer.valueOf(currentUser.get("id") + ""));
        }
        entity.setUpdateTime(new Date());
        getMapper().update(entity);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    public Map selectById(Integer id) {
        return getMapper().selectById(id);
    }

    /**
     * 查询全部
     *
     * @return
     */
    public List selectAll() {
        return getMapper().selectList();
    }

    /**
     * @param start  起始值
     * @param length 每页显示数据大小
     * @param draw   客户端访问次数
     * @return
     */
    public DataTable selectList(Integer start, Integer length, Integer draw) {
        DataTable dataTable = new DataTable();
        // 下面两句要连着写在一起，就可以实现分页
        dataTable.setLength(length);
        dataTable.setPageNum(start);
        PageHelper.startPage(dataTable.getPageNum(), dataTable.getLength());
        List<Map> list = getMapper().selectList();
        // 下面这句是为了获取分页信息，比如记录总数等等
        PageInfo<Map> pageInfo = new PageInfo<Map>(list);
        dataTable.setData(list);
        dataTable.setRecordsTotal(Long.valueOf(dataTable.getLength()));
        dataTable.setRecordsFiltered(pageInfo.getTotal());
        return dataTable;
    }

    /**
     * 分页(带参数)
     *
     * @param start  起始值
     * @param length 每页显示数据大小
     * @param draw   客户端访问次数
     * @param para   参数
     * @return
     */
    public DataTable selectListPara(Integer start, Integer length, Integer draw, Map para) {
        DataTable dataTable = new DataTable();
        // 下面两句要连着写在一起，就可以实现分页
        dataTable.setLength(length);
        dataTable.setPageNum(start);
        PageHelper.startPage(dataTable.getPageNum(), dataTable.getLength());
        List<Map> list = getMapper().selectListPara(para);
        // 下面这句是为了获取分页信息，比如记录总数等等
        PageInfo<Map> pageInfo = new PageInfo<Map>(list);
        dataTable.setData(list);
        dataTable.setRecordsTotal(Long.valueOf(dataTable.getLength()));
        dataTable.setRecordsFiltered(pageInfo.getTotal());
        return dataTable;
    }


}