package egovframework.golf.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.breeze.member.service.MemberVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.golf.service.EventService;
import egovframework.golf.service.EventVO;

@Controller
@RequestMapping("/_event")
public class EventActController {

    @Resource(name = "eventService")
    private EventService eventService;
    
    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;

    @Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
    
    private static final String MOBILE_PATTERN = "^(\\d{3})-?(\\d{3,4})-?(\\d{4})$";
    
    /**
     * 관리자 > 예약/일정 관리 > 사용자 청렴 교육 조회
     * @param eventVO
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/integrityApplyList.do")
    public @ResponseBody Map<String, Object> integrityApplyList(@ModelAttribute("searchVO") EventVO eventVO, HttpServletRequest request, ModelMap model) throws Exception {
    	//신청자 목록 조회
		List<EventVO> applyList = eventService.applyList(eventVO);
		
		for (EventVO event : applyList) {
			if(event.getApplicant() != null && !event.getApplicant().equals("")) {
				event.setApplicant(maskingName(event.getApplicant())); 
			}
			if(event.getPhone() != null && !event.getPhone().equals("")) {
				event.setPhone(maskingPhone(event.getPhone())); 
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applyList", applyList);
		
		return map;
	
    }
    
    /**
     * 관리자 > 예약/일정 관리 > 시간별 리스트 조회
     * @param eventVO
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/eventTimeList.do")
    public @ResponseBody Map<String, Object> explanationTimeList(@ModelAttribute("searchVO") EventVO eventVO, HttpServletRequest request, ModelMap model) throws Exception {
    	//단체 해설 시간 리스트 조회
		List<EventVO> timeList = eventService.userEventTimeDayList(eventVO);
		
		for (EventVO event : timeList) {
			if(event.getApplicant() != null && !event.getApplicant().equals("")) {
				event.setApplicant(maskingName(event.getApplicant())); 
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("timeList", timeList);
		
		return map;
	
    }
    
    /**
     * 관리자 > 예약/일정 관리 > 토요의병놀이마당 시간리스트 조회
     * @param eventVO
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/gameEventTimeDayList.do")
    public @ResponseBody Map<String, Object> gameEventTimeDayList(@ModelAttribute("searchVO") EventVO eventVO, HttpServletRequest request, ModelMap model) throws Exception {
    	//단체 해설 시간 리스트 조회
		List<EventVO> timeList = eventService.gameEventTimeDayList(eventVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("timeList", timeList);
		
		return map;
	
    }
    
    /**
     * 관리자 > 예약/일정 관리 > 단체 해설 신청
     * @param eventVO
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/eventApply.do")
    public String explanationApply(@ModelAttribute("searchVO") EventVO eventVO, HttpServletRequest request, ModelMap model) throws Exception {

	    MemberVO user = (MemberVO) request.getSession().getAttribute("loginVO");
	    if(user == null){
        	model.addAttribute("message", "로그인 후 이용 가능합니다.");
    		model.addAttribute("retType", ":null_submit");
    		model.addAttribute("retUrl", "/login");
    		return "/common/message";
	    }else {
	    	String returnUrl = request.getParameter("returnUrl") == null ? "/" : request.getParameter("returnUrl");
	    	
	    	// eventDate, eventTime 조회
	    	EventVO event = eventService.selectEventVO(eventVO);
	    	if(event == null) {
	    		model.addAttribute("message", "예약신청 오류입니다. 다시 시도해주시기 바랍니다.");
	    		model.addAttribute("retType", ":submit");
	    		model.addAttribute("retUrl", returnUrl);
	    		return "/common/message";
	    	}else {
	    		
	    		eventVO.setEventDate(event.getEventDate());
	        	eventVO.setEventTime(event.getEventTime());
	        	
	    		// 접수인원 초과 확인(1타임당 200명)
	    		int cnt = eventService.selectApplyCnt(eventVO);
	    		cnt += Integer.parseInt(eventVO.getAdultNum());
	    		System.out.println("기존+신청인원:::::::::::::::::::::::::::::::::::"+cnt);
	    		if(cnt > 200) {
	    			model.addAttribute("message", "정원이 초과하여 신청 할 수 없습니다.");
	        		model.addAttribute("retType", ":submit");
	        		model.addAttribute("retUrl", returnUrl);
	        		return "/common/message";
	    		}
	        	
	    		// 신청자 중복 신청 체크
	    		eventVO.setRegId(user.getMemberId());
	    		int dayCnt = eventService.selectApplyCnt(eventVO);
	    		System.out.println("중복신청cnt:::::::::::::::::::::::::::::::::::"+dayCnt);
	    		if(dayCnt > 0) {
	    			model.addAttribute("message", "해당 신청일에 중복하여 신청 할 수 없습니다.");
	        		model.addAttribute("retType", ":submit");
	        		model.addAttribute("retUrl", returnUrl);
	        		return "/common/message";
	    		}
	    		
	        	// 등록
	        	eventService.eventApply(eventVO);
	        	// 문자 전송
	        	eventVO.setSmsTitle("사용자 예약 신청");
	        	eventVO.setSmsMsg(eventVO.getApplicant()+"님 예약신청이 완료되었습니다. 담당자가 승인하면 최종 예약 완료됩니다.");
	        	eventVO.setDestInfo(eventVO.getApplicant()+"^"+eventVO.getPhone().replace("-", "")+"|관리자^01090573130");
	        	eventService.sendSms(eventVO);
	    		
	        	model.addAttribute("message", "신청이 완료되었습니다.");
	    		model.addAttribute("retType", ":submit");
	    		model.addAttribute("retUrl", returnUrl);
	    		return "/common/message";
	    	}
	    }
    }
    
    
    /**
     * 이름 마스킹 처리
     * @param str
     * @return
     */
    public static String maskingName(String str) {
		String replaceString = str;

		String pattern = "";
		if(str.length() == 2) {
			pattern = "^(.)(.+)$";
		} else {
			pattern = "^(.)(.+)(.)$";
		}

		Matcher matcher = Pattern.compile(pattern).matcher(str);

		if(matcher.matches()) {
			replaceString = "";

			for(int i=1;i<=matcher.groupCount();i++) {
				String replaceTarget = matcher.group(i);
				if(i == 2) {
					char[] c = new char[replaceTarget.length()];
					Arrays.fill(c, '*');

					replaceString = replaceString + String.valueOf(c);
				} else {
					replaceString = replaceString + replaceTarget;
				}

			}
		}
		return replaceString;
	}
    
    
    /**
     * 전화번호 마스킹
     * @param str
     * @return
     */
    public String maskingPhone(String mobile) {
        String replaceString = mobile;

        Matcher matcher = Pattern.compile(MOBILE_PATTERN).matcher(mobile);

        if(matcher.matches()) {
            replaceString = "";

            boolean isHyphen = false;
            if(mobile.indexOf("-") > -1) {
                isHyphen = true;
            }

            for(int i=1;i<=matcher.groupCount();i++) {
                String replaceTarget = matcher.group(i);
                if(i == 2) {
                    char[] c = new char[replaceTarget.length()];
                    Arrays.fill(c, '*');

                    replaceString = replaceString + String.valueOf(c);
                } else {
                    replaceString = replaceString + replaceTarget;
                }

                if(isHyphen && i < matcher.groupCount()) {
                    replaceString = replaceString + "-";
                }
            }
        }
        return replaceString;
    }
    
