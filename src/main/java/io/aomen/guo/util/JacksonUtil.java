package io.aomen.guo.util;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author AMGuo E-mail:www.guoao@foxmail.com
 * @version 创建时间：2018年1月4日 下午4:48:42 类说明
 */
public class JacksonUtil {
    private static ObjectMapper objectMapper = null;

    private JacksonUtil() {

    }

    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }

    // map转换为json
    public void test01() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("name", "zhangsan");
        map.put("age", "1");
        try {
            String jsonStr = JacksonUtil.getObjectMapper().writeValueAsString(map);
            // 解析json字符串
            JsonNode node = JacksonUtil.getObjectMapper().readTree(jsonStr);
            System.out.println(jsonStr);
            System.out.println("name=" + node.get("name").asText());
            System.out.println("name=" + node.get("name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 解析json格式字符串
    public void test02() {
        String json = "{data:{\"birth_day\":7,\"birth_month\":6},\"errcode\":0,\"msg\":\"ok\",\"rest\":0}";
        try {
            JsonNode node = JacksonUtil.getObjectMapper().readTree(json);
            JsonNode data = node.path("data");

            JsonNode birth_day = data.path("birth_day");
            System.out.println(birth_day.asInt());

            JsonNode birth_month = data.path("birth_month");
            System.out.println(birth_month.asInt());

            JsonNode msg = node.path("msg");
            System.out.println(msg);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // json直接提取值
    public static void MyTest05() {
        String str = "{\"data\":{\"hashnext\":0,\"info\":[{\"id\":\"939399393\"，\"timestamp\":\"22242244\"},{\"id\":\"939399393\"，\"timestamp\":\"22242244\"},{\"id\":\"939399393\"，\"timestamp\":\"22242244\"}],\"errcode\":0,\"msg\":\"ok\",\"rest\":0}}";

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(str);
            // 提取data
            JsonNode data = root.path("data");
            // 提取info
            JsonNode info = root.path("info");
            System.out.println(info.size());

            JsonNode item = info.get(0);
            System.out.println(item.get("id"));
            System.out.println(item.get("timestamp"));
            // 得到info的第二个值
            item = info.get(1);
            System.out.println(item.get("id"));
            System.out.println(item.get("timestamp"));

            if (info.isArray()) {
                for (JsonNode jsonNode : info) {
                    System.out.println(jsonNode);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 创建一个json,并像该json添加内容
    public static void MyTest07() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        objectNode.put("nodekey1", 1);
        objectNode.put("nodekey2", 2);

        System.out.println(objectNode.toString());

        ObjectNode root = mapper.createObjectNode();

        ObjectNode node1 = mapper.createObjectNode();
        node1.put("nodekey1", 1);
        node1.put("nodekey2", 2);

        root.put("child", node1);

        ArrayNode arrayNode = mapper.createArrayNode();
        arrayNode.add(node1);
        arrayNode.add(1);
        root.put("arraynode", arrayNode);

        try {
            System.out.println(mapper.writeValueAsString(root));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 创建一个arrayNode
    public static void MyTest08() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();

        int i = 0;
        // 在array内创建3组存入array

        for (i = 0; i < 3; i++) {
            ObjectNode node = mapper.createObjectNode();
            node.put("nodeA", i);
            node.put("nodeB", i);
            node.put("nodeC", i);

            arrayNode.add(node);
        }

        ObjectNode root = mapper.createObjectNode();
        root.put("total", i);
        root.put("rows", arrayNode);

        try {
            System.out.println(mapper.writeValueAsString(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new JacksonUtil().test01();
        new JacksonUtil().test02();
        new JacksonUtil().MyTest05();
        new JacksonUtil().MyTest07();
        new JacksonUtil().MyTest08();
    }
}
