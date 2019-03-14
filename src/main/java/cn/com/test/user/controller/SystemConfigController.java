package cn.com.test.user.controller;/**
 * Created by cuidianlong on 2018-07-04.
 */

import cn.com.test.user.entity.SystemConfig;
import cn.com.test.user.service.SystemConfigService;
import cn.com.test.user.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName SystemConfigController
 * @Description TODO
 * @Author cuidianlong
 * @Date 2018-07-04 16:04
 * @Version 1.0
 **/
@RestController
@RequestMapping("/config")
public class SystemConfigController extends BaseController {

    @Resource
    SystemConfigService systemConfigService;

    /**
     * 查询
     *
     * @return
     */
    @GetMapping(value = "/select", produces = "application/json;charset=utf-8")
    public @ResponseBody
    BaseResponse select() {
        try {
            SystemConfig systemConfig = systemConfigService.selectById(1);
            return ajaxSucc(systemConfig, FIND_SUCCESS, SUCCESS_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            return ajaxFail(FIND_FAIL, FAIL_CODE);
        }

    }

    /**
     * 修改
     *
     * @param systemConfig
     * @return
     */
    @PostMapping(value = "/update", produces = "application/json;charset=utf-8")
    public BaseResponse update(@RequestBody SystemConfig systemConfig) {
        try {
            int count = systemConfigService.updateById(systemConfig);
            if (count > 0) {
                return ajaxSucc("", UPDATE_SUCCESS, SUCCESS_CODE);
            } else {
                return ajaxFail(UPDATE_FAIL, FAIL_CODE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ajaxFail(UPDATE_FAIL, FAIL_CODE);
        }
    }

}
