package com.example.sbgksc.saveanimal.service;

import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;


public interface ApiToListService {

    public Map<String, String> sido() throws ParserConfigurationException, IOException, SAXException;

}
