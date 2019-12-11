package com.example.demo.controller;

import com.example.demo.model.User;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author xiaohu
 * @Date 2019/12/9 13:27
 * 参考链接:https://mrbird.cc/Spring-Boot%20JSON.html
 */
@Controller
public class JackJsonController {

    @Autowired
    ObjectMapper mapper;

    @JsonView(User.AllUserFieldView.class)
    @RequestMapping("getuserAll")
    @ResponseBody
    public User getUserAll() {
        User user = new User();
        user.setAge(26);
        user.setUserName("mrbird");
        user.setPassword("123456");
        user.setBirthday(new Date());
        return user;
    }

    @JsonView(User.UserNameView.class)
    @RequestMapping("getuserView")
    @ResponseBody
    public User getUserView() {
        User user = new User();
        user.setUserName("mrbird");
        user.setPassword("123456");
        user.setAge(26);
        user.setBirthday(new Date());
        return user;
    }

    /**
     * 序列化：Jackson通过使用mapper的writeValueAsString方法将Java对象序列化为JSON格式字符串
     * @return
     */
    @RequestMapping("serialization")
    @ResponseBody
    public String serialization() {
        try {
            User user = new User();
            user.setUserName("mrbird");
            user.setBirthday(new Date());
            String str = mapper.writeValueAsString(user);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化：树遍历
     * @return
     */
    @RequestMapping("readjsonstring")
    @ResponseBody
    public String readJsonString() {
        try {
            String json = "{\"name\":\"mrbird\",\"age\":26}";
            JsonNode node = this.mapper.readTree(json);
            String name = node.get("name").asText();
            int age = node.get("age").asInt();
            return name + " " + age;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化：绑定对象
     * @return
     */
    @RequestMapping("readjsonasobject")
    @ResponseBody
    public String readJsonAsObject() {
        try {
            String json = "{\"userName\":\"mrbird\"}";
            User user = mapper.readValue(json, User.class);
            String name = user.getUserName();
            return name;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("updateuser")
    @ResponseBody
    public int updateUser(@RequestBody List<User> list) {
        return list.size();
    }

    /**
     * json转对象使用JavaType
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @RequestMapping("customize")
    @ResponseBody
    public String customize() throws JsonParseException, JsonMappingException, IOException {
        String jsonStr = "[{\"userName\":\"mrbird\",\"age\":26},{\"userName\":\"scott\",\"age\":27}]";
        JavaType type = mapper.getTypeFactory().constructParametricType(List.class, User.class);
        List<User> list = mapper.readValue(jsonStr, type);
        String msg = "";
        for (User user : list) {
            msg += user.getUserName();
        }
        return msg;
    }

    /**
     * json转对象使用javabean
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @RequestMapping("customize2")
    @ResponseBody
    public String customize2() throws IOException {
        String jsonStr = "{\"userName\":\"mrbird\",\"age\":26}";
        User user = mapper.readValue(jsonStr, User.class);
        return user.getUserName()+user.getAge();
    }


    /**
     * json转集合使用TypeReference
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @RequestMapping("customize3")
    @ResponseBody
    public String customize3() throws IOException {
        String jsonStr = "[{\"userName\":\"mrbird\",\"age\":26},{\"userName\":\"scott\",\"age\":27}]";
        List<User> list = mapper.readValue(jsonStr, new TypeReference<List<User>>(){});
        String msg = "";
        for (User user : list) {
            msg += user.getUserName();
        }
        return msg;
    }

}
