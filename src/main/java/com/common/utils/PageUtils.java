package com.common.utils;

import com.common.model.DataInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 15:52 2018/7/30 0030
 * Modeified By:
 */
public class PageUtils {
    /**
     * 一页多少条数据
     */
    public static final int PAGE_SIZE = 10;

    public static void startPage(Integer pageNum) {
        if (Objects.isNull(pageNum)) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, PAGE_SIZE);
    }

    /**
     * 传入当前需要查询的第几页不传默认差寻第一页
     *
     * @param pageNum 当前页
     * @param size    一页多少数据
     */
    public static void startPage(Integer pageNum, Integer size) {
        if (Objects.isNull(pageNum)) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, size);
    }

    public static <T> DataInfo<T> getDataInfo(List<T> list){
        if (list==null){
            return getEmptyDataInfo();
        }
        DataInfo<T> dataInfo=new DataInfo<>();
        PageInfo pageInfo=new PageInfo(list);
        BeanUtils.copyProperties(pageInfo,dataInfo);
        return dataInfo;
    }

    public static DataInfo getEmptyDataInfo(){
        return new DataInfo();
    }
}
