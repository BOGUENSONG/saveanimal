package com.example.sbgksc.saveanimal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class baseRestController {

    @RequestMapping(value="/sido" , method= RequestMethod.POST)
    public String sidoList(@RequestParam("sido")String sido){

        if(sido == "대전광역시")
        {
            return "유성구";
        }

        return "구가 존재하지 않습니다";
    }
}
