package egovframework.breeze.site.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.breeze.site.service.MenuVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("menuDAO")
public class MenuDAO extends EgovComAbstractDAO {
	

	public List<MenuVO> selectMenuList(MenuVO menuVO) {
		return (List<MenuVO>) list("menuMapper.selectMenuList", menuVO);
	}

	public MenuVO selectMenuView(MenuVO menuVO) {
		return (MenuVO) selectOne("menuMapper.selectMenuView", menuVO);
	}

	public void menuInsert(MenuVO menuVO) {
		insert("menuMapper.menuInsert", menuVO);
	}

	public void menuUpdate(MenuVO menuVO) {
		update("menuMapper.menuUpdate", menuVO);
	}
	public void menuDepthUpdate(MenuVO menuVO) {
		update("menuMapper.menuDepthUpdate", menuVO);
	}

	public void menuDelete(MenuVO menuVO) {
		update("menuMapper.menuDelete", menuVO);
	}

	public int menuNameOverlapChk(MenuVO menuVO) {
		return (int) selectOne("menuMapper.menuNameOverlapChk", menuVO);
	}
	public int menuNameOverlapChk2(MenuVO menuVO) {
		return (int) selectOne("menuMapper.menuNameOverlapChk2", menuVO);
	}

	public void menuBakInsert(MenuVO menuVO) {
		insert("menuMapper.menuBakInsert", menuVO);
	}
	
	public List<MenuVO> selectMenuBakList(MenuVO menuVO) {
		return (List<MenuVO>) list("menuMapper.selectMenuBakList", menuVO);
	}
	
	public int selectMenuBakListCnt(MenuVO menuVO) {
		return (Integer)selectOne("menuMapper.selectMenuBakListCnt", menuVO);
	}
	
	public MenuVO selectMenuBakView(MenuVO menuVO) {
		return (MenuVO) selectOne("menuMapper.selectMenuBakView", menuVO);
	}

	public List<MenuVO> selectMenuLowList(String menuId) {
		return (List<MenuVO>) list("menuMapper.selectMenuLowList", menuId);
	}

	public List<MenuVO> selectMenuAllList() {
		return selectList("menuMapper.selectMenuAllList");
	}

	public List<MenuVO> getMenuListDepth(MenuVO menu) {
		return (List<MenuVO>) list("menuMapper.getMenuListDepth", menu);
	}

	public String getMenuIdTitle(String menuId) {
		return (String) selectOne("menuMapper.getMenuIdTitle", menuId);
	}
}
