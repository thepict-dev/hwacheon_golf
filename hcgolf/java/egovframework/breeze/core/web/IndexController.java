package egovframework.breeze.core.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibatis.common.beans.ProbeException;
import com.ibm.icu.text.SimpleDateFormat;

import egovframework.breeze.core.service.IndexService;
import egovframework.breeze.core.service.IndexVO;
import egovframework.breeze.member.service.MemberVO;
import egovframework.breeze.site.service.AnalyticsService;
import egovframework.breeze.site.service.AnalyticsVO;
import egovframework.breeze.site.service.ContentsService;
import egovframework.breeze.site.service.ContentsVO;
import egovframework.breeze.site.service.LayoutService;
import egovframework.breeze.site.service.LayoutVO;
import egovframework.breeze.site.service.MenuService;
import egovframework.breeze.site.service.MenuVO;
import egovframework.breeze.skin.service.SkinService;
import egovframework.breeze.skin.service.SkinVO;
import egovframework.breeze.survey.service.SurveyService;
import egovframework.breeze.survey.service.SurveyVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cop.bbs.service.BoardCateVO;
import egovframework.com.cop.bbs.service.BoardItemVO;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.com.cop.bbs.service.EgovBBSMasterService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping("/_breeze")
public class IndexController {

	/** indexService */
	@Resource(name = "indexService")
	private IndexService indexService;

	/** layoutService */
	@Resource(name = "layoutService")
	private LayoutService layoutService;

	/** menuService */
	@Resource(name = "menuService")
	private MenuService menuService;
	
	/** contentsService */
    @Resource(name = "contentsService")
    private ContentsService contentsService;

	/** skinService */
	@Resource(name = "skinService")
	private SkinService skinService;
	
    @Resource(name = "EgovBBSMasterService")
    private EgovBBSMasterService egovBBSMasterService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

	@Resource(name = "EgovArticleService")
    private EgovArticleService egovArticleService;

	@Resource(name = "analyticsService")
	private AnalyticsService analyticsService;
	
    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;

	

