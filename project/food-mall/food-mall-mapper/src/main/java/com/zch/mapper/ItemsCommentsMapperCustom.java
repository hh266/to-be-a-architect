package com.zch.mapper;

import com.zch.my.mapper.MyMapper;
import com.zch.pojo.ItemsComments;
import com.zch.pojo.vo.MyCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsCommentsMapperCustom extends MyMapper<ItemsComments> {

    /**
     * 保存评论
     *
     * @param map
     */
    public void saveComments(Map<String, Object> map);

    /**
     * 查询我的评论
     *
     * @param map
     * @return
     */
    public List<MyCommentVO> queryMyComments(@Param("paramsMap") Map<String, Object> map);

}