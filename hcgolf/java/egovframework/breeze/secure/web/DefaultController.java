package egovframework.breeze.secure.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.breeze.admin.service.AdminVO;
import egovframework.breeze.secure.service.DefaultService;
import egovframework.breeze.secure.service.DefaultVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;

@Controller
@RequestMapping("/_admin/secure")
public class DefaultController {

	@Resource(name="defaultService")
	private DefaultService defaultService;

	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;
	
	@Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
	
	
	/**
	 * 관리자 > 보안관리 > Breeze 기본설정
	 * @param accessVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/defaultForm.do")
	public String defaultForm(DefaultVO defaultVO, HttpServletRequest request, ModelMap model) throws Exception {
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
			defaultVO.setDefaultId("breeze");
			defaultVO = defaultService.selectDefaultSetting(defaultVO);
			model.addAttribute("defaultVO", defaultVO);
			return "/admin/secure/defaultForm";
		}
	}

	/**
	 * 관리자 > 보안관리 > Breeze 기본설정 > 저장
	 * @param defaultVO
	 * @param request
	 * @param multiRequest
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/defaultUpdate.do")
	public String defaultUpdate(DefaultVO defaultVO, HttpServletRequest request, final MultipartHttpServletRequest multiRequest, ModelMap model)throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		} else {

			// 수정 시
			if(defaultVO.getLogoFileId() != null && !defaultVO.getLogoFileId().equals("")) {
				final Map<String, MultipartFile> files = multiRequest.getFileMap();
				// 새로 첨부된 파일이 있다면
				if(files.get("pcImg") != null && !files.get("pcImg").isEmpty() ) {
					// 기존 저장된 파일 정보 삭제
					FileVO fvo = new FileVO();
					fvo.setAtchFileId(defaultVO.getLogoFileId());
					fvo.setImgFlag("pcImg");
					fvo = fileMngService.selectFileInf(fvo);
					fileMngService.deleteFileInf(fvo);
					
					// 새로 첨부된 파일 저장
					fvo = new FileVO();
					fvo.setAtchFileId(defaultVO.getLogoFileId());
					int maxSn = fileMngService.getMaxFileSN(fvo);
					
					List<FileVO> result = fileUtil.parseFileInf(files, "LOGO_", maxSn, defaultVO.getLogoFileId(), "");
					fileMngService.updateFileInfs(result);
					
					defaultVO.setLogoFilePath("/_cmm/fms/getImage.do?atchFileId="+defaultVO.getLogoFileId()+"&fileSn="+maxSn+"&ImgFlag=pcImg");
				}
				
			// 등록 시
			}else {	
				// 파일 저장
				List<FileVO> result = null;
				String atchFileId = "";
				final Map<String, MultipartFile> files = multiRequest.getFileMap();
				if(!files.isEmpty()){
					result = fileUtil.parseFileInf(files, "LOGO_", 0, "", "");
					atchFileId = fileMngService.insertFileInfs(result);
					defaultVO.setLogoFileId(atchFileId);
					defaultVO.setLogoFilePath("/_cmm/fms/getImage.do?atchFileId="+defaultVO.getLogoFileId()+"&fileSn=0&ImgFlag=pcImg");
				}
			}
			
			
			// 등록자ID, 수정자ID 저장
			defaultVO.setRegId(user.getAdminId());
			defaultVO.setUpdId(user.getAdminId());
			
			// 저장
			defaultVO.setDefaultId("breeze");
			defaultService.defaultUpdate(defaultVO);
			
			
			model.addAttribute("message", "저장이 완료되었습니다.<br>수정된 로고는 로그아웃 후 확인 부탁드립니다.");
			model.addAttribute("retType", ":location");
			model.addAttribute("retUrl", defaultVO.getReturnUrl());
			
			return "/common/message";
		}
	}
	

	/**
	 * 관리자 > 보안관리 > Breeze 기본설정 > 초기화
	 * @param defaultVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/defaultReset.do")
	public String defaultReset(DefaultVO defaultVO, HttpServletRequest request, ModelMap model)throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		} else {
			
			// 등록자ID, 수정자ID 저장
			defaultVO.setRegId(user.getAdminId());
			defaultVO.setUpdId(user.getAdminId());
			
			// 초기화
			defaultVO.setDefaultId("breeze");
			defaultService.defaultReset(defaultVO);
			
			model.addAttribute("message", "초기화가 완료되었습니다.");
			model.addAttribute("retType", ":location");
			model.addAttribute("retUrl", "/_admin/secure/defaultForm.do");
			
			return "/common/message";
		}
	}
}
