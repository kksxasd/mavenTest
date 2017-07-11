package com.zxh.service;

import com.google.common.collect.Lists;
import com.zxh.entity.Common;
import com.zxh.entity.dao.Menu;
import com.zxh.entity.dao.MenuExample;
import com.zxh.entity.dto.MenuDto;
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

    public MenuDto findParentTree(Long id){
        return menuMapper.findParentTree(id);
    }

    /**
     * 返回全部的一级菜单
     * @return
     */
    public List<MenuDto> getMenu(){
        List<MenuDto> menuDtos= Lists.newArrayList();

        MenuExample menuExample=new MenuExample();
        MenuExample.Criteria criteria=menuExample.createCriteria();
        criteria.andParentIdEqualTo(Common.MAIN_PARENT_ID);
        List<Menu> menus=menuMapper.selectByExample(menuExample);

        for(Menu menu:menus){
            MenuDto menuDto=new MenuDto();
            menuDto.setId(menu.getId());
            menuDto.setName(menu.getName());
            menuDto.setUrl(menu.getLink());
            menuDtos.add(menuDto);
        }
        return menuDtos;
    }

}
