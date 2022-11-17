package com.zhengqi.wiki03.mapper;

import com.zhengqi.wiki03.domain.Test;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocMapperCust {
    public void increaseViewCount(@Param("id") Long id);
}
