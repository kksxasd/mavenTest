package com.zxh.controller;

import com.zxh.entity.RBTree;
import com.zxh.service.RedTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhengxh on 2017/7/5.
 * 红黑树的用例
 */
@Controller
@RequestMapping("/redBlackTree")
public class RedTreeController {
    @Autowired
    private RedTreeService redTreeService;

    private static final int a[] = {10, 40, 30, 60, 90, 70, 20, 50, 80};

    public static void main(String[] args) {
//        int i;
//        int ilen = a.length;
//        RBTree<Integer> tree = new RBTree<Integer>();
//        for (i = 0; i < ilen; i++) {
//            //插入
//            redTreeService.insert(a);
//            //打印
//            redTreeService.print(tree,i,a);
//        }
    }

    @RequestMapping("/insert")
    public void insert(){
        RBTree<Integer> tree = new RBTree<Integer>();
        for (int i = 0; i < a.length; i++) {
            //插入
            redTreeService.insert(a[i],tree);
            //打印
            redTreeService.print(tree,i,a);
        }
    }
}
