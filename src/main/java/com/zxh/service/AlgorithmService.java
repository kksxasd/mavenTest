package com.zxh.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/6/2.
 */
@Service
@Transactional
public class AlgorithmService {
    /**
     * 冒泡排序
     */
    public void bubbleSort(int[] arr){
        for(int i=0;i<arr.length-1;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]>arr[j]){
                    int temp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                }
            }
        }
    }

    /**
     * 选择排序
     */
    public void selectSort(int[] arr){
        int min=0;
        for(int i=0;i<arr.length-1;i++){
            min=i;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]<arr[min]){
                    min=j;
                }
            }
            if(min!=i){
                int temp=arr[min];
                arr[min]=arr[i];
                arr[i]=temp;
            }
        }
    }

    /**
     * 快速排序
     * @param start
     * @param end
     * @param arr
     */
    public void quickSort(int start,int end,int[] arr){
        int i=start;
        int j=end;
        int key=arr[start];
        while(i<j){
            while(i<j&&arr[j]>=key) {
                j--;
            }
            if(i<j){
                int temp=arr[j];
                arr[j]=arr[i];
                arr[i]=temp;
                i++;
            }
            while(i<j&&arr[i]<=key){
                i++;
            }
            if(i<j){
                int temp=arr[j];
                arr[j]=arr[i];
                arr[i]=temp;
                j--;
            }
        }
        if(i>start) quickSort(start,i-1,arr);
        if(j<end) quickSort(j+1,end,arr);
    }

    /**
     *堆排序，最大值放在末尾，data虽然是最大堆，在排序后就成了递增的
     *
     *@paramdata
     */
    public void heapSort(int[] data){
        buildMaxHeapify(data);
        //末尾与头交换，交换后调整最大堆
        for(int i=data.length-1;i>0;i--){
            int temp=data[0];
            data[0]=data[i];
            data[i]=temp;
            maxHeapify(data,i,0);
        }
    }

    private void buildMaxHeapify(int[] data){
        //没有子节点的才需要创建最大堆，从最后一个的父节点开始
        int startIndex=getParentIndex(data.length-1);
        //从尾端开始创建最大堆，每次都是正确的堆
        for(int i=startIndex;i>=0;i--){
            maxHeapify(data,data.length,i);
        }
    }

    /**
     *创建最大堆
     *
     *@paramdata
     *@paramheapSize需要创建最大堆的大小，一般在sort的时候用到，因为最多值放在末尾，末尾就不再归入最大堆了
     *@paramindex当前需要创建最大堆的位置
     */
    private void maxHeapify(int[] data,int heapSize,int index){
        //当前点与左右子节点比较
        int left=getChildLeftIndex(index);
        int right=getChildRightIndex(index);

        int largest=index;
        if(left<heapSize&&data[index]<data[left]){
            largest=left;
        }
        if(right<heapSize&&data[largest]<data[right]){
            largest=right;
        }
        //得到最大值后可能需要交换，如果交换了，其子节点可能就不是最大堆了，需要重新调整
        if(largest!=index){
            int temp=data[index];
            data[index]=data[largest];
            data[largest]=temp;
            maxHeapify(data,heapSize,largest);
        }
    }

    /**
     *父节点位置
     *
     *@paramcurrent
     *@return
     */
    private int getParentIndex(int current){
        return (current-1)>>1;
    }

    /**
     *左子节点position注意括号，加法优先级更高
     *
     *@paramcurrent
     *@return
     */
    private int getChildLeftIndex(int current){
        return(current<<1)+1;
    }

    /**
     *右子节点position
     *
     *@paramcurrent
     *@return
     */
    private int getChildRightIndex(int current){
        return(current<<1)+2;
    }

}
