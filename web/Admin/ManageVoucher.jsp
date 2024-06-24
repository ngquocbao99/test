<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Ogani Template">
        <meta name="keywords" content="Ogani, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Rau củ tươi G7</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="../css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="../css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="../css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="../css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="../css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="../css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="../css/style.css" type="text/css">

        <style>
            .text-danger{
                margin: 20px;
                color: red;
            }

        </style>
    </head>

    <body>
        <jsp:include page="HeaderAdmin.jsp"></jsp:include>

            <!-- Shoping Cart Section Begin -->
            <section class="shoping-cart spad">
                <div class="container">
                    <div class="row">
                        <span class="text-danger" id="textError"></span> 
                        <div class="col-lg-12">

                            <div class="hero__search__form">
                                <form action="searchVoucher" method="get" onsubmit="return validateForm()">
                                    <select name="option">
                                        <option value="1">Code</option>
                                        <option value="2">Tỷ lệ chiết khấu</option>

                                    </select>

                                    <input type="text" id="textSearch" name="textSearch"  value="${textSearch}" placeholder="Bạn đang tìm gì?">

                                <button type="submit" class="site-btn">Tìm kiếm</button>
                            </form>
                        </div>
                        <div class="shoping__cart__table">
                            <table>
                                <thead>
                                    <tr>
                                        <th>Voucher ID</th>
                                        <th>Voucher Code</th>
                                        <th>Tỷ lệ chiết khấu</th>
                                        <th></th>
                                    </tr>
                                </thead>    

                                <c:if test="${listV != null}">
                                    <c:forEach items="${listV}" var="o">
                                        <tbody>
                                            <tr>
                                                <td> ${o.voucher_id}</td>

                                                <td> ${o.voucher_code}</td>

                                                <td>${o.discount_percentage}</td>
                                                <td class="shoping__cart__item__close">
                                                    <a href="deleteVoucher?voucher_id=${o.voucher_id}">
                                                        <span class="icon_close"></span>
                                                    </a>
                                                </td>
                                                <td></td>
                                            </tr>
                                        </tbody>

                                    </c:forEach>
                                </c:if>

                                <c:if test="${listV == null}">
                                    <tbody>
                                        <tr>
                                            <td> ${vou.voucher_id}</td>

                                            <td> ${vou.voucher_code}</td>

                                            <td>${vou.discount_percentage}</td>
                                            <td></td>
                                        </tr>
                                    </tbody>
                                </c:if>
                            </table>
                            <div>
                                <a href="Admin/CreateVoucher.jsp" class="site-btn"></span>
                                    Tạo mã mới</a>
                                <a href="Admin/UpdateVoucher.jsp" class="site-btn"></span>
                                    Cập nhật mã</a>                               
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <script>
                                    function validateForm() {
                                        var textSearch = document.getElementById("textSearch").value.trim(); // Trim whitespace

                                        var textError = document.getElementById("textError");

                                        var hasError = false;
                                        if (textSearch === "") {
                                            textError.innerText = "Điền vào ô này";
                                            hasError = true;
                                        } else {
                                            textError.innerText = "";
                                        }
                                        return !hasError;
                                    }
        </script>
    </body>

</html>