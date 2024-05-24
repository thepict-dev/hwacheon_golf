<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.breeze.menu.web.MenuBundle"%>			
<%@ page import="egovframework.breeze.site.service.MenuVO"%>			
<%@ page import="egovframework.breeze.member.web.SessionBundle"%>		
<%@ page import="egovframework.breeze.member.service.MemberVO"%>		
<%@ page import="egovframework.breeze.code.web.CodeBundle"%>			
<%@ page import="egovframework.breeze.code.service.CodeVO"%>			
<%												
	MenuBundle mb = new MenuBundle(request);		
	SessionBundle sb = new SessionBundle(request);	
	CodeBundle cb = new CodeBundle(request);		
%>												
<style>
        html, body{
          width: 100%;
          height: 100%;
        }
        .wrap {
          width: 100%;
          height: 100%;
        }
        .vod{
          position: absolute;
          left: 0;
          top: 0;
          overflow: hidden;
          width: 100%;
          height: 100%;
          display: flex;
          align-items: center;
          justify-content: center;
        }
        .intro-wrap{
          overflow: hidden;
          display: flex;
          position: absolute;
          left: 50%;
          top: 95%;
          transform: translate(-50%, -50%);

        }
        video {
          object-fit: cover;
        }
        .intro-link{
          display: block;
          flex-direction: row;
          width: 200px;
          height: 50px;
          margin: 0 20px;
          line-height: 50px;
          text-align: center;
          border: 1px solid #fff;
          color: #fff;
          font-size: 17px;
          font-family: "NotoSansKR-Regular";
        }
        .intro-link:hover{
          background-color: #e6081f;
          transition: all 0.5s;
          border: #e6081f
        }
        @media screen and (max-width: 768px) {
          .intro-link{
          width: 150px;
          height: 40px;
          margin: 0 15px;
          line-height: 40px;
          font-size: 14px;
          font-family: "NotoSansKR-Regular";
          }
        }
          @media screen and (max-width: 414px) {
            .vod {
          position: fixed;
          left: 0;
          top: 0;
          display: flex;
          justify-content: center;
          overflow: hidden;
          width: 100%;
          height: 100vh;
          }
          .intro-wrap{
          overflow: hidden;
          display: flex;
          position: absolute;
          left: 50%;
          top: 90%;
          transform: translate(-50%, -50%);
          }
            .intro-link{
            width: 100px;
            height: 40px;
            margin: 0 15px;
            line-height: 40px;
            font-size: 12px;
          font-family: "NotoSansKR-Regular";
          }
        }
    </style>