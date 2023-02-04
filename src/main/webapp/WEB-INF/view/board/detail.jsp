<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>
        <%@ include file="../layout/nav.jsp" %>

            <div class="container my-3">
                <div class="mb-3">
                    <a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
                    <button id="btn-delete" class="btn btn-danger" onclick="deletePost()">삭제</button>
                </div>

                <div class="mb-2">
                    글 번호 : <span id="id"><i>${board.id} </i></span> 작성자 : <span><i>${board.username} </i></span>
                    <div id="heart" class="fa-regular fa-heart my-xl my-cursor" value="no"></div>
                </div>

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
                            <button type="button" id="btn-reply-save" class="btn btn-primary">등록</button>
                        </div>
                    </form>
                </div>
                <br />
                <div class="card">
                    <div class="card-header">댓글 리스트</div>
                    <ul id="reply-box" class="list-group">
                        <li id="reply-1" class="list-group-item d-flex justify-content-between">
                            <div>댓글내용입니다</div>
                            <div class="d-flex">
                                <div class="font-italic">작성자 : cos &nbsp;</div>
                                <button onClick="replyDelete()" class="badge bg-secondary">삭제</button>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>

            <script>
                function deletePost() {
                    // boardId, userId 담기
                    // let deleteInfo = {
                    //     id: `${board.id}`,
                    //     userId: `${board.userId}`
                    // }

                    // Clinet -> Controller 통신
                    $.ajax({
                        type: "delete",
                        url: `/board/${board.id}/${board.userId}/delete`,
                        dataType: "json"

                    }).done((res) => {
                        if (res.code == 1) {
                            alert("게시글 삭제 성공");
                            location.href = "/";
                        } else if (res.msg == "로그인이 필요합니다") {
                            alert("로그인이 필요합니다")
                            location.href = `/board/${board.id}`;
                        } else if (res.msg == "권한이 없습니다") {
                            alert("권한이 없습니다")
                            location.href = `/board/${board.id}`;
                        }
                        console.log(res.code);
                        console.log(res.msg);

                    }).fail((err) => {
                        alert("게시글 삭제 실패(에러)")
                        console.log(err.code);
                        console.log(err.msg);
                        location.href = `/board/${board.id}`;
                    });

                }

            </script>

            <%@ include file="../layout/footer.jsp" %>