<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">Blog</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-between" id="collapsibleNavbar">
                <ul class="navbar-nav">
                    <c:choose>
                       <c:when test="${principal == null}">
                        <li class="nav-item">
                            <a class="nav-link" href="/loginForm">로그인</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/joinForm">회원가입</a>
                        </li>
                       </c:when>
                    
                       <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="/board/saveForm">글쓰기</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/user/${principal.id}/updateForm">회원정보</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/logout">로그아웃</a>
                        </li>                       
                       </c:otherwise>
                    </c:choose>



                </ul>
                <div>
                    <a href="/user/profileUpdate"><img src="/images/profile.png"
                            style="width: 35px;" class="rounded-circle" alt="Cinque Terre"></a>
                </div>
            </div>

        </div>
    </nav>