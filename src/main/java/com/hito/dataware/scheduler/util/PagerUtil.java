package com.hito.dataware.scheduler.util;/**
 * @title: PagerUtil
 * @projectName dataware-ship-service
 * @description: TODO
 * @author 伏长海
 * @date 2019/7/111:18
 */

import com.hito.common.page.Pager;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author fuchanghai
 * @description 分页参数的设置
 * @date 2019/7/111:18
 */
@Component
public class PagerUtil<T> {

    public Pager pagerHelp(Integer pageNum, Integer pageSize, Long totalCount, List< T> list) {
        Pager pager = new Pager();
        Integer total = totalCount.intValue();
        int pageCount = 0;
        if (total % pageSize == 0) {
            pageCount = total / pageSize;
        } else {
            pageCount = total / pageSize + 1;
        }
        pager.setPageNumber(pageNum > pageCount ? pageCount : pageNum + 1);
        pager.setPageSize(list.size());
        pager.setPageCount(pageCount);
        pager.setTotalCount(total);
        pager.setList(list);
        pager.setOrderBy("score");
        return pager;
    }
}
