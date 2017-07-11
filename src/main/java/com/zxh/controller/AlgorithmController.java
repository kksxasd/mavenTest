package com.zxh.controller;

import com.zxh.entity.dao.ResponseInfoBody;
import com.zxh.service.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 * 算法控制类
 */
@Controller
@RequestMapping("/algorithm")
public class AlgorithmController
{
    @Autowired
    private AlgorithmService algorithmService;

    /**
     * 快速排序
     * @param arr
     * @return
     */
    @RequestMapping("/quickSort")
    @ResponseBody
    public ResponseInfoBody quickSort(@RequestParam("arr") String arr){
        String returnArr="";
        int array[]=stringToArray(arr);
        Long startTime=System.currentTimeMillis();
        algorithmService.quickSort(0,array.length-1,array);
        Long consumeTime=System.currentTimeMillis()-startTime;
        for(int i=0;i<array.length;i++){
            returnArr+=array[i]+",";
        }
        returnArr=returnArr.substring(0,returnArr.length()-1);
        return new ResponseInfoBody(returnArr,consumeTime);
    }

    /**
     * 冒泡排序
     * @param arr
     * @return
     */
    @RequestMapping("/bubbleSort")
    @ResponseBody
    public ResponseInfoBody bubbleSort(@RequestParam("arr") String arr){
        String returnArr="";
        int array[]=stringToArray(arr);

        Long startTime=System.currentTimeMillis();
        algorithmService.bubbleSort(array);
        Long consumeTime=System.currentTimeMillis();

        for(int i=0;i<array.length;i++){
            returnArr+=array[i]+",";
        }
        returnArr=returnArr.substring(0,returnArr.length()-1);
        return new ResponseInfoBody(returnArr,consumeTime-startTime);
    }

    /**
     * 选择排序
     * @param arr
     * @return
     */
    @RequestMapping("/selectSort")
    @ResponseBody
    public ResponseInfoBody selectSort(@RequestParam("arr") String arr){
        String returnArr="";
        int array[]=stringToArray(arr);

        Long startTime=System.currentTimeMillis();
        algorithmService.selectSort(array);
        Long consumeTime=System.currentTimeMillis()-startTime;

        for(int i=0;i<array.length;i++){
            returnArr+=array[i]+",";
        }
        returnArr=returnArr.substring(0,returnArr.length()-1);
        return new ResponseInfoBody(returnArr,consumeTime);
    }

    /**
     * 堆排序
     * @param arr
     * @return
     */
    @RequestMapping("/heapSort")
    @ResponseBody
    public ResponseInfoBody heapSort(@RequestParam("arr") String arr){
        String returnArr="";
        int array[]=stringToArray(arr);

        Long startTime=System.currentTimeMillis();
        algorithmService.heapSort(array);
        Long consumeTime=System.currentTimeMillis()-startTime;

        for(int i=0;i<array.length;i++){
            returnArr+=array[i]+",";
        }
        returnArr=returnArr.substring(0,returnArr.length()-1);
        return new ResponseInfoBody(returnArr,consumeTime);
    }



    /**
     * 逗号分隔的String转int数组
     * @param arr
     * @return
     */
    public int[] stringToArray(String arr){
        String[] arrs=arr.split(",");
        int array[]=new int[arrs.length];
        for(int i=0;i<arrs.length;i++){
            array[i]=Integer.parseInt(arrs[i]);
        }
        return array;
    }
}
