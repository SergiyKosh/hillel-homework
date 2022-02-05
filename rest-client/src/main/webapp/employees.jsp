<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Employees</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
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
    <th scope="row">Department</th>
    <th scope="row">Employee name</th>
    <th scope="row">Salary</th>
    <th scope="row">Chief id</th>
    <td></td>
    <td></td>
    </thead>
    <tbody>
    <c:forEach var="employee" items="${employees}">
        <tr>
            <td>${employee.getDepartment().getName()}</td>
            <td>${employee.getName()}</td>
            <td>${employee.getSalary()}</td>
            <td>${employee.getChiefId()}</td>
            <td><a href="/employee/edit?id=${employee.getId()}" class="btn btn-link">Edit</a></td>

            <form>
                <td>
                    <input type="hidden" name="departmentId" value="${employee.getDepartment().getId()}">
                    <input type="hidden" name="id" value="${employee.getId()}">
                    <input type="hidden" name="name" value="${employee.getName()}">
                    <input type="hidden" name="salary" value="${employee.getSalary()}">
                    <input type="hidden" name="chiefId" value="${employee.getChiefId()}">
                    <button type="button" onclick="clicked()" class="btn btn-danger">Delete</button>
                </td>
            </form>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"--%>
<%--        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>--%>
</body>
<script type="application/javascript">
    function clicked() {
        $.ajax({
            url: "http://127.0.0.1:8080/employee/delete",
            contentType: "application/json",
            type: "DELETE",
            data: {
                "departmentId" : $('departmentId').val(),
                "id" : $('id').val(),
                "name" : $('name').val(),
                "salary" : $('salary').val(),
                "chiefId" : $('chiefId').val()
            }
        })
    }
</script>
</html>
