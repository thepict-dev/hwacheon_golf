package egovframework.breeze.site.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.breeze.site.service.MenuService;
import egovframework.breeze.site.service.MenuVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("menuService")
public class MenuServiceImpl extends EgovAbstractServiceImpl implements MenuService {

	@Resource(name="menuDAO")
	private MenuDAO menuDAO;
	
	@Resource(name = "menuIdService")
	private EgovIdGnrService idgenService;
	
	@Override
	public List<MenuVO> selectMenuList(MenuVO menuVO) {
		return menuDAO.selectMenuList(menuVO);
	}

	@Override
	public MenuVO selectMenuView(MenuVO menuVO) {
		return menuDAO.selectMenuView(menuVO);
	}

	@Override
	public void menuInsert(MenuVO menuVO) throws Exception {
		String menuId = idgenService.getNextStringId();
		menuVO.setMenuId(menuId);
		menuDAO.menuInsert(menuVO);
	}

	@Override
	public void menuUpdate(MenuVO menuVO) throws Exception {
		menuDAO.menuUpdate(menuVO);
	}
	@Override
	public void menuDepthUpdate(MenuVO menuVO) throws Exception {
		menuDAO.menuDepthUpdate(menuVO);
	}

	@Override
	public void menuDelete(MenuVO menuVO) throws Exception {
		menuDAO.menuDelete(menuVO);
	}

	@Override
	public int menuNameOverlapChk(MenuVO menuVO) throws Exception {
		return menuDAO.menuNameOverlapChk(menuVO);
	}
	@Override
	public int menuNameOverlapChk2(MenuVO menuVO) throws Exception {
		return menuDAO.menuNameOverlapChk2(menuVO);
	}

	@Override
	public List<MenuVO> selectMenuLowList(String menuId) throws Exception {
		return menuDAO.selectMenuLowList(menuId);
	}

	@Override
	public List<MenuVO> selectMenuAllList() throws Exception {
		return menuDAO.selectMenuAllList();
	}

	@Override
	public List<MenuVO> getMenuListDepth(MenuVO menu) throws Exception {
		return menuDAO.getMenuListDepth(menu);
	}

	@Override
	public String getMenuIdTitle(String menuId) throws Exception {
		return menuDAO.getMenuIdTitle(menuId);
	}

}
