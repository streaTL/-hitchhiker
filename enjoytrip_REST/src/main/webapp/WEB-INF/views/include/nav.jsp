<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<nav
	class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top"
	id="mainNav">
	<div class="container">
		<a class="navbar-brand" href="${root}/">Go! Trip!</a>
		<button
			class="navbar-toggler text-uppercase font-weight-bold bg-primary text-white rounded"
			type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			Menu <i class="fas fa-bars"></i>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ms-auto">
				<li class="nav-item mx-0 mx-lg-1"><a
					class="nav-link py-3 px-0 px-lg-3 rounded"
					href="${root}/board/map">Search</a></li>
				<li class="nav-item mx-0 mx-lg-1">
					<div class="dropdown">
						<button
							class="btn btn-secondary border-0 dropdown-toggle py-3 px-0 px-lg-3 rounded"
							type="button" data-bs-toggle="dropdown" aria-expanded="false">
							Board</button>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="${root}/board/announceList">announce</a></li>
							<li><a class="dropdown-item" href="${root}/board/freeList">Free
									Board</a></li>
						</ul>
					</div>
				</li>
				<c:if test="${empty userinfo}">
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="${root}/user/login"
						style="display: block" id="loginNavBtn">Login</a></li>
				</c:if>

				<c:if test="${not empty userinfo}">
					<li class="nav-item mx-0 mx-lg-1" id="myPageBtn"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="${root}/user/myPage">My
							Page</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded"
						href="${root}/user/logout" id="logoutNavBtn">Logout</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>