package com.example.sbgksc.saveanimal.controller;

import com.example.sbgksc.saveanimal.service.ApiToListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.SAXException;

import javax.annotation.Resource;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class baseController {

    @Resource(name="ApiToListService")
    private ApiToListService apiToListService;

    //주소창에 localhost:8080 치면, index.jsp를 매핑해준다.
    @RequestMapping(value = "/")
    public String initIndex()  {
        return "index";
    }

    //    축종코드 리스트
//   - 개 : 417000
// - 고양이 : 422400
// - 기타 : 429900


    @RequestMapping(value = "/dogs")
    public String dogindex(Model model ) throws IOException, SAXException, ParserConfigurationException {

        Map<String,String> sido = apiToListService.sido();
        model.addAttribute("sido",sido);
        model.addAttribute("petkind","417000");
        model.addAttribute("pet","dog");
        return "animalList";
    }
    @RequestMapping(value ="/cats")
    public String catindex(Model model ) throws IOException, SAXException, ParserConfigurationException {

        Map<String,String> sido = apiToListService.sido();
        model.addAttribute("sido",sido);
        model.addAttribute("petkind","422400");
        model.addAttribute("pet","cat");
        return "animalList";
    }
    @RequestMapping(value ="/others")
    public String otherindex(Model model ) throws IOException, SAXException, ParserConfigurationException {

        Map<String,String> sido = apiToListService.sido();
        model.addAttribute("sido",sido);
        model.addAttribute("petkind","429900");
        model.addAttribute("pet","fish");
        return "animalList";
    }



}