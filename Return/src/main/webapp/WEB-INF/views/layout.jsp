<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0" />

<!-- My style -->
<link rel="stylesheet" type="text/css" href="<c:url value="./resources/css/allnew.css"/>">

<!-- Font style -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400%7CRaleway:300,400,500,600,700%7CLato:300,400,400italic,600,700" rel="stylesheet" type="text/css" />

<!-- BOOTSTRAP style -->
<link rel="stylesheet" type="text/css" href="<c:url value="./resources/css/bootstrap.css"/>">

<!-- REVOLUTION SLIDER -->
<link rel="stylesheet" type="text/css" href="<c:url value="./resources/css/slider/extralayers.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="./resources/css/slider/settings.css"/>">

<!-- THEME CSS -->
<link rel="stylesheet" type="text/css" href="<c:url value="./resources/css/theme/essentials.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="./resources/css/theme/layout.css"/>">

<!-- PAGE LEVEL SCRIPTS CSS -->
<link rel="stylesheet" type="text/css" href="<c:url value="./resources/css/page_level/header-1.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="./resources/css/page_level/layout-shop.css"/>">

<!-- Design Color -->
<link rel="stylesheet" type="text/css" href="<c:url value="./resources/css/designColor/lightgrey.css"/>">

<!-- JQUERY , BOOTSTRAP script -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.js"></script>
<title>Travel Main</title>
</head>
<body>
	<div id="wrap">
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="content" />
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>