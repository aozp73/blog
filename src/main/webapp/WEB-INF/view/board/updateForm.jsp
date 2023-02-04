<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>
        <%@ include file="../layout/nav.jsp" %>


            <div class="container my-3">
                <form>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Enter title" name="title" id="title"
                            value="${board.title}">
                    </div>

                    <div class="form-group">
                        <textarea class="form-control summernote" rows="5" id="content" name="content">
                        ${board.content}
                        </textarea>
                    </div>
                </form>
                <button type="button" class="btn btn-primary" onclick="insert()">글수정완료</button>
            </div>

            <script>
                $('.summernote').summernote({
                    tabsize: 2,
                    height: 400
                });

                function insert() {
                    // 1. title, content 값 가져와서
                    //    JSON Object 담기
                    let put = {
                        id: `${board.id}`,
                        userId: `${board.userId}`,
                        title: $("#title").val(),
                        content: $("#content").val()
                    }


                    // 2. Controller한테 Json Object -> Json 문자열로 넘기기
                    $.ajax({
                        type: "put",
                        url: `/board/${board.id}/${board.userId}/update`,
                        data: JSON.stringify(put),
                        headers: {
                            "Content-Type": "application/json; charset=utf-8"
                        },
                        dataType: "json"
                    }).done((res) => {
                        console.log(res.code);
                        console.log(res.msg);
                        location.href=`/board/${board.id}`;
                    }).fail((err) => {
                        console.log(err.code);
                        console.log(err.msg);
                        location.href=`/board/${board.id}`;
                    });

                }

                // function insert() {
                //     if ($("#title").val == "") {
                //         alert("제목을 입력해주세요.")
                //     }

                //     if ($("#content").val == "") {
                //         alert("내용을 입력해주세요.")
                //     }
                // }
            </script>

            <%@ include file="../layout/footer.jsp" %>