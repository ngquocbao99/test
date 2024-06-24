<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đăng nhập</title>
        <link rel="stylesheet" href="css/login.css">
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
            <header>Đăng nhập</header>
            <p class="text-danger">${mess}</p>
            <form action="login" method="post" class="form" onsubmit="return validateForm()">
                <div class="input-box">
                    <label>Tài khoản</label>
                    <input type="text" id="username" name="username" value="${username}" placeholder="Nhập tài khoản" class="form-control"/>
                    <span id="userError"></span> 
                </div>

                <div class="input-box">
                    <label>Mật khẩu</label>
                    <input type="password" id="password" name="password" value="${password}" placeholder="Nhập mật khẩu" class="form-control"/>
                    <span id="passError"></span> 
                </div>

                <div class="remember-forgot">
                    <!--<label><input type="checkbox"> Remember me</label>-->
                    <a href="ForgotPassword.jsp">Bạn quên mật khẩu?</a>
                </div>

                <button type="submit" class="btn-prime">Đăng nhập</button>

                <div class="register-link">
                    <p>Đăng ký tài khoản mới <a href="Register.jsp">tại đây</a></p>   
                </div>
            </form>

        </section>

        <script>
                    function validateForm() {
                        var username = document.getElementById("username").value;
                        var password = document.getElementById("password").value;

                        var userError = document.getElementById("userError");
                        var passError = document.getElementById("passError");

                        var hasError = false;
                        if (username === "") {
                            userError.innerText = "Điền vào ô này";
                            hasError = true;
                        } else {
                            userError.innerText = "";
                        }

                        if (password === "") {
                            passError.innerText = "Điền vào ô này";
                            hasError = true;
                        } else {
                            if (password.length < 3) {
                                passError.innerText = "Mật khẩu phải dài hơn 3";
                                hasError = true;
                            } else {
                                passError.innerText = "";
                            }
                        }
                        return !hasError;
                    }
        </script>
    </body>

</html>