	@RequestMapping(value = {"/*", "/*/*", "/*/*/*", "/*/*/*/*", "/*/*/*/*/*", "/*/*/*/*/*/*"})
	public String contents(@ModelAttribute("searchVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		
		// =============================================== 접속한 URI를 가지고 해당 MENU 조회 세팅 시작 ===================================================
		System.out.println("request.getRequestURI():::::"+request.getRequestURI());		// 프로젝트경로부터 파일까지의 경로값을 얻어옴 (/test/index.jsp)
		//System.out.println("request.getContextPath():::::"+request.getContextPath());	// 프로젝트의 경로값만 가져옴(/test)
		//System.out.println("request.getRequestURL():::::"+request.getRequestURL());	// 전체 경로를 가져옴 (http://localhost:8080/test/index.jsp)
		//System.out.println("request.getServletPath():::::"+request.getServletPath());	// 파일명 (/index.jsp)

		// 메인페이지 찾기
		IndexVO mainVO = new IndexVO();
		mainVO.setSiteId("SITE_000000000000001");
		mainVO = indexService.selectSiteSetting(mainVO);
		model.addAttribute("mainVO", mainVO);
		
		MenuVO paramMenuVO = new MenuVO();
		String sUri = request.getRequestURI();
		sUri = sUri.replace("/_breeze", "");
		String arrUri[] = sUri.split("/");
		if(arrUri.length == 0) {	// 메인페이지로 이동
			paramMenuVO.setMenuId(mainVO.getMenuId());
			MenuVO menuVO = menuService.selectMenuView(paramMenuVO);
			model.addAttribute("mainFlag", "Y");
			if(menuVO.getMenuDepth().equals("dep1")) return "forward:/_breeze/"+menuVO.getMenuName();
			else if(menuVO.getMenuDepth().equals("dep2")) return "forward:/_breeze/"+menuVO.getMenuNameDepth1()+"/"+menuVO.getMenuName();
			else if(menuVO.getMenuDepth().equals("dep3")) return "forward:/_breeze/"+menuVO.getMenuNameDepth1()+"/"+menuVO.getMenuNameDepth2()+"/"+menuVO.getMenuName();
			else if(menuVO.getMenuDepth().equals("dep4")) return "forward:/_breeze/"+menuVO.getMenuNameDepth1()+"/"+menuVO.getMenuNameDepth2()+"/"+menuVO.getMenuNameDepth3()+"/"+menuVO.getMenuName();
			else if(menuVO.getMenuDepth().equals("dep5")) return "forward:/_breeze/"+menuVO.getMenuNameDepth1()+"/"+menuVO.getMenuNameDepth2()+"/"+menuVO.getMenuNameDepth3()+"/"+menuVO.getMenuNameDepth4()+"/"+menuVO.getMenuName();
			else if(menuVO.getMenuDepth().equals("dep6")) return "forward:/_breeze/"+menuVO.getMenuNameDepth1()+"/"+menuVO.getMenuNameDepth2()+"/"+menuVO.getMenuNameDepth3()+"/"+menuVO.getMenuNameDepth4()+"/"+menuVO.getMenuNameDepth5()+"/"+menuVO.getMenuName();
		}else if(arrUri.length == 2) {
			paramMenuVO.setMenuDepth("dep1");
			paramMenuVO.setMenuNameDepth1(arrUri[1]);
			//System.out.println("1DEP:::::::::"+arrUri[1]);
		}else if(arrUri.length == 3) {
			paramMenuVO.setMenuDepth("dep2");
			paramMenuVO.setMenuNameDepth1(arrUri[1]);
			paramMenuVO.setMenuNameDepth2(arrUri[2]);
			//System.out.println("2DEP:::::::::"+arrUri[1]+","+arrUri[2]);
		}else if(arrUri.length == 4) {
			paramMenuVO.setMenuDepth("dep3");
			paramMenuVO.setMenuNameDepth1(arrUri[1]);
			paramMenuVO.setMenuNameDepth2(arrUri[2]);
			paramMenuVO.setMenuNameDepth3(arrUri[3]);
			//System.out.println("3DEP:::::::::"+arrUri[1]+","+arrUri[2]+","+arrUri[3]);
		}else if(arrUri.length == 5) {
			paramMenuVO.setMenuDepth("dep4");
			paramMenuVO.setMenuNameDepth1(arrUri[1]);
			paramMenuVO.setMenuNameDepth2(arrUri[2]);
			paramMenuVO.setMenuNameDepth3(arrUri[3]);
			paramMenuVO.setMenuNameDepth4(arrUri[4]);
			//System.out.println("4DEP:::::::::"+arrUri[1]+","+arrUri[2]+","+arrUri[3]+","+arrUri[4]);
		}else if(arrUri.length == 6) {
			paramMenuVO.setMenuDepth("dep5");
			paramMenuVO.setMenuNameDepth1(arrUri[1]);
			paramMenuVO.setMenuNameDepth2(arrUri[2]);
			paramMenuVO.setMenuNameDepth3(arrUri[3]);
			paramMenuVO.setMenuNameDepth4(arrUri[4]);
			paramMenuVO.setMenuNameDepth5(arrUri[5]);
			//System.out.println("5DEP:::::::::"+arrUri[1]+","+arrUri[2]+","+arrUri[3]+","+arrUri[4]+","+arrUri[5]);
		}else if(arrUri.length == 7) {
			paramMenuVO.setMenuDepth("dep6");
			paramMenuVO.setMenuNameDepth1(arrUri[1]);
			paramMenuVO.setMenuNameDepth2(arrUri[2]);
			paramMenuVO.setMenuNameDepth3(arrUri[3]);
			paramMenuVO.setMenuNameDepth4(arrUri[4]);
			paramMenuVO.setMenuNameDepth5(arrUri[5]);
			paramMenuVO.setMenuNameDepth6(arrUri[6]);
			//System.out.println("6DEP:::::::::"+arrUri[1]+","+arrUri[2]+","+arrUri[3]+","+arrUri[4]+","+arrUri[5]+","+arrUri[6]);
		}
		// =============================================== 접속한 URI를 가지고 해당 MENU 조회 세팅 종료 ===================================================
		
		// 해당 메뉴 조회
		MenuVO menuVO = indexService.selectMenu(paramMenuVO);
		model.addAttribute("menuVO", menuVO);
		// 컨텐츠 조회
		ContentsVO contentsVO = new ContentsVO();
		if(menuVO != null) {

			// ############################################################ Koffset Login 체크 ############################################################
			/*
			MemberVO loginVO = (MemberVO) request.getSession().getAttribute("loginVO");
			if(loginVO == null) {
				// 현재 메뉴 ID와 설정된 로그인 메뉴 ID가 다를때만 체크
				if(!menuVO.getMenuId().equals(mainVO.getLoginMenuId())) {
					// 로그인 메뉴 조회
					paramMenuVO.setMenuId(mainVO.getLoginMenuId());
					MenuVO loginMenuVO = menuService.selectMenuView(paramMenuVO);
					
					// 로그인 URL 설정 및 redirect
					String loginUrl = "";
					if(loginMenuVO.getMenuDepth().equals("dep1")) loginUrl ="/"+loginMenuVO.getMenuName();
					else if(loginMenuVO.getMenuDepth().equals("dep2")) loginUrl ="/"+loginMenuVO.getMenuNameDepth1()+"/"+loginMenuVO.getMenuName();
					else if(loginMenuVO.getMenuDepth().equals("dep3")) loginUrl ="/"+loginMenuVO.getMenuNameDepth1()+"/"+loginMenuVO.getMenuNameDepth2()+"/"+loginMenuVO.getMenuName();
					else if(loginMenuVO.getMenuDepth().equals("dep4")) loginUrl ="/"+loginMenuVO.getMenuNameDepth1()+"/"+loginMenuVO.getMenuNameDepth2()+"/"+loginMenuVO.getMenuNameDepth3()+"/"+loginMenuVO.getMenuName();
					else if(loginMenuVO.getMenuDepth().equals("dep5")) loginUrl ="/"+loginMenuVO.getMenuNameDepth1()+"/"+loginMenuVO.getMenuNameDepth2()+"/"+loginMenuVO.getMenuNameDepth3()+"/"+loginMenuVO.getMenuNameDepth4()+"/"+loginMenuVO.getMenuName();
					else if(loginMenuVO.getMenuDepth().equals("dep6")) loginUrl ="/"+loginMenuVO.getMenuNameDepth1()+"/"+loginMenuVO.getMenuNameDepth2()+"/"+loginMenuVO.getMenuNameDepth3()+"/"+loginMenuVO.getMenuNameDepth4()+"/"+loginMenuVO.getMenuNameDepth5()+"/"+loginMenuVO.getMenuName();
	
					model.addAttribute("message", "로그인이 필요한 서비스입니다.");
					model.addAttribute("retUrl", loginUrl);
					model.addAttribute("retType", ":location");
					return "/common/message";
				}
			}
			*/
			// ############################################################ Koffset Login 체크 ############################################################
			
			contentsVO.setContentsId(menuVO.getContentsId());
			contentsVO = contentsService.selectContentsView(contentsVO);
			if(contentsVO != null) {
				//System.out.println("getMenuType:::::::::::::::::::::::::::::"+menuVO.getMenuType());
				if(contentsVO.getContentsType().equals("URL")) {		// 메뉴타입이 URL
					System.out.println(":::::::::::::::::::::::::::::::::::"+contentsVO.getUrl());
					return "redirect:"+contentsVO.getUrl();
				}else {
					
					IndexVO indexVO = new IndexVO();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					// ::::::::::::::::::::::::::::::::::::::::::::: Layout 조회 및 파일 생성 시작 :::::::::::::::::::::::::::::::::::::::::::::
					LayoutVO layoutVO = new LayoutVO();
					layoutVO.setLayoutId(contentsVO.getLayoutId());
					layoutVO = layoutService.selectLayoutView(layoutVO);
					model.addAttribute("layoutVO", layoutVO);
					
					if(layoutVO != null) {
						// JS, CSS 변환 작업 및 파일 생성
						try {
							// 패턴 선언
							Pattern regex = Pattern.compile("\\[(CSS|JS|CNT):(CONTENTS_)([0-9]*)\\]");

							// ############################################# LayoutHead 조회 및 파일 생성 시작 #############################################
							Matcher regexMatcher = regex.matcher(layoutVO.getLayoutHead());
							String incStr = "";
							while (regexMatcher.find()) {
								incStr = regexMatcher.group();
								incStr = incStr.substring(1, incStr.length() - 1);
								StringTokenizer st = new StringTokenizer(incStr, ":");
								ArrayList def = new ArrayList();

								while (st.hasMoreTokens()) {
									def.add(st.nextToken());
								}
								String type = (String)def.get(0);
								String contentId = (String)def.get(1);
								
								// 매칭된 CSS|JS|CNT 콘텐츠 조회
								ContentsVO tmpContentsVO = new ContentsVO(); 
								tmpContentsVO.setContentsId(contentId);
								tmpContentsVO = contentsService.selectContentsView(tmpContentsVO);
								if (tmpContentsVO == null) {
						            throw new ProbeException("해당 콘텐츠가 존재하지 않습니다. 다시 확인 부탁드립니다.");
								}
								
								String JSFile = "";
								String CSSFile = "";
								String CNTFile = "";
								
								if(type.equals("JS")) {
					            	if ((!(new File(EgovProperties.getProperty("Globals.contentsPath") + "js/" + tmpContentsVO.getContentsId() + ".js").exists())) || (FileUtils.isFileOlder(new File(EgovProperties.getProperty("Globals.contentsPath") + "js/" + tmpContentsVO.getContentsId() + ".js"), sdf.parse(tmpContentsVO.getUpdDate())))) {
						            	FileUtils.writeStringToFile(new File(EgovProperties.getProperty("Globals.contentsPath") + "js/" + tmpContentsVO.getContentsId() + ".js"), tmpContentsVO.getContentsContent(), "UTF-8");
						            }
									JSFile = "<script src=\"/_source/js/" + tmpContentsVO.getContentsId() + ".js\"></script>";
									layoutVO.setLayoutHead(layoutVO.getLayoutHead().replace("[" + incStr + "]", JSFile));
								}else if(type.equals("CSS")) {
					            	if ((!(new File(EgovProperties.getProperty("Globals.contentsPath") + "css/" + tmpContentsVO.getContentsId() + ".css").exists())) || (FileUtils.isFileOlder(new File(EgovProperties.getProperty("Globals.contentsPath") + "css/" + tmpContentsVO.getContentsId() + ".css"), sdf.parse(tmpContentsVO.getUpdDate())))) {
					            		FileUtils.writeStringToFile(new File(EgovProperties.getProperty("Globals.contentsPath") + "css/" + tmpContentsVO.getContentsId() + ".css"), tmpContentsVO.getContentsContent(), "UTF-8");
					            	}
									CSSFile = "<link rel=\"stylesheet\" href=\"/_source/css/" + tmpContentsVO.getContentsId() + ".css\">";
									layoutVO.setLayoutHead(layoutVO.getLayoutHead().replace("[" + incStr + "]", CSSFile));
								}else {
					            	if ((!(new File(EgovProperties.getProperty("Globals.contentsPath") + "include/" + tmpContentsVO.getContentsId() + ".jsp").exists())) || (FileUtils.isFileOlder(new File(EgovProperties.getProperty("Globals.contentsPath") + "include/" + tmpContentsVO.getContentsId() + ".jsp"), sdf.parse(tmpContentsVO.getUpdDate())))) {
					            		FileUtils.writeStringToFile(new File(EgovProperties.getProperty("Globals.contentsPath") + "include/" + tmpContentsVO.getContentsId() + ".jsp"), indexVO.getDefaultHeader() + tmpContentsVO.getContentsContent(), "UTF-8");
					            	}
									CNTFile = "<jsp:include page=\"/_source/include/" + tmpContentsVO.getContentsId() + ".jsp\" flush=\"true\" />";
									layoutVO.setLayoutHead(layoutVO.getLayoutHead().replace("[" + incStr + "]", CNTFile));
								}
							}
							// ############################################# LayoutHead 조회 및 파일 생성 종료 #############################################
							// ############################################# LayoutHeader 조회 및 파일 생성 시작 ###########################################
							regexMatcher = regex.matcher(layoutVO.getLayoutHeader());
							incStr = "";
							while (regexMatcher.find()) {
								incStr = regexMatcher.group();
								incStr = incStr.substring(1, incStr.length() - 1);
								StringTokenizer st = new StringTokenizer(incStr, ":");
								ArrayList def = new ArrayList();

								while (st.hasMoreTokens()) {
									def.add(st.nextToken());
								}
								String type = (String)def.get(0);
								String contentId = (String)def.get(1);
								
								// 매칭된 CSS|JS|CNT 콘텐츠 조회
								ContentsVO tmpContentsVO = new ContentsVO(); 
								tmpContentsVO.setContentsId(contentId);
								tmpContentsVO = contentsService.selectContentsView(tmpContentsVO);
								if (tmpContentsVO == null) {
						            throw new ProbeException("해당 콘텐츠가 존재하지 않습니다. 다시 확인 부탁드립니다.");
								}
								
								String JSFile = "";
								String CSSFile = "";
								String CNTFile = "";
								
								if(type.equals("JS")) {
					            	if ((!(new File(EgovProperties.getProperty("Globals.contentsPath") + "js/" + tmpContentsVO.getContentsId() + ".js").exists())) || (FileUtils.isFileOlder(new File(EgovProperties.getProperty("Globals.contentsPath") + "js/" + tmpContentsVO.getContentsId() + ".js"), sdf.parse(tmpContentsVO.getUpdDate())))) {
						            	FileUtils.writeStringToFile(new File(EgovProperties.getProperty("Globals.contentsPath") + "js/" + tmpContentsVO.getContentsId() + ".js"), tmpContentsVO.getContentsContent(), "UTF-8");
						            }
									JSFile = "<script src=\"/_source/js/" + tmpContentsVO.getContentsId() + ".js\"></script>";
									layoutVO.setLayoutHeader(layoutVO.getLayoutHeader().replace("[" + incStr + "]", JSFile));
								}else if(type.equals("CSS")) {
					            	if ((!(new File(EgovProperties.getProperty("Globals.contentsPath") + "css/" + tmpContentsVO.getContentsId() + ".css").exists())) || (FileUtils.isFileOlder(new File(EgovProperties.getProperty("Globals.contentsPath") + "css/" + tmpContentsVO.getContentsId() + ".css"), sdf.parse(tmpContentsVO.getUpdDate())))) {
					            		FileUtils.writeStringToFile(new File(EgovProperties.getProperty("Globals.contentsPath") + "css/" + tmpContentsVO.getContentsId() + ".css"), tmpContentsVO.getContentsContent(), "UTF-8");
					            	}
									CSSFile = "<link rel=\"stylesheet\" href=\"/_source/css/" + tmpContentsVO.getContentsId() + ".css\">";
									layoutVO.setLayoutHeader(layoutVO.getLayoutHeader().replace("[" + incStr + "]", CSSFile));
								}else {
					            	if ((!(new File(EgovProperties.getProperty("Globals.contentsPath") + "include/" + tmpContentsVO.getContentsId() + ".jsp").exists())) || (FileUtils.isFileOlder(new File(EgovProperties.getProperty("Globals.contentsPath") + "include/" + tmpContentsVO.getContentsId() + ".jsp"), sdf.parse(tmpContentsVO.getUpdDate())))) {
					            		FileUtils.writeStringToFile(new File(EgovProperties.getProperty("Globals.contentsPath") + "include/" + tmpContentsVO.getContentsId() + ".jsp"), indexVO.getDefaultHeader() + tmpContentsVO.getContentsContent(), "UTF-8");
					            	}
									CNTFile = "<jsp:include page=\"/_source/include/" + tmpContentsVO.getContentsId() + ".jsp\" flush=\"true\" />";
									layoutVO.setLayoutHeader(layoutVO.getLayoutHeader().replace("[" + incStr + "]", CNTFile));
								}
							}
							// ############################################# LayoutHeader 조회 및 파일 생성 종료 ###########################################
							// ############################################# LayoutFooter 조회 및 파일 생성 시작 ###########################################
							regexMatcher = regex.matcher(layoutVO.getLayoutFooter());
							incStr = "";
							while (regexMatcher.find()) {
								incStr = regexMatcher.group();
								incStr = incStr.substring(1, incStr.length() - 1);
								StringTokenizer st = new StringTokenizer(incStr, ":");
								ArrayList def = new ArrayList();

								while (st.hasMoreTokens()) {
									def.add(st.nextToken());
								}
								String type = (String)def.get(0);
								String contentId = (String)def.get(1);
								
								// 매칭된 CSS|JS|CNT 콘텐츠 조회
								ContentsVO tmpContentsVO = new ContentsVO(); 
								tmpContentsVO.setContentsId(contentId);
								tmpContentsVO = contentsService.selectContentsView(tmpContentsVO);
								if (tmpContentsVO == null) {
						            throw new ProbeException("해당 콘텐츠가 존재하지 않습니다. 다시 확인 부탁드립니다.");
								}
								
								String JSFile = "";
								String CSSFile = "";
								String CNTFile = "";
								
								if(type.equals("JS")) {
					            	if ((!(new File(EgovProperties.getProperty("Globals.contentsPath") + "js/" + tmpContentsVO.getContentsId() + ".js").exists())) || (FileUtils.isFileOlder(new File(EgovProperties.getProperty("Globals.contentsPath") + "js/" + tmpContentsVO.getContentsId() + ".js"), sdf.parse(tmpContentsVO.getUpdDate())))) {
						            	FileUtils.writeStringToFile(new File(EgovProperties.getProperty("Globals.contentsPath") + "js/" + tmpContentsVO.getContentsId() + ".js"), tmpContentsVO.getContentsContent(), "UTF-8");
						            }
									JSFile = "<script src=\"/_source/js/" + tmpContentsVO.getContentsId() + ".js\"></script>";
									layoutVO.setLayoutFooter(layoutVO.getLayoutFooter().replace("[" + incStr + "]", JSFile));
								}else if(type.equals("CSS")) {
					            	if ((!(new File(EgovProperties.getProperty("Globals.contentsPath") + "css/" + tmpContentsVO.getContentsId() + ".css").exists())) || (FileUtils.isFileOlder(new File(EgovProperties.getProperty("Globals.contentsPath") + "css/" + tmpContentsVO.getContentsId() + ".css"), sdf.parse(tmpContentsVO.getUpdDate())))) {
					            		FileUtils.writeStringToFile(new File(EgovProperties.getProperty("Globals.contentsPath") + "css/" + tmpContentsVO.getContentsId() + ".css"), tmpContentsVO.getContentsContent(), "UTF-8");
					            	}
									CSSFile = "<link rel=\"stylesheet\" href=\"/_source/css/" + tmpContentsVO.getContentsId() + ".css\">";
									layoutVO.setLayoutFooter(layoutVO.getLayoutFooter().replace("[" + incStr + "]", CSSFile));
								}else {
					            	if ((!(new File(EgovProperties.getProperty("Globals.contentsPath") + "include/" + tmpContentsVO.getContentsId() + ".jsp").exists())) || (FileUtils.isFileOlder(new File(EgovProperties.getProperty("Globals.contentsPath") + "include/" + tmpContentsVO.getContentsId() + ".jsp"), sdf.parse(tmpContentsVO.getUpdDate())))) {
					            		FileUtils.writeStringToFile(new File(EgovProperties.getProperty("Globals.contentsPath") + "include/" + tmpContentsVO.getContentsId() + ".jsp"), indexVO.getDefaultHeader() + tmpContentsVO.getContentsContent(), "UTF-8");
					            	}
									CNTFile = "<jsp:include page=\"/_source/include/" + tmpContentsVO.getContentsId() + ".jsp\" flush=\"true\" />";
									layoutVO.setLayoutFooter(layoutVO.getLayoutFooter().replace("[" + incStr + "]", CNTFile));
								}
							}
							// ############################################# LayoutFooter 조회 및 파일 생성 종료 ###########################################
						}catch(PatternSyntaxException patternSyntaxException) {
							System.out.println("error::::::"+patternSyntaxException.toString());
						}
						
						// Layout 파일 생성
						if((!(new File(EgovProperties.getProperty("Globals.contentsPath") + "layout/" + layoutVO.getLayoutId() + "/head.jsp").exists())) || (FileUtils.isFileOlder(new File(EgovProperties.getProperty("Globals.contentsPath") + "layout/" + layoutVO.getLayoutId() + "/head.jsp"), sdf.parse(layoutVO.getUpdDate())))) {
							FileUtils.writeStringToFile(new File(EgovProperties.getProperty("Globals.contentsPath") + "layout/" + layoutVO.getLayoutId() + "/head.jsp"), indexVO.getDefaultHeader() + layoutVO.getLayoutHead(), "UTF-8");
						}
						if((!(new File(EgovProperties.getProperty("Globals.contentsPath") + "layout/" + layoutVO.getLayoutId() + "/header.jsp").exists())) || (FileUtils.isFileOlder(new File(EgovProperties.getProperty("Globals.contentsPath") + "layout/" + layoutVO.getLayoutId() + "/header.jsp"), sdf.parse(layoutVO.getUpdDate())))) {
							FileUtils.writeStringToFile(new File(EgovProperties.getProperty("Globals.contentsPath") + "layout/" + layoutVO.getLayoutId() + "/header.jsp"), indexVO.getDefaultHeader() + layoutVO.getLayoutHeader(), "UTF-8");
						}
						if((!(new File(EgovProperties.getProperty("Globals.contentsPath") + "layout/" + layoutVO.getLayoutId() + "/footer.jsp").exists())) || (FileUtils.isFileOlder(new File(EgovProperties.getProperty("Globals.contentsPath") + "layout/" + layoutVO.getLayoutId() + "/footer.jsp"), sdf.parse(layoutVO.getUpdDate())))) {
							FileUtils.writeStringToFile(new File(EgovProperties.getProperty("Globals.contentsPath") + "layout/" + layoutVO.getLayoutId() + "/footer.jsp"), indexVO.getDefaultHeader() + layoutVO.getLayoutFooter(), "UTF-8");
						}
					}
					// ::::::::::::::::::::::::::::::::::::::::::::: Layout 조회 및 파일 생성 종료 :::::::::::::::::::::::::::::::::::::::::::::
					
					// ::::::::::::::::::::::::::::::::::::::::::::: Analytics 조회 시작 :::::::::::::::::::::::::::::::::::::::::::::::::::
					AnalyticsVO analyticsVO = new AnalyticsVO();
					analyticsVO.setFirstIndex(0);
					analyticsVO.setRecordCountPerPage(9999);
					Map<String, Object> analyticsMap = analyticsService.selectAnalyticsList(analyticsVO);
					model.addAttribute("analyticsList", analyticsMap.get("resultList"));
					// ::::::::::::::::::::::::::::::::::::::::::::: Analytics 조회 종료 :::::::::::::::::::::::::::::::::::::::::::::::::::

					// 현재 uri
					model.addAttribute("currentUri", sUri);
					
					// ::::::::::::::::::::::::::::::::::::::::::::: Contents 조회 및 파일 생성 시작 :::::::::::::::::::::::::::::::::::::::::::
					if(contentsVO.getContentsType().equals("CON")) {
						model.addAttribute("contentsVO", contentsVO);
						// JS, CSS, CNT 변환 작업 및 파일 생성
						try {
							// 패턴 선언
							Pattern regex = Pattern.compile("\\[(CSS|JS|CNT):(CONTENTS_)([0-9]*)\\]");
							Matcher regexMatcher = regex.matcher(contentsVO.getContentsContent());
							String incStr = "";
							while (regexMatcher.find()) {
								incStr = regexMatcher.group();
								incStr = incStr.substring(1, incStr.length() - 1);
								StringTokenizer st = new StringTokenizer(incStr, ":");
								ArrayList def = new ArrayList();

								while (st.hasMoreTokens()) {
									def.add(st.nextToken());
								}
								String type = (String)def.get(0);
								String contentId = (String)def.get(1);
								
								// 매칭된 CSS|JS|CNT 콘텐츠 조회
								ContentsVO tmpContentsVO = new ContentsVO(); 
								tmpContentsVO.setContentsId(contentId);
								tmpContentsVO = contentsService.selectContentsView(tmpContentsVO);
								if (tmpContentsVO == null) {
						            throw new ProbeException("해당 콘텐츠가 존재하지 않습니다. 다시 확인 부탁드립니다.");
								}
								
								String JSFile = "";
								String CSSFile = "";
								String CNTFile = "";
								
								if(type.equals("JS")) {
					            	if ((!(new File(EgovProperties.getProperty("Globals.contentsPath") + "js/" + tmpContentsVO.getContentsId() + ".js").exists())) || (FileUtils.isFileOlder(new File(EgovProperties.getProperty("Globals.contentsPath") + "js/" + tmpContentsVO.getContentsId() + ".js"), sdf.parse(tmpContentsVO.getUpdDate())))) {
						            	FileUtils.writeStringToFile(new File(EgovProperties.getProperty("Globals.contentsPath") + "js/" + tmpContentsVO.getContentsId() + ".js"), tmpContentsVO.getContentsContent(), "UTF-8");
						            }
									JSFile = "<script src=\"/_source/js/" + tmpContentsVO.getContentsId() + ".js\"></script>";
									contentsVO.setContentsContent(contentsVO.getContentsContent().replace("[" + incStr + "]", JSFile));
								}else if(type.equals("CSS")) {
					            	if ((!(new File(EgovProperties.getProperty("Globals.contentsPath") + "css/" + tmpContentsVO.getContentsId() + ".css").exists())) || (FileUtils.isFileOlder(new File(EgovProperties.getProperty("Globals.contentsPath") + "css/" + tmpContentsVO.getContentsId() + ".css"), sdf.parse(tmpContentsVO.getUpdDate())))) {
					            		FileUtils.writeStringToFile(new File(EgovProperties.getProperty("Globals.contentsPath") + "css/" + tmpContentsVO.getContentsId() + ".css"), tmpContentsVO.getContentsContent(), "UTF-8");
					            	}
									CSSFile = "<link rel=\"stylesheet\" href=\"/_source/css/" + tmpContentsVO.getContentsId() + ".css\">";
									contentsVO.setContentsContent(contentsVO.getContentsContent().replace("[" + incStr + "]", CSSFile));
								}else {
					            	if ((!(new File(EgovProperties.getProperty("Globals.contentsPath") + "include/" + tmpContentsVO.getContentsId() + ".jsp").exists())) || (FileUtils.isFileOlder(new File(EgovProperties.getProperty("Globals.contentsPath") + "include/" + tmpContentsVO.getContentsId() + ".jsp"), sdf.parse(tmpContentsVO.getUpdDate())))) {
					            		FileUtils.writeStringToFile(new File(EgovProperties.getProperty("Globals.contentsPath") + "include/" + tmpContentsVO.getContentsId() + ".jsp"), indexVO.getDefaultHeader() + tmpContentsVO.getContentsContent(), "UTF-8");
					            	}
									CNTFile = "<jsp:include page=\"/_source/include/" + tmpContentsVO.getContentsId() + ".jsp\" flush=\"true\" />";
									contentsVO.setContentsContent(contentsVO.getContentsContent().replace("[" + incStr + "]", CNTFile));
								}
							}
						}catch(PatternSyntaxException patternSyntaxException) {
							System.out.println("error::::::"+patternSyntaxException.toString());
						}
						if ((!(new File(EgovProperties.getProperty("Globals.contentsPath") + "contents/" + contentsVO.getContentsId() + ".jsp").exists())) || (FileUtils.isFileOlder(new File(EgovProperties.getProperty("Globals.contentsPath") + "contents/" + contentsVO.getContentsId() + ".jsp"), sdf.parse(contentsVO.getUpdDate()) ))) {
							FileUtils.writeStringToFile(new File(EgovProperties.getProperty("Globals.contentsPath") + "contents/" + contentsVO.getContentsId() + ".jsp"), indexVO.getDefaultHeader() + contentsVO.getContentsContent(), "UTF-8");
						}
					// ::::::::::::::::::::::::::::::::::::::::::::: Contents 조회 및 파일 생성 종료 :::::::::::::::::::::::::::::::::::::::::::
					// ::::::::::::::::::::::::::::::::::::::::::::: Board-Skin 조회 및 파일 생성 시작 :::::::::::::::::::::::::::::::::::::::::
					}else if(contentsVO.getContentsType().equals("BBS")) {
	
						String sBbsId = boardVO.getBbsId() == "" ? contentsVO.getBbsId() : boardVO.getBbsId();
						String sFlag = boardVO.getFlag() == null ? "list" : boardVO.getFlag();
						if(sBbsId.equals("BBSMSTR_000000000021")) {		// 문의하기 게시판은 form만
							sFlag = "form";
						}
						boardVO.setFlag(sFlag);
						
						// 메뉴에 등록된 bbsId 등록
						boardVO.setBbsId(sBbsId);
	
						BoardMasterVO vo = new BoardMasterVO();
						vo.setBbsId(boardVO.getBbsId());
						BoardMasterVO master = egovBBSMasterService.selectBBSMasterInf(vo);
						model.addAttribute("brdMstrVO", master);
						
						// skin 조회
						SkinVO skinVO = new SkinVO();
						skinVO.setSkinId(master.getSkinId());
						skinVO = skinService.selectSkinView(skinVO);
						model.addAttribute("skinVO", skinVO);

						// CNT 변환 작업 및 파일 생성
						try {
							// 패턴 선언
							Pattern regex = Pattern.compile("\\[(CNT):(CONTENTS_)([0-9]*)\\]");

							// ############################################# 공통 상단 CNT 조회 및 파일 생성 시작 ###########################################
							Matcher regexMatcher = regex.matcher(contentsVO.getBbsHeader());
							String incStr = "";
							while (regexMatcher.find()) {
								incStr = regexMatcher.group();
								incStr = incStr.substring(1, incStr.length() - 1);
								StringTokenizer st = new StringTokenizer(incStr, ":");
								ArrayList def = new ArrayList();

								while (st.hasMoreTokens()) {
									def.add(st.nextToken());
								}
								String type = (String)def.get(0);
								String contentId = (String)def.get(1);
								
								// 매칭된 CNT 콘텐츠 조회
								ContentsVO tmpContentsVO = new ContentsVO(); 
								tmpContentsVO.setContentsId(contentId);
								tmpContentsVO = contentsService.selectContentsView(tmpContentsVO);
								if (tmpContentsVO == null) {
						            throw new ProbeException("해당 콘텐츠가 존재하지 않습니다. 다시 확인 부탁드립니다.");
								}
								
								String CNTFile = "";
								
				            	if ((!(new File(EgovProperties.getProperty("Globals.contentsPath") + "include/" + tmpContentsVO.getContentsId() + ".jsp").exists())) || (FileUtils.isFileOlder(new File(EgovProperties.getProperty("Globals.contentsPath") + "include/" + tmpContentsVO.getContentsId() + ".jsp"), sdf.parse(tmpContentsVO.getUpdDate())))) {
				            		FileUtils.writeStringToFile(new File(EgovProperties.getProperty("Globals.contentsPath") + "include/" + tmpContentsVO.getContentsId() + ".jsp"), indexVO.getDefaultHeader() + tmpContentsVO.getBbsHeader(), "UTF-8");
				            	}
								CNTFile = "<jsp:include page=\"/_source/include/" + tmpContentsVO.getContentsId() + ".jsp\" flush=\"true\" />";
								contentsVO.setBbsHeader(contentsVO.getBbsHeader().replace("[" + incStr + "]", CNTFile));
							}
							// ############################################# 공통 상단 CNT 조회 및 파일 생성 종료 ###########################################
							// ############################################# 공통 하단 CNT 조회 및 파일 생성 시작 ###########################################
							regexMatcher = regex.matcher(contentsVO.getBbsFooter());
							incStr = "";
							while (regexMatcher.find()) {
								incStr = regexMatcher.group();
								incStr = incStr.substring(1, incStr.length() - 1);
								StringTokenizer st = new StringTokenizer(incStr, ":");
								ArrayList def = new ArrayList();

								while (st.hasMoreTokens()) {
									def.add(st.nextToken());
								}
								String type = (String)def.get(0);
								String contentId = (String)def.get(1);
								
								// 매칭된 CNT 콘텐츠 조회
								ContentsVO tmpContentsVO = new ContentsVO(); 
								tmpContentsVO.setContentsId(contentId);
								tmpContentsVO = contentsService.selectContentsView(tmpContentsVO);
								if (tmpContentsVO == null) {
						            throw new ProbeException("해당 콘텐츠가 존재하지 않습니다. 다시 확인 부탁드립니다.");
								}
								
								String CNTFile = "";
								
				            	if ((!(new File(EgovProperties.getProperty("Globals.contentsPath") + "include/" + tmpContentsVO.getContentsId() + ".jsp").exists())) || (FileUtils.isFileOlder(new File(EgovProperties.getProperty("Globals.contentsPath") + "include/" + tmpContentsVO.getContentsId() + ".jsp"), sdf.parse(tmpContentsVO.getUpdDate())))) {
				            		FileUtils.writeStringToFile(new File(EgovProperties.getProperty("Globals.contentsPath") + "include/" + tmpContentsVO.getContentsId() + ".jsp"), indexVO.getDefaultHeader() + tmpContentsVO.getBbsFooter(), "UTF-8");
				            	}
								CNTFile = "<jsp:include page=\"/_source/include/" + tmpContentsVO.getContentsId() + ".jsp\" flush=\"true\" />";
								contentsVO.setBbsFooter(contentsVO.getBbsFooter().replace("[" + incStr + "]", CNTFile));
							}
							// ############################################# 공통 하단 CNT 조회 및 파일 생성 종료 ###########################################
						}catch(PatternSyntaxException patternSyntaxException) {
							System.out.println("error::::::"+patternSyntaxException.toString());
						}
						
						// skin 파일 생성
						if((!(new File(EgovProperties.getProperty("Globals.contentsPath") + "skin/" + skinVO.getSkinId() + "/header.jsp").exists())) || (FileUtils.isFileOlder(new File(EgovProperties.getProperty("Globals.contentsPath") + "skin/" + skinVO.getSkinId() + "/header.jsp"), sdf.parse(contentsVO.getUpdDate())))) {
							FileUtils.writeStringToFile(new File(EgovProperties.getProperty("Globals.contentsPath") + "skin/" + skinVO.getSkinId() + "/header.jsp"), indexVO.getDefaultHeader() + contentsVO.getBbsHeader(), "UTF-8");
						}
						if((!(new File(EgovProperties.getProperty("Globals.contentsPath") + "skin/" + skinVO.getSkinId() + "/footer.jsp").exists())) || (FileUtils.isFileOlder(new File(EgovProperties.getProperty("Globals.contentsPath") + "skin/" + skinVO.getSkinId() + "/footer.jsp"), sdf.parse(contentsVO.getUpdDate())))) {
							FileUtils.writeStringToFile(new File(EgovProperties.getProperty("Globals.contentsPath") + "skin/" + skinVO.getSkinId() + "/footer.jsp"), indexVO.getDefaultHeader() + contentsVO.getBbsFooter(), "UTF-8");
						}
						if((!(new File(EgovProperties.getProperty("Globals.contentsPath") + "skin/" + skinVO.getSkinId() + "/list.jsp").exists())) || (FileUtils.isFileOlder(new File(EgovProperties.getProperty("Globals.contentsPath") + "skin/" + skinVO.getSkinId() + "/list.jsp"), sdf.parse(skinVO.getUpdDate())))) {
							FileUtils.writeStringToFile(new File(EgovProperties.getProperty("Globals.contentsPath") + "skin/" + skinVO.getSkinId() + "/list.jsp"), indexVO.getBbsHeader() + skinVO.getSkinList(), "UTF-8");
						}
						if((!(new File(EgovProperties.getProperty("Globals.contentsPath") + "skin/" + skinVO.getSkinId() + "/view.jsp").exists())) || (FileUtils.isFileOlder(new File(EgovProperties.getProperty("Globals.contentsPath") + "skin/" + skinVO.getSkinId() + "/view.jsp"), sdf.parse(skinVO.getUpdDate())))) {
							FileUtils.writeStringToFile(new File(EgovProperties.getProperty("Globals.contentsPath") + "skin/" + skinVO.getSkinId() + "/view.jsp"), indexVO.getBbsHeader() + skinVO.getSkinView(), "UTF-8");
						}
						if((!(new File(EgovProperties.getProperty("Globals.contentsPath") + "skin/" + skinVO.getSkinId() + "/form.jsp").exists())) || (FileUtils.isFileOlder(new File(EgovProperties.getProperty("Globals.contentsPath") + "skin/" + skinVO.getSkinId() + "/form.jsp"), sdf.parse(skinVO.getUpdDate())))) {
							FileUtils.writeStringToFile(new File(EgovProperties.getProperty("Globals.contentsPath") + "skin/" + skinVO.getSkinId() + "/form.jsp"), indexVO.getBbsHeader() + skinVO.getSkinForm(), "UTF-8");
						}
						
						
						//-------------------------------- FLAG 값이 list 일 때 시작 --------------------------------
						if(sFlag.equals("list")) {
					
							boardVO.setPageUnit(propertyService.getInt("pageUnit"));
							boardVO.setPageSize(propertyService.getInt("pageSize"));
					
							PaginationInfo paginationInfo = new PaginationInfo();
							
							paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
							paginationInfo.setRecordCountPerPage(Integer.parseInt(master.getBbsPageUnit()));
							paginationInfo.setPageSize(boardVO.getPageSize());
					
							boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
							boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
							boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
					
							Map<String, Object> map = egovArticleService.selectArticleList(boardVO);
							int totCnt = Integer.parseInt((String)map.get("resultCnt"));
							
							paginationInfo.setTotalRecordCount(totCnt);
					
							model.addAttribute("boardDataList", map.get("resultList"));
							model.addAttribute("boardDataCnt", map.get("resultCnt"));
							model.addAttribute("boardVO", boardVO);
							model.addAttribute("paginationInfo", paginationInfo);
							model.addAttribute("pageIndex", boardVO.getPageIndex());
							model.addAttribute("pageUnit", Integer.parseInt(master.getBbsPageUnit()));

							// 항목 list 조회
							BoardItemVO boardItemVO = new BoardItemVO();
							boardItemVO.setBbsId(boardVO.getBbsId());
							boardItemVO.setItemFlag("list");
							List<BoardItemVO> resultList = egovBBSMasterService.selectBBSItemList(boardItemVO);
							model.addAttribute("itemList", resultList);
		
							// 일반게시판 > 공지글 조회 후 리턴
							boardVO.setSearchNotice("Y");
							boardVO.setFirstIndex(0);
							boardVO.setRecordCountPerPage(3);
							Map<String, Object> map2 = egovArticleService.selectArticleList(boardVO);
							model.addAttribute("noticeList", map2.get("resultList"));
							model.addAttribute("noticeListCnt", map2.get("resultCnt"));
		
						//-------------------------------- FLAG 값이 list 일 때 종료 --------------------------------
						}else if(sFlag.equals("view")) {
						//-------------------------------- FLAG 값이 view 일 때 시작 --------------------------------
							// 조회수 증가 여부 지정
							boardVO.setPlusCount(true);
							if(master.getLoginChkAt().equals("Y")) {
								MemberVO user = (MemberVO) request.getSession().getAttribute("loginVO");
								if(user == null) {
									model.addAttribute("message", "로그인이 필요한 서비스입니다.");
									model.addAttribute("retType", ":back");
									
									return "/common/message";
								}
							}

							BoardVO resultVO = egovArticleService.selectArticleDetail(boardVO);
							if(resultVO == null) {
								return "redirect:/";
							}
							
							resultVO.setFlag(sFlag);
							resultVO.setSearchCnd(boardVO.getSearchCnd());
							resultVO.setSearchWrd(boardVO.getSearchWrd());
							resultVO.setSearchCate1(boardVO.getSearchCate1());
							resultVO.setSearchCate2(boardVO.getSearchCate2());
							resultVO.setSearchCate3(boardVO.getSearchCate3());
							model.addAttribute("boardVO", resultVO);
							model.addAttribute("pageIndex", boardVO.getPageIndex());
							
							FileVO file = new FileVO();
							file.setAtchFileId(resultVO.getAtchFileId());
							List<FileVO> result = fileMngService.selectFileInfs(file);
							
							model.addAttribute("fileList", result);
						//-------------------------------- FLAG 값이 view 일 때 종료 --------------------------------
						}else if(sFlag.equals("form")) {
						//-------------------------------- FLAG 값이 form 일 때 시작 --------------------------------
							String command = request.getParameter("command")==null?"write":request.getParameter("command");
							boardVO.setCommand(command);
							if(command.equals("update")) {//글 수정 page 이동
								MemberVO user = (MemberVO) request.getSession().getAttribute("loginVO");
								if(user == null) {
									model.addAttribute("message", "로그인이 필요한 서비스입니다.");
									model.addAttribute("retType", ":back");
									
									return "/common/message";
								}else {
									BoardVO resultVO = egovArticleService.selectArticleDetail(boardVO);
									if(resultVO == null) {
										model.addAttribute("message", "시스템 오류입니다.");
										model.addAttribute("retType", ":back");
										
										return "/common/message";
									}else {
										String ntcrId = resultVO.getNtcrId()==null?"":resultVO.getNtcrId();
										if(ntcrId.equals(user.getMemberId())) {
											resultVO.setFlag(sFlag);
											resultVO.setCommand(command);
											resultVO.setSearchCnd(boardVO.getSearchCnd());
											resultVO.setSearchWrd(boardVO.getSearchWrd());
											resultVO.setSearchCate1(boardVO.getSearchCate1());
											resultVO.setSearchCate2(boardVO.getSearchCate2());
											resultVO.setSearchCate3(boardVO.getSearchCate3());
											
											FileVO file = new FileVO();
											file.setAtchFileId(resultVO.getAtchFileId());
											List<FileVO> result = fileMngService.selectFileInfs(file);
											
											model.addAttribute("fileList", result);
											model.addAttribute("boardVO", resultVO);
											model.addAttribute("pageIndex", boardVO.getPageIndex());
										}else {
											model.addAttribute("message", "시스템 오류입니다.");
											model.addAttribute("retType", ":back");
											
											return "/common/message";
										}
									}
								}
							}else {//글 등록 page 이동
								model.addAttribute("boardVO", boardVO);
								model.addAttribute("pageIndex", boardVO.getPageIndex());
							}
						//-------------------------------- FLAG 값이 form 일 때 종료 --------------------------------
						}
						

						//카테고리 조회
						BoardCateVO boardCateVO = new BoardCateVO();
						boardCateVO.setBbsId(boardVO.getBbsId());
						if(master.getCateType01().equals("Y")) {	//카테고리 1 사용
							boardCateVO.setCateType("CATE01");
							Map<String, Object> map = egovBBSMasterService.selectBBSCateList(boardCateVO);
							model.addAttribute("categoryList1", map.get("resultList"));
						}
						if(master.getCateType02().equals("Y")) {	//카테고리 2 사용
							boardCateVO.setCateType("CATE02");
							Map<String, Object> map2 = egovBBSMasterService.selectBBSCateList(boardCateVO);
							model.addAttribute("categoryList2", map2.get("resultList"));
						}
						if(master.getCateType03().equals("Y")) {	//카테고리 3 사용
							boardCateVO.setCateType("CATE03");
							Map<String, Object> map3 = egovBBSMasterService.selectBBSCateList(boardCateVO);
							model.addAttribute("categoryList3", map3.get("resultList"));
						}
						return "/common/board";
					}
					// ::::::::::::::::::::::::::::::::::::::::::::: Board-Skin 조회 및 파일 생성 종료 :::::::::::::::::::::::::::::::::::::::::
				}
			}else {	// 해당 콘텐츠 존재하지 않을 때
				return "/common/error";
			}
		}else {	// 해당 메뉴가 존재하지 않을 때
			return "/common/error";
		}

		return "/common/contents";
	}
	
}
