<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String swfFilePath=session.getAttribute("swfpath").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/flexpaper_flash.js"></script>
    <script type="text/javascript" src="js/flexpaper_flash_debug.js"></script>
    <script src="js/materialize.js"></script>

	<!-- CSS  -->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
	<link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>

<style type="text/css" media="screen"> 
			html, body	{ height:100%; }
			body { margin:0; padding:0; overflow:auto; }   
			#flashContent { display:none; }
			#viewerPlaceHolder{
				width:820px;
				height:650px;
				display:block;
			}
			.main-container{
				margin-top: 100px;
				margin-left: 8%;
				margin-right: 8%;
				min-width: 84%;
				max-width: 84%;

			}
        </style> 

<title>文档在线预览系统</title>
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

        <div class="main-container">
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
</body>
</html>