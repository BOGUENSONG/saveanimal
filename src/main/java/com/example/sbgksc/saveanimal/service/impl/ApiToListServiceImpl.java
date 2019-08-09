package com.example.sbgksc.saveanimal.service.impl;

import com.example.sbgksc.saveanimal.service.ApiToListService;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service("ApiToListService")
public class ApiToListServiceImpl implements ApiToListService {
    static String key = "zxOaIwbPBgRjuyXZ68rmrBZInTfArAVYIP0FYxy7QRU%2BnqxZYWscmRnpvaNUmy7nrILp8XPZ9StvfD67MhiIxg%3D%3D";
    @Override
    public Map<String, String> sido() throws ParserConfigurationException, IOException, SAXException { //시도 해시맵 리턴
        Map<String, String> sido = new HashMap<>();
        String url = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/sido?numOfRows=20&ServiceKey=";
        url = url + key; //api키값 삽입
        DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
        Document doc = dBuilder.parse(url);
        doc.getDocumentElement().normalize();
        NodeList listnm = doc.getElementsByTagName("orgdownNm"); //시도 이름
        NodeList listcd = doc.getElementsByTagName("orgCd"); //시도 코드
        for (int i = 0; i < listnm.getLength(); i++) {
            Node nodenm = listnm.item(i);
            Node tempnm = nodenm.getFirstChild();
            String valuenm = tempnm.getNodeValue();
            Node nodecd = listcd.item(i);
            Node tempcd = nodecd.getFirstChild();
            String valuecd = tempcd.getNodeValue();
            sido.put(valuecd, valuenm);
        }
        return sido;
    }
}
