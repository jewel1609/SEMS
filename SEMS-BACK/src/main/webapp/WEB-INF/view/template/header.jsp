<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="wrapper row1">
  <header id="header" class="clear"> 
    <div id="logo" class="fl_left">
      <h1><a href="index.html">Smart Education Management System</a></h1>
    </div>
    <div class="fl_right">
      <ul class="inline">
        <li><i class="fa fa-phone"></i> +00 (123) 456 1316</li>
        <li><i class="fa fa-envelope-o"></i> cocomo@ktds.com</li>
        <li><i class="fa fa-power-off"></i> <a href="/backend/logout"> logout </a> </li>
      </ul>
    </div>
  </header>
</div>
<div class="wrapper row2">
  <nav id="mainav" class="clear"> 
    <ul class="clear">
      <li class="active"><a href="<c:url value="/main" />">Home</a></li>
      <li><a class="drop" href="<c:url value="/showEducationList" />">교육 관리</a>
        <ul>
          <li><a href="<c:url value="/showEducationList" />">교육 조회</a></li>
          <li><a href="<c:url value="/eduPlaceList" />">교육장 조회</a></li>
        </ul>
      </li>
      
      <li><a class="drop" href="<c:url value="/memberManage"/>">회원 관리</a>
      	<ul>
          <li><a href="<c:url value="/showEducationList" />">출결 조회</a></li>
        </ul>
      </li>
      
      <li><a class="drop" href="<c:url value="/teacher/teaacherList"/>">강사 관리</a></li>
      
      <li><a class="drop" href="<c:url value="/cooList"/>">협력사 관리</a></li>
      
      <li><a class="drop" href="<c:url value="/memberHistory" />">시스템 관리</a>
      	<ul>
          <li><a href="<c:url value="/memberHistory" />">회원 로그인 이력</a></li>
          <li><a href="<c:url value="/adminHistory" />">관리자 로그인 이력</a></li>
        </ul>
      </li>
    </ul>
  </nav>
</div>
