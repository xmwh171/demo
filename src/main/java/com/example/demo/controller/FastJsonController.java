package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author xiaohu
 * @Date 2019/12/9 15:07
 */
@Controller
public class FastJsonController {


    //json字符串-简单对象型
    private static final String  JSON_OBJ_STR = "";

    //json字符串-数组类型
    private static final String  JSON_ARRAY_STR = "[{\"name\":\"lily\",\"sno\":12},{\"name\":\"lucy\",\"sno\":15}]";

    //复杂格式json字符串
    private static final String  COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"name\":\"lily\",\"sno\":12},{\"name\":\"lucy\",\"sno\":15}]}";


    /**
     * json字符串-简单对象型到JSONObject的转换
     */
    @RequestMapping("testJSONStrToJSONObject")
    @ResponseBody
    public void testJSONStrToJSONObject() {
        JSONObject jsonObject = JSONObject.parseObject("{\"name\":\"lily\",\"sno\":12}");
        System.out.println("name:  " + jsonObject.getString("name") + ":" + "  sno:  "
                + jsonObject.getInteger("sno"));
    }
    /**
     * JSONObject到json字符串-简单对象型的转换
     */
    @RequestMapping("testJSONObjectToJSONStr")
    @ResponseBody
    public void testJSONObjectToJSONStr() {

        //已知JSONObject,目标要转换为json字符串
        JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);
        // 第一种方式
        String jsonString = JSONObject.toJSONString(jsonObject);

