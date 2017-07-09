package com.koali.web;

import com.koali.model.NewStudent;
import com.koali.model.sorceResult;
import com.koali.service.NewStudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elric on 2017/7/6.
 */
@Controller
public class EchartsController {
    @Autowired
    private NewStudentInfoService newStudentInfoService;

    @RequestMapping(value = "/hello")
    @ResponseBody
    public List<NewStudent> page() {
        List<NewStudent> newStudents = newStudentInfoService.getAllStudent();
        System.out.println(newStudents.size());
        return newStudents;
    }

    @RequestMapping(value = "/getScore")
    @ResponseBody
    public List<Integer> getScore() {
        List<NewStudent> newStudents = newStudentInfoService.getAllStudent();
        List<Integer> scores = null;
        for (NewStudent newStudent : newStudents) {
            scores.add(newStudent.getScore());
        }
        return scores;
    }

//    @RequestMapping(value = "/getEcharts")
//    @ResponseBody
//    public String getEcharts(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//
//        try {
//            httpServletResponse.setContentType("text/html;charset=utf-8");
//            PrintWriter printWriter = httpServletResponse.getWriter();
//            JSONArray jsonArray = new JSONArray();
//            List<NewStudent> students = newStudentInfoService.getAllStudent();
//            for (NewStudent student : students) {
//                System.out.println(student);
//                jsonArray.put(student);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//       }
//       return "Test";
//    }

    @RequestMapping(value = "/myeCharts")
    public String myeCharts() {
        return "Hello";
    }

    @RequestMapping(value = "/getPieCharts")
    public String getPieCharts() {
        return "PieCharts";
    }

    @RequestMapping(value = "/getData")
    @ResponseBody
    public List<sorceResult> getData() {
        List<NewStudent> students = newStudentInfoService.getAllStudent();
        List<sorceResult> results = new ArrayList<sorceResult>();
        for (NewStudent newStudent : students) {
            //把学生的成绩和姓名封装到一个result
            sorceResult result = new sorceResult(newStudent.getScore(), newStudent.getName());
            results.add(result);
        }
        return results;
    }
}
