package egovframework.com.cmm.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.breeze.admin.service.AdminVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cop.bbs.service.EgovArticleService;

/**
 * 파일 조회, 삭제, 다운로드 처리를 위한 컨트롤러 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.3.25  이삼섭          최초 생성
 *   2016.10.13 장동한           deleteFileInf 메소드 return 방식 수정
 *
 * </pre>
 */
@Controller
public class EgovFileMngController {

    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;


    @Resource(name = "EgovArticleService")
    private EgovArticleService bbsMngService;

    /**
     * 첨부파일에 대한 목록을 조회한다.
     *
     * @param fileVO
     * @param atchFileId
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/_cmm/fms/selectFileInfs.do")
    public String selectFileInfs(@ModelAttribute("searchVO") FileVO fileVO, @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {
		String atchFileId = (String)commandMap.get("param_atchFileId");
		String updateFlag = (String)commandMap.get("updateFlag");
		String returnUrl = (String)commandMap.get("returnUrl");
		String fileSn = (String)commandMap.get("fileSn")==null?"":(String)commandMap.get("fileSn");		// visualView에서 c:param 사용하기 위해 추가 hds
		String viewFlag = (String)commandMap.get("viewFlag")==null?"":(String)commandMap.get("viewFlag");
	
		fileVO.setAtchFileId(atchFileId);
		fileVO.setViewFlag(viewFlag);
		if(fileSn != null && !fileSn.equals("")){
			fileVO.setFileSn(fileSn);
		}
	
		List<FileVO> result = fileService.selectFileInfs(fileVO);
	
		model.addAttribute("fileList", result);
		model.addAttribute("updateFlag", updateFlag);
		model.addAttribute("fileListCnt", result.size());
		model.addAttribute("atchFileId", atchFileId);
		model.addAttribute("returnUrl", returnUrl);
		model.addAttribute("fileSn", fileSn);

		return "egovframework/com/cmm/fms/EgovFileList";
    }
    
    /**
     * 첨부파일에 대한 목록을 조회한다.
     *
     * @param fileVO
     * @param atchFileId
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/_cmm/fms/selectBusinessFileInfs.do")
    public String selectBusinessFileInfs(@ModelAttribute("searchVO") FileVO fileVO, @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {
		String atchFileId = (String)commandMap.get("param_atchFileId");
		String businessFlag = (String)commandMap.get("businessFlag")==null?"":(String)commandMap.get("businessFlag");
		String fileSn = (String)commandMap.get("fileSn")==null?"":(String)commandMap.get("fileSn");
		String updateFlag = (String)commandMap.get("updateFlag")==null?"":(String)commandMap.get("updateFlag");
		
		
		fileVO.setAtchFileId(atchFileId);
		if(fileSn != null && !fileSn.equals("")){
			fileVO.setFileSn(fileSn);
		}
	
		List<FileVO> result = fileService.selectFileInfs(fileVO);
	
		model.addAttribute("fileList", result);
		model.addAttribute("fileListCnt", result.size());
		model.addAttribute("atchFileId", atchFileId);
		model.addAttribute("businessFlag", businessFlag);
		model.addAttribute("updateFlag", updateFlag);
		model.addAttribute("fileSn", fileSn);

		return "egovframework/com/cmm/fms/EgovBusinessFileList";
    }

    /**
     * 첨부파일 변경을 위한 수정페이지로 이동한다.
     *
     * @param fileVO
     * @param atchFileId
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/_cmm/fms/selectFileInfsForUpdate.do")
    public String selectFileInfsForUpdate(@ModelAttribute("searchVO") FileVO fileVO, @RequestParam Map<String, Object> commandMap,
	    //SessionVO sessionVO,
	    ModelMap model) throws Exception {

	String atchFileId = (String)commandMap.get("param_atchFileId");

	fileVO.setAtchFileId(atchFileId);

	List<FileVO> result = fileService.selectFileInfs(fileVO);

	model.addAttribute("fileList", result);
	model.addAttribute("updateFlag", "Y");
	model.addAttribute("fileListCnt", result.size());
	model.addAttribute("atchFileId", atchFileId);

	return "egovframework/com/cmm/fms/EgovFileList";
    }

    /**
     * 첨부파일에 대한 삭제를 처리한다.
     *
     * @param fileVO
     * @param returnUrl
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/_cmm/fms/deleteFileInfs.do")
    public String deleteFileInf(@ModelAttribute("searchVO") FileVO fileVO, @RequestParam("returnUrl") String returnUrl,
	    //SessionVO sessionVO,
	    HttpServletRequest request,
	    ModelMap model) throws Exception {

		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else{
		    fileService.deleteFileInf(fileVO);
		    
		    List<FileVO> fileList = fileService.selectFileInfs(fileVO);
		    if(fileList == null || fileList.size() == 0){//리스트가 널이거나 첨부파일이 없는경우
		    	bbsMngService.deleteAtchFileId(fileVO.getAtchFileId());
		    }
		}
		 
		//--------------------------------------------
		// contextRoot가 있는 경우 제외 시켜야 함
		//--------------------------------------------
		////return "forward:/cmm/fms/selectFileInfs.do";
		//return "forward:" + returnUrl;
		/* *******************************************************
		 *  modify by jdh
		 *******************************************************
		*/

		if ("".equals(request.getContextPath()) || "/".equals(request.getContextPath())) {
			System.out.println("@@@@ : "+returnUrl);
		    return "forward:" + returnUrl;
		}

		if (returnUrl.startsWith(request.getContextPath())) {
			System.out.println("@@@@@ : "+returnUrl.substring(returnUrl.indexOf("/", 1)));
		    return "forward:" + returnUrl.substring(returnUrl.indexOf("/", 1));
		} else {
		    return "forward:" + returnUrl;
		}
		////------------------------------------------
    }

    /**
     * 이미지 첨부파일에 대한 목록을 조회한다.
     *
     * @param fileVO
     * @param atchFileId
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/_cmm/fms/selectImageFileInfs.do")
    public String selectImageFileInfs(@ModelAttribute("searchVO") FileVO fileVO, @RequestParam Map<String, Object> commandMap,
	    //SessionVO sessionVO,
	    ModelMap model) throws Exception {

	String atchFileId = (String)commandMap.get("atchFileId");

	fileVO.setAtchFileId(atchFileId);
	List<FileVO> result = fileService.selectImageFileList(fileVO);

	model.addAttribute("fileList", result);

	return "egovframework/com/cmm/fms/EgovImgFileList";
    }
}
