<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>
        <%@ include file="../layout/nav.jsp" %>

            <div class="container my-3">
                <div class="container d-flex justify-content-center">
                    <form action="/join" method="post" onsubmit="return valid()">

                        <div class="d-flex form-group mb-2">
                            <div class="d-flex ">
                                <input id="username" type="text" name="username" class="form-control"
                                    placeholder="Enter username" size="30">
                            </div>
                            <button type="button" class="badge bg-secondary ms-2">중복확인</button>
                        </div>



                        <div class="d-flex form-group mb-2">
                            <div class="d-flex">
                                <input id="password" type="password" name="password" class="form-control"
                                    placeholder="Enter password" size="40">
                            </div>
                        </div>

                        <div class="d-flex form-group mb-2">
                            <div style="position :relative;" class="d-flex">
                                <input id="passwordCheck" type="password" name="passwordCheck" class="form-control"
                                    placeholder="Enter passwordCheck" size="40">
                                <div id="passwordCheckBox">

                                </div>
                            </div>

                        </div>

                        <div class="d-flex form-group mb-2">
                            <div class="d-flex">
                                <input id="email" type="email" name="email" class="form-control"
                                    placeholder="Enter email" size="40">
                            </div>
                        </div>
                        <div class="d-flex justify-content-end">
                            <button type="submit" class="btn btn-primary">회원가입</button>
                        </div>
                    </form>
                </div>
            </div>


            <script>
                function valid() {
                    // alert("회원가입 유효성 검사");
                }

                $("#password, #passwordCheck").keyup(() => {
                    if (($("#password").val() != $("#passwordCheck").val())
                        && (($("#password").val()) != "") && ($("#passwordCheck").val() != "")) {

                        $("#passwordCheck").css("background-color", "#E8D1C3");
                        let el = `
                            <div id="passwordCheckChild" style="position: absolute; right: 10px; top: 10px; font-size:x-small; ">
                                 <i style=" color:chocolate">비밀번호 불일치</i>
                            </div>
                        `;
                        $("#passwordCheckBox").empty();
                        $("#passwordCheckBox").append(el);

                    } else {
                        $("#passwordCheck").removeAttr("style");
                        $("#passwordCheckBox").empty();
                    }
                })
            </script>

            <%@ include file="../layout/footer.jsp" %>