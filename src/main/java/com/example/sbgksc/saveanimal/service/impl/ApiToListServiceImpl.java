package com.example.sbgksc.saveanimal.service.impl;

import com.example.sbgksc.saveanimal.service.ApiToListService;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class ApiToListServiceImpl implements ApiToListService {
    static String key = "zxOaIwbPBgRjuyXZ68rmrBZInTfArAVYIP0FYxy7QRU%2BnqxZYWscmRnpvaNUmy7nrILp8XPZ9StvfD67MhiIxg%3D%3D";
    @Override
    public ArrayList sido() {
        ArrayList sido = new ArrayList();
        try {
            String url = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/sido?ServiceKey=";
            url = url + key; //api키값 삽입
            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
            Document doc = dBuilder.parse(url);
            doc.getDocumentElement().normalize();
            NodeList listnm = doc.getElementsByTagName("orgdownNm"); //시도 이름
            NodeList listcd = doc.getElementsByTagName("orgCd"); //시도 코드
            for (int i = 0; i < listnm.getLength(); i++) {
                ArrayList sidodata = new ArrayList();
                Node nodenm = listnm.item(i);
                Node tempnm = nodenm.getFirstChild();
                String valuenm = tempnm.getNodeValue();
                Node nodecd = listcd.item(i);
                Node tempcd = nodecd.getFirstChild();
                String valuecd = tempcd.getNodeValue();
                sidodata.add(valuenm); //시도 이름 삽입
                sidodata.add(valuecd); //시도 코드 삽입
                String ourl = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/sigungu";
                ourl = ourl + "?upr_cd=" + valuecd;//시도 코드삽입
                ourl = ourl + "&ServiceKey=" + key; //api키값 삽입
                dBuilder.reset();
                Document odoc = dBuilder.parse(ourl);
                odoc.getDocumentElement().normalize();
                NodeList olistnm = odoc.getElementsByTagName("orgdownNm"); //시도 이름
                NodeList olistcd = odoc.getElementsByTagName("orgCd"); //시도 코드
                ArrayList sigungu = new ArrayList();
                for (int j = 0; j < olistnm.getLength(); j++) {
                    ArrayList sigungudata = new ArrayList();
                    Node onodenm = olistnm.item(j);
                    Node otempnm = onodenm.getFirstChild();
                    String ovaluenm = otempnm.getNodeValue();
                    Node onodecd = olistcd.item(j);
                    Node otempcd = onodecd.getFirstChild();
                    String ovaluecd = otempcd.getNodeValue();
                    sigungudata.add(ovaluenm); //시군구 이름 삽입
                    sigungudata.add(ovaluecd); //시군구 코드 삽입
                    String oourl = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/shelter";
                    oourl = oourl + "?upr_cd=" + valuecd;//시도 코드삽입
                    oourl = oourl + "&org_cd=" + ovaluecd;//시군구 코드삽입
                    oourl = oourl + "&ServiceKey=" + key; //api키값 삽입
                    dBuilder.reset();
                    Document oodoc = dBuilder.parse(oourl);
                    oodoc.getDocumentElement().normalize();
                    NodeList oolistnm = oodoc.getElementsByTagName("careNm"); //보호소명
                    NodeList oolistcd = oodoc.getElementsByTagName("carRegNo"); //보호소코드
                    ArrayList bohoso = new ArrayList();
                    for (int k = 0; k < oolistnm.getLength(); k++) {
                        ArrayList bohosodata = new ArrayList();
                        Node oonodenm = oolistnm.item(k);
                        Node ootempnm = oonodenm.getFirstChild();
                        String oovaluenm = ootempnm.getNodeValue();
                        Node oonodecd = oolistcd.item(k);
                        Node ootempcd = oonodecd.getFirstChild();
                        String oovaluecd = ootempcd.getNodeValue();
                        bohosodata.add(oovaluenm); //보호소명 삽입
                        bohosodata.add(oovaluecd); //보호소코드 삽입
                        bohoso.add(bohosodata);
                    }
                    sigungu.add(bohoso);
                }
                sidodata.add(sigungu);
                sido.add(sidodata);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sido;
    }
}
