package org.tm.common.base.model;

import lombok.Getter;
import lombok.ToString;

/*
    author: Timor
    date: 2020/3/22 0022
*/
@ToString
@Getter
public class BaseCondition implements ICondition{
    static final int defaultPageNumber = 1;
    static int defaultPageSize = 20;
    static int maxPageSize = 100;
    static final boolean printInfo = false;

    protected int pageNumber = defaultPageNumber;
    protected int pageSize = defaultPageSize;

    public void setPageNumber(int pageNumber) {
        if(pageNumber > 1) {
            this.pageNumber = pageNumber;
        }else {
            if(printInfo) System.out.println("[info] BaseCondition use default pageNumber: " + defaultPageNumber);
        }
    }

    public void setPageSize(int pageSize) {
        if(pageSize > 1 && pageSize <= defaultPageSize){
            this.pageSize = pageSize;
        }else {
            if(printInfo) System.out.println("[info] BaseCondition use default pageSize: " + defaultPageSize);
        }
    }

    @Override
    public String check() {
        return "";
    }
}
