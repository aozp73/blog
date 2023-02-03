<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>
        <%@ include file="../layout/nav.jsp" %>
        

    <div class="container my-3">
        <form action="/board/insert" method="post" class="mb-1" onsubmit="">
            <div class="form-group">
                <input id="title" type="text" class="form-control" placeholder="Enter title" name="title">
            </div>

            <div class="form-group">
                <textarea id="content" class="form-control summernote" rows="5" name="content"></textarea>
            </div>
            <button type="submit" class="btn btn-primary">글쓰기완료</button>
        </form>


    </div>

    <script>
        $('.summernote').summernote({
            tabsize: 2,
            height: 400
        });
    </script>

            <%@ include file="../layout/footer.jsp" %>