package egovframework.breeze.popup.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.breeze.popup.service.PopupService;
import egovframework.breeze.popup.service.PopupVO;

public class PopupBundle {
	
	private WebApplicationContext context;
	private PopupService popupService;
	private List<PopupVO> popupList;
	private PopupVO popupVO;
	
	public PopupBundle() {
		
	}
	
	public PopupBundle(HttpServletRequest request) {
		
		this.popupList = (List)request.getAttribute("popupList");
		this.popupVO = (PopupVO)request.getAttribute("popupVO");
		
		this.context = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
	    this.popupService = ((PopupService)this.context.getBean("popupService"));
	}
	
	public List<PopupVO> getPopupList(String category, String flag) throws Exception{
		PopupVO popup = new PopupVO();
		popup.setCategory(category);
		popup.setFlag(flag);
		
		List<PopupVO> list = this.popupService.selectMainPopupList(popup);
		return list;
	}
	
	public String getPopupId() {
		return this.popupVO.getPopupId();
	}
	
	public String getPopupTitle() {
		return this.popupVO.getPopupTitle();
	}
	
	public String getCategory() {
		return this.popupVO.getCategory();
	}
	
	public String getStartDate() {
		return this.popupVO.getStartDate();
	}
	
	public String getEndDate() {
		return this.popupVO.getEndDate();
	}
	
	public String getViewFlag() {
		return this.popupVO.getViewFlag();
	}
	
	public String getAtchFileId() {
		return this.popupVO.getAtchFileId();
	}
	
	public String getUrl() {
		return this.popupVO.getUrl();
	}
	
	public String getTarget() {
		return this.popupVO.getTarget();
	}
	
	public String getAltText() {
		return this.popupVO.getAltText();
	}
	
	public String getIr() {
		return this.popupVO.getIr();
	}
	
	public String getSizeWidth() {
		return this.popupVO.getSizeWidth();
	}
	
	public String getSizeHeight() {
		return this.popupVO.getSizeHeight();
	}
	
	public String getViewWidth() {
		return this.popupVO.getViewWidth();
	}
	
	public String getViewHeight() {
		return this.popupVO.getViewHeight();
	}
	
	public String getFlag() {
		return this.popupVO.getFlag();
	}
	
	public String getRegId() {
		return this.popupVO.getRegId();
	}
	
	public String getRegDate() {
		return this.popupVO.getRegDate();
	}
	
	public String getUpdId() {
		return this.popupVO.getUpdId();
	}
	
	public String getUpdDate() {
		return this.popupVO.getUpdDate();
	}
	
	public String getUseFlag() {
		return this.popupVO.getUseFlag();
	}
	
	public int getPopupOrder() {
		return this.popupVO.getPopupOrder();
	}
	
	public void setPopupVO(PopupVO popupVO) {
		this.popupVO = popupVO;
	}
	
	
}
