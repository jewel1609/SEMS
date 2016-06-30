<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<c:set value="${sessionScope._MEMBER_TYPE_}" var="memberType" />

<div class="wrapper row1">
  <header id="header" class="clear"> 
    <div id="logo" class="fl_left">
      <h1><a href="/main">Kt'ds SEMS</a></h1>
    </div>
    <div class="fl_right">
      <ul class="inline">
        <li><i class="fa fa-phone"></i> +00 (123) 456 1316</li>
        <li><i class="fa fa-envelope-o"></i> cocomo@ktds.com</li>
        <li><i class="fa fa-power-off"></i> <a href="/logout"> logout </a> </li>
        <!-- 관리자라면 관리하기 버튼 보여주기 -->
        <c:if test="${memberType eq 'ADM' }">
        	<li><i class="fa fa-cog"></i> <a href="#" onclick="window.open('/comm/main','Place Detail','toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizeable=no, width=930, height=820');"> 관리하기 </a> </li>
        </c:if>
      </ul>
    </div>
  </header>
</div>
<div class="wrapper row2">
  <nav id="mainav" class="clear"> 
    <ul class="clear">
      <li class="active"><a href="/main">Home</a></li>
      <li><a class="drop" href="#">Pages</a>
        <ul>
          <li><a href="pages/gallery.html">Gallery</a></li>
          <li><a href="pages/full-width.html">Full Width</a></li>
          <li><a href="pages/sidebar-left.html">Sidebar Left</a></li>
          <li><a href="pages/sidebar-right.html">Sidebar Right</a></li>
          <li><a href="pages/basic-grid.html">Basic Grid</a></li>
        </ul>
      </li>
      <li><a class="drop" href="#">Dropdown</a>
        <ul>
          <li><a href="#">Level 2</a></li>
          <li><a class="drop" href="#">Level 2 + Drop</a>
            <ul>
              <li><a href="#">Level 3</a></li>
              <li><a href="#">Level 3</a></li>
            </ul>
          </li>
        </ul>
      </li>
      <li><a href="#">Link Text</a></li>
      <li><a href="#">Link Text</a></li>
    </ul>
  </nav>
</div>
<div class="wrapper row3">
  <div id="slider" class="clear"> 
    <div class="flexslider basicslider">
      <ul class="slides">
        <li><a href="#"><img class="radius-10" src="images/demo/slides/01.png" alt=""></a></li>
        <li><a href="#"><img class="radius-10" src="images/demo/slides/02.png" alt=""></a></li>
        <li><a href="#"><img class="radius-10" src="images/demo/slides/03.png" alt=""></a></li>
      </ul>
    </div>
  </div>
</div>
