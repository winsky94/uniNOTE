<%@page import="server.DocConverter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%	
	String id=request.getParameter("ID");
	String filename=new String(request.getParameter("filename").getBytes("iso-8859-1"), "utf-8");
	String profile=new String(request.getParameter("profile").getBytes("iso-8859-1"), "utf-8");
	String origin_filename=new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");

	String[] str=origin_filename.split("\\.");
	//String uploader=request.getParameter("uploader");
	String swfFilePath="swfFile/"+id+".swf";
	String filePath="D:/web_server_file/"+origin_filename;
	DocConverter c=new DocConverter(filePath,id);
	c.conver();

%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="js/jQuery.js"></script>
	<script type="text/javascript" src="js/flexpaper_flash.js"></script>
	<script type="text/javascript" src="js/flexpaper_flash_debug.js"></script>
	<script src="js/materialize.js"></script>
	<script src="js/LoginAjax.js"></script>
	<script src="js/cookie.js"></script>

	<!-- CSS  -->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
	<link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>

	<style type="text/css" media="screen"> 
		#flashContent { 
			display:none; 
		}
		#viewerPlaceHolder{
			height:650px;
			display:block;
		}
		h3{
			display: relative;
			margin:auto;
		}
    </style>

	<script type="text/javascript">
        $(document).ready(function(){
        	$('#file-name').html("<%=filename%>");
        	$('#file-profile').html("<%=profile%>");
        	$('#ID').attr("value","<%=id%>");
        });

        function check_login(){
        	login=check_cookie();
            if(!login){
                $(document).ready(function(){
                    $('#modal1').openModal();
                });        
        }
        }
    </script>

	<title>detail</title>
</head>
<body onload="check_cookie()">

	<header>
	</header>

	<div class="main container">
		<div class="row"> <font size="15"><span class="col s12 l8" id="file-name">文件名</span></font> 
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
						<span class="card-title" id="file-uploader"></span>
					</div>
					<div class="card-content">
						<p id="file-profile"></p>
					</div>
					<div class="card-action">
						<a href="#">上传的其他文件</a>
					</div>
				</div>
				<div class="row">
					<div class="col s12">
						<a class="waves-effect waves-light btn">
							<i class="material-icons left"></i>
							赞
						</a>
					</div>
					<div class="col s12">
						<a class="waves-effect waves-light btn">
							<i class="material-icons right"></i>
							踩
						</a>
					</div>
				</div>
				<form method="post" action="/UniNote/DownLoadServlet" onsubmit="
check_login()">
					<input type="hidden" id="ID" name="ID" value="456">
					<input type="submit" value=" 下载 " class="btn btn-primary"></form>
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