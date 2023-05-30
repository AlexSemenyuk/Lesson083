<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <%@ page contentType="text/html; charset=UTF-8" %>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Blog Home - Start Bootstrap Template</title>
    <%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"--%>
    <%--          rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"--%>
    <%--          cros-sorigin="anonymous">--%>
    <%--    <link rel="icon" type="image/x-icon" href="<c:url value="/resources/assets/favicon.ico"/>"/>--%>
    <link href="<c:url value="/resources/css/styles.css"/>" rel="stylesheet"/>

    <style>
        #title.input,
        #imagepath.input {
            margin-top: 30px;
        }

        .input {
            margin-top: 30px;
        }

        .ml-5 {
            margin-left: 20px;
        }

        #but-post.btn {
            display: block;
            margin-top: 30px;
            width: 100%;
            margin-bottom: 30px;
        }

        input#file-upload-button {

            background-color: #0d6efd;
            border-radius: 3px;
        }

    </style>
</head>
<body>
<main>
    <div class="container">
        <div class="row">
            <div class="col-xl-2"></div>
            <div class="col-xl-8 text-center my-5">
                <h1>Input your book</h1>
                <form class="form" method="post">
                    <%-- name --%>
                    <input type="text" class="input form-control" id="name" name="name"
                           placeholder="Name">
                    <%-- author-id--%>
                    <input type="text" class="input form-control" id="author" name="author"
                           placeholder="Author">
                    </select>
                    <%-- release year --%>
                    <input type="number" class="input form-control" id="year" name="year"
                           placeholder="Release Year">
                    <%-- genre-id--%>
                    <input type="text" class="input form-control" id="genre" name="genre"
                           placeholder="Genre">
                    <%-- amount of page --%>
                    <input type="number" class="input form-control" id="amount" name="amount"
                           placeholder="Amount of Page">
                    <%--description--%>
                    <textarea class="input" id="description" name="description">
                         Description
                    </textarea>
                    <button type="submit" class="btn btn-primary" id="but-post">Ok</button>
                </form>

                <table class="table out-books">
                    <thead>
                    <tr>
                        <th>Book name</th>
                        <th>Author</th>
                        <th>Release Year</th>
                        <th>Genre</th>
                        <th>Amount of page</th>
                        <th>Description</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${books}" var="book" varStatus="s">
                        <tr>

                            <th>${book.name}</th>
                            <th>${book.author}</th>
                            <th>${book.releaseYear}</th>
                            <th>${book.genre}</th>
                            <th>${book.amountOfPage}</th>
                            <th>${book.description}</th>
                            <th>
                                <form action="<c:url value="/"></c:url>">
                                    <button class="details btn btn-primary" data-id-delete="${book.id}" type="submit">
                                        Delete
                                    </button>
                                </form>
                            </th>
                            <th>
                                <div class="form-check">
                                    <input class="radio-edit form-check-input" type="radio" value="${book.id}"
                                           id="flexCheckChecked" name="edit">
                                    <label class="form-check-label" for="flexCheckChecked">
                                        Edit
                                    </label>
                                </div>
                            </th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="edit-total">
                    <form method="post">
                </div>
                <label for="edit">For Edit</label>
                <select id="edit" name="edit">
                    <option value="name">Name</option>
                    <option value="author">Author</option>
                    <option value="year">Release Year</option>
                    <option value="genre">Genre</option>
                    <option value="amount">Amount Of Page</option>
                    <option value="description">Description</option>
                </select>
                <input type="text" class="input form-control d-inline-block w-50" id="editdata" name="editdata"
                       placeholder="New Data">
                <form class="d-inline" action="<c:url value="/"></c:url>">
                    <button class="btn-edit btn btn-primary d-inline" id="btn-edit" type="submit">Confirm Edit</button>
                </form>
                </form>

                <form method="post">
                    <div class="find-total">
                        <label for="find">For Find</label>
                        <select id="find" name="find">
                            <option value="name">By Name</option>
                            <option value="author">By Author</option>
                            <option value="year">By Release Year</option>
                            <option value="genre">By Genre</option>
                            <option value="amount">By Amount Of Page</option>
                            <option value="description">By Description</option>
                        </select>
                        <input type="text" class="input form-control d-inline-block w-50" id="finddata" name="finddata"
                               placeholder="Find Data">
                        <form class="d-inline" action="<c:url value="/"></c:url>">
                            <button class="btn btn-primary d-inline" type="submit">Confirm Edit</button>
                        </form>
                    </div>
                </form>
                <c:choose>
                    <c:when test="${bookFind != null}">
                        <h2>Yours book</h2>
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Book name</th>
                                <th>Author</th>
                                <th>Release Year</th>
                                <th>Genre</th>
                                <th>Amount of page</th>
                                <th>Description</th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <th>${bookFind.name}</th>
                                    <th>${bookFind.author}</th>
                                    <th>${bookFind.releaseYear}</th>
                                    <th>${bookFind.genre}</th>
                                    <th>${bookFind.amountOfPage}</th>
                                    <th>${bookFind.description}</th>
                                </tr>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <div>Book wasn't found</div>
                    </c:otherwise>

                </c:choose>


            </div>
            <div class="col-xl-2"></div>
        </div>
    </div>


</main>
<footer class="py-5 bg-dark">
    <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2023</p></div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        in-tegrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        cros-sorigin="anonymous"></script>
<script src="https://cdn.tiny.cloud/1/smgt7motjmt39brlvr481ll1m7kn9fycui3egjbpp7vdwodp/tinymce/6/tinymce.min.js"
        referrerpolicy="origin"></script>
<script>
    tinymce.init({
        selector: '#description'
    });
</script>

<script>
    function getAttributeFromTarget(target) {
        const postId = target.getAttribute('data-id-delete');
        fetch("<c:url value="/"/>", {
            method: "POST",
            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
            body: `id=\${postId}`
        }).then(response => {
            console.log(response.status);
            location.reload();
            // if (response.redirected) {
            //     location = '/';
            // }
        }).catch(error => {
            alert(error);
        });
    }

    const tableOutBooks = document.querySelector(".out-books");
    tableOutBooks.addEventListener("click", e => {
        if (e.target.nodeName === 'BUTTON') {
            const target1 = e.target;
            getAttributeFromTarget(target1);
        }
    });


    let buttonEdit = document.getElementById("btn-edit");
    console.dir(buttonEdit);
    buttonEdit.addEventListener('click', () => {
        console.log("super 1");
        let radio = document.querySelectorAll(".radio-edit");
        let editId = "1000";
        for (let i = 0; i < radio.length; i++) {
            console.dir(radio[i]);
            if (radio[i].checked) {
                editId = radio[i].value;
                console.log("super 2");
                // dataId = radio[i].getAttribute('data-id-edit');
                break;
            }
        }
        console.log("editId" + editId);
        <%--}--%>
        <%--// console.log("data = " + data);--%>
        fetch("<c:url value="/"/>", {
            method: "POST",
            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
            body: `editId=\${editId}`
        }).then(response => {
            console.log(response.status);
            location.reload();
            // if (response.redirected) {
            //     location = '/';
            // }
        }).catch(error => {
            alert(error);
        });
    });
</script>
</body>
</html>