package org.tm.common.generator;

/*
    author: Timor
    date: 2020/3/13 0013
*/
enum GenerateRule{
    GENERATE_ALL(0),// 生成所有，会覆盖原有的文件
    GENERATE_EXIST(1),// 仅重新生成已生成的
    GENERATE_NOT_EXIST(2);// 生成未生成过的
    int rule;

    GenerateRule(int rule) {
        this.rule = rule;
    }

    public int getRule() {
        return rule;
    }
}
