<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>
        <%@ include file="../layout/nav.jsp" %>

            <div class="container my-3">
                <div class="my-board-box row">

                    <div id="mainBoardList" class="my-board-box row">

                        <c:forEach begin="0" end="11" items="${boardList}" var="board">
                            <div class="card col-lg-3">
                                <img class="card-img-top" style="height: 250px;" src="/images/profile.png"
                                    alt="Card image">
                                <div class="card-body">
                                    <h4 class="card-title my-text-ellipsis">${board.title}</h4>
                                    <a href="/board/${board.id}" class="btn btn-primary">게시글 보기</a>
                                    <div class="my-boardMain-lovelayout">
                                        <div id="heartPicture" class="fa-regular fa-heart fa-xl my-cursor" value="no">
                                        </div>

                                        <div>${board.loveCnt}</div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                    <ul class="pagination mt-3 d-flex justify-content-center">
                        <li id="previousToggle" class="page-item disabled"><a id="previous" class="page-link"
                                href="#">Previous</a></li>
                        <li id="nextToggle" class="page-item"><a id="next" class="page-link" href="#">Next</a></li>
                    </ul>


                    <div class="mx-quto input-group justify-content-center">
                        <div>
                            <mx-auto>
                                <input id="boardSearch" name="query" type="text" class="form-control"
                                    placeholder="검색어 입력" aria-label="search" value="" aria-describedby="button-addon2">
                            </mx-auto>
                        </div>
                        <div>
                            <button id="button-addon2" class="btn btn-primary" type="submit"
                                onclick="serachGet()">검색</button>
                        </div>
                    </div>
                </div>

            </div>



            <div class="my-mainPagePaging-layout page navigation">
                <ul class="pagination">
                    <c:if test="${pageMaker.prev}">
                        <li class="paginate_button previous">
                            <a class="page-link" href="#">Prev</a>
                        </li>
                    </c:if>
                    <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                        <li class="paginate_button">
                            <a class="page-link" href="#">${num}</a>
                        </li>
                    </c:forEach>
                    <c:if test="${pageMaker.next}">
                        <li class="paginate_button next">
                            <a class="page-link" href="#">Next</a>
                        </li>
                    </c:if>
                </ul>
            </div>
            <form id='actionForm' action="/board/list" method="get">
                <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
                <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
            </form>



            <script>
                // var actionForm = $('#actionForm');
                // $('.paginate_button a').on('click', function (e) {
                //     e.preventDefault();
                //     actionForm.find('input[name="pageNum"]').val($(this).attr('href'));
                //     actionForm.submit();
                // });
                let searchCheck = false;
                let check = 1;
                let newCheck = 1;
                let searchAndPage = 0;
                let remain = 0;

                function serachGet() {
                    let search = $("#boardSearch").val();
                    $.ajax({

                        type: "get",
                        url: "/board/search/?serachKeyword=" + search,
                        dataType: "json"

                    }).done((res) => {

                        searchCheck = true;

                        searchAndPage = Math.ceil(res.length / 12);
                        remain = res.length % 12;

                        $("#mainBoardList").empty();
                        for (let i = 0; i <= 11; i++) {
                            let title = res[i].title;
                            let id = res[i].id;
                            let loveCnt = res[i].loveCnt;
                            let el1 =
                                ` <div class="card col-lg-3">
                                    <img class="card-img-top" style="height: 250px;" src="/images/profile.png"
                                        alt="Card image">
                                    <div class="card-body">
                                        <h4 class="card-title my-text-ellipsis">`
                            let el2 = `</h4>
                                        <a href="/board/`
                            let el3 = `" class="btn btn-primary">게시글 보기</a>
                                        <div class="my-boardMain-lovelayout">
                                            <div id="heartPicture" class="fa-regular fa-heart fa-xl my-cursor" value="no">
                                            </div>
                                            <div>`
                            let el4 = `</div>
                                        </div>
                                    </div>
                                </div>`;
                            let result = el1 + title + el2 + id + el3 + loveCnt + el4;
                            $("#mainBoardList").append(result);
                            // document.querySelector("#tes").innerHTML = loveCnt;
                        }
                    }).fail((err) => {
                    });
                }


                $("#previous").click(() => {
                    if (searchCheck == false) {
                        check--
                        let endPage = `${pageMaker.endPage}`;
                        if (check == 1) {
                            $("#previousToggle").addClass("disabled");
                        } else if (check > 1) {
                            $("#previousToggle").removeClass("disabled");
                        }
                        if (check < endPage) {
                            $("#nextToggle").removeClass("disabled");
                        } else if (check == endPage) {
                            $("#nextToggle").addClass("disabled");
                        }
                        if (searchCheck == false) {
                        }
                        let beignCheck = check * 12 - 12;
                        let endCheck = check * 12 - 1;
                        $.ajax({
                            type: "get",
                            url: "/board/paging?begin=" + beignCheck + "&end=" + endCheck,
                            dataType: "json"
                        }).done((res) => {
                            $("#mainBoardList").empty();
                            for (let i = 0; i <= 11; i++) {
                                let title = res[i].title;
                                let id = res[i].id;
                                let loveCnt = res[i].loveCnt;
                                let el1 =
                                    ` <div class="card col-lg-3">
                                <img class="card-img-top" style="height: 250px;" src="/images/profile.png"
                                    alt="Card image">
                                <div class="card-body">
                                    <h4 class="card-title my-text-ellipsis">`
                                let el2 = `</h4>
                                    <a href="/board/`
                                let el3 = `" class="btn btn-primary">게시글 보기</a>
                                    <div class="my-boardMain-lovelayout">
                                        <div id="heartPicture" class="fa-regular fa-heart fa-xl my-cursor" value="no">
                                        </div>
    
                                        <div>`
                                let el4 = `</div>
                                    </div>
                                </div>
                            </div>`;
                                let result = el1 + title + el2 + id + el3 + loveCnt + el4;
                                console.log(result);
                                $("#mainBoardList").append(result);
                                // document.querySelector("#tes").innerHTML = loveCnt;
                            }
                        }).fail((err) => {
                        });
                    }
                })


                // 작업 중
                $("#next").click(() => {
                    if (searchCheck == false) {
                        check++
                        let endPage = `${pageMaker.endPage}`;
                        if (check == 0) {
                            $("#previousToggle").addClass("disabled");
                        } else if (check > 1) {
                            $("#previousToggle").removeClass("disabled");
                        }
                        if (check < endPage) {
                            $("#nextToggle").removeClass("disabled");
                        } else if (check == endPage) {
                            $("#nextToggle").addClass("disabled");
                        }

                        // alert(check);
                        let beignCheck = check * 12 - 12;
                        let endCheck = check * 12 - 1;
                        $.ajax({
                            type: "get",
                            url: "/board/paging?begin=" + beignCheck + "&end=" + endCheck,
                            dataType: "json"
                        }).done((res) => {
                            $("#mainBoardList").empty();
                            for (let i = 0; i <= 11; i++) {
                                let title = res[i].title;
                                let id = res[i].id;
                                let loveCnt = res[i].loveCnt;
                                let el1 =
                                    ` <div class="card col-lg-3">
                                    <img class="card-img-top" style="height: 250px;" src="/images/profile.png"
                                        alt="Card image">
                                    <div class="card-body">
                                        <h4 class="card-title my-text-ellipsis">`
                                let el2 = `</h4>
                                        <a href="/board/`
                                let el3 = `" class="btn btn-primary">게시글 보기</a>
                                        <div class="my-boardMain-lovelayout">
                                            <div id="heartPicture" class="fa-regular fa-heart fa-xl my-cursor" value="no">
                                            </div>
    
                                            <div>`
                                let el4 = `</div>
                                        </div>
                                    </div>
                                </div>`;
                                let result = el1 + title + el2 + id + el3 + loveCnt + el4;
                                console.log(result);
                                $("#mainBoardList").append(result);
                                // document.querySelector("#tes").innerHTML = loveCnt;
                            }
                        }).fail((err) => {
                        });
                    } else if (searchCheck == true) {

                        newCheck++

                        if (newCheck == 0) {
                            $("#previousToggle").addClass("disabled");
                        } else if (newCheck > 1) {
                            $("#previousToggle").removeClass("disabled");
                        }
                        if (newCheck < searchAndPage) {
                            $("#nextToggle").removeClass("disabled");
                        } else if (newCheck == searchAndPage) {
                            $("#nextToggle").addClass("disabled");
                        }
                        // alert(check);
                        let searchBeignCheck = newCheck * 12 - 12;
                        let searchEndCheck = newCheck * 12 - 1;

                        $.ajax({
                            type: "get",
                            url: "/board/paging?begin=" + beignCheck + "&end=" + endCheck,
                            dataType: "json"
                        }).done((res) => {

                            $("#mainBoardList").empty();
                            for (let i = 0; i <= 11; i++) {
                                let title = res[i].title;
                                let id = res[i].id;
                                let loveCnt = res[i].loveCnt;


                                let el1 =
                                    ` <div class="card col-lg-3">
                                    <img class="card-img-top" style="height: 250px;" src="/images/profile.png"
                                        alt="Card image">
                                    <div class="card-body">
                                        <h4 class="card-title my-text-ellipsis">`
                                let el2 = `</h4>
                                        <a href="/board/`
                                let el3 = `" class="btn btn-primary">게시글 보기</a>
                                        <div class="my-boardMain-lovelayout">
                                            <div id="heartPicture" class="fa-regular fa-heart fa-xl my-cursor" value="no">
                                            </div>
    
                                            <div>`
                                let el4 = `</div>
                                        </div>
                                    </div>
                                </div>`;
                                let result = el1 + title + el2 + id + el3 + loveCnt + el4;
                                console.log(result);
                                $("#mainBoardList").append(result);
                                // document.querySelector("#tes").innerHTML = loveCnt;
                            }
                        }).fail((err) => {
                        });
                    }
                    // 작업 중

                })
            </script>

            <%@ include file="../layout/footer.jsp" %>