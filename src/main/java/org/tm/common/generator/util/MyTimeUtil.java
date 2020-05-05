package org.tm.common.generator.util;

/*
    author: Timor
    date: 2020/3/13 0013
*/
public class MyTimeUtil {

    public static <T> long getExecuteTime(Runnable func) {
        long start = System.currentTimeMillis();
        // run
        func.run();
        long usedTime = (System.currentTimeMillis() - start) / 1000L;
//        System.out.println("running complete in " + usedTime + " seconds.");
        return usedTime;
    }
}