    /**
     * 일정 안내 달력
     * @param boardVO
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/getScheduleList.do")
    public @ResponseBody Map<String, Object> getScheduleList(@ModelAttribute("searchVO") EventVO eventVO, HttpServletRequest request, ModelMap model) throws Exception {
    	
    	// 오늘 날짜
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.KOREA);
	 	Date currentTime = new Date();
	 	String mTime = mSimpleDateFormat.format(currentTime);
	 	
    	String date = (request.getParameter("date")==null || request.getParameter("date").equals(""))?mTime:request.getParameter("date");
    	
    	eventVO.setDate(date);
    	eventVO.setFirstIndex(0);
		eventVO.setRecordCountPerPage(9999);
		
    	Map<String, Object> map = eventService.selectScheduleList(eventVO);
		
		return map;
    }

    /**
     * 사용자 > 신청현황 > 취소
     * @param eventVO
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/statusCancel.do")
    public String statusCancel(EventVO eventVO, HttpServletRequest request, ModelMap model) throws Exception {

	    MemberVO user = (MemberVO) request.getSession().getAttribute("loginVO");
	    if(user == null){
        	model.addAttribute("message", "로그인 후 이용 가능합니다.");
    		model.addAttribute("retType", ":null_submit");
    		model.addAttribute("retUrl", "/login");
    		return "/common/message";
	    }else {
			// 수정자 ID 저장
		    eventVO.setRegId(user.getMemberId());
		    eventVO.setUpdId(user.getMemberId());
		    
		    // 업데이트
		    eventService.statusUpdate(eventVO);
        	// 문자 전송
		    eventVO = eventService.selectApplyDetail(eventVO);
        	eventVO.setSmsTitle("사용자 예약 취소");
        	eventVO.setSmsMsg(eventVO.getApplicant()+"님 예약이 정상적으로 취소되었습니다.");
        	eventVO.setDestInfo(eventVO.getApplicant()+"^"+eventVO.getPhone().replace("-", "")+"|관리자^01090573130");
        	eventService.sendSms(eventVO);

			model.addAttribute("message", "취소가 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/parkgolf/confirm");

			return "/common/message";
		}
	}

	@RequestMapping("/getWeather.do")
	public void getWeather(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Calendar cal = Calendar.getInstance();
		int yyyy = cal.get(Calendar.YEAR);
		int mm = cal.get(Calendar.MONTH) + 1;
		int dd = cal.get(Calendar.DAY_OF_MONTH);
		int hours = cal.get(Calendar.HOUR_OF_DAY);
		int minutes  = 0;
		
		//String apikey = "qXJzkdIYO4cQQk06COfzHcT0F0vh10a%2BYStd47h%2FT%2FIpoSQ3KJxqZVjmRw7uxvBwp2yT18aEUczQRUALXBqy%2FQ%3D%3D";	// 개발
		String apikey = "7LRqfTL6lN%2BwD%2BLwVQd53W3y5nPfjKOfUTc3RAtiHPLHKDRMKzKshG0MCbqtp9hKo6HSuW1BVQv%2Bgn%2FbmtV77w%3D%3D";	// 운영
		
		if(hours < 2){
			dd--;
			hours = 23;
		}else if(hours < 5){ hours = 2;
		}else if(hours < 8){ hours = 5;
		}else if(hours < 11){ hours = 8;
		}else if(hours < 14){ hours = 11;
		}else if(hours < 17){ hours = 14;
		}else if(hours < 20){ hours = 17;
		}else if(hours < 23){ hours = 20;
		}

		String year = String.valueOf(yyyy);
		String month = String.valueOf(mm);
		String day = String.valueOf(dd);
		String hour = String.valueOf(hours);
		String minute = String.valueOf(minutes);
		if(hours<10) {
	        hour='0'+hour;
	    }
	    if(mm<10) {
	        month='0'+month;
	    }
	    if(dd<10) {
	        day='0'+day;
	    }
	    if(minutes <10){
	    	minute='0'+minute;
	    }
		String baseDate = year+month+day;
		String baseTime = hour+minute;

		String urlStr = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey="+apikey+"&numOfRows=10&pageNo=1&base_date="+year+month+day+"&base_time="+hour+minute+"&nx=72&ny=139";
		System.out.println("urlStr############################"+urlStr);
		
		URL url = new URL(urlStr);
		 
		URLConnection connection = url.openConnection();
		 
		connection.setDoOutput(true);
		 
		// 타입 설정
		connection.setRequestProperty("CONTENT-TYPE","text/xml"); 
		 
		 
		//openStream() : URL페이지 정보를 읽어온다.
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
		    
		String inputLine;
		String weatherBuffer = "";
		 
		// 페이지의 정보를 저장한다.
		while ((inputLine = in.readLine()) != null){
			weatherBuffer += inputLine.trim();
		}
		
		//기상 정보를 weatherBuffer에 저장
		in.close();
		
		response.setContentType("text/json;charset=utf-8");
		PrintWriter pr = response.getWriter();
		pr.write(weatherBuffer);
		pr.flush();
		pr.close();
	}
}
