package com.zhengqi.wiki03.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhengqi.wiki03.domain.User;
import com.zhengqi.wiki03.domain.UserExample;
import com.zhengqi.wiki03.mapper.UserMapper;
import com.zhengqi.wiki03.req.UserQueryReq;
import com.zhengqi.wiki03.req.UserSaveReq;
import com.zhengqi.wiki03.resp.UserQueryResp;
import com.zhengqi.wiki03.resp.PageResp;
import com.zhengqi.wiki03.util.CopyUtil;
import com.zhengqi.wiki03.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    private final static Logger LOG = LoggerFactory.getLogger(UserService.class);
    @Resource
    private UserMapper userMapper;

    @Resource
    private SnowFlake snowFlake;

    public PageResp<UserQueryResp> list(UserQueryReq req) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNameLike("%" + req.getLoginName() + "%");

        PageHelper.startPage(req.getPage(), req.getSize());
        List<User> usersList = userMapper.selectByExample(userExample);

        PageInfo<User> pageInfo = new PageInfo<>(usersList);

        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());


        // List<UserResp> respList = new ArrayList<>();
        // for (User user : usersList) {
        //     // UserResp userResp = new UserResp();
        //     // BeanUtils.copyProperties(user, userResp);
        //
        //     UserResp userResp = CopyUtil.copy(user, UserResp.class);
        //     respList.add(userResp);
        // }


        List<UserQueryResp> list = CopyUtil.copyList(usersList, UserQueryResp.class);
        PageResp<UserQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    /**
     * 保存
     * @param req
     */
    public void save(UserSaveReq req) {
        User user = CopyUtil.copy(req, User.class);
        if(ObjectUtils.isEmpty(req.getId())) {
            user.setId(snowFlake.nextId());
            userMapper.insert(user);
        } else {
            userMapper.updateByPrimaryKey(user);
        }
    }


    /**
     * 删除
     * @param id
     */
    public void delete(Long id) {
        userMapper.deleteByPrimaryKey(id);
    }
}
