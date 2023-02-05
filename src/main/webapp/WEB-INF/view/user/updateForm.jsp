<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>
        <%@ include file="../layout/nav.jsp" %>

            <div class="container my-3">
                <div class="container d-flex justify-content-center">
                    <form>
                        <div class="form-group mb-2">
                            <input id="username" type="text" name="username" class="form-control"
                                placeholder="Enter username" value="${user.username}" size="40" readonly>
                        </div>

                        <div class="form-group mb-2">
                            <input id="password" type="password" name="password" class="form-control"
                                placeholder="Enter password" value="${user.password}" size="40">
                        </div>

                        <div class="form-group mb-2">
                            <input id="email" type="email" name="email" class="form-control" placeholder="Enter email"
                                value="${user.email}" size="40">
                        </div>
                        <div class="d-flex justify-content-end">
                            <button type="button" class="btn btn-primary" onclick="userUpdate()">회원수정</button>
                        </div>
                    </form>

                </div>
            </div>

            <script>

                function verifyEmail() {
                    var email = $("#email").val();

                    if (email.indexOf('@') == -1) {
                        alert('이메일에 @를 포함해주세요');
                        return -1;
                    }

                    let checkArr = ['nate.com', 'naver.com', 'kakao.com'];
                    let check = false
                    for (let i = 0; i < checkArr.length; i++) {
                        if (email.indexOf(checkArr[i]) != -1) {
                            check = true;
                        }
                    }
                    if (!check) {
                        alert('등록하시려는 이메일 주소를 확인해주세요')
                        return -1;
                    }


                };
                function userUpdate() {
                    let check = verifyEmail();
                    if (check == -1) {
                        return
                    }
                    let user = {
                        userId: `${principal.id}`,
                        password: $("#password").val(),
                        email: $("#email").val()
                    }

                    if (!user.password) {
                        alert("비밀번호를 입력하세요")
                    } else if (!user.email) {
                        alert("email을 입력하세요")
                    }

                    $.ajax({
                        type: "put",
                        url: `/user/${principal.id}/update`,
                        data: JSON.stringify(user),
                        headers: {
                            "Content-Type": "application/json; charset=utf-8"
                        },
                        dataType: "json"
                    }).done((res) => {
                        alert(res.msg);
                    }).fail((err) => {
                        alert(err.msg);
                    });

                }
            </script>

            <%@ include file="../layout/footer.jsp" %>