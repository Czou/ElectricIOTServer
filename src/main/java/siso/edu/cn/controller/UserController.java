package siso.edu.cn.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import siso.edu.cn.entity.FlagEntity;
import siso.edu.cn.entity.ResultEntity;
import siso.edu.cn.entity.UserDepartmentRelationEntity;
import siso.edu.cn.entity.UserEntity;
import siso.edu.cn.service.UserDepartmentRelationService;
import siso.edu.cn.service.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @apiDefine userGroup 用户管理接口
 */
@RestController
@RequestMapping(value = "/api/manage", produces = "application/json;charset=utf-8")
public class UserController {

    private UserService userService;
    private UserDepartmentRelationService userDepartmentRelationService;

    @Autowired
    public UserController(UserService userService, UserDepartmentRelationService userDepartmentRelationService) {
        this.userService = userService;
        this.userDepartmentRelationService = userDepartmentRelationService;
    }

    /**
     * @api {post} /api/manage/user 创建新用户
     * @apiVersion 0.0.1
     * @apiName createUser
     * @apiGroup userGroup
     *
     * @apiParam {String} login_name 用户账号
     * @apiParam {String} login_password 用户密码
     * @apiParam {String} name 用户名
     * @apiParam {String} [mobile] 用户手机号
     * @apiParam {String} [email] 用户邮箱
     *
     * @apiSuccess {String} code 返回码.
     * @apiSuccess {String} msg  返回消息.
     * @apiSuccess {Object} data  JSON格式的对象.
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResultEntity createUser(@RequestParam("login_name") String loginName,
                                   @RequestParam("login_password") String loginPassword,
                                   @RequestParam("name") String name,
                                   @RequestParam(name = "mobile", required = false, defaultValue = "") String mobile,
                                   @RequestParam(name = "email", required = false, defaultValue = "") String email) {
        // 获取时间格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

        UserEntity userEntity = new UserEntity();
        userEntity.setLoginName(loginName);
        userEntity.setLoginPassword(loginPassword);
        userEntity.setName(name);
        // 获取当前时间和日期
        userEntity.setCreateTime(simpleDateFormat.format(new Date()));
        if (!mobile.isEmpty()) {
            userEntity.setMobile(mobile);
        }
        if (!email.isEmpty()) {
            userEntity.setEmail(email);
        }
        userEntity.setIsDelete(FlagEntity.NO_DELETE);

        this.userService.save(userEntity);

        // 返回保存的结果
        if (userEntity.getId() > 0) {
            ObjectMapper objectMapper = new ObjectMapper();
            return CommonController.CreateResultEntity(ResultEntity.SUCCESS, objectMapper.convertValue(userEntity, JsonNode.class));
        }

        return CommonController.CreateResultEntity(ResultEntity.SAVE_DATA_ERROR);
    }

    /**
     * @api {delete} /api/manage/user/:id 根据ID删除用户
     * @apiVersion 0.0.1
     * @apiName deleteUserById
     * @apiGroup userGroup
     *
     * @apiParam {Number} id 用户ID
     *
     * @apiSuccess {String} code 返回码.
     * @apiSuccess {String} msg  返回消息.
     * @apiSuccess {Object} data  JSON格式的对象.
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResultEntity deleteUserById(@PathVariable("id") long id) {
        UserEntity userEntity = userService.findById(id);

        if (userEntity == null) {
            return CommonController.CreateResultEntity(ResultEntity.DELETE_ERROR);
        }

        userEntity.setIsDelete(FlagEntity.DELETE);
        userEntity = userService.update(userEntity);

        ObjectMapper objectMapper = new ObjectMapper();

        return CommonController.CreateResultEntity(ResultEntity.SUCCESS, objectMapper.convertValue(userEntity, JsonNode.class));
    }

    /**
     * @api {put} /api/manage/user 根据ID修改用户信息
     * @apiVersion 0.0.1
     * @apiName modifyUserById
     * @apiGroup userGroup
     *
     * @apiParam {Number} id 用户id
     * @apiParam {String} [login_name] 用户账户
     * @apiParam {String} [login_password] 用户密码
     * @apiParam {String} [name] 用户名称
     * @apiParam {String} [mobile] 用户电话
     * @apiParam {String} [email] 用户邮件
     *
     * @apiSuccess {String} code 返回码.
     * @apiSuccess {String} msg  返回消息.
     * @apiSuccess {Object} data  JSON格式的对象.
     */
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public ResultEntity modifyUserById(@RequestParam("id") long id,
                                       @RequestParam(name = "login_name", required = false, defaultValue = "") String loginName,
                                       @RequestParam(name = "login_password", required = false, defaultValue = "") String loginPassword,
                                       @RequestParam(name = "name", required = false, defaultValue = "") String name,
                                       @RequestParam(name = "mobile", required = false, defaultValue = "") String mobile,
                                       @RequestParam(name = "email", required = false, defaultValue = "") String email) {
        UserEntity entity = userService.findById(id);

        if (entity == null) {
            return CommonController.CreateResultEntity(ResultEntity.NOT_FIND_ERROR);
        }

        if (!loginName.isEmpty()) {
            entity.setLoginName(loginName);
        }
        if (!loginPassword.isEmpty()) {
            entity.setLoginPassword(loginPassword);
        }
        if (!name.isEmpty()) {
            entity.setName(name);
        }
        if (!mobile.isEmpty()) {
            entity.setMobile(mobile);
        }
        if (!email.isEmpty()) {
            entity.setEmail(email);
        }

        entity = userService.update(entity);

        ObjectMapper objectMapper = new ObjectMapper();
        return CommonController.CreateResultEntity(ResultEntity.SUCCESS, objectMapper.convertValue(entity, JsonNode.class));
    }

