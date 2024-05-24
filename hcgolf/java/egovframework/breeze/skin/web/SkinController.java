package egovframework.breeze.skin.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.breeze.admin.service.AdminVO;
import egovframework.breeze.skin.service.SkinService;
import egovframework.breeze.skin.service.SkinVO;
import egovframework.breeze.common.StringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping({"/_admin/board/", "_admin/survey/"})
public class SkinController {
	
	/** skinService */
	@Resource(name = "skinService")
	private SkinService skinService;
	
	/**
	 * 관리자 > 게시판/스킨 관리 > 스킨 관리 > 리스트
	 * @param skinVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/skinList.do")
	public String skinList(@ModelAttribute("searchVO") SkinVO skinVO, HttpServletRequest request, ModelMap model) throws Exception  {
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {

			String skinType = skinVO.getSkinType()==null ? "BBS" : skinVO.getSkinType(); 
			skinVO.setSkinType(skinType);
			
			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(skinVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(skinVO.getPageUnit());
			paginationInfo.setPageSize(skinVO.getPageSize());
			
			skinVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			skinVO.setLastIndex(paginationInfo.getLastRecordIndex());
			skinVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			Map<String, Object> map = skinService.selectSkinList(skinVO);
			int totCnt = Integer.parseInt((String)map.get("resultCnt"));
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("skinType", skinType);
			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("paginationInfo", paginationInfo);

			return "/admin/skin/skinList";
			
		}
	}
	
	/**
	 * 관리자 > 게시판/스킨 관리 > 스킨 관리 > 등록/수정 페이지
	 * @param skinVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/skinForm.do")
	public String skinForm(@ModelAttribute("searchVO") SkinVO skinVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{

			String skinType = skinVO.getSkinType()==null ? "BBS" : skinVO.getSkinType();
			skinVO.setSkinType(skinType);
			
			//update 일때만 조회
			String command = skinVO.getCommand() == null ? "insert" : skinVO.getCommand();
			if(command.equals("update")){
				skinVO = skinService.selectSkinView(skinVO);
				model.addAttribute("skinVO", skinVO);
			}
			 
			model.addAttribute("skinType", skinType);
			
			return "/admin/skin/skinForm";
		}
	}

	/**
	 * 관리자 > 게시판/스킨 관리 > 스킨 관리 > 등록 action
	 * @param skinVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/skinInsert.do")
	public String skinInsert(SkinVO skinVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{

			String skinType = skinVO.getSkinType()==null ? "BBS" : skinVO.getSkinType();
			skinVO.setSkinType(skinType);
			
			// 등록자ID, 수정자ID 추가
			skinVO.setRegId(user.getAdminId());
			skinVO.setUpdId(user.getAdminId());

			// html태그 치환
			skinVO.setSkinList(StringUtil.getStringToTag(skinVO.getSkinList()));
			skinVO.setSkinView(StringUtil.getStringToTag(skinVO.getSkinView()));
			skinVO.setSkinForm(StringUtil.getStringToTag(skinVO.getSkinForm()));
			
			// 등록 action
			skinService.skinInsert(skinVO);
			
			// 백업 action
			skinService.skinBakInsert(skinVO);
			
			model.addAttribute("message", "등록이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/" + (!skinType.equals("SRV") ? "board" : "survey") + "/skinList.do");
			model.addAttribute("hiddenName1", "skinType");
			model.addAttribute("hiddenValue1", skinType );
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 > 게시판/스킨 관리 > 스킨 관리 > 수정 action
	 * @param skinVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/skinUpdate.do")
	public String skinUpdate(SkinVO skinVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{

			String skinType = skinVO.getSkinType()==null ? "BBS" : skinVO.getSkinType();
			skinVO.setSkinType(skinType);

			// 수정자ID 추가
			skinVO.setUpdId(user.getAdminId());

			// html태그 치환
			skinVO.setSkinList(StringUtil.getStringToTag(skinVO.getSkinList()));
			skinVO.setSkinView(StringUtil.getStringToTag(skinVO.getSkinView()));
			skinVO.setSkinForm(StringUtil.getStringToTag(skinVO.getSkinForm()));
			
			// 수정 action
			skinService.skinUpdate(skinVO);
			
			// 백업 action
			skinService.skinBakInsert(skinVO);
			
			model.addAttribute("message", "수정이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/" + (!skinType.equals("SRV") ? "board" : "survey") + "/skinForm.do");
			model.addAttribute("hiddenName1", "skinId");
			model.addAttribute("hiddenValue1", skinVO.getSkinId());
			model.addAttribute("hiddenName2", "skinType");
			model.addAttribute("hiddenValue2", skinType);
			model.addAttribute("hiddenName3", "pageIndex");
			model.addAttribute("hiddenValue3", skinVO.getPageIndex());
			model.addAttribute("hiddenName4", "searchCnd");
			model.addAttribute("hiddenValue4", skinVO.getSearchCnd());
			model.addAttribute("hiddenName5", "searchWrd");
			model.addAttribute("hiddenValue5", skinVO.getSearchWrd());
			model.addAttribute("hiddenName6", "command");
			model.addAttribute("hiddenValue6", "update");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 > 게시판/스킨 관리 > 스킨 관리 > 삭제 action
	 * @param skinVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/skinDelete.do")
	public String skinDelete(SkinVO skinVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{

			String skinType = skinVO.getSkinType()==null ? "BBS" : skinVO.getSkinType(); 
			skinVO.setSkinType(skinType);

			// 수정자ID 추가
			skinVO.setUpdId(user.getAdminId());
			
			// delete action
			skinService.skinDelete(skinVO);
			
			model.addAttribute("message", "삭제가 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/" + (!skinType.equals("SRV") ? "board" : "survey") + "/skinList.do");
			model.addAttribute("hiddenName1", "skinType");
			model.addAttribute("hiddenValue1", skinType );
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 > 게시판/스킨 관리 > 스킨 관리 > 백업 목록
	 * @param skinVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/skinBakList.do")
	public String skinBakList(@ModelAttribute("searchVO") SkinVO skinVO, HttpServletRequest request, ModelMap model) throws Exception {

	    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {

			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(skinVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(20);
			paginationInfo.setPageSize(skinVO.getPageSize());
			
			skinVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			skinVO.setLastIndex(paginationInfo.getLastRecordIndex());
			skinVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			Map<String, Object> map = skinService.selectSkinBakList(skinVO);
			int totCnt = Integer.parseInt((String)map.get("resultCnt"));
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("paginationInfo", paginationInfo);

			return "/popup/admin/skinBakList";
		}
	}
	
	/**
	 * 관리자 > 게시판/스킨 관리 > 스킨 관리 > 백업 조회
	 * @param skinVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/skinBakForm.do")
	public String skinBakForm(@ModelAttribute("searchVO") SkinVO skinVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{
			skinVO = skinService.selectSkinBakView(skinVO);
			model.addAttribute("skinVO", skinVO);
			
			return "/popup/admin/skinBakForm";
		}
	}
}
