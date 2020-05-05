package org.tm.common.generator.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/*
    author: Timor
    date: 2020/3/14 0014
*/
public class MyFileUtil {
    public static JSONObject getFileToJSONObject(String fileName) throws IOException {
        File file = new File(fileName);
        String jsonStr = FileUtils.readFileToString(file, StandardCharsets.UTF_8);//前面两行是读取文件
        return JSON.parseObject(jsonStr);
    }

    public static void writeJSONObjectToFile(JSONObject json, String fileName) throws IOException {
        FileUtils.writeStringToFile(new File(fileName),
                json.toJSONString(), StandardCharsets.UTF_8);
    }

    public static void writeStringToFile(String str, String fileName) throws IOException {
        FileUtils.writeStringToFile(new File(fileName),
                str, StandardCharsets.UTF_8);
    }

}
