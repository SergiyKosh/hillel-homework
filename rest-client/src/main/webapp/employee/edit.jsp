<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="employee" value="${employee}"/>
<html>
<head>
    <title>Edit employee</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>

<script type="application/javascript">
    function deleteData() {
        $.ajax({
            type: "DELETE",
            url: "http://127.0.0.1:8080/employee",
            data: $('#form').serialize()
        }).done(function (data) {
            console.log(data);
        });
        window.location.href = '/employees';
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
                <li class="nav-item">
                    <a class="nav-link" href="/employee/new">Add employee</a>
                </li>
            </ul>
        </div>
    </nav>
</header>

<body>
<form method="post" id="form" action="http://127.0.0.1:8080/employee">
    <div class="form-floating mt-2">
        <input type="hidden" name="id" value="${employee.getId()}">
        <div class="col-md">
            <div class="form-floating">
                <input type="text" class="form-control" id="floatingInputGrid" name="departmentId"
                       placeholder="${employee.getDepartmentId()}" value="${employee.getDepartmentId()}">
                <label for="floatingInputGrid">Department id</label>
            </div>
        </div>
        <div class="col-md">
            <div class="form-floating">
                <input type="text" class="form-control" id="floatingInputGrid" name="name"
                       placeholder="${employee.getName()}" value="${employee.getName()}">
                <label for="floatingInputGrid">Name</label>
            </div>
        </div>
        <div class="col-md">
            <div class="form-floating">
                <input type="text" class="form-control" id="floatingInputGrid" name="salary"
                       placeholder="${employee.getSalary()}" value="${employee.getSalary()}">
                <label for="floatingInputGrid">Salary</label>
            </div>
        </div>
        <div class="col-md">
            <div class="form-floating">
                <input type="text" class="form-control" id="floatingInputGrid" name="chiefId"
                       placeholder="${employee.getChiefId()}" value="${employee.getChiefId()}">
                <label for="floatingInputGrid">Chief id</label>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Edit employee</button>
        <button type="button" onclick="deleteData()">delete</button>
    </div>
</form>
</body>
</html>
