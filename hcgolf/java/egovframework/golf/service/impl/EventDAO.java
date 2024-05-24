package egovframework.golf.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.golf.service.EventVO;

@Repository("eventDAO")
public class EventDAO extends EgovComAbstractDAO {

	// 관리자 > 예약/일정 관리 > 일정안내 list
	public List<?> selectScheduleList(EventVO vo){
		return list("eventMapper.selectScheduleList", vo);
	}
	public int selectScheduleListCnt(EventVO vo) {
		return (Integer) selectOne("eventMapper.selectScheduleListCnt", vo);
	}
	public EventVO selectScheduleDetail(EventVO vo) {
		return (EventVO) selectOne("eventMapper.selectScheduleDetail", vo);
	}
	public void scheduleInsert(EventVO vo) {
		insert("eventMapper.scheduleInsert", vo);
	}
	public void scheduleUpdate(EventVO vo) {
		update("eventMapper.scheduleUpdate", vo);
	}
	public void scheduleDelete(EventVO vo) {
		update("eventMapper.scheduleDelete", vo);
	}

	// 관리자 > 예약/일정 관리 > 행사 달력 list
	public List<EventVO> eventList(EventVO vo) {
		return (List<EventVO>) list("eventMapper.eventList", vo);
	}
	public EventVO selectEventDetail(EventVO vo) {
		return (EventVO) selectOne("eventMapper.selectEventDetail", vo);
	}
	public List<EventVO> applyList(EventVO vo) {
		return (List<EventVO>) list("eventMapper.applyList", vo);
	}
	public void eventInsert(EventVO vo) {
		insert("eventMapper.eventInsert", vo);
	}
	public void eventUpdate(EventVO vo) {
		update("eventMapper.eventUpdate", vo);
	}
	public void eventDelete(EventVO vo) {
		update("eventMapper.eventDelete", vo);
	}
	public void applyDelete(EventVO vo) {
		update("eventMapper.applyDelete", vo);
	}
	public void applyInsert(EventVO vo) {
		insert("eventMapper.applyInsert", vo);
	}
	public void applyUpdate(EventVO vo) {
		update("eventMapper.applyUpdate", vo);
	}

	public List<EventVO> eventTimeSet(EventVO vo) {
		return (List<EventVO>) list("eventMapper.eventTimeSet", vo);
	}
	public List<EventVO> eventTimeList(EventVO vo) {
		return (List<EventVO>) list("eventMapper.eventTimeList", vo);
	}
	public EventVO selectApplyDetail(EventVO vo) {
		return (EventVO) selectOne("eventMapper.selectApplyDetail", vo);
	}
	public List<EventVO> eventEduList(EventVO vo) {
		return (List<EventVO>) list("eventMapper.eventEduList", vo);
	}
	public void eventTimeInsert(EventVO vo) {
		insert("eventMapper.eventTimeInsert", vo);
	}
	public void eventTimeDelete(String eventId) {
		update("eventMapper.eventTimeDelete", eventId);
	}
	public int eventRegistChk(EventVO vo) {
		return (Integer)selectOne("eventMapper.eventRegistChk", vo);
	}
	public List<EventVO> userEventTimeMonList(EventVO event) {
		return (List<EventVO>) list("eventMapper.userEventTimeMonList", event);
	}
	public List<EventVO> userEventTimeDayList(EventVO eventVO) {
		return (List<EventVO>) list("eventMapper.userEventTimeDayList", eventVO);
	}
	public void eventApply(EventVO eventVO) {
		insert("eventMapper.eventApply", eventVO);
	}
	public void eventTimeUpdate(EventVO vo) {
		update("eventMapper.eventTimeUpdate", vo);
	}
	public EventVO selectEventVO(EventVO event) {
		return (EventVO) selectOne("eventMapper.selectEventVO", event);
	}
	public int selectApplyCnt(EventVO eventVO) {
		return (Integer)selectOne("eventMapper.selectApplyCnt", eventVO);
	}
	public List<EventVO> gameEventTimeDayList(EventVO event) {
		return (List<EventVO>) list("eventMapper.gameEventTimeDayList", event);
	}
	public EventVO gameApplyCnt(EventVO eventVO) {
		return (EventVO) selectOne("eventMapper.gameApplyCnt", eventVO);
	}
	public List<EventVO> gameEventTimeMonList(EventVO event) {
		return (List<EventVO>) list("eventMapper.gameEventTimeMonList", event);
	}
	public void statusUpdate(EventVO vo) {
		update("eventMapper.statusUpdate", vo);
	}

	public List<EventVO> selectUserApplyList(String memberId) {
		return (List<EventVO>) list("eventMapper.selectUserApplyList", memberId);
	}
	public void sendSms(EventVO eventVO) {
		insert("eventMapper.sendSms", eventVO);
	}
}
