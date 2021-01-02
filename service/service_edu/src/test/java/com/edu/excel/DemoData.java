package com.edu.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.junit.experimental.categories.Categories;

/**
 * @author xmy
 * @data 2020/12/30 23:26
 */
@Data
public class DemoData {

    //设置excle表头的名称
    @ExcelProperty("学生编号")
    private Integer sno;

    @ExcelProperty("学生姓名")
    private String sname;
}
