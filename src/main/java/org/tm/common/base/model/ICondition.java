package org.tm.common.base.model;

/*
    author: Timor
    date: 2020/3/22 0022
*/
public interface ICondition {
    // 获取第几页
    int getPageNumber();
    // 获取页面大小
    int getPageSize();
    // 检查查询条件，如果没有问题则返回"";
    String check();
}
