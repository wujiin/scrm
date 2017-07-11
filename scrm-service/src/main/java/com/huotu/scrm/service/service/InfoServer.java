package com.huotu.scrm.service.service;

import com.huotu.scrm.service.entity.Information.Info;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by luohaibo on 2017/7/5.
 */
public interface InfoServer {

    /**
     * 根据Disable字段查询行数
     * @return
     */
    long infoListsCount(boolean disable);


    /**
     *  根据某一个模糊条件搜索标题查找相应的资讯列表
     */
    List<Info> findListsByWord(String title);


    /**
     * 根据分页条件查找到某一页的资讯列表
     */
    Page<Info> infoSList(boolean disable, Pageable pageable);


    /**
     * jpa 自带可以不用自己写  saveAndFlush
     * 创建资讯保存到数据库
     */
    void infoSave(Info info);


}