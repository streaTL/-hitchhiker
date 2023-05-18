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
<link rel="icon" type="image/x-icon" href="${root }/assets/favicon.ico" />
<!-- Font Awesome icons (free version)-->

<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
	crossorigin="anonymous"></script>
<script src="${root }/js/key.js"></script>
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
	<%@ include file="/WEB-INF/views/include/nav.jsp"%>
	<!-- Masthead-->

	<section class="page-section masthead" id="contact">
		<div class="container">
			<!-- Contact Section Heading-->
			<h2
				class="page-section-heading text-center text-uppercase text-secondary mb-0">
				${msg}게시판</h2>
			<!-- Icon Divider-->
			<div class="divider-custom">
				<div class="divider-custom-line"></div>
				<div class="divider-custom-icon">
					<i class="fas fa-star"></i>
				</div>
				<div class="divider-custom-line"></div>
			</div>
			<!-- Contact Section Form-->
			<div class="row justify-content-center">
				<table class="table board border-1" id="announce-board">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>조회수</th>
							<th>날짜</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="board" items="${boards }">
							<tr>
								<td>${board.articleNo}</td>
								
								<c:if test="${msg eq '공지'}">
									<td><a href="${root}/board/announceList/${board.articleNo}" class="article-title link-dark" data-no="${article.articleNo}" style="text-decoration: none">${board.subject} </a></td>
								</c:if>
								<c:if test="${msg eq '자유'}">
									<td><a href="${root}/board/freeList/${board.articleNo}" class="article-title link-dark" data-no="${article.articleNo}" style="text-decoration: none">${board.subject} </a></td>
								</c:if>
								<td>${board.userId}</td>
								<td>${board.hit}</td>
								<td>${board.date}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<hr />
				<div>
					<ul class="pagination justify-content-center">
						<li><a href="#" style="margin-right: 5px"
							class="text-secondary">◀ </a></li>
						<li><a href="#" style="margin-right: 5px"
							class="text-secondary">1 </a></li>
						<li><a href="#" style="margin-right: 5px"
							class="text-secondary">2 </a></li>
						<li><a href="#" style="margin-right: 5px"
							class="text-secondary">3 </a></li>
						<li><a href="#" style="margin-right: 5px"
							class="text-secondary">▶ </a></li>
					</ul>
				</div>
				<c:if test="${msg eq '공지'}">
					<a class="btn btn-dark text-nowrap mt-3 input-sm me-3" id="write" href="${root}/board/announceList/write">글쓰기</a>
				</c:if>
				<c:if test="${msg eq '자유'}">
					<a class="btn btn-dark text-nowrap mt-3 input-sm me-3" id="write" href="${root}/board/freeList/write">글쓰기</a>
				</c:if>
			</div>
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
	<script src="${root }/assets/js/scripts.js"></script>

	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>

	<script src="${root }/assets/js/main.js"></script>
</body>
</html>
