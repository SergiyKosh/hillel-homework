<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="employee" value="${employee}"/>
<html>
<head>
    <title>Add employee</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>

<script type="application/javascript">
    function putData() {
        $.ajax({
            type: "PUT",
            url: "http://127.0.0.1:8080/employees/new",
            data: $('#put-form').serialize()
        }).done(function (data) {
            console.log(data);
        });
    }
</script>

<header>
    <nav class="navbar navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/employees">Employees</a>
        </div>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
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
            </ul>
        </div>
    </nav>
</header>

<body>
<form id="put-form" name="put-form">
    <div class="form-floating mt-2">
        <div class="col-md">
            <div class="form-floating">
                <input type="text" class="form-control" id="floatingInputGrid" placeholder="Name" name="name">
                <label for="floatingInputGrid">Name</label>
            </div>
        </div>
        <div class="col-md">
            <div class="form-floating">
                <input type="text" class="form-control" id="floatingInputGrid" placeholder="Salary" name="salary">
                <label for="floatingInputGrid">Salary</label>
            </div>
        </div>
        <div class="col-md">
            <div class="form-floating">
                <input type="text" class="form-control" id="floatingInputGrid" placeholder="Chief id" name="chiefId">
                <label for="floatingInputGrid">Chief id</label>
            </div>
        </div>
        <div class="col-md">
            <div class="form-floating">
                <input type="text" class="form-control" id="floatingInputGrid" placeholder="Department id" name="departmentId">
                <label for="floatingInputGrid">Department id</label>
            </div>
        </div>
        <button type="button" onclick="putData()" class="btn btn-primary">Add new employee</button>
    </div>
</form>
</body>
</html>
