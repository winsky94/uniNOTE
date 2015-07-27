<%@page import="server.DocConverter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%	
	String id=request.getParameter("ID");
	String filename=new String(request.getParameter("filename").getBytes("iso-8859-1"), "utf-8");
	String profile=new String(request.getParameter("profile").getBytes("iso-8859-1"), "utf-8");
	String origin_filename=new String(request.getParameter("origin_filename").getBytes("iso-8859-1"), "utf-8");
    String tag=new String(request.getParameter("tag").getBytes("iso-8859-1"),"utf-8");

	String[] str=origin_filename.split("\\.");
	//String uploader=request.getParameter("uploader");
	String swfFilePath="swfFile/"+id+".swf";
	String filePath="D:/web_server_file/"+origin_filename;
	DocConverter c=new DocConverter(filePath,id);
	c.conver();

%>

<!DOCTYPE <!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
	<title>manage my file</title>

	<!-- CSS  -->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
	<link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>

	<style media="screen"> 
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
		.table-container{
			display: table;
			border-spacing: 10px;
		}
		.table-row-container{
			display: table-row;
		}
		.table-cell-container{
			display: table-cell;
			margin: 10px;
		}
		#cancel-button{
			float: right;
		}
    </style>

	<script src="js/jQuery.js"></script>
	<script src="js/flexpaper_flash.js"></script>
	<script src="js/flexpaper_flash_debug.js"></script>
	<script src="js/materialize.js"></script>
	<script src="js/cookie.js"></script>
	<script src="js/writeHTML.js"></script>

	<script>
	    function check_login(){
	    	var login=check_cookie();
	    	if(!login){
	    		$(document).ready(function(){
                    $('#modal1').openModal();
                });  
	    	}
	    }

	    $(document).ready(function(){
        	$('#file-name').html("<%=filename%>");
        	$('#file-profile').html("<%=profile%>");
        	$('#ID').attr("value","<%=id%>");
        	$("#profile").html("<%=profile%>");
        });

        function cancel(){
        	$("#profile").html("<%=profile%>");
        	$("#tag").attr('value',"<%=tag%>");
        }

	</script>

</head>
<body onload="write_header();write_footer();check_login()">

	<header></header>

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
			<form action="/UniNote/ChangeDocumentInfoServlet" method="post" class="col s12 l4" id="my-form">
			<input class="validate" type="hidden" name="id" value="<%=id%>">
				<div class="input-field col s12">
					<textarea class="materialize-textarea" id="profile" name="profile"  length="300"></textarea>
					<label for="profile">简介</label>
				</div>
				<div class="input-field col s12">
					<input class="validate" type="text" id="tag" name="tag" value=tag>
					<label for="tag">标签</label>
				</div>
				<div class="row col s12">
					<a class="btn btn-primary" onclick="$('#my-form').submit()">保存</a>
					<a class="btn btn-primary" id="cancel-button" onclick="cancel()">取消</a>
				</div>
			</form>
		</div>
	</div>

	<div id="footer"></div>

</body>
</html>