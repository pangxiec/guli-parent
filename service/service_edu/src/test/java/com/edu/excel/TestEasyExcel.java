package com.edu.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xmy
 * @data 2020/12/30 23:29
 */
public class TestEasyExcel {

    @Test
    public void writeExcel(){
        //实现excel 写的操作
        //设置写入的文件夹地址和excel的文件名称
        String fileName = "D:\\test\\easy.xlsx";
        //调用easyExcel里面写的操作
        //write() 里面有两个参数 第一个参数是文件路径 第二个参数是参数实体类
        EasyExcel.write(fileName,DemoData.class).sheet("学生列表").doWrite(getData());
    }

    public List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData demoData = new DemoData();
            demoData.setSno(i);
            demoData.setSname("lucy" + i);
            list.add(demoData);
        }
        return list;
    }
}
