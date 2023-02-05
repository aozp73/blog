<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>
        <%@ include file="../layout/nav.jsp" %>

            <div class="container my-3">
                <div class="mb-3">
                    <a href="/board/${board.id}/${board.userId}/updateForm" class="btn btn-warning">수정</a>
                    <button id="btn-delete" class="btn btn-danger" onclick="postDelete()">삭제</button>
                </div>

                <div class="mb-2">
                    글 번호 : <span id="id"><i>${board.id} </i></span> 작성자 : <span><i>${board.username} </i></span>

                    <c:choose>
                        <c:when test="${love.isCheck == null}">
                            <div id="heartPicture" class="fa-regular fa-heart my-xl my-cursor" value="no"></div>
                            <input id="heart" type="hidden" value="no">
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${love.isCheck}">
                                    <div id="heartPicture" class="fa-regular fa-heart my-xl my-cursor" value="ok"></div>
                                    <input id="heart" type="hidden" value="ok">
                                </c:when>
                                <c:otherwise>
                                    <div id="heartPicture" class="fa-regular fa-heart my-xl my-cursor" value="no"></div>
                                    <input id="heart" type="hidden" value="no">
                                </c:otherwise>
                            </c:choose>

                        </c:otherwise>
                    </c:choose>


                </div>

                <div id="tes" value="a"></div>
                <div>
                    <h3>${board.title}</h3>
                </div>
                <hr />
                <div>
                    <div>${board.content}</div>
                </div>
                <hr />

                <div class="card">
                    <form>
                        <div class="card-body">
                            <textarea id="reply-content" class="form-control" rows="1"></textarea>
                        </div>
                        <div class="card-footer">
                            <button type="button" id="btn-reply-save" class="btn btn-primary"
                                onclick="replyPost()">등록</button>
                        </div>
                    </form>
                </div>
                <br />
                <div class="card">
                    <div class="card-header">댓글 리스트</div>

                    <c:forEach items="${replyList}" var="reply">
                        <c:if test="${reply.boardId == board.id}">
                            <ul id="reply-box${reply.id}" class="list-group">
                                <li id="reply-1" class="list-group-item d-flex justify-content-between">
                                    <div>
                                        <div>${reply.content}</div>
                                    </div>
                                    <div class="d-flex">
                                        <div class="font-italic">작성자 : ${reply.username} &nbsp;</div>
                                        <button onClick="replyDelete(`${reply.id}`, `${reply.userId}`)"
                                            class="badge bg-secondary">삭제</button>

                                    </div>
                                </li>
                            </ul>
                        </c:if>
                    </c:forEach>

                </div>
            </div>

            <script>

                function printLove() {
                    let value = $("#heart").val();
                    if ($("#heart").val() == "ok") {
                        $("#heartPicture").addClass("fa-solid");
                    }
                }

                $("#heartPicture").click(() => {
                    if (`${principal}`) {
                        let heart = $("#heart").val();
                        // 통신
                        $.ajax({
                            type: "post",
                            url: `/love/${board.id}/insert`,
                            data: heart,
                            headers: {
                                "Content-Type": "application/json; charset=utf-8"
                            },
                            dataType: "json"
                        }).done((res) => {
                            console.log(res.code)
                            console.log(res.isCheck)
                        }).fail((err) => {
                            console.log(err.code)
                            console.log(err.isCheck)
                        });

                        let value = $("#heart").val();
                        if (value == "ok") {
                            $("#heartPicture").removeClass("fa-solid");
                            $("#heart").val("no");
                        } else {
                            $("#heartPicture").addClass("fa-solid");
                            $("#heart").val("ok");
                        }

                    }
                });



                function replyDelete(replyId, replyUserId) {
                    // Client -> Controller 통신
                    $.ajax({
                        type: "delete",
                        url: `/reply/${board.id}/` + replyId + `/${board.userId}/` + replyUserId + `/delete`,
                        dataType: "json"

                    }).done((res) => {
                        if (res.code == 1) {
                            $("#reply-box" + replyId).remove();
                            alert("댓글 삭제 성공");
                            location.href = `/board/${board.id}`;
                            console.log(res);
                        } else if (res.msg == "로그인 필요") {
                            alert("로그인이 필요합니다")
                            location.href = `/board/${board.id}`;
                            console.log(res);
                        } else if (res.msg == "권한 필요") {
                            alert("삭제 권한이 없습니다")
                            location.href = `/board/${board.id}`;
                            console.log(res);
                        }

                        // reply-box${reply.id}
                    }).fail((err) => {
                        if (err.msg == "DB오류") {
                            alert("오류 발생 (고객센터 문의 부탁드립니다)")
                        } else if (err.msg = "권한 필요") {
                            alert("권한이 없습니다")
                        }
                        console.log(err);
                        location.href = `/board/${board.id}`;
                    });
                }

                /* 게시글 Delete */
                function postDelete() {
                    // Clinet -> Controller 통신
                    $.ajax({
                        type: "delete",
                        url: `/board/${board.id}/${board.userId}/delete`,
                        dataType: "json"

                    }).done((res) => {
                        if (res.code == 1) {
                            alert("게시글 삭제 성공");
                            console.log(res);
                            location.href = "/";
                        } else if (res.msg == "로그인 필요") {
                            alert("로그인이 필요합니다")
                            console.log(res);
                            location.href = `/board/${board.id}`;
                        } else if (res.msg == "권한 필요") {
                            alert("삭제 권한이 없습니다")
                            console.log(res);
                            location.href = `/board/${board.id}`;
                        }


                    }).fail((err) => {
                        if (err.msg = "DB에러") {
                            alert("오류 발생 (고객센터 문의 부탁드립니다)")
                        } else if (err.msg = "권한 필요") {
                            alert("권한이 없습니다")
                        }
                        location.href = `/board/${board.id}`;
                    });
                } /* 게시글 Delete */


                /* 댓글 Post */
                function replyPost() {
                    let replyContent = $("#reply-content").val();
                    // 1. 유효성 검사 (댓글 공백)
                    if (!replyContent) {
                        alert("내용을 입력해주세요");
                    }

                    // 2. JS 오브젝트
                    let reply = {
                        boardId: `${board.id}`,
                        userId: `${principal.id}`,
                        username: `${principal.username}`,
                        content: replyContent,
                    }

                    // Client -> Controller 통신
                    $.ajax({
                        type: "post",
                        url: "/reply/insert",
                        data: JSON.stringify(reply),
                        headers: {
                            "Content-Type": "application/json; charset=utf-8"
                        },
                        dataType: "json"

                    }).done((res) => {
                        console.log(res.msg);
                        if (res.msg == "로그인 필요") {
                            alert("로그인이 필요합니다")
                            location.href = `/board/${board.id}`;
                        }
                        location.href = `/board/${board.id}`;
                    }).fail((err) => {
                        console.log(err.msg)
                    });
                }/* 댓글 Post */


                printLove()
            </script>

            <%@ include file="../layout/footer.jsp" %>