package com.zxh.controller;

import com.zxh.entity.dto.MenuDto;
import com.zxh.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2017/6/2.
 */
@Controller
@RequestMapping(value = "/main")
public class MainController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/goAlgorithm")
    public String nextPage(HttpServletRequest request, HttpServletResponse response,Model model) {
        List<MenuDto> menuDtoList=menuService.getMenu();
        model.addAttribute("menuDtoList",menuDtoList);
        return "/algorithmPage";
    }

    @RequestMapping("/gobang")
    public String gobang(HttpServletRequest request, HttpServletResponse response,Model model) {

        List<MenuDto> menuDtoList=menuService.getMenu();
        model.addAttribute("menuDtoList",menuDtoList);
        return "/gobang";
    }

   @RequestMapping("/file")
    public String filePage(HttpServletRequest request, HttpServletResponse response,Model model) {

       List<MenuDto> menuDtoList=menuService.getMenu();
       model.addAttribute("menuDtoList",menuDtoList);
       return "/file";
    }


}
