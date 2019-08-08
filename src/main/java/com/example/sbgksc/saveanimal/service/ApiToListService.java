package com.example.sbgksc.saveanimal.service;

import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;


public interface ApiToListService {

    public ArrayList sido() throws ParserConfigurationException, IOException, SAXException;
    // 시도 리스트
    // List[0][0] = 서울
    // List[0][1] = 0123
    // List[1][0] = 대전


}
