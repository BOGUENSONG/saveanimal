package com.example.sbgksc.saveanimal.controller;

import org.springframework.ui.Model;
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
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

//    축종코드 리스트
//   - 개 : 417000
// - 고양이 : 422400
// - 기타 : 429900
    @RequestMapping(value="/listReturn" , method= RequestMethod.GET)
    public ArrayList listReturn(@RequestParam("sigungu")String sigungu, @RequestParam("bohoso")String bohoso, @RequestParam("upkind")String upkind, @RequestParam("bgnde")String bgnde, @RequestParam("endde")String endde) throws ParserConfigurationException, IOException, SAXException { //보호소 해시맵 리턴
        ArrayList list = new ArrayList();
        String url = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic";
        url = url + "?org_cd=" + sigungu;//시군구 코드삽입
        url = url + "&care_reg_no=" + bohoso;//보호소 코드삽입 ("전체"라는 보호소값 생성바람, "전체"의 value는 ""로 설정)
        url = url + "&numOfRows=" + "1000";
        url = url + "&upkind=" + upkind; //축종코드 삽입
        url = url + "&state=" + "notice"; //상태코드 삽입 notice = 공고중
        url = url + "&bgnde=" + bgnde; //검색시작날짜 삽입
        url = url + "&endde=" + endde; //검색종료날짜 삽입
        url = url + "&ServiceKey=" + key; //api키값 삽입
        DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
        Document doc = dBuilder.parse(url);
        doc.getDocumentElement().normalize();
        NodeList listdn = doc.getElementsByTagName("desertionNo"); //유기번호
        NodeList listfn = doc.getElementsByTagName("filename"); //썸네일 이미지
        NodeList listhd = doc.getElementsByTagName("happenDt"); //접수일
        NodeList listhp = doc.getElementsByTagName("happenPlace"); //발견 장소
        NodeList listkc = doc.getElementsByTagName("kindCd"); //품종
        NodeList listag = doc.getElementsByTagName("age"); //나이
        NodeList listwg = doc.getElementsByTagName("weight"); //무게
        NodeList listnn = doc.getElementsByTagName("noticeNo"); //공고번호
        NodeList listcc = doc.getElementsByTagName("colorCd"); //색상
        NodeList listns = doc.getElementsByTagName("noticeSdt"); //공고시작일
        NodeList listne = doc.getElementsByTagName("noticeEdt"); //공고종료일
        NodeList listpf = doc.getElementsByTagName("popfile"); //이미지파일
        NodeList listps = doc.getElementsByTagName("processState"); //상태
        NodeList listsc = doc.getElementsByTagName("sexCd"); //성별
        NodeList listsm = doc.getElementsByTagName("specialMark"); //특징
        NodeList listcn = doc.getElementsByTagName("careNm"); //보호소이름
        NodeList listct = doc.getElementsByTagName("careTel"); //보호소 전화번호
        NodeList listca = doc.getElementsByTagName("careAddr"); //보호장소
        NodeList liston = doc.getElementsByTagName("orgNm"); //관할기관
        NodeList listch = doc.getElementsByTagName("chargeNm"); //담당자이름
        NodeList listot = doc.getElementsByTagName("officetel"); //담당자 연락처
        for (int i = 0; i < listdn.getLength(); i++) {
            Map<String, String> each = new HashMap<>();
            Node node = listdn.item(i);
            Node temp= node.getFirstChild();
            String value = temp.getNodeValue();
            each.put("desertionNo", value); //유기번호 삽입
            node = listfn.item(i);
            temp= node.getFirstChild();
            value = temp.getNodeValue();
            each.put("filename", value); //썸네일 이미지 삽입
            node = listhd.item(i);
            temp= node.getFirstChild();
            value = temp.getNodeValue();
            each.put("happenDt", value); //접수일 삽입
            node = listhp.item(i);
            temp= node.getFirstChild();
            value = temp.getNodeValue();
            each.put("happenPlace", value); //발견장소 삽입
            node = listkc.item(i);
            temp= node.getFirstChild();
            value = temp.getNodeValue();
            each.put("kindCd", value); //품종 삽입
            node = listag.item(i);
            temp= node.getFirstChild();
            value = temp.getNodeValue();
            each.put("age", value); //나이 삽입
            node = listwg.item(i);
            temp= node.getFirstChild();
            value = temp.getNodeValue();
            each.put("weight", value); //무게 삽입
            node = listnn.item(i);
            temp= node.getFirstChild();
            value = temp.getNodeValue();
            each.put("noticeNo", value); //공고번호 삽입
            node = listcc.item(i);
            temp= node.getFirstChild();
            value = temp.getNodeValue();
            each.put("colorCd", value); //색상 삽입
            node = listns.item(i);
            temp= node.getFirstChild();
            value = temp.getNodeValue();
            each.put("noticeSdt", value); //공고시작일 삽입
            node = listne.item(i);
            temp= node.getFirstChild();
            value = temp.getNodeValue();
            each.put("noticeEdt", value); //공고종료일 삽입
            node = listpf.item(i);
            temp= node.getFirstChild();
            value = temp.getNodeValue();
            each.put("popfile", value); //이미지파일 삽입
            node = listps.item(i);
            temp= node.getFirstChild();
            value = temp.getNodeValue();
            each.put("processState", value); //상태 삽입
            node = listsc.item(i);
            temp= node.getFirstChild();
            value = temp.getNodeValue();
            each.put("sexCd", value); //성별 삽입
            node = listsm.item(i);
            temp= node.getFirstChild();
            value = temp.getNodeValue();
            each.put("specialMark", value); //특징 삽입
            node = listcn.item(i);
            temp= node.getFirstChild();
            value = temp.getNodeValue();
            each.put("careNm", value); //보호소이름 삽입
            node = listct.item(i);
            temp= node.getFirstChild();
            value = temp.getNodeValue();
            each.put("careTel", value); //보호서전화번호 삽입
            node = listca.item(i);
            temp= node.getFirstChild();
            value = temp.getNodeValue();
            each.put("careAddr", value); //보호장소 삽입
            node = liston.item(i);
            temp= node.getFirstChild();
            value = temp.getNodeValue();
            each.put("orgNm", value); //관할기관 삽입
            node = listch.item(i);
            temp= node.getFirstChild();
            value = temp.getNodeValue();
            each.put("chargeNm", value); //담당자이름 삽입
            node = listot.item(i);
            temp= node.getFirstChild();
            value = temp.getNodeValue();
            each.put("officetel", value); //담당자연락처 삽입
            list.add(each); //정보값 입력한 정보값리스트 삽입
        }
        return list;
    }


}
