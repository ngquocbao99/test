<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Rau củ tươi G7</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>

        <style>
            .text-danger{
                margin-top: 10px;
                color: red;
            }
        </style>
    </head>

    <body>
        <section class="container">
            <header>Cập nhật mã giảm giá</header>
            <p class="text-danger">${mess}</p>
            <form action="${pageContext.request.contextPath}/updateVoucher" method="get" class="form" onsubmit="return validateForm()">
                <div class="input-box">
                    <label>Voucher ID</label>
                    <input id="id" name="id" type="text" value="${id}" placeholder="Nhập ID của mã cần cập nhật"/>
                    <span id="idError"></span> 
                </div>

                <div class="input-box">
                    <label>Voucher Code</label>
                    <input id="code" name="code" type="text" value="${code}" placeholder="Nhập code mới"/>
                    <span id="codeError"></span> 
                </div>

                <div class="column">
                    <div class="input-box">
                        <label>Tỷ lệ chiết khấu</label>
                        <input id="discount_percentage" name="discount_percentage" type="text" value="${discount_percentage}" placeholder="Nhập tỷ lệ chiết khấu"  />
                        <span id="discountError"></span> 
                    </div>
                </div>

                <button class="btn-prime">Tạo</button>      
            </form>

        </section>

        <script>
            function validateForm() {
                var id = document.getElementById("id").value;
                var code = document.getElementById("code").value;
                var discount_percentage = document.getElementById("discount_percentage").value;

                var idError = document.getElementById("idError");
                var codeError = document.getElementById("codeError");
                var discountError = document.getElementById("discountError");

                var hasError = false;



                if (id === "") {
                    idError.innerText = "Điền vào ô này";
                    hasError = true;
                } else {
                    idError.innerText = "";
                }

                if (code === "") {
                    codeError.innerText = "Điền vào ô này";
                    hasError = true;
                } else if (!/^[a-zA-Z]/.test(code)) {
                    // Kiểm tra xem chuỗi code có bắt đầu bằng một chữ cái hay không
                    codeError.innerText = "Code phải bắt đầu bằng một chữ cái";
                    hasError = true;
                } else {
                    codeError.innerText = "";
                }

                if (discount_percentage === "") {
                    discountError.innerText = "Điền vào ô này";
                    hasError = true;
                } else {
                    var discountValue = parseFloat(discount_percentage);
                    if (isNaN(discountValue)) {
                        discountError.innerText = "Giá trị nhập vào phải là một số";
                        hasError = true;
                    } else if (discountValue <= 0 || discountValue >= 1) {
                        discountError.innerText = "Tỷ lệ chiết khấu phải lớn hơn 0 và nhỏ hơn 1";
                        hasError = true;
                    } else {
                        discountError.innerText = "";
                        hasError = false;
                    }
                }


                return !hasError;
            }
        </script>
    </body>

</html>