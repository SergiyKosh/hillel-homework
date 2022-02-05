<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="department" value="${department}"/>
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
<form method="post" action="http://localhost:8080/department/update">
    <div class="form-floating mt-2">
        <div class="col-md">
            <div class="form-floating">
                <input type="text" class="form-control" id="floatingInputGrid" name="id"
                       placeholder="${department.getId()}" value="${department.getId()}">
                <label for="floatingInputGrid">Department id</label>
            </div>
        </div>
        <div class="col-md">
            <div class="form-floating">
                <input type="text" class="form-control" id="floatingInputGrid" name="name"
                       placeholder="${department.getName()}" value="${department.getName()}">
                <label for="floatingInputGrid">Name</label>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Primary</button>
    </div>
</form>
</body>
</html>
