package com.zhengqi.wiki03.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhengqi.wiki03.domain.Category;
import com.zhengqi.wiki03.domain.CategoryExample;
import com.zhengqi.wiki03.mapper.CategoryMapper;
import com.zhengqi.wiki03.req.CategoryQueryReq;
import com.zhengqi.wiki03.req.CategorySaveReq;
import com.zhengqi.wiki03.resp.CategoryQueryResp;
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
public class CategoryService {

    private final static Logger LOG = LoggerFactory.getLogger(CategoryService.class);
    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private SnowFlake snowFlake;

    public List<CategoryQueryResp> all(CategoryQueryReq req) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        List<Category> categorysList = categoryMapper.selectByExample(categoryExample);
        List<CategoryQueryResp> list = CopyUtil.copyList(categorysList, CategoryQueryResp.class);

        return list;
    }

    public PageResp<CategoryQueryResp> list(CategoryQueryReq req) {
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        // criteria.andNameLike("%" + req.getName() + "%");
        categoryExample.setOrderByClause("sort asc");


        PageHelper.startPage(req.getPage(), req.getSize());
        List<Category> categorysList = categoryMapper.selectByExample(categoryExample);

        PageInfo<Category> pageInfo = new PageInfo<>(categorysList);

        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());


        // List<CategoryResp> respList = new ArrayList<>();
        // for (Category category : categorysList) {
        //     // CategoryResp categoryResp = new CategoryResp();
        //     // BeanUtils.copyProperties(category, categoryResp);
        //
        //     CategoryResp categoryResp = CopyUtil.copy(category, CategoryResp.class);
        //     respList.add(categoryResp);
        // }


        List<CategoryQueryResp> list = CopyUtil.copyList(categorysList, CategoryQueryResp.class);
        PageResp<CategoryQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    /**
     * 保存
     * @param req
     */
    public void save(CategorySaveReq req) {
        Category category = CopyUtil.copy(req, Category.class);
        if(ObjectUtils.isEmpty(req.getId())) {
            category.setId(snowFlake.nextId());
            categoryMapper.insert(category);
        } else {
            categoryMapper.updateByPrimaryKey(category);
        }
    }


    /**
     * 删除
     * @param id
     */
    public void delete(Long id) {
        categoryMapper.deleteByPrimaryKey(id);
    }
}

