package com.zxh.service;

import com.zxh.entity.RBTree;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by zhengxh on 2017/7/5.
 * 红黑树服务类
 */
@Component
@Transactional
public class RedTreeService {
    public void insert(int a, RBTree<Integer> tree) {

        tree.insert(a);
    }

    /**
     * 打印红黑树的信息
     *
     * @param tree
     */
    public void print(RBTree<Integer> tree, int i, int[] a) {
        System.out.printf("== 添加节点: %d\n", a[i]);
        System.out.printf("== 树的详细信息: \n");
        tree.print();
        System.out.printf("\n");
    }
}
