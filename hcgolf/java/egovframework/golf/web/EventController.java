package egovframework.golf.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import egovframework.breeze.admin.service.AdminVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.golf.service.EventService;
import egovframework.golf.service.EventVO;

@Controller
@RequestMapping("/_admin/event")
public class EventController {

    @Resource(name = "eventService")
    private EventService eventService;

    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;

    @Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;

    /**
     * 관리자 > 예약/일정 관리 > 행사 달력 list
     * @param eventVO
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/eventList.do")
    public String eventList(@ModelAttribute("searchVO") EventVO eventVO, HttpServletRequest request, ModelMap model) throws Exception {

    	AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else{
			String eventFlag = eventVO.getEventFlag() == null ? "event01" : eventVO.getEventFlag();
			eventVO.setEventFlag(eventFlag);
			//전체 일정 조회
			List<EventVO> eventList = eventService.eventList(eventVO);
			model.addAttribute("resultList", eventList);
			model.addAttribute("eventVO", eventVO);

			return "/admin/golf/eventList";
		}
    }
	
	/**
	 * 관리자 > 예약/일정 관리 > 일정안내 view
	 * @param eventVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/eventView.do")
	public String eventView(@ModelAttribute("searchVO") EventVO eventVO, HttpServletRequest request, ModelMap model) throws Exception {

		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		} else {
			eventVO = eventService.selectEventDetail(eventVO);
			model.addAttribute("eventVO", eventVO);

			//신청자 목록 조회
			List<EventVO> applyList = eventService.applyList(eventVO);
			model.addAttribute("resultList", applyList);
			
			
			return "/admin/golf/eventView";
		}
	}

	/**
	 * 관리자 > 예약/일정 관리 > 행사 달력 form
	 * @param eventVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/eventForm.do")
	public String eventForm(@ModelAttribute("searchVO") EventVO eventVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}
		else {
			String command = eventVO.getCommand() == null ? "insert" : eventVO.getCommand();
			if(command.equals("update")) {
				eventVO = eventService.selectEventDetail(eventVO);
				model.addAttribute("selectVO", eventVO);
			}

			return "/admin/golf/eventForm";
		}
	}

    /**
     * 관리자 > 예약/일정 관리 > 행사 등록 action
     * @param eventVO
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/eventInsert.do")
    public String eventInsert(EventVO eventVO, HttpServletRequest request, ModelMap model) throws Exception {
	       		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else{
		    
		    eventVO.setRegId(user.getAdminId());

		    //행사 일자 시간 세팅
    		//String eventDay[] = eventVO.getEventDate().split(" ");
		    //eventVO.setEventDate(eventDay[0]+" 23:59:59");
		    int chkCnt = eventService.eventRegistChk(eventVO);		// 해당 날짜에 일정이 등록되어 있는 지 확인
		    if(chkCnt == 0) {
	    		//String openDate[] = eventVO.getOpenDate().split(" ");
	    		//eventVO.setOpenDate(openDate[0]+" 00:00:00");
			    eventService.eventInsert(eventVO);
		    }else {
				model.addAttribute("message", "해당 날짜는 이미 일정이 등록되어 있습니다.");
				model.addAttribute("retType", ":back");
				
				return "/common/message";
		    }

	    	//오전 일정 사용
	    	EventVO amEvent = new EventVO();
    		amEvent.setUseFlag("Y");
	    	amEvent.setEventId(eventVO.getEventId());
	    	amEvent.setEventDate(eventVO.getEventDate());
	    	amEvent.setEventFlag(eventVO.getEventFlag());
	    	amEvent.setEventTime("AM");
	    	amEvent.setAmpm("am");
	    	amEvent.setTimeFlag("Y");
			eventService.eventTimeInsert(amEvent);

			/*
			//오후 일정 사용
	    	EventVO pmEvent = new EventVO();
	    	if(eventVO.getPmUseFlag().equals("Y")) {
	    		pmEvent.setUseFlag("Y");
	    	}else {
	    		pmEvent.setUseFlag("N");
	    	}
	    	pmEvent.setEventId(eventVO.getEventId());
	    	pmEvent.setEventDate(eventVO.getEventDate());
	    	pmEvent.setEventFlag(eventVO.getEventFlag());
	    	pmEvent.setEventTime("14:00 ~ 16:00");
	    	pmEvent.setAmpm("pm");
	    	pmEvent.setTimeFlag("Y");
			eventService.eventTimeInsert(pmEvent);
			*/

