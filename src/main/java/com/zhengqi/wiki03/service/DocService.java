package com.zhengqi.wiki03.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhengqi.wiki03.domain.Content;
import com.zhengqi.wiki03.domain.Doc;
import com.zhengqi.wiki03.domain.DocExample;
import com.zhengqi.wiki03.exception.BusinessException;
import com.zhengqi.wiki03.exception.BusinessExceptionCode;
import com.zhengqi.wiki03.mapper.ContentMapper;
import com.zhengqi.wiki03.mapper.DocMapper;
import com.zhengqi.wiki03.mapper.DocMapperCust;
import com.zhengqi.wiki03.req.DocQueryReq;
import com.zhengqi.wiki03.req.DocSaveReq;
import com.zhengqi.wiki03.resp.DocQueryResp;
import com.zhengqi.wiki03.resp.PageResp;
import com.zhengqi.wiki03.util.CopyUtil;
import com.zhengqi.wiki03.util.RedisUtil;
import com.zhengqi.wiki03.util.RequestContext;
import com.zhengqi.wiki03.util.SnowFlake;
import com.zhengqi.wiki03.websocket.WebSocketServer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.websocket.server.ServerEndpoint;
import java.util.List;

@Service
public class DocService {

    private final static Logger LOG = LoggerFactory.getLogger(DocService.class);
    @Resource
    private DocMapper docMapper;
    @Resource
    private WsService wsService;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Resource
    private ContentMapper contentMapper;
    @Resource
    private DocMapperCust docMapperCust;

    @Resource
    private SnowFlake snowFlake;

    @Resource
    private RedisUtil redisUtil;

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

        LOG.info("????????????{}", pageInfo.getTotal());
        LOG.info("????????????{}", pageInfo.getPages());


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
     * ??????
     *
     * @param req
     */
    @Transactional
    public void save(DocSaveReq req) {
        Doc doc = CopyUtil.copy(req, Doc.class);
        Content content = CopyUtil.copy(req, Content.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            doc.setId(snowFlake.nextId());
            doc.setViewCount(0);
            doc.setVoteCount(0);
            docMapper.insert(doc);

            content.setId(doc.getId());
            contentMapper.insert(content);
        } else {
            docMapper.updateByPrimaryKey(doc);
            int count = contentMapper.updateByPrimaryKeyWithBLOBs(content);
            if (count == 0) {
                contentMapper.insert(content);
            }
        }
    }


    /**
     * ??????
     *
     * @param id
     */
    public void delete(Long id) {
        docMapper.deleteByPrimaryKey(id);
    }

    /**
     * ??????
     *
     * @param ids
     */
    public void delete(List<String> ids) {
        DocExample docExample = new DocExample();
        DocExample.Criteria criteria = docExample.createCriteria();
        criteria.andIdIn(ids);

        docMapper.deleteByExample(docExample);
    }

    public String findContent(Long id) {
        Content content = contentMapper.selectByPrimaryKey(id);
        // ???????????????+1
        docMapperCust.increaseViewCount(id);
        if (ObjectUtils.isEmpty(content)) {
            return null;
        } else {
            return content.getContent();
        }
    }

    public void vote(Long id) {
        String key = RequestContext.getRemoteAddr();
        if(redisUtil.validateRepeat("DOC_VOTE_" + id + "_" + key, 3600*24)) {
            docMapperCust.increaseViewCount(id);
        } else {
            throw new BusinessException(BusinessExceptionCode.VOTE_REPEAT);
        }
        Doc docDb = docMapper.selectByPrimaryKey(id);
        String logId = MDC.get("LOG_ID");

        // wsService.sendInfo("[" + docDb.getName() + "]?????????", logId);
        rocketMQTemplate.convertAndSend("VOTE_TOPIC", "[" + docDb.getName() + "]?????????");
    }

    public void updateEbookInfo(){
        docMapperCust.updateEbookInfo();
    }

}

