<!DOCTYPE html>
<html>
<head>
    <meta charset="utf8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <base href="/">
    <title>Lockit Angular</title>
    <link href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap-theme.min.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}/static/styles/default.css" type="text/css" rel="stylesheet"/>

</head>
<body>

<div>
    <form>
        <div class="row">
            <div class="md-col-4">USERNAME</div>
            <div class="md-col-4">
                <input type="text" id="loginName" class="form-control" name="loginName" placeholder="用户名">
            </div>
        </div>
        <div class="row">
            <div class="md-col-4">PASSWORD</div>
            <div class="md-col-4">
                <input type="password" id="password" class="form-control" name="password" placeholder="密码">
            </div>
        </div>
        <div class="row">
            <div class="md-col-4"></div>
            <div class="md-col-4">
                <button type="button" id="loginBtn" class="form-control">LOGIN</button>
                <a href="/api/v1/user?loginName=v5zhu">TEST CACHE</a>
            </div>
        </div>
        <div id="error"></div>
    </form>
</div>

</body>
<script src="http://cdn.bootcss.com/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
<script>
    $(function () {
        $("#loginBtn").click(function () {
            $.ajax({
                url: '${ctx}/login',
                data: JSON.stringify({
                    "loginName": $("#loginName").val(),
                    "password": $("#password").val()
                }),
                type: 'post',
                contentType: 'application/json',
                success: function (data) {
                    window.load(data);
                },
                error: function (data) {
                    $("#error").html((JSON.stringify(data)));
                }

            });
        })
    })
</script>
</html>