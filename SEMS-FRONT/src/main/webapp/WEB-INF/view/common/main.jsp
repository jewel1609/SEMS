<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Kt'ds SEMS</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<link rel="stylesheet" href="/resources/css/layout.css" />
</head>

<c:set value="${sessionScope._MEMBER_TYPE_}" var="memberType" />
<body id="top">
<div class="wrapper row3">
  <main class="container clear"> 
    <!-- main body --> 
    <ul class="nospace group btmspace-80">
      <li class="one_third first">
        <article class="service"><i class="icon fa fa-ambulance"></i>
          <h6 class="heading"><a href="/educationList">Lorem Ipsum Dolor</a></h6>
          <p>Aenean semper elementum tellus, ut placerat leo. Quisque vehicula, urna sit amet.</p>
          <footer><a href="/educationList">Read More &raquo;</a></footer>
        </article>
      </li>
      <li class="one_third">
        <article class="service"><i class="icon fa fa-h-square"></i>
          <h6 class="heading"><a href="/myPage/myReportList">Lorem Ipsum Dolor</a></h6>
          <p>Aenean semper elementum tellus, ut placerat leo. Quisque vehicula, urna sit amet.</p>
          <footer><a href="/myPage/myReportList">Read More &raquo;</a></footer>
        </article>
      </li>
      <li class="one_third">
        <article class="service"><i class="icon fa fa-hospital-o"></i>
          <h6 class="heading"><a href="#">Lorem Ipsum Dolor</a></h6>
          <p>Aenean semper elementum tellus, ut placerat leo. Quisque vehicula, urna sit amet.</p>
          <footer><a href="#">Read More &raquo;</a></footer>
        </article>
      </li>
    </ul>
    <h2 class="sectiontitle">Lorem Ipsum Dolor</h2>
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
        <li>
          <figure><img class="radius-10 btmspace-10" src="images/demo/320x185.png" alt="">
            <figcaption><a href="#">Lorem Ipsum Dolor Sit Amet</a></figcaption>
          </figure>
        </li>
        <li>
          <figure><img class="radius-10 btmspace-10" src="images/demo/320x185.png" alt="">
            <figcaption><a href="#">Lorem Ipsum Dolor Sit Amet</a></figcaption>
          </figure>
        </li>
        <li>
          <figure><img class="radius-10 btmspace-10" src="images/demo/320x185.png" alt="">
            <figcaption><a href="#">Lorem Ipsum Dolor Sit Amet</a></figcaption>
          </figure>
        </li>
        <li>
          <figure><img class="radius-10 btmspace-10" src="images/demo/320x185.png" alt="">
            <figcaption><a href="#">Lorem Ipsum Dolor Sit Amet</a></figcaption>
          </figure>
        </li>
        <li>
          <figure><img class="radius-10 btmspace-10" src="images/demo/320x185.png" alt="">
            <figcaption><a href="#">Lorem Ipsum Dolor Sit Amet</a></figcaption>
          </figure>
        </li>
      </ul>
    </div>
    <h2 class="sectiontitle">Lorem Ipsum Dolor</h2>
    <ul class="nospace group">
      <li class="one_half first">
        <article><img class="imgl radius-10" src="images/demo/100x100.gif" alt="">
          <h6 class="heading"><a href="#">Lorem Ipsum Dolor</a></h6>
          <p>Aenean semper elementum tellus, ut placerat leo. Quisque vehicula, urna sit amet.</p>
        </article>
      </li>
      <li class="one_half">
        <article><img class="imgl radius-10" src="images/demo/100x100.gif" alt="">
          <h6 class="heading"><a href="#">Lorem Ipsum Dolor</a></h6>
          <p>Aenean semper elementum tellus, ut placerat leo. Quisque vehicula, urna sit amet.</p>
        </article>
      </li>
    </ul>
    <!-- / main body -->
    <div class="clear"></div>
  </main>
</div>
</body>
</html>