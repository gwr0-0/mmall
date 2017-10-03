<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
<h2>Hello World!</h2>

springMVC上传文件
<form name="form1" action="/manage/product/upload.do" method="POST" enctype="multipart/form-data">
    <input type="file" name="upload_file" />
    <input type="submit" value="springMVC上传文件" />
</form>

富文本图片上传文件
<form name="form1" action="/manage/product/richtext_img_upload.do" method="POST" enctype="multipart/form-data">
    <input type="file" name="upload_file" />
    <input type="submit" value="富文本图片上传文件" />
</form>
</body>
</html>