        // 第二种方式
        //String jsonString = jsonObject.toJSONString();
        System.out.println(jsonString);
    }

    /**
     * json字符串-数组类型到JSONArray的转换
     */
    @RequestMapping("testJSONStrToJSONArray")
    @ResponseBody
    public void testJSONStrToJSONArray() {

        JSONArray jsonArray = JSONArray.parseArray(JSON_ARRAY_STR);

        //遍历方式1
        int size = jsonArray.size();
        for (int i = 0; i < size; i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println("name:  " + jsonObject.getString("name") + ":" + "  sno:  "
                    + jsonObject.getInteger("sno"));
        }

        //遍历方式2
        for (Object obj : jsonArray) {

            JSONObject jsonObject = (JSONObject) obj;
            System.out.println("name:  " + jsonObject.getString("name") + ":" + "  sno:  "
                    + jsonObject.getInteger("sno"));
        }
    }

    /**
     * JSONArray到json字符串-数组类型的转换
     */
    @RequestMapping("testJSONArrayToJSONStr")
    @ResponseBody
    public void testJSONArrayToJSONStr() {

        //已知JSONArray,目标要转换为json字符串
        JSONArray jsonArray = JSONArray.parseArray(JSON_ARRAY_STR);
        //第一种方式
        String jsonString = JSONArray.toJSONString(jsonArray);

        // 第二种方式
        //String jsonString = jsonArray.toJSONString(jsonArray);
        System.out.println(jsonString);
    }

    /**
     * 复杂json格式字符串到JSONObject的转换
     */
    @RequestMapping("testComplexJSONStrToJSONObject")
    @ResponseBody
    public void testComplexJSONStrToJSONObject() {
        JSONObject jsonObject = JSONObject.parseObject(COMPLEX_JSON_STR);
        String teacherName = jsonObject.getString("teacherName");
        Integer teacherAge = jsonObject.getInteger("teacherAge");
        System.out.println("teacherName:  " + teacherName + "   teacherAge:  " + teacherAge);
        JSONObject jsonObjectcourse = jsonObject.getJSONObject("course");
        //获取JSONObject中的数据
        String courseName = jsonObjectcourse.getString("courseName");
        Integer code = jsonObjectcourse.getInteger("code");
        System.out.println("courseName:  " + courseName + "   code:  " + code);
        JSONArray jsonArraystudents = jsonObject.getJSONArray("students");
        //遍历JSONArray
        for (Object object : jsonArraystudents) {
            JSONObject jsonObjectone = (JSONObject) object;
            String name = jsonObjectone.getString("name");
            Integer sno = jsonObjectone.getInteger("sno");
            System.out.println("name:  " + name + "   sno:  " + sno);
        }
    }

    /**
     * 复杂JSONObject到json格式字符串的转换
     */
    @RequestMapping("testJSONObjectToComplexJSONStr")
    @ResponseBody
    public void testJSONObjectToComplexJSONStr() {
        //复杂JSONObject,目标要转换为json字符串
        JSONObject jsonObject = JSONObject.parseObject(COMPLEX_JSON_STR);
        //第一种方式
        //String jsonString = JSONObject.toJSONString(jsonObject);
        //第二种方式
        String jsonString = jsonObject.toJSONString();
        System.out.println(jsonString);
    }


    /**
     * json字符串-简单对象到JavaBean之间的转换
     */
    @RequestMapping("testJSONStrToJavaBeanObj")
    @ResponseBody
    public void testJSONStrToJavaBeanObj() {

        //第一种方式
        JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);

        String name = jsonObject.getString("name");
        Integer sno = jsonObject.getInteger("sno");

        //Student student = new Student(name, sno);

        //第二种方式,使用TypeReference<T>类,由于其构造方法使用protected进行修饰,故创建其子类
        //Student student = JSONObject.parseObject(JSON_OBJ_STR, new TypeReference<Student>() {});

        //第三种方式,使用Gson的思想
        Student student = JSONObject.parseObject(JSON_OBJ_STR, Student.class);

        System.out.println(student);
    }

    /**
     * JavaBean到json字符串-简单对象的转换
     */
    @RequestMapping("testJavaBeanObjToJSONStr")
    @ResponseBody
    public void testJavaBeanObjToJSONStr() {

        Student student = new Student("1000","lily", "m");
        String jsonString = JSONObject.toJSONString(student);
        System.out.println(jsonString);
    }

    /**
     * json字符串-数组类型到JavaBean_List的转换
     */
    @RequestMapping("testJSONStrToJavaBeanList")
    @ResponseBody
    public void testJSONStrToJavaBeanList() {
        //第一种方式
        JSONArray jsonArray = JSONArray.parseArray(JSON_ARRAY_STR);

        //遍历JSONArray
        List<Student> students = new ArrayList<Student>();
        Student student = null;
        for (Object object : jsonArray) {

            JSONObject jsonObjectone = (JSONObject) object;
            String name = jsonObjectone.getString("name");
            String sno = jsonObjectone.getString("sno");

            student = new Student(name,sno);
            students.add(student);
        }
        System.out.println("students:  " + students);
        //第二种方式,使用TypeReference<T>类,由于其构造方法使用protected进行修饰,故创建其子类
        List<Student> studentList = JSONArray.parseObject(JSON_ARRAY_STR, new TypeReference<ArrayList<Student>>() {});
        System.out.println("studentList:  " + studentList);
        //第三种方式,使用Gson的思想
        List<Student> studentList1 = JSONArray.parseArray(JSON_ARRAY_STR, Student.class);
        System.out.println("studentList1:  " + studentList1);
    }


    /**
     * JavaBean_List到json字符串-数组类型的转换
     */
    @RequestMapping("testJavaBeanListToJSONStr")
    @ResponseBody
    public void testJavaBeanListToJSONStr() {
        Student student = new Student("1000","lily", "m");;
        Student studenttwo = new Student("1001","tom", "m");;
        List<Student> students = new ArrayList<Student>();
        students.add(student);
        students.add(studenttwo);
        String jsonString = JSONArray.toJSONString(students);
        System.out.println(jsonString);
    }


    /**
     * 复杂json格式字符串到JavaBean_obj的转换
     */
    @RequestMapping("testComplexJSONStrToJavaBean")
    @ResponseBody
    public void testComplexJSONStrToJavaBean(){

        //第一种方式,使用TypeReference<T>类,由于其构造方法使用protected进行修饰,故创建其子类
        Teacher teacher = JSONObject.parseObject(COMPLEX_JSON_STR, new TypeReference<Teacher>() {});
        System.out.println(teacher);

        //第二种方式,使用Gson思想
        Teacher teacher1 = JSONObject.parseObject(COMPLEX_JSON_STR, Teacher.class);
        System.out.println(teacher1);
    }

    /**
     * 复杂JavaBean_obj到json格式字符串的转换
     */
    @RequestMapping("testJavaBeanToComplexJSONStr")
    @ResponseBody
    public void testJavaBeanToComplexJSONStr(){

        //已知复杂JavaBean_obj
        Teacher teacher = JSONObject.parseObject(COMPLEX_JSON_STR, new TypeReference<Teacher>() {});
        String jsonString = JSONObject.toJSONString(teacher);
        System.out.println(jsonString);
    }


    /**
     * 简单JavaBean_obj到json对象的转换
     */
    @RequestMapping("testJavaBeanToJSONObject")
    @ResponseBody
    public void testJavaBeanToJSONObject(){

        //已知简单JavaBean_obj
        Student student = new Student("lily", "12","m");

        //方式一
        String jsonString = JSONObject.toJSONString(student);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        System.out.println(jsonObject);

        //方式二
        JSONObject jsonObject1 = (JSONObject) JSONObject.toJSON(student);
        System.out.println(jsonObject1);
    }

    /**
     * 简单json对象到JavaBean_obj的转换
     */
    @RequestMapping("testJSONObjectToJavaBean")
    @ResponseBody
    public void testJSONObjectToJavaBean(){

        //已知简单json对象
        JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);

        //第一种方式,使用TypeReference<T>类,由于其构造方法使用protected进行修饰,故创建其子类
        Student student = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<Student>() {});
        System.out.println(student);

        //第二种方式,使用Gson的思想
        Student student1 = JSONObject.parseObject(jsonObject.toJSONString(), Student.class);
        System.out.println(student1);
    }


    /**
     * JavaList到JsonArray的转换
     */
    @RequestMapping("testJavaListToJsonArray")
    @ResponseBody
    public void testJavaListToJsonArray() {

        //已知JavaList
        Student student = new Student("1003","lily", "m");;
        Student studenttwo = new Student("1004","lucy", "m");;

        List<Student> students = new ArrayList<Student>();
        students.add(student);
        students.add(studenttwo);

        //方式一
        String jsonString = JSONArray.toJSONString(students);
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
        System.out.println(jsonArray);

        //方式二
        JSONArray jsonArray1 = (JSONArray) JSONArray.toJSON(students);
        System.out.println(jsonArray1);
    }

    /**
     * JsonArray到JavaList的转换
     */
    @RequestMapping("testJsonArrayToJavaList")
    @ResponseBody
    public void testJsonArrayToJavaList() {

        //已知JsonArray
        JSONArray jsonArray = JSONArray.parseArray(JSON_ARRAY_STR);

        //第一种方式,使用TypeReference<T>类,由于其构造方法使用protected进行修饰,故创建其子类
        ArrayList<Student> students = JSONArray.parseObject(jsonArray.toJSONString(),
                new TypeReference<ArrayList<Student>>() {});

        System.out.println(students);
        //第二种方式,使用Gson的思想
        List<Student> students1 = JSONArray.parseArray(jsonArray.toJSONString(), Student.class);
        System.out.println(students1);
    }



    /**
     * 复杂JavaBean_obj到json对象的转换
     */
    @RequestMapping("testComplexJavaBeanToJSONObject")
    @ResponseBody
    public void testComplexJavaBeanToJSONObject() {
        //已知复杂JavaBean_obj
        Student student = new Student("lily", "12","m");
        Student studenttwo = new Student("lucy", "15","w");

        List<Student> students = new ArrayList<Student>();
        students.add(student);
        students.add(studenttwo);
        Course course = new Course("english", 1270);

        Teacher teacher = new Teacher("crystall", 27, course, students);

        //方式一
        String jsonString = JSONObject.toJSONString(teacher);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        System.out.println(jsonObject);

        //方式二
        JSONObject jsonObject1 = (JSONObject) JSONObject.toJSON(teacher);
        System.out.println(jsonObject1);
    }

    /**
     * 复杂json对象到JavaBean_obj的转换
     */
    @RequestMapping("testComplexJSONObjectToJavaBean")
    @ResponseBody
    public void testComplexJSONObjectToJavaBean() {
        //已知复杂json对象
        JSONObject jsonObject = JSONObject.parseObject(COMPLEX_JSON_STR);

        //第一种方式,使用TypeReference<T>类,由于其构造方法使用protected进行修饰,故创建其子类
        Teacher teacher = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<Teacher>() {});
        System.out.println(teacher);
        //第二种方式,使用Gson的思想
        Teacher teacher1 = JSONObject.parseObject(jsonObject.toJSONString(), Teacher.class);
        System.out.println(teacher1);
    }


}
