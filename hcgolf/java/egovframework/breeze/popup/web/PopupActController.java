package egovframework.breeze.popup.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.breeze.popup.service.PopupService;
import egovframework.breeze.popup.service.PopupVO;

@Controller
public class PopupActController {

	@Resource(name="popupService")
	private PopupService popupService;
	
	@RequestMapping(value = "/_popup")
	public String infozoneList( @ModelAttribute("searchVO") PopupVO popupVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		popupVO = popupService.selectPopupView(popupVO);
		
		model.addAttribute("popupVO", popupVO);
		
		return "/popup/main/popup";
	}
}
