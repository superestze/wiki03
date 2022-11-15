package com.zhengqi.wiki03.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhengqi.wiki03.domain.Doc;
import com.zhengqi.wiki03.domain.DocExample;
import com.zhengqi.wiki03.mapper.DocMapper;
import com.zhengqi.wiki03.req.DocQueryReq;
import com.zhengqi.wiki03.req.DocSaveReq;
import com.zhengqi.wiki03.resp.DocQueryResp;
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
public class DocService {

    private final static Logger LOG = LoggerFactory.getLogger(DocService.class);
    @Resource
    private DocMapper docMapper;

    @Resource
    private SnowFlake snowFlake;

    public List<DocQueryResp> all(DocQueryReq req) {
        DocExample docExample = new DocExample();
        docExample.setOrderByClause("sort asc");
        List<Doc> docsList = docMapper.selectByExample(docExample);
        List<DocQueryResp> list = CopyUtil.copyList(docsList, DocQueryResp.class);

        return list;
    }

    public PageResp<DocQueryResp> list(DocQueryReq req) {
        DocExample docExample = new DocExample();
        DocExample.Criteria criteria = docExample.createCriteria();
        // criteria.andNameLike("%" + req.getName() + "%");
        docExample.setOrderByClause("sort asc");


        PageHelper.startPage(req.getPage(), req.getSize());
        List<Doc> docsList = docMapper.selectByExample(docExample);

        PageInfo<Doc> pageInfo = new PageInfo<>(docsList);

        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());


        // List<DocResp> respList = new ArrayList<>();
        // for (Doc doc : docsList) {
        //     // DocResp docResp = new DocResp();
        //     // BeanUtils.copyProperties(doc, docResp);
        //
        //     DocResp docResp = CopyUtil.copy(doc, DocResp.class);
        //     respList.add(docResp);
        // }


        List<DocQueryResp> list = CopyUtil.copyList(docsList, DocQueryResp.class);
        PageResp<DocQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    /**
     * 保存
     * @param req
     */
    public void save(DocSaveReq req) {
        Doc doc = CopyUtil.copy(req, Doc.class);
        if(ObjectUtils.isEmpty(req.getId())) {
            doc.setId(snowFlake.nextId());
            docMapper.insert(doc);
        } else {
            docMapper.updateByPrimaryKey(doc);
        }
    }


    /**
     * 删除
     * @param id
     */
    public void delete(Long id) {
        docMapper.deleteByPrimaryKey(id);
    }
}

