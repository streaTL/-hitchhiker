<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Go! Trip!</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="${root}/assets/favicon.ico" />
<!-- Font Awesome icons (free version)-->

<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
	crossorigin="anonymous"></script>
<script src="${root}/assets/js/key.js"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="${root }/assets/css/styles.css" rel="stylesheet" />

</head>
<body id="page-top">
	<!-- Navigation-->
	<%@ include file="/WEB-INF/views/include/confirm.jsp" %>
	<%@ include file="/WEB-INF/views/include/nav.jsp"%>
	<!-- Masthead-->

	<section class="page-section masthead" id="contact">
		<div class="container">
			<div class="row justify-content-center">
				<h1>공지사항 수정</h1>
				<%-- 입력 폼 --%>
				<form action="${root}/board/modifyAnnounce/${board.articleNo}" method="post">
					<input type="text" name="subject" class="form-control mt-4 mb-2"
						placeholder="제목을 입력해주세요." value="${board.subject}" required>
					<div class="form-group">
						<textarea class="form-control" rows="10" name="content"
							placeholder="내용을 입력해주세요" required>${board.content}</textarea>
					</div>
					<button type="submit" class="btn btn-secondary mt-3 mb-3">수정하기</button>
				</form>

			</div>
	</section>

	<!-- Footer-->
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	<!-- Copyright Section-->
	<div class="copyright py-4 text-center text-white">
		<div class="container">
			<small>Copyright &copy; Your Website 2022</small>
		</div>
	</div>

	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="${root}/assets/js/scripts.js"></script>

	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
	<script src="${root}/assets/js/main.js"></script>
</body>
</html>