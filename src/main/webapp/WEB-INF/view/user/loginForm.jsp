<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>
        <%@ include file="../layout/nav.jsp" %>

            <div class="container my-3">
                <div class="container d-flex justify-content-center">
                    <form action="/login" method="post">
                        <div class="form-group mb-2">
                            <input type="text" name="username" class="form-control" placeholder="Enter username"
                                id="username" size="40">

                        </div>

                        <div class="form-group mb-2">
                            <input type="password" name="password" class="form-control" placeholder="Enter password"
                                id="password" size="40">
                        </div>
                        <div class="d-flex justify-content-end">
                            <button type="submit" class="btn btn-primary">로그인</button>
                        </div>
                    </form>

                </div>
            </div>


            <%@ include file="../layout/footer.jsp" %>