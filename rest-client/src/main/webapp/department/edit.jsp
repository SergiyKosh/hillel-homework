<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="employee" value="${employee}"/>
<html>
<head>
    <title>Edit employee</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<header>
    <nav class="navbar navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/employees">Employees</a>
        </div>
    </nav>
</header>

<body>
<form method="post" action="/employee/edit/apply">
    <div class="form-floating mt-2">
        <div class="col-md">
            <div class="form-floating">
                <input type="text" class="form-control" id="floatingInputGrid" name="departmentId"
                       placeholder="${employee.getDepartment().getId()}" value="${employee.getDepartment().getId()}">
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
        <input type="hidden" name="id" value="${department.getId()}">
        <button type="submit" class="btn btn-primary">Primary</button>
    </div>
</form>
</body>
</html>
