<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html
        PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh" lang="zh">
<head>
    <title>添加用户</title>
</head>
<body>
<div>
    <form action="user/add" method="post" enctype="multipart/form-data">
        <table style="align-items: center">
            <tr>
                <td>姓名：</td>
                <td><input name="name"/></td>
            </tr>

            <tr>
                <td>密码：</td>
                <td><input name="password"/></td>
            </tr>

            <tr>
                <td>图片：</td>
                <td><input type="file" name="avatar"/></td>
            </tr>

            <tr>
                <td><input type="submit" value="提交"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>