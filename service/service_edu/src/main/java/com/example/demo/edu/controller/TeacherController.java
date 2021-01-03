package com.example.demo.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.R;
import com.example.demo.edu.entity.Teacher;
import com.example.demo.edu.entity.vo.TeacherQuery;
import com.example.demo.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author xmy
 * @since 2020-12-29
 */
@Api(description = "教师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @ApiOperation("查询全部")
    @GetMapping("/findAll")
    public R findAll(){
        List<Teacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    @ApiOperation("删除教师")
    @DeleteMapping("/delete/{id}")
    public R remove(@PathVariable("id") Long id){
        boolean flag = teacherService.removeById(id);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 不带条件的分页查询
     * @param current 当前页
     * @param limit 每页记录数
     * @return
     */
    @ApiOperation("不带条件的分页查询")
    @GetMapping("/pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,@PathVariable long limit){
        //创建Page对象
        Page<Teacher> teacherPage = new Page<>(current,limit);

        //调用方法实现分页
        teacherService.page(teacherPage,null);
        //总记录数
        long total = teacherPage.getTotal();
        //数据List集合
        List<Teacher> records = teacherPage.getRecords();

        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation("带条件的分页查询")
    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit,
                                  @RequestBody TeacherQuery teacherQuery){
        Page<Teacher> teacherPage = new Page<>(current,limit);
        //构建条件
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        //多条件组合查询
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        //判断是否为空,不为空拼接
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }

        //实现条件分页查询
        teacherService.page(teacherPage,wrapper);

        long total = teacherPage.getTotal();
        List<Teacher> records = teacherPage.getRecords();

        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation("添加教师")
    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody Teacher teacher){
        boolean save = teacherService.save(teacher);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation("根据id查询")
    @GetMapping("/getById/{id}")
    public R getById(@PathVariable("id") String id){
        System.out.println(id);
        Teacher teacher = teacherService.getById(id);
        System.out.println(teacher);
        return R.ok().data("teacher",teacher);
    }

    @ApiOperation("修改教师内容")
    @PostMapping("/updateTeacher")
    public R updateTeacher(@RequestBody Teacher teacher){
        boolean b = teacherService.updateById(teacher);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }




}

