package com.zxh.controller;

import com.zxh.entity.dao.Menu;
import com.zxh.entity.dto.MenuDto;
import com.zxh.service.MenuService;
import com.zxh.service.interfaces.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

/**
 * Created by zhengxh on 2017/6/30.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/index")
    public String index(){
        Menu menu=menuService.selectById(2L);
        MenuDto menuDto=menuService.findParentTree(2L);
        return "/index";
    }
}
