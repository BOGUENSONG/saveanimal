package com.example.sbgksc.saveanimal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

@RestController
public class baseRestController {
    static String key = "zxOaIwbPBgRjuyXZ68rmrBZInTfArAVYIP0FYxy7QRU%2BnqxZYWscmRnpvaNUmy7nrILp8XPZ9StvfD67MhiIxg%3D%3D";

    @RequestMapping(value="/sigungu" , method= RequestMethod.GET)
    public Map<String, String> sigunguList(@RequestParam(value="sido", required=false)String sido) throws ParserConfigurationException, IOException, SAXException { //시군구 해시맵 리턴
        System.out.println("시도 : " + sido);
        Map<String, String> sigungu = new HashMap<>();
        String url = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/sigungu";
        url = url + "?upr_cd=" + sido;//시도 코드삽입
        url = url + "&ServiceKey=" + key; //api키값 삽입
        DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
        Document doc = dBuilder.parse(url);
        doc.getDocumentElement().normalize();
        NodeList listnm = doc.getElementsByTagName("orgdownNm"); //시군구 이름
        NodeList listcd = doc.getElementsByTagName("orgCd"); //시군구 코드
        for (int i = 0; i < listnm.getLength(); i++) {
            Node nodenm = listnm.item(i);
            Node tempnm = nodenm.getFirstChild();
            String valuenm = tempnm.getNodeValue();
            Node nodecd = listcd.item(i);
            Node tempcd = nodecd.getFirstChild();
            String valuecd = tempcd.getNodeValue();
            sigungu.put(valuecd, valuenm);
        }
        return sigungu;
    }

    @RequestMapping(value="/bohoso" , method= RequestMethod.GET)
    public Map<String, String> bohosoList(@RequestParam("sido")String sido, @RequestParam("sigungu")String sigungu) throws ParserConfigurationException, IOException, SAXException { //보호소 해시맵 리턴
        Map<String, String> bohoso = new HashMap<>();
        String url = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/shelter";
        url = url + "?upr_cd=" + sido;//시도 코드삽입
        url = url + "&org_cd=" + sigungu;//시군구 코드삽입
        url = url + "&ServiceKey=" + key; //api키값 삽입
        DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
        Document doc = dBuilder.parse(url);
        doc.getDocumentElement().normalize();
        NodeList listnm = doc.getElementsByTagName("careNm"); //보호소 이름
        NodeList listcd = doc.getElementsByTagName("careRegNo"); //보호소 코드
        for (int i = 0; i < listnm.getLength(); i++) {
            Node nodenm = listnm.item(i);
            Node tempnm = nodenm.getFirstChild();
            String valuenm = tempnm.getNodeValue();
            Node nodecd = listcd.item(i);
            Node tempcd = nodecd.getFirstChild();
            String valuecd = tempcd.getNodeValue();
            bohoso.put(valuecd, valuenm);
        }
        return bohoso;
    }

}
