<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Change Password</title>
        <link rel="stylesheet" href="css/changepass.css">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>

        <style>
            .text-succes{
                margin-top: 20px;
                color: green;
            }
        </style>
    </head>

    <body>
        <section class="container">
            <header>Nhập mật khẩu mới của bạn</header>
            <p class="text-succes">${messSuccess}</p>
            <form action="updatePassword" method="post" class="form" onsubmit="return validateForm()">
                <div class="input-box">
                    <label>Tài khoản</label>
                    <input value="${user}" readonly />
                </div>


                <div class="input-box">
                    <label>Nhập lại mật khẩu</label>
                    <input id="password" name="password" type="password" placeholder="Nhập mật khẩu mới của bạn" />
                    <span id="passwordError"></span>
                </div>

                <div class="input-box">
                    <label>Nhập lại mật khẩu</label>
                    <input id="repassword" name="repassword" type="password" placeholder="Nhập lại mật khẩu" />
                    <span id="repasswordError"></span>
                </div>
                <button class="btn-prime">Cập nhật</button>      
            </form>
        </section>

       
        <script>
                function validateForm() {
                    var password = document.getElementById("password").value;
                    var repassword = document.getElementById("repassword").value;

                    var passwordError = document.getElementById("passwordError");
                    var repasswordError = document.getElementById("repasswordError");

                    var hasError = false;

                    if (password === "") {
                        passwordError.innerText = "Điền vào ô này";
                        hasError = true;
                    } else {
                        if (password.length < 3) {
                            passwordError.innerText = "Mật khẩu phải dài hơn 3";
                            hasError = true;
                        } else {
                            passwordError.innerText = "";
                        }
                    }

                    if (repassword === "") {
                        repasswordError.innerText = "Điền vào ô này";
                        hasError = true;
                    } else if (repassword !== password) {
                        repasswordError.innerText = "Nhập lại mật khẩu không trùng khớp";
                        hasError = true;
                    } else {
                        repasswordError.innerText = "";
                    }

                    return !hasError;
                }
        </script>
    </body>

</html>