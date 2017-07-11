$(function () {
    $('#quickSort').click(function(){
        var url="/algorithm/quickSort";
        var arr=$('#arr').val();
        $.post(url,{arr:arr},function (data){
            $('#quickSortResult').val(data.msg);
        })
    });

    $('#bubbleSort').click(function(){
        var url="/algorithm/bubbleSort";
        var arr=$('#arr').val();
        $.post(url,{arr:arr},function (data){
            $('#bubbleSortResult').val(data.msg);
        })
    });

    $('#selectSort').click(function(){
        var url="/algorithm/selectSort";
        var arr=$('#arr').val();
        $.post(url,{arr:arr},function (data){
            $('#selectSortResult').val(data.msg);
        })
    });

    $('#heapSort').click(function(){
        var url="/algorithm/heapSort";
        var arr=$('#arr').val();
        $.post(url,{arr:arr},function (data){
            $('#heapSortResult').val(data.msg);
        })
    });

    $(".menu").click(function(){
        alert(aaa);
    })
    
    
});
