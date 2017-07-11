package com.zxh.controller;

import com.zxh.entity.dto.MenuDto;
import com.zxh.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zhengxh on 2017/7/3.
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("")
    public String index(Model model){
        //返回全部的一级菜单
        List<MenuDto> menuDtoList=menuService.getMenu();
        model.addAttribute("menuDtoList",menuDtoList);
        return "/index";
    }
}
