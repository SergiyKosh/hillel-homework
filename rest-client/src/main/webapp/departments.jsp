<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Departments</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
</head>

<script type="application/javascript">
    function deleteData() {
        $.ajax({
            type: "DELETE",
            url: "http://127.0.0.1:8080/department/delete",
            data: $('#delete-form').serialize()
        }).done(function (data) {
            console.log(data);
        });
        window.location.href = '/departments';
    }
</script>

<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">CRUD</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
                    aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/employees">Employees</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="/departments">Departments</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/department/new">Add department</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/employee/new">Add employee</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>

<body style="background-color: #212529">
<div class="container">
    <div class="row justify-content-center align-items-center">
        <table class="table table-dark mt-2">
            <thead>
            <th scope="row">Id</th>
            <th scope="row">Department name</th>
            <td></td>
            </thead>
            <tbody>
            <c:forEach var="department" items="${departments}">
                <tr>
                    <td>${department.getId()}</td>
                    <td>${department.getName()}</td>
                    <td><a href="/department/edit?id=${department.getId()}" class="btn btn-link">Edit</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
