package egovframework.golf.service;

import java.util.List;
import java.util.Map;


public interface EventService {
	
	// 관리자 > 예약/일정 관리 > 일정안내 list
	Map<String, Object> selectScheduleList(EventVO vo) throws Exception;
	// 관리자 > 예약/일정 관리 > 일정안내 view
	EventVO selectScheduleDetail(EventVO vo) throws Exception;
	// 관리자 > 예약/일정 관리 > 일정안내 등록
	void scheduleInsert(EventVO vo) throws Exception;
	// 관리자 > 예약/일정 관리 > 일정안내 수정
	void scheduleUpdate(EventVO vo) throws Exception;
	// 관리자 > 예약/일정 관리 > 일정안내 삭제
	void scheduleDelete(EventVO vo) throws Exception;

	// 관리자 > 예약/일정 관리 > 행사 달력 list
	public List<EventVO> eventList(EventVO vo) throws Exception;
	// 관리자 > 예약/일정 관리 > 행사 view
	EventVO selectEventDetail(EventVO vo) throws Exception;
	// 관리자 > 예약/일정 관리 > 행사 > 신청자 list
	public List<EventVO> applyList(EventVO vo) throws Exception;
	// 관리자 > 예약/일정 관리 > 행사 등록
	void eventInsert(EventVO vo) throws Exception;
	// 관리자 > 예약/일정 관리 > 행사 수정
	void eventUpdate(EventVO vo) throws Exception;
	// 관리자 > 예약/일정 관리 > 행사 삭제
	void eventDelete(EventVO vo) throws Exception;
	// 관리자 > 예약/일정 관리 > 행사 > 신청자 삭제
	void applyDelete(EventVO vo) throws Exception;
	// 관리자 > 예약/일정 관리 > 청렴교육 > 신청자 등록
	void applyInsert(EventVO vo) throws Exception;
	// 관리자 > 예약/일정 관리 > 청렴교육 > 신청자 수정
	void applyUpdate(EventVO vo) throws Exception;

	// 관리자 > 예약/일정 관리 > 단체해설 > default 시간 list
	public List<EventVO> eventTimeSet(EventVO vo) throws Exception;
	// 관리자 > 예약/일정 관리 > 단체해설 > db에 저장되어 있는 시간 list
	public List<EventVO> eventTimeList(EventVO vo) throws Exception;

	// 관리자 > 예약/일정 관리 > 청렴교육 view
	EventVO selectApplyDetail(EventVO vo) throws Exception;
	// 관리자 > 예약/일정 관리 > 청렴교육 달력 list
	public List<EventVO> eventEduList(EventVO vo) throws Exception;

	// 관리자 > 예약/일정 관리 > 단체해설 > row별로 시간 등록
	void eventTimeInsert(EventVO vo) throws Exception;
	// 관리자 > 예약/일정 관리 > 단체해설 > 시간 삭제
	void eventTimeDelete(String eventId) throws Exception;

	// 관리자 > 예약/일정 관리 > 해당 날짜에 일정 등록되어 있는 지 체크
	public int eventRegistChk(EventVO vo);
	
	// 사용자 > 달력 리스트 조회
	List<EventVO> userEventTimeMonList(EventVO event) throws Exception;
	
	// 사용자 > 시간 리스트 조회
	List<EventVO> userEventTimeDayList(EventVO eventVO) throws Exception;
	
	// 사용자 > 단체 해설 신청
	void eventApply(EventVO eventVO) throws Exception;

	// 관리자 > 예약/일정 관리 > 단체해설 > row별로 시간 수정
	void eventTimeUpdate(EventVO vo) throws Exception;
	
	// eventVO 조회
	EventVO selectEventVO(EventVO event) throws Exception;
	
	// 신청 cnt 조회
	int selectApplyCnt(EventVO eventVO) throws Exception;
	
	// 토요의병놀이마당 타임 조회
	List<EventVO> gameEventTimeDayList(EventVO event) throws Exception;
	
	// 토요의병놀이마당 접수인원 및 접수가능인원 조회
	EventVO gameApplyCnt(EventVO eventVO) throws Exception;
	
	// 토요의병놀이마당 달력 조회	
	List<EventVO> gameEventTimeMonList(EventVO event) throws Exception;

	// 관리자 > 신청자 상태 업데이트
	void statusUpdate(EventVO vo) throws Exception;
	
	// 사용자 > 마이페이지 > 예약확인 조회
	List<EventVO> selectUserApplyList(String memberId) throws Exception;

	void sendSms(EventVO eventVO) throws Exception;
}
