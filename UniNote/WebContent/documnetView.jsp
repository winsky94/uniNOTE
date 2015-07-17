<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String swfFilePath=request.getParameter("swfpath");
	out.print(swfFilePath);
    // swfFilePath=swfpath;
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<script type="text/javascript" src="js/jQuery.js"></script>
	<script type="text/javascript" src="js/flexpaper_flash.js"></script>
	<script type="text/javascript" src="js/flexpaper_flash_debug.js"></script>
	<script src="js/materialize.js"></script>

	<!-- CSS  -->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
	<link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>

	<style type="text/css" media="screen"> 
  
			#flashContent { display:none; }
			#viewerPlaceHolder{
				height:650px;
				display:block;
			}
			h3{
				display: relative;
				margin:auto;
			}
    </style>

	<title>detail</title>
</head>
<body>

	<header>
		<!-- Dropdown Structure -->
		<ul id="dropdown1" class="dropdown-content">
			<li>
				<a href="#!">南京大学</a>
			</li>
			<li>
				<a href="#!">切换</a>
			</li>

			<li class="divider"></li>
			<li>
				<a href="http://www.nju.edu.cn" target="_blank">主页</a>
			</li>
		</ul>
		<!-- Dropdown2 Structure -->
		<ul id="dropdown2" class="dropdown-content">
			<li>
				<a href="#!">王宁</a>
			</li>
			<li>
				<a href="#!">个人信息</a>
			</li>

		</ul>
		<!-- Dropdown Structure -->
		<ul id="dropdown3" class="dropdown-content">
			<li>
				<a href="#!">南京大学</a>
			</li>
			<li>
				<a href="#!">切换</a>
			</li>

			<li class="divider"></li>
			<li>
				<a href="http://www.nju.edu.cn" target="_blank">主页</a>
			</li>
		</ul>
		<!-- Dropdown2 Structure -->
		<ul id="dropdown4" class="dropdown-content">
			<li>
				<a href="#!">王宁</a>
			</li>
			<li>
				<a href="#!">个人信息</a>
			</li>

		</ul>

		<nav>
			<div class="nav-wrapper teal lighten-3">
				<div class="container">
					<div class="row">
						<div class="col l6 s12">
							<a href="#!" class="brand-logo">uniNOTE</a>
						</div>

						<ul class="right hide-on-med-and-down">
							<li>
								<a href="index.html">首页</a>
							</li>
							<li>
								<a href="list.html">文档</a>
							</li>
							<li>
								<a href="upload.html">上传文档</a>
							</li>
							<!-- Dropdown Trigger -->
							<li>
								<a class="dropdown-button" href="#!" data-activates="dropdown1">
									南京大学 <i class="material-icons right">arrow_drop_down</i>
								</a>
							</li>
							<li>
								<a class="dropdown-button" href="#!" data-activates="dropdown2">
									王宁 <i class="material-icons right">arrow_drop_down</i>
								</a>
							</li>
						</ul>
						<ul id="slide-out" class="side-nav">
							<li>
								<a href="index.html">首页</a>
							</li>
							<li>
								<a href="list.html">文档</a>
							</li>
							<li>
								<a href="upload.html">上传文档</a>
							</li>
							<!-- Dropdown Trigger -->
							<li>
								<a class="dropdown-button" href="#!" data-activates="dropdown3">
									南京大学
									<i class="material-icons right">arrow_drop_down</i>
								</a>
							</li>
							<li>
								<a class="dropdown-button" href="#!" data-activates="dropdown4">
									王宁
									<i class="material-icons right">arrow_drop_down</i>
								</a>
							</li>
						</ul>
						<a href="#" data-activates="slide-out" class="button-collapse">
							<i class="mdi-navigation-menu"></i>
						</a>
					</div>
				</div>
			</div>
		</nav>
	</header>

	<div class="main container">
		<div class="row"> <font size="15"><span class="col s12 l8">文件名</span></font> 
		</div>
		<div class="row">

			<div class="col s12 l8">

				<a id="viewerPlaceHolder" ></a>

				<script type="text/javascript"> 
				var fp = new FlexPaperViewer(	
					 'FlexPaperViewer',
					 'viewerPlaceHolder', { config : {
					 SwfFile : encodeURI('<%=swfFilePath%>'),
					 Scale : 0.6, 
					 ZoomTransition : 'easeOut',
					 ZoomTime : 0.5,
					 ZoomInterval : 0.2,
					 FitPageOnLoad : true,
					 FitWidthOnLoad : false,
					 FullScreenAsMaxWindow : false,
					 ProgressiveLoading : false,
					 MinZoomSize : 0.2,
					 MaxZoomSize : 5,
					 SearchMatchAll : false,
					 InitViewMode : 'SinglePage',
					 
					 ViewModeToolsVisible : true,
					 ZoomToolsVisible : true,
					 NavToolsVisible : true,
					 CursorToolsVisible : true,
					 SearchToolsVisible : true,
  					
  					 localeChain: 'zh_CN'
					 }});
	        </script>
			</div>
			<div class="col s12 l4">
				<div class="card">
					<div class="card-image">
						<img src="images/kuan.jpg">
						<span class="card-title">宽哥</span>
					</div>
					<div class="card-content">
						<p>文件介绍</p>
					</div>
					<div class="card-action">
						<a href="#">上传的其他文件</a>
					</div>
				</div>
				<div class="row">
					<a class="waves-effect waves-light btn">
						<i class="material-icons left"></i>
						赞
					</a>
					<a class="waves-effect waves-light btn">
						<i class="material-icons right"></i>
						踩
					</a>
				</div>
				<a class="waves-effect waves-light btn-large">下载</a>
			</div>
		</div>
	</div>

	<footer class="page-footer teal lighten-3">
		<div class="container">
			<div class="row">
				<div class="col l6 s12">
					<h5 class="white-text">Footer Content</h5>
					<p class="grey-text text-lighten-4">
						You can use rows and columns here to organize your footer content.
					</p>
				</div>
				<div class="col l4 offset-l2 s12">
					<h5 class="white-text">Links</h5>
					<ul>

						<li>
							<a class="grey-text text-lighten-3" href="#!">Link 1</a>
						</li>

					</ul>

				</div>
			</div>
		</div>
		<div class="footer-copyright">
			<div class="container">
				© 2015 uniNOTE Group, NJU
				<a class="grey-text text-lighten-4 right" href="#!">More Links</a>
			</div>
		</div>
	</footer>
</body>
</html>