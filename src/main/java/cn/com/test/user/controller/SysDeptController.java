package cn.com.test.user.controller;

import cn.com.test.user.aop.Log;
import cn.com.test.user.entity.SysDept;
import cn.com.test.user.entity.SysDeptExample;
import cn.com.test.user.entity.SysUserDept;
import cn.com.test.user.entity.SysUserDeptExample;
import cn.com.test.user.service.SysDeptService;
import cn.com.test.user.service.SysUserDeptService;
import cn.com.test.user.service.SysUserService;
import cn.com.test.user.utils.BaseResponse;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author mazhiqiang
 * @Description //TODO 部门管理controller
 * @Date 9:46 2019\3\14 0014
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/dept")
public class SysDeptController extends BaseController {

    @Resource
    SysDeptService sysDeptService;

    @Resource
    SysUserDeptService sysUserDeptService;

    @Resource
    SysUserService sysUserService;

    /*log*/
    private static final Logger log = Logger.getLogger(SysDeptController.class);

    /**
     * 修改部门信息
     *
     * @param dept_id
     * @param sysDept
     * @return
     */
    @PutMapping(value = "/update/{dept_id}", produces = "application/json;charset=utf-8")
    @Log(function_id = "5", operate_type = "3", operate_content = "修改部门信息")
    public BaseResponse update(@PathVariable("dept_id") Integer dept_id, @RequestBody SysDept sysDept) {

        try {
            sysDept.setDept_id(dept_id);
            sysDept.setUpdate_time(new Date());
            if (sysDept.getDept_name() != null && !sysDept.getDept_name().equals("")) {
                if (sysDept.getDept_code() != null && !sysDept.getDept_code().equals("")) {
                    int count = sysDeptService.updateById(sysDept);
                    log.info(count + "根据id修改" + "----------------------------------");
                    if (count > 0) {
                        return ajaxSucc("", UPDATE_SUCCESS, SUCCESS_CODE);
                    } else {
                        return ajaxFail(UPDATE_FAIL, FAIL_CODE);
                    }
                }
                return ajaxFail("部门编号！不能为空！", FAIL_CODE);
            }
            return ajaxFail("部门名称！不能为空！", FAIL_CODE);


        } catch (Exception e) {
            e.printStackTrace();
            return ajaxFail(UPDATE_FAIL, FAIL_CODE);
        }
    }

    /**
     * 根据部门id 删除当前部门及以下的父类部门，及每个部门的人
     *
     * @param deptIds
     * @return
     */
    @DeleteMapping(value = "/delete/{id}", produces = "application/json;charset=utf-8")
    @Log(function_id = "5", operate_type = "2", operate_content = "删除部门信息")
    public BaseResponse delete(@PathVariable("id") Integer[] deptIds) {
        try {
            List<Integer> ids = Arrays.asList(deptIds);

            //判定当前部门是否含有子部门，有子部门不能删除，需先删除子部门
            SysDeptExample sysDeptExample = new SysDeptExample();
            sysDeptExample.createCriteria().andParent_idIn(ids);
            List<SysDept> sysDepts = sysDeptService.selectAll(sysDeptExample);
            log.info("判定当前部门是否含有子部门，有子部门不能删除，需先删除子部门" + "-------------------------");
            log.info(sysDepts);
            if (sysDepts.size() > 0 && sysDepts != null) {
                return ajaxFail("当前删除的部门含有子部门,需先删除子部门,才能删除该部门！", FAIL_CODE);
            }

            //删除用户与部门中间表中的数据
            SysUserDeptExample sysUserDeptExample = new SysUserDeptExample();
            SysUserDeptExample.Criteria sysUserDeptCriteria = sysUserDeptExample.createCriteria();
            sysUserDeptCriteria.andDept_idIn(ids);

            List<SysUserDept> sysUserDepts = sysUserDeptService.selectAll(sysUserDeptExample);
            log.info("删除用户与部门中间表中的数据" + "---------------------------------");
            log.debug(sysUserDepts);
            if (sysUserDepts.size() > 0 && sysUserDepts != null) {
                return ajaxFail("当前部门下有成员！不允许删除！", FAIL_CODE);
            }

            sysUserDeptService.delete(sysUserDeptExample);
            //删除部门
            SysDeptExample deptExample = new SysDeptExample();
            SysDeptExample.Criteria deptCriteria = deptExample.createCriteria();
            deptCriteria.andDept_idIn(ids);
            sysDeptService.delete(deptExample);
            log.info("删除部门" + "---------------------------------------");
            return ajaxFail(DELETE_SUCCESS, SUCCESS_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            return ajaxFail(DELETE_FAIL, FAIL_CODE);
        }
    }

    /**
     * 新增加部门
     *
     * @param sysDept
     * @return
     */
    @PostMapping(value = "/insert", produces = "application/json;charset=utf-8")
    @Log(function_id = "5", operate_type = "1", operate_content = "添加部门信息")
    public BaseResponse insert(@RequestBody SysDept sysDept) {
        try {
            //根据部门编号查询部门编号是否存在
            SysDeptExample example = new SysDeptExample();
            example.createCriteria().andDept_codeEqualTo(sysDept.getDept_code());
            List<SysDept> list = sysDeptService.selectAll(example);
            log.info("根据部门编号查询部门编号是否存在" + "----------------------");
            log.info(list);
            if (list.size() > 0 && list != null) {
                return ajaxFail("部门编号重复，请重新填写部门编号!", FAIL_CODE);
            }
            sysDept.setCreate_time(new Date());
            sysDept.setUpdate_time(new Date());
            int count = sysDeptService.save(sysDept);
            if (count > 0) {
                return ajaxSucc("", SAVE_SUCCESS, SUCCESS_CODE);
            } else {
                return ajaxFail(SAVE_FAIL, FAIL_CODE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ajaxFail(SAVE_FAIL, FAIL_CODE);
        }
    }


    /**
     * 查询部门的集合list
     *
     * @return
     */
    @GetMapping(value = "/select", produces = "application/json;charset=utf-8")
    public BaseResponse select() {
        try {
            JSONArray array = new JSONArray();
            SysDeptExample example = new SysDeptExample();
            List<SysDept> list = sysDeptService.selectAll(example);
            log.info(list + "部门数据 ---------------------------");
            if (list != null && list.size() > 0) {
                list.stream().forEach(sysDept -> {
                    JSONObject deptJson = new JSONObject();
                    deptJson.put("id", sysDept.getDept_id());
                    deptJson.put("parentId", sysDept.getParent_id());
                    deptJson.put("title", sysDept.getDept_name());
                    deptJson.put("icon", "");
                    deptJson.put("href", "");
                    deptJson.put("deptCode", sysDept.getDept_code());
                    deptJson.put("create_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sysDept.getCreate_time()));
                    deptJson.put("update_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sysDept.getUpdate_time()));
                    array.add(deptJson);
                });
            }
            log.info(array + "部门数据 展示数据---------------------------");
            return ajaxSucc(array, FIND_SUCCESS, SUCCESS_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            return ajaxFail(FIND_FAIL, FAIL_CODE);
        }
    }

}
