package cn.tm.hotel.common;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PathKit;
import org.tm.common.generator.Generator;
import org.tm.common.generator.util.MyFileUtil;

import javax.sql.DataSource;
import java.io.IOException;

/*
    author: Timor
    date: 2020/3/13 0013
*/
public class CodeGenerator {
    static String dbUrl = "jdbc:mysql://localhost:3306/hotel";
    static String username = "root";
    static String password = "123456";

    public static void main(String[] args) throws IOException {
        // 生成代码
        buildMappingRules();
        build();

//         测试生成代码
//        buildTestMappingRules();
//        buildTest();
    }

    static void build() throws IOException {
        String fileName = PathKit.getRootClassPath() + "\\generator\\config\\config.json";
        String mappingFileName = PathKit.getRootClassPath() + "\\generator\\config\\mapping.json";
        JSONObject config = MyFileUtil.getFileToJSONObject(fileName);
        JSONObject mapping = MyFileUtil.getFileToJSONObject(mappingFileName);

//        generate(config);
        generateWithRule(config, mapping);
    }

    static void buildTest() throws IOException {
        String fileName = PathKit.getRootClassPath() + "\\generator\\config\\configTest.json";
        String mappingFileName = PathKit.getRootClassPath() + "\\generator\\config\\mappingTest.json";
        JSONObject config = MyFileUtil.getFileToJSONObject(fileName);
        JSONObject mapping = MyFileUtil.getFileToJSONObject(mappingFileName);

        generateWithRule(config, mapping);
    }

    static void buildTestMappingRules() throws IOException {
        String fileName = PathKit.getRootClassPath() + "\\generator\\config\\configTest.json";
        String mappingFileName = PathKit.getWebRootPath() + "\\src\\main\\resources\\generator\\config\\mappingTest.json";
        System.out.println("mappingFileName: " + mappingFileName);
        generateMappingRule(fileName, mappingFileName);
    }

    static void buildMappingRules() throws IOException {
        String fileName = PathKit.getRootClassPath() + "\\generator\\config\\config.json";
        String mappingFileName = PathKit.getWebRootPath() + "\\src\\main\\resources\\generator\\config\\mapping.json";
        System.out.println("mappingFileName: " + mappingFileName);
        generateMappingRule(fileName, mappingFileName);
    }

    // 通过配置文件一键生成
    static void generate(JSONObject config) throws IOException {
        DataSource dataSource = Generator.getDataSource(dbUrl, username, password);

        Generator generator = new Generator(dataSource, config);
        JSONObject metaData = generator.generateMappingConfig(config);

        generator.generate(metaData);
    }

    // 通过配置文件并指定生成规则一键生成
    static void generateWithRule(JSONObject config, JSONObject mapping) throws IOException {
        DataSource dataSource = Generator.getDataSource(dbUrl, username, password);

        new Generator(dataSource, config).generate(mapping);
    }

    // 通过配置文件生成生成规则，大型项目建议如此，
    static void generateMappingRule(String configFile, String filename) throws IOException {
        DataSource dataSource = Generator.getDataSource(dbUrl, username, password);

        JSONObject json = MyFileUtil.getFileToJSONObject(configFile);

        Generator generator = new Generator(dataSource, json);
        JSONObject metaData = generator.generateMappingConfig(json);
//        System.out.println(metaData);
        MyFileUtil.writeJSONObjectToFile(metaData, filename);
    }

    // 通过配置文件并指定生成规则一键生成
    public static DataSource getDataSource() {
        return Generator.getDataSource(dbUrl, username, password);
    }
}
