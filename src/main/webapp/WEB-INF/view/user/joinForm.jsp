<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>
        <%@ include file="../layout/nav.jsp" %>

    <div class="container my-3">
        <div class="container">
            <form action="/join" method="post" onsubmit="return valid()">
                <div class="d-flex form-group mb-2">
                    <input id="username" type="text" name="username" class="form-control" 
                      placeholder="Enter username" required>
                    <button type="button" class="badge bg-secondary ms-2">중복확인</button>
                </div>

                <div class="form-group mb-2">
                    <input id="password" type="password" name="password" class="form-control"
                      placeholder="Enter password" required>
                </div>

                <div class="form-group mb-2">
                    <input id="passwordCheck" type="password" class="form-control" 
                      placeholder="Enter passwordCheck" required>
                </div>

                <div class="form-group mb-2">
                    <input id="email" type="email" name="email" class="form-control" 
                      placeholder="Enter email" required>
                </div>

                <button type="submit" class="btn btn-primary">회원가입</button>
            </form>
        </div>
    </div>


    <script>
        function valid() {

            if ($("#password").val() != $("#passwordCheck").val()) {
                alert("비밀번호가 일치하지 않습니다.")
                return false;
            }
            // alert("회원가입 유효성 검사");
        }
    </script>

            <%@ include file="../layout/footer.jsp" %>