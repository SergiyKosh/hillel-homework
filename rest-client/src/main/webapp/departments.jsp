<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Employees</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
</head>

<header>
    <nav class="navbar navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/employees">Employees</a>
        </div>
    </nav>
</header>

<body>
<table class="table table-dark mt-2">
    <thead>
    <th scope="row">Id</th>
    <th scope="row">Department name</th>
    <td></td>
    <td></td>
    </thead>
    <tbody>
    <c:forEach var="department" items="${departments}">
        <tr>
            <td>${department.getId()}</td>
            <td>${department.getName()}</td>
            <td><a href="/department/edit?id=${department.getId()}" class="btn btn-link">Edit</a></td>
            <td>
                <form>
                    <input type="hidden" id="id" name="id" value="${department.getId()}">
                    <input type="hidden" id="name" name="name" value="${department.getName()}">
                    <button type="button" onclick="clicked()">delete</button>
                </form>
                <script type="text/javascript">
                    function clicked() {
                        $.ajax({
                            url: "http://127.0.0.1:8080/department/delete",
                            contentType: "application/json",
                            type: "DELETE",
                            data: {
                                "test123": "324123"
                            }

                        })
                    }
                </script>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
