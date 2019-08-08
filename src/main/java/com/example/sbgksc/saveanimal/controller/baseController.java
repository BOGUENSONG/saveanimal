package com.example.sbgksc.saveanimal.controller;

import com.example.sbgksc.saveanimal.service.ApiToListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class baseController {


    private ApiToListService apiToListService;

    //주소창에 localhost:8080 치면, index.jsp를 매핑해준다.
    @RequestMapping(value = "/")
    public String initIndex()  {


        return "index";
    }
    @RequestMapping(value = "/dogs")
    public String dogindex(Model model ) {

//        ArrayList sido = apiToListService.sido();
//      model.addAttribute("sido",sido);
        return "animalList";
    }


}
