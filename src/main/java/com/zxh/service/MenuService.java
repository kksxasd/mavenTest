package com.zxh.service;

import com.zxh.entity.dao.Menu;
import com.zxh.entity.dao.MenuExample;
import com.zxh.service.interfaces.MenuMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhengxh on 2017/6/30.
 */
@Component
@Transactional
public class MenuService  {
    @Autowired
    private MenuMapper menuMapper;

    public Menu selectById(Long id){
        return menuMapper.selectByPrimaryKey(id);
    }

}
