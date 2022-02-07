<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="department" value="${department}"/>
<html>
<head>
    <title>Edit department</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>

<script type="application/javascript">
    function deleteData() {
        $.ajax({
            type: "DELETE",
            url: "http://127.0.0.1:8080/department/delete",
            data: $('#form').serialize()
        }).done(function (data) {
            console.log(data);
        });
        window.location.href = '/departments';
    }
</script>

<header>
    <nav class="navbar navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/employees">Employees</a>
        </div>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="/departments">Departments</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/employees">Employees</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/department/new">Add department</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/employee/new">Add employee</a>
                </li>
            </ul>
        </div>
    </nav>
</header>

<body style="background-color: #212529">
<div class="container">
    <div class="row justify-content-center align-items-center">
        <form method="post" id="form" action="http://localhost:8080/department/update">
            <div class="form-floating mt-2">
                <div class="col-md mb-2">
                    <div class="form-floating">
                        <input style="background-color: #6c757d; border: none;" type="text" class="form-control" id="floatingInputGrid" name="id"
                               placeholder="${department.getId()}" value="${department.getId()}">
                        <label for="floatingInputGrid">Department id</label>
                    </div>
                </div>
                <div class="col-md mb-2">
                    <div class="form-floating">
                        <input style="background-color: #6c757d; border: none;" type="text" class="form-control" id="floatingInputGrid" name="name"
                               placeholder="${department.getName()}" value="${department.getName()}">
                        <label for="floatingInputGrid">Name</label>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Edit</button>
                <button type="button" onclick="deleteData()" class="btn btn-danger">Delete</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
