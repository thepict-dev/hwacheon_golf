package egovframework.golf.web;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.breeze.member.service.MemberService;
import egovframework.com.cmm.UsrPaginationRenderer;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.golf.service.EventService;
import egovframework.golf.service.EventVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public class GolfBundle {
	
	private HttpServletRequest request;
	private EventVO eventVO;
	//private ContactVO contactVO;
	
	//private List<BusinessVO> businessDataList;
	private int businessDataCnt = 0;
	
	private WebApplicationContext context;
	private EventService eventService;
    private MemberService memberService;
    private EgovFileMngService egovFileMngService;
    
    private PaginationInfo paginationInfo;
	private UsrPaginationRenderer paging;
    
    private List<FileVO> fileList;

    
    private boolean isManager = false;
	private String pattern = "yyyy-MM-dd";
	private int pageUnit = 10;
	private int pageIndex = 1;
    
	private static final String MOBILE_PATTERN = "^(\\d{3})-?(\\d{3,4})-?(\\d{4})$";
	
	public GolfBundle() throws Exception{

	}

	public GolfBundle(HttpServletRequest request){
		this.request = request;
		this.context = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
	    this.eventService = ((EventService)this.context.getBean("eventService"));
	    this.egovFileMngService = ((EgovFileMngService)this.context.getBean("EgovFileMngService"));
	    this.memberService = ((MemberService)this.context.getBean("memberService"));
	    
	    //this.businessDataList = ((List)request.getAttribute("businessDataList"));
		
		/*if(request.getAttribute("businessDataCnt") != null) {
			this.businessDataCnt = Integer.parseInt((String) request.getAttribute("businessDataCnt"));
		}*/
		
		this.paginationInfo = ((PaginationInfo)request.getAttribute("paginationInfo"));
	    if(this.paginationInfo == null)
	    {
	    	this.paginationInfo = new PaginationInfo();
	    }
	    
	    this.paging = new UsrPaginationRenderer();
	    
	    this.pageUnit = (int)(request.getAttribute("pageUnit")==null?10:request.getAttribute("pageUnit"));
		this.pageIndex = (int)(request.getAttribute("pageIndex")==null?1:request.getAttribute("pageIndex"));
	    
	    this.fileList = ((List)request.getAttribute("fileList"));
	    
	}
	
	public List<EventVO> eventEduList(String eventFlag) throws Exception {
		EventVO event = new EventVO();
		event.setEventFlag(eventFlag);
		List<EventVO> eventList = this.eventService.eventEduList(event);
		return eventList;
	}
	
	// 사용자 > 달력 리스트 조회
	public List<EventVO> userEventTimeMonList(String eventFlag) throws Exception {
		EventVO event = new EventVO();
		event.setEventFlag(eventFlag);
		List<EventVO> eventList = this.eventService.userEventTimeMonList(event);
		return eventList;
	}
	
	// 사용자 > 달력 시간 리스트 조회
	public List<EventVO> userEventTimeDayList(String eventId, String eventFlag) throws Exception {
		EventVO event = new EventVO();
		event.setEventId(eventId);
		event.setEventFlag(eventFlag);
		List<EventVO> timeList = this.eventService.userEventTimeDayList(event);
		
		for (EventVO time : timeList) {
			if(time.getApplicant() != null && !time.getApplicant().equals("")) {
				time.setApplicant(maskingName(time.getApplicant())); 
			}
		}
		
		return timeList;
	}
	
	// 사용자 > 토요의병놀이마당 시간 리스트 조회
	public List<EventVO> gameEventTimeDayList(String eventId) throws Exception {
		EventVO event = new EventVO();
		event.setEventId(eventId);
		event.setEventFlag("event01");
		List<EventVO> timeList = this.eventService.gameEventTimeDayList(event);
		
		return timeList;
	}
	
	// 사용자 > 토요의병놀이마당 달력 리스트 조회
	public List<EventVO> gameEventTimeMonList(String eventFlag) throws Exception {
		EventVO event = new EventVO();
		event.setEventFlag(eventFlag);
		List<EventVO> eventList = this.eventService.gameEventTimeMonList(event);
		return eventList;
	}
	
	// 사용자 > eventVO 조회
	public EventVO selectEventVO(String eventId, String eventTimeId) throws Exception {
		EventVO event = new EventVO();
		event.setEventId(eventId);
		event.setEventTimeId(eventTimeId);
		event = this.eventService.selectEventVO(event);
		
		return event;
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
	
    public List<EventVO> scheduleList() throws Exception {
		EventVO eventVO = new EventVO();
    	eventVO.setFirstIndex(0);
		eventVO.setRecordCountPerPage(9999);
		List<EventVO> scheduleList = (List<EventVO>) this.eventService.selectScheduleList(eventVO).get("resultList");
		return scheduleList;
	}
    
    public EventVO scheduleDetail(EventVO eventVO) throws Exception {
    	return this.eventService.selectScheduleDetail(eventVO);
    }
    
    public List<FileVO> getFileList(String atchFileId) throws Exception{
		FileVO file = new FileVO();
		file.setAtchFileId(atchFileId);
		List<FileVO> result = egovFileMngService.selectFileInfs(file);
		return result;
	}
	
	public List<EventVO> getUserApplyList(String memberId) throws Exception{
		List<EventVO> applyList = this.eventService.selectUserApplyList(memberId);
		
		return applyList;
	}
}