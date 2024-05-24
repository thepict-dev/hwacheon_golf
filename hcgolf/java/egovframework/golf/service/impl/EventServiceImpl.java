package egovframework.golf.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.golf.service.EventService;
import egovframework.golf.service.EventVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("eventService")
public class EventServiceImpl extends EgovAbstractServiceImpl implements EventService {

	@Resource(name="eventDAO")
	private EventDAO eventDAO;

    @Resource(name = "scheduleIdService")
    private EgovIdGnrService scheIdgenService;

    @Resource(name = "eventIdService")
    private EgovIdGnrService eventIdgenService;


	// 관리자 > 예약/일정 관리 > 일정안내 list
    @Override
 	public Map<String, Object> selectScheduleList(EventVO vo) throws Exception{
    	List<?> list = eventDAO.selectScheduleList(vo);
    	int cnt = eventDAO.selectScheduleListCnt(vo);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", list);
		map.put("resultCnt", Integer.toString(cnt));
		
		return map;
    }

	@Override
	public EventVO selectScheduleDetail(EventVO vo) throws Exception {
		return eventDAO.selectScheduleDetail(vo);
	}

    @Override
    public void scheduleInsert(EventVO vo) throws Exception{
		
		//일정 ID 채번
		String scheduleId = scheIdgenService.getNextStringId();
		vo.setScheduleId(scheduleId);

		eventDAO.scheduleInsert(vo);
    }

    @Override
    public void scheduleUpdate(EventVO vo) throws Exception{
    	eventDAO.scheduleUpdate(vo);
    }

    @Override
    public void scheduleDelete(EventVO vo) throws Exception{
    	eventDAO.scheduleDelete(vo);
    }

	// 관리자 > 예약/일정 관리 > 행사 달력 list
	@Override
	public List<EventVO> eventList(EventVO vo) throws Exception {
		return eventDAO.eventList(vo);
	}

	@Override
	public EventVO selectEventDetail(EventVO vo) throws Exception {
		return eventDAO.selectEventDetail(vo);
	}

	@Override
	public List<EventVO> applyList(EventVO vo) throws Exception {
		return eventDAO.applyList(vo);
	}

    @Override
    public void eventInsert(EventVO vo) throws Exception{
		
		//행사 ID 채번
		String eventId = eventIdgenService.getNextStringId();
		vo.setEventId(eventId);

		eventDAO.eventInsert(vo);
    }

    @Override
    public void eventUpdate(EventVO vo) throws Exception{
    	eventDAO.eventUpdate(vo);
    }

    @Override
    public void eventDelete(EventVO vo) throws Exception{
    	eventDAO.eventDelete(vo);
    }

    @Override
    public void applyDelete(EventVO vo) throws Exception{
    	eventDAO.applyDelete(vo);
    }

    @Override
    public void applyInsert(EventVO vo) throws Exception{
		eventDAO.applyInsert(vo);
    }
    @Override
    public void applyUpdate(EventVO vo) throws Exception{
    	eventDAO.applyUpdate(vo);
    }

	@Override
	public List<EventVO> eventTimeSet(EventVO vo) throws Exception {
		return eventDAO.eventTimeSet(vo);
	}
	@Override
	public List<EventVO> eventTimeList(EventVO vo) throws Exception {
		return eventDAO.eventTimeList(vo);
	}

	@Override
	public EventVO selectApplyDetail(EventVO vo) throws Exception {
		return eventDAO.selectApplyDetail(vo);
	}
	@Override
	public List<EventVO> eventEduList(EventVO vo) throws Exception {
		return eventDAO.eventEduList(vo);
	}

    @Override
    public void eventTimeInsert(EventVO vo) throws Exception{
		eventDAO.eventTimeInsert(vo);
    }

    @Override
    public void eventTimeDelete(String eventId) throws Exception{
    	eventDAO.eventTimeDelete(eventId);
    }

	@Override
	public int eventRegistChk(EventVO vo) {
		return eventDAO.eventRegistChk(vo);
	}

	@Override
	public List<EventVO> userEventTimeMonList(EventVO event) throws Exception {
		return eventDAO.userEventTimeMonList(event);
	}

	@Override
	public List<EventVO> userEventTimeDayList(EventVO eventVO) throws Exception {
		return eventDAO.userEventTimeDayList(eventVO);
	}

	@Override
	public void eventApply(EventVO eventVO) throws Exception {
		eventDAO.eventApply(eventVO);
	}

    @Override
    public void eventTimeUpdate(EventVO vo) throws Exception{
		eventDAO.eventTimeUpdate(vo);
    }

	@Override
	public EventVO selectEventVO(EventVO event) throws Exception {
		return eventDAO.selectEventVO(event);
	}

	@Override
	public int selectApplyCnt(EventVO eventVO) throws Exception {
		return eventDAO.selectApplyCnt(eventVO);
	}

	@Override
	public List<EventVO> gameEventTimeDayList(EventVO event) throws Exception {
		return eventDAO.gameEventTimeDayList(event);
	}

	@Override
	public EventVO gameApplyCnt(EventVO eventVO) throws Exception {
		return eventDAO.gameApplyCnt(eventVO);
	}

	@Override
	public List<EventVO> gameEventTimeMonList(EventVO event) throws Exception {
		return eventDAO.gameEventTimeMonList(event);
	}

    @Override
    public void statusUpdate(EventVO vo) throws Exception{
    	eventDAO.statusUpdate(vo);
    }
    
	@Override
	public List<EventVO> selectUserApplyList(String memberId) throws Exception {
		return eventDAO.selectUserApplyList(memberId);
	}

	@Override
	public void sendSms(EventVO eventVO) throws Exception {
		eventDAO.sendSms(eventVO);
	}

}
