package egovframework.breeze.site.service;

import java.util.List;
import java.util.Map;

public interface MenuService {

	List<MenuVO> selectMenuList(MenuVO menuVO);
	
	MenuVO selectMenuView(MenuVO menuVO);
	
	void menuInsert(MenuVO menuVO) throws Exception;

	void menuUpdate(MenuVO menuVO) throws Exception;
	void menuDepthUpdate(MenuVO menuVO) throws Exception;

	void menuDelete(MenuVO menuVO) throws Exception;

	int menuNameOverlapChk(MenuVO menuVO) throws Exception;
	int menuNameOverlapChk2(MenuVO menuVO) throws Exception;

	List<MenuVO> selectMenuLowList(String menuId) throws Exception;

	List<MenuVO> selectMenuAllList() throws Exception;

	List<MenuVO> getMenuListDepth(MenuVO menu) throws Exception;

	String getMenuIdTitle(String menuId) throws Exception;

}