    /**
     * @api {get} /api/manage/user/:id 根据ID获取用户信息
     * @apiVersion 0.0.1
     * @apiName getUserById
     * @apiGroup userGroup
     *
     * @apiParam {Number} id 用户ID
     *
     * @apiSuccess {String} code 返回码.
     * @apiSuccess {String} msg  返回消息.
     * @apiSuccess {Object} data  JSON格式的对象.
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResultEntity getUserById(@PathVariable("id") long id) {
        UserEntity userEntity = userService.findById(id);

        if (userEntity != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            return CommonController.CreateResultEntity(ResultEntity.SUCCESS,
                    objectMapper.convertValue(userEntity, JsonNode.class));
        }

        return CommonController.CreateResultEntity(ResultEntity.NOT_FIND_ERROR);
    }

    /**
     * @api {post} /api/manage/user/department 把用户加入某个部门
     * @apiVersion 0.0.1
     * @apiName bindDepartment
     * @apiGroup userGroup
     *
     * @apiParam {Number} user_id 用户ID
     * @apiParam {Number} department_id 部门ID
     *
     * @apiSuccess {String} code 返回码.
     * @apiSuccess {String} msg  返回消息.
     * @apiSuccess {Object} data  JSON格式的对象.
     */
    @RequestMapping(value = "/user/department", method = RequestMethod.POST)
    public ResultEntity bindDepartment(@RequestParam("user_id") long userId,
                                       @RequestParam("department_id") long departmentId) {
        UserDepartmentRelationEntity entity = new UserDepartmentRelationEntity();
        entity.setDepartmentId(departmentId);
        entity.setUserId(userId);

        userDepartmentRelationService.save(entity);

        if (entity.getId() > 0) {
            ObjectMapper objectMapper = new ObjectMapper();
            return CommonController.CreateResultEntity(ResultEntity.SUCCESS, objectMapper.convertValue(entity, JsonNode.class));
        }

        return CommonController.CreateResultEntity(ResultEntity.SAVE_DATA_ERROR);
    }

}