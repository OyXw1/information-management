package com.oy.informationmanagement.service.serviceImpl;

import com.oy.informationmanagement.dao.NewsCommentMapper;
import com.oy.informationmanagement.entity.NewsComment;
import com.oy.informationmanagement.service.CommentService;
import com.oy.informationmanagement.utils.PageQueryUtil;
import com.oy.informationmanagement.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private NewsCommentMapper newsCommentMapper;

    @Override
    public Boolean addComment(NewsComment newsComment) {
        return newsCommentMapper.insertSelective(newsComment) > 0;
    }

    @Override
    public PageResult getCommentsPage(PageQueryUtil pageUtil) {
        List<NewsComment> comments = newsCommentMapper.findNewsCommentList(pageUtil);
        int total = newsCommentMapper.getTotalNewsComments(pageUtil);
        PageResult pageResult = new PageResult(comments, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public Boolean checkDone(Integer[] ids) {
        return newsCommentMapper.checkDone(ids) > 0;
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return newsCommentMapper.deleteBatch(ids) > 0;
    }
}
