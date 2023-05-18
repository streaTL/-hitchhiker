<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Go! Trip!</title>
    <link rel="icon" type="image/x-icon" href="${root}/assets/favicon.ico" />
    <script
      src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
      crossorigin="anonymous"
    ></script>
    <link
      href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
      rel="stylesheet"
      type="text/css"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
      rel="stylesheet"
      type="text/css"
    />
    <link href="${root}/assets/css/styles.css" rel="stylesheet" />
  </head>

  <body id="page-top">
    <!-- Navigation-->
    <%@ include file="/WEB-INF/views/include/nav.jsp" %>

    <section class="masthead bg-primary text-white text-center" id="loginBackground">
      <div class="container d-flex align-items-center flex-column card2 border-white">
        <h1 class="masthead-heading text-uppercase mb-0">My Page</h1>

        <div class="divider-custom divider-light">
          <div class="divider-custom-line"></div>
          <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
          <div class="divider-custom-line"></div>
        </div>

        <form name="login-form" class="login-form">
          <div class="d-flex mb-4 justify-content-md-between">
            <h3 class="masthead-heading text-uppercase mb-0 fs-1 me-5">ID</h3>
            <input
              disabled
              type="text"
              class="form-control input-sm"
              id="userId"
              style="width: 200px"
              placeholder="${userinfo.id}"
            />
          </div>
          <div class="d-flex mb-4 justify-content-md-between">
            <h3 class="masthead-heading text-uppercase mb-0 fs-1 me-5">E-MAIL</h3>
            <input
              disabled
              type="text"
              class="form-control input-sm"
              id="userEmail"
              style="width: 200px"
              placeholder="${userinfo.email}"
            />
          </div>
          <div class="d-flex mb-4 justify-content-md-between">
            <h3 class="masthead-heading text-uppercase mb-0 fs-1 me-5">NAME</h3>
            <input
              disabled
              type="text"
              class="form-control input-sm"
              id="userName"
              style="width: 200px"
              placeholder="${userinfo.name}"
            />
          </div>
          <a
            class="btn btn-outline-light text-nowrap mt-3 input-sm me-3"
            href="${root}/user/editUser"
            id="editUser"
          >
            수정
          </a>
          <a
            class="btn btn-outline-light text-nowrap mt-3 input-sm me-3"
            id="leaveUser"
            onclick="alert('탈퇴 되었습니다.')"
            href="${root}/"
          >
            탈퇴
          </a>
        </form>
      </div>
    </section>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="${root}/assets/js/main.js"></script>
  </body>
</html>