			model.addAttribute("message", "등록이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/event/eventList.do");
			model.addAttribute("hiddenName1", "eventId");
			model.addAttribute("hiddenValue1", eventVO.getEventId());
			model.addAttribute("hiddenName2", "eventFlag");
			model.addAttribute("hiddenValue2", eventVO.getEventFlag());
			model.addAttribute("hiddenName3", "eventDate");
			model.addAttribute("hiddenValue3", eventVO.getEventDate());
			return "/common/message";
		}
	}

    /**
     * 관리자 > 예약/일정 관리 > 행사 수정 action
     * @param eventVO
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/eventUpdate.do")
    public String eventUpdate(EventVO eventVO, HttpServletRequest request, ModelMap model) throws Exception {

    	AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else{

		    eventVO.setUpdId(user.getAdminId());

		    //행사 일자 시간 세팅
    		//String eventDay[] = eventVO.getEventDate().split(" ");
		    //eventVO.setEventDate(eventDay[0]+" 23:59:59");

			// 날짜 중복 체크
			String eventDate2 = eventVO.getEventDate2() == null ? "" : eventVO.getEventDate2();

	    	EventVO event = eventService.selectEventDetail(eventVO);
	    	if(event.getApplyCnt() > 0) {	//신청자가 있을 경우
				model.addAttribute("message", "신청자가 있을 경우 수정할 수 없습니다.");
				model.addAttribute("retType", ":back");
				
				return "/common/message";
	    	}else {
		    	if(!eventVO.getEventDate().equals(eventDate2)){
				    int chkCnt = eventService.eventRegistChk(eventVO);		// 해당 날짜에 일정이 등록되어 있는 지 확인
				    if(chkCnt > 0) {
						model.addAttribute("message", "해당 날짜는 이미 일정이 등록되어 있습니다.");
						model.addAttribute("retType", ":back");
						
						return "/common/message";
				    }
			    }
	    		//String openDate[] = eventVO.getOpenDate().split(" ");
	    		//eventVO.setOpenDate(openDate[0]+" 08:00:00");
			    eventService.eventUpdate(eventVO);

		    	//오전 일정 사용
		    	EventVO amEvent = new EventVO();
	    		amEvent.setUseFlag("Y");
		    	amEvent.setEventDate(eventVO.getEventDate());
		    	amEvent.setAmpm("am");
		    	amEvent.setEventId(eventVO.getEventId());
				eventService.eventTimeUpdate(amEvent);

				//오후 일정 사용
				/*
		    	EventVO pmEvent = new EventVO();
		    	if(eventVO.getPmUseFlag().equals("Y")) {
		    		pmEvent.setUseFlag("Y");
		    	}else {
		    		pmEvent.setUseFlag("N");
		    	}
		    	pmEvent.setEventDate(eventVO.getEventDate());
		    	pmEvent.setAmpm("pm");
		    	pmEvent.setEventId(eventVO.getEventId());
				eventService.eventTimeUpdate(pmEvent);
				*/
	    	}

			model.addAttribute("message", "수정이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/event/eventList.do");
			model.addAttribute("hiddenName1", "eventId");
			model.addAttribute("hiddenValue1", eventVO.getEventId());
			model.addAttribute("hiddenName2", "eventFlag");
			model.addAttribute("hiddenValue2", eventVO.getEventFlag());
			model.addAttribute("hiddenName3", "eventDate");
			model.addAttribute("hiddenValue3", eventVO.getEventDate());

			return "/common/message";
		}
	}

	/**
	 * 관리자 > 예약/일정 관리 > 행사 삭제 action
	 * @param eventVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/eventDelete.do")
	public String eventDelete(EventVO eventVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}
		else {

			// 삭제자 ID입력
			eventVO.setUpdId(user.getAdminId());

	    	EventVO event = eventService.selectEventDetail(eventVO);
	    	if(event.getApplyCnt() > 0) {
				model.addAttribute("message", "신청자가 있을 경우 삭제할 수 없습니다.");
				model.addAttribute("retType", ":back");
				
				return "/common/message";
	    	}else {
				eventService.eventDelete(eventVO);
				eventService.eventTimeDelete(eventVO.getEventId());
	    	}

			model.addAttribute("message", "삭제되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/event/eventList.do");
			model.addAttribute("hiddenName1", "eventFlag");
			model.addAttribute("hiddenValue1", eventVO.getEventFlag());

			return "/common/message";
		}
	}

	/**
	 * 관리자 > 예약/일정 관리 > 행사 > 신청자 삭제 action
	 * @param eventVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/applyDelete.do")
	public String applyDelete(EventVO eventVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}
		else {

			// 삭제자 ID입력
			eventVO.setUpdId(user.getAdminId());

			eventService.applyDelete(eventVO);

			model.addAttribute("message", "삭제되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/event/applyView.do");
			model.addAttribute("hiddenName4", "ampm");
			model.addAttribute("hiddenValue4", eventVO.getAmpm());
			model.addAttribute("hiddenName1", "eventId");
			model.addAttribute("hiddenValue1", eventVO.getEventId());
			model.addAttribute("hiddenName2", "eventFlag");
			model.addAttribute("hiddenValue2", eventVO.getEventFlag());
			model.addAttribute("hiddenName3", "eventDate");
			model.addAttribute("hiddenValue3", eventVO.getEventDate());

			return "/common/message";
		}
	}
	
	/**
	 * 관리자 > 예약/일정 관리 > 토요의병놀이마당 > 신청자 관리view
	 * @param eventVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/applyView.do")
	public String applyView(@ModelAttribute("searchVO") EventVO eventVO, HttpServletRequest request, ModelMap model) throws Exception {

		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		} else {

			//행사 내용 조회
			EventVO event = eventService.selectEventDetail(eventVO);
			model.addAttribute("eventVO", event);

			// 신청자 신청그룹, 예비그룹 구분을 위해
			String amApply = event.getAmApply() == null ? "" : event.getAmApply();
			String pmApply = event.getPmApply() == null ? "" : event.getPmApply();
			if(eventVO.getAmpm().equals("am")) {
				model.addAttribute("applyCnt", amApply);
			}else if(eventVO.getAmpm().equals("pm")) {
				model.addAttribute("applyCnt", pmApply);
			}

			//신청자 목록 조회
			List<EventVO> applyList = eventService.applyList(eventVO);
			model.addAttribute("resultList", applyList);
			
			
			return "/admin/golf/applyView";
		}
	}
	
	/**
	 * 관리자 > 예약/일정 관리 > 신청자 엑셀 다운로드
	 * @param eventVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/eventExcelDown.do")
	public ModelAndView eventExcelDown(@ModelAttribute("searchVO") EventVO eventVO, HttpServletRequest request, ModelMap model) throws Exception {
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("redirect:/_admin/login.do");
			return mv;
		}else {

			// 신청자 목록 조회
			List<EventVO> applyList = eventService.applyList(eventVO);
			model.addAttribute("applyList", applyList);

			String flag = (eventVO.getExcelFlag() == null || eventVO.getExcelFlag().equals("")) ? "" : eventVO.getExcelFlag();
			String eventFlag = (eventVO.getEventFlag() == null || eventVO.getEventFlag().equals("")) ? "" : eventVO.getEventFlag();
			model.addAttribute("excelFlag", flag);
			model.addAttribute("eventFlag", eventFlag);
			model.addAttribute("user", user);
			
			// 현재 날짜 구하기
		 	SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.KOREA);
		 	Date currentTime = new Date();
		 	String mTime = mSimpleDateFormat.format(currentTime);
		 	model.addAttribute("currentDate", mTime);
			
		 	return new ModelAndView("excelDownView", "categoryMap", model);
		}
	}

	/**
	 * 관리자 > 예약/일정 관리 > 신청자 form
	 * @param eventVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/applyForm.do")
	public String applyForm(@ModelAttribute("searchVO") EventVO eventVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}
		else {
			String command = eventVO.getCommand() == null ? "insert" : eventVO.getCommand();
			if(command.equals("update")) {
			    eventVO = eventService.selectApplyDetail(eventVO);
				model.addAttribute("selectVO", eventVO);
			}

			return "/admin/golf/applyForm";
		}
	}

    /**
     * 관리자 > 예약/일정 관리 > 일정안내 수정 action
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/applyUpdate.do")
    public String applyUpdate(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") EventVO eventVO, ModelMap model, HttpServletRequest request) throws Exception {

    	AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else{
			String atchFileId = eventVO.getAtchFileId();
			final Map<String, MultipartFile> files = multiRequest.getFileMap();

			// 새로 첨부된 파일이 있다면
			if(!files.get("inp-file").isEmpty() ) {
				// 기존 저장된 파일 정보 삭제
				FileVO fvo = new FileVO();
				fvo.setAtchFileId(atchFileId);
				fvo = fileMngService.selectFileInf(fvo);
				fileMngService.deleteFileInf(fvo);
				
				// 새로 첨부된 파일 저장
				if ("".equals(atchFileId)) {
			    	List<FileVO> result = fileUtil.parseFileInf(files, "APPLY_", 0, atchFileId, "");
			    	atchFileId = fileMngService.insertFileInfs(result);
				    eventVO.setAtchFileId(atchFileId);
		    	} else {
					fvo = new FileVO();
					fvo.setAtchFileId(atchFileId);
					int maxSn = fileMngService.getMaxFileSN(fvo);
					List<FileVO> result = fileUtil.parseFileInf(files, "APPLY_", maxSn, atchFileId, "");
					fileMngService.updateFileInfs(result);
				}
			}

		    eventVO.setUpdId(user.getAdminId());
		    
		    eventService.applyUpdate(eventVO);

			model.addAttribute("message", "수정이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/event/applyView.do");
			model.addAttribute("hiddenName4", "ampm");
			model.addAttribute("hiddenValue4", eventVO.getAmpm());
			model.addAttribute("hiddenName1", "eventId");
			model.addAttribute("hiddenValue1", eventVO.getEventId());
			model.addAttribute("hiddenName2", "eventFlag");
			model.addAttribute("hiddenValue2", eventVO.getEventFlag());
			model.addAttribute("hiddenName3", "eventDate");
			model.addAttribute("hiddenValue3", eventVO.getEventDate());
		
			return "/common/message";
		}
	}
    
    
    /**
     * 관리자 > 예약/일정 관리 > 행사 등록 action
     * @param eventVO
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/eventCron.do")
    public String eventCron(EventVO eventVO, HttpServletRequest request, ModelMap model) throws Exception {
	    eventVO.setRegId("admin");
	    
	    if(eventVO == null || eventVO.getEventFlag() == null) {
	    	model.addAttribute("message", "등록시 오류가 발생하였습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/main.do");
			
			return "/common/message";
	    }
	    
	    // 날짜 조회
	    SimpleDateFormat dateFormat;
        //dateFormat = new SimpleDateFormat("yyyyMM"); //년월 표시
        dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //년월일 표시
	    
        Calendar cal = Calendar.getInstance();
        
        String endDate = "";
        String startDate = "";
    	cal.set ( cal.get(Calendar.YEAR), 11-1, 31 ); //종료 날짜 셋팅
        endDate = dateFormat.format(cal.getTime());
        
        cal.set ( cal.get(Calendar.YEAR), 3-1, 2 ); //시작 날짜 셋팅
        startDate = dateFormat.format(cal.getTime());
        
        int i = 0;
        
        List<String> dateList = new ArrayList<String>();
        
    	while(!startDate.equals(endDate)){ //다르다면 실행, 동일 하다면 빠져나감
        	
            if(i==0) { //최초 실행 출력
            	if(cal.get(Calendar.DAY_OF_WEEK) == 7) {
            		dateList.add(dateFormat.format(cal.getTime()));
            	}
            }
            
            cal.add(Calendar.DATE, 1); //1일 더해줌
            
            startDate = dateFormat.format(cal.getTime()); //비교를 위한 값 셋팅
            if(cal.get(Calendar.DAY_OF_WEEK) == 7 && !endDate.equals(dateFormat.format(cal.getTime()))) {
            	dateList.add(dateFormat.format(cal.getTime()));
        	}
            i++;
        }
        
        
    	for (String string : dateList) {
 			eventVO.setEventDate(string+" 23:59:59");
 			int chkCnt = eventService.eventRegistChk(eventVO);
 			if(chkCnt == 0) {
	    		eventVO.setOpenDate(cal.get(Calendar.YEAR)+"-02-23 08:00:00");
	    		eventVO.setAmUseFlag("Y");
	    		eventVO.setAmApply("4");
	    		eventVO.setAmSpare("2");
	    		eventVO.setPmUseFlag("Y");
	    		eventVO.setPmApply("4");
	    		eventVO.setPmSpare("2");
	    		
 				eventService.eventInsert(eventVO);
 				
 				//오전 일정 사용
		    	EventVO amEvent = new EventVO();
		    	amEvent.setUseFlag("Y");
		    	amEvent.setEventId(eventVO.getEventId());
		    	amEvent.setEventDate(eventVO.getEventDate());
		    	amEvent.setEventFlag(eventVO.getEventFlag());
		    	amEvent.setEventTime("10:30 ~ 12:00");
		    	amEvent.setAmpm("am");
		    	amEvent.setTimeFlag("Y");
				eventService.eventTimeInsert(amEvent);

				//오후 일정 사용
		    	EventVO pmEvent = new EventVO();
		    	pmEvent.setUseFlag("Y");
		    	pmEvent.setEventId(eventVO.getEventId());
		    	pmEvent.setEventDate(eventVO.getEventDate());
		    	pmEvent.setEventFlag(eventVO.getEventFlag());
		    	pmEvent.setEventTime("14:00 ~ 16:00");
		    	pmEvent.setAmpm("pm");
		    	pmEvent.setTimeFlag("Y");
				eventService.eventTimeInsert(pmEvent);
 			}
 		}
        
		model.addAttribute("message", "등록이 완료되었습니다.");
		model.addAttribute("retType", ":submit");
		model.addAttribute("retUrl", "/_admin/event/eventList.do");
		model.addAttribute("hiddenName1", "eventFlag");
		model.addAttribute("hiddenValue1", eventVO.getEventFlag());
		
		return "/common/message";
		
	}

    /**
     * 신청자 상태 업데이트
     * @param eventVO
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/statusUpdate.do")
    public String statusUpdate(EventVO eventVO, HttpServletRequest request, ModelMap model) throws Exception {

    	AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else{
			// 수정자 ID 저장
		    eventVO.setUpdId(user.getAdminId());
		    
		    // 업데이트
		    eventService.statusUpdate(eventVO);
		    
        	// 문자 전송
		    eventVO = eventService.selectApplyDetail(eventVO);
		    if(eventVO.getStatusFlag().equals("Y")) {
	        	eventVO.setSmsTitle("관리자 예약 승인");
	        	eventVO.setSmsMsg(eventVO.getApplicant()+"님 예약이 확정되었습니다. 자세한 내용은 '예약확인' 메뉴에서 확인 가능합니다.");
		    }else {
	        	eventVO.setSmsTitle("관리자 예약 취소");
	        	eventVO.setSmsMsg(eventVO.getApplicant()+"님 관리자에 의해 예약이 취소되었습니다.");
		    }
        	eventVO.setDestInfo(eventVO.getApplicant()+"^"+eventVO.getPhone().replace("-", "")+"|관리자^01090573130");
        	eventService.sendSms(eventVO);

			model.addAttribute("message", "수정이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/event/applyView.do");
			model.addAttribute("hiddenName1", "eventId");
			model.addAttribute("hiddenValue1", eventVO.getEventId());
			model.addAttribute("hiddenName2", "eventFlag");
			model.addAttribute("hiddenValue2", eventVO.getEventFlag());
			model.addAttribute("hiddenName3", "ampm");
			model.addAttribute("hiddenValue3", "am");

			return "/common/message";
		}
	}
    

}
