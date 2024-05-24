<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.breeze.board.web.BoardBundle"%>			
<%@ page import="egovframework.com.cop.bbs.service.BoardVO"%>			
<%@ page import="egovframework.com.cop.bbs.service.BoardMasterVO"%>		
<%@ page import="egovframework.breeze.menu.web.MenuBundle"%>			
<%@ page import="egovframework.breeze.site.service.MenuVO"%>			
<%@ page import="egovframework.breeze.member.web.SessionBundle"%>		
<%@ page import="egovframework.breeze.member.service.MemberVO"%>		
<%@ page import="egovframework.breeze.code.web.CodeBundle"%>			
<%@ page import="egovframework.breeze.code.service.CodeVO"%>			
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>		
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>		
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>		
<%												
	BoardBundle bb = new BoardBundle(request);		
	MenuBundle mb = new MenuBundle(request);		
	SessionBundle sb = new SessionBundle(request);	
	CodeBundle cb = new CodeBundle(request);		
%>												
-