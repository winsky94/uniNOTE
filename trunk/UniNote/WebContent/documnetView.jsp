<%@page import="server.DocConverter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%	
	String id=request.getParameter("ID");
	String filename=new String(request.getParameter("filename").getBytes("iso-8859-1"), "utf-8");
	String profile=new String(request.getParameter("profile").getBytes("iso-8859-1"), "utf-8");
	String origin_filename=new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
	String zanNum=request.getParameter("zanNum");
	String caiNum=request.getParameter("caiNum");
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
	<script src="js/jQuery.js"></script>
	<script src="js/flexpaper_flash.js"></script>
	<script src="js/flexpaper_flash_debug.js"></script>
	<script src="js/materialize.js"></script>
	<script src="js/LoginAjax.js"></script>
	<script src="js/cookie.js"></script>
	<script src="js/xmlhttp.js"></script>
	<script src="js/jquery-form.js"></script>
	<script src=""></script>
	<!-- CSS  -->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
	<link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
	<link href="css/comment.css" type="text/css" rel="stylesheet" media="screen,projection">

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
    </style>

	<script type="text/javascript">

        $(document).ready(function(){
        	$('#file-name').html("<%=filename%>");
        	$('#file-profile').html("<%=profile%>");
        	$('#ID').attr("value","<%=id%>");
        	$('#commentNow').html("好评数：<%=zanNum%>，差评数：<%=caiNum%>");
        });

        function check_login(){
        	login=check_cookie();
            if(!login){      	
                $(document).ready(function(){
                    $('#modal1').openModal();
                });  
                return false;      
            }else{
            	$("#nickname").attr("value",$("#user-name").attr("title"));
            	return true;
            }
        }
        
        function show_comment(xmlhttp_comment){
        	if (xmlhttp_comment.readyState==4 && xmlhttp_comment.status==200){
                var text=xmlhttp_comment.responseText;
                var comments=text.split(",");
                if(comments[2]==0){
                	var result="<p>您已经评价过此文档了。</p><p>好评数："+comments[0]+"，差评数："+comments[1]+"</p>";
                }else if(comments[2]==1){
                var result="<p>感谢您的评价!</p><p>好评数："+comments[0]+"，差评数："+comments[1]+"</p>";
            	}else if(comments[2]==2){
            		var result="<p>感谢你自己？</p><p>好评数："+comments[0]+"，差评数："+comments[1]+"</p>";
            	}
                $("#commentArea").html(result);
            }
        }
        function comment(operand){
            login=check_cookie();
            if(!login){      	
                $(document).ready(function(){
                    $('#modal1').openModal();
                });  
                return false;      
            }
        	var xmlhttp_comment=getXmlHttp();
        	var username=$('#user-name').attr('title');
        	var id=<%=id%>;
            if(xmlhttp_comment!=null){
            	xmlhttp_comment.open("GET","/UniNote/CommentServlet?flag="+operand+"&documentID="+id+"&nickname="+username,true);
            	xmlhttp_comment.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            	xmlhttp_comment.onreadystatechange=function(){show_comment(xmlhttp_comment)};
            	xmlhttp_comment.send();
            }else{
            	alert("Your browser does not support XMLHttpRequest.");
            }
		}
        
        function init_form(){

        	form=$("<form>");//定义一个form表单
            form.attr("style","display:none");
            form.attr("target","");
            form.attr("method","post");
            form.attr("action","DownLoadServlet");

            var input1=$("<input>");
            input1.attr("type","hidden");
            input1.attr("name","ID");
            input1.attr("value","2");        
            form.append(input1);

            var input2=$("<input>");
            input2.attr("type","hidden");
            input2.attr("name","nickname");
            input2.attr("value","sysan");   
            form.append(input2);

            var input3=$("<input>");
            input3.attr("type","submit");
            input3.attr("name","download");
            input3.attr("value","下载");   
            input3.addClass("btn");
            input3.addClass("btn-primary");
            input3.attr("onclick","submit_form()");
            form.append(input3);            

            $("#form-area").html(form);//将表单放置在web中       

            
        }

        function submit_form(){
        	form.submit();//表单提交
        }


    </script>

	<title>detail</title>
</head>
<body onload="write_header();init_form()">

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
				<section class="comments">
					<article class="comment">
						<a class="comment-img" href="#non">
							<img src="images/portrait.jpg" alt="" width="50" height="50"></a>
						<div class="comment-body">
							<div class="text">
								<p>Hello, this is an example comment</p>
							</div>
							<p class="attribution">
								by
								<a href="#non">Joe Bloggs</a>
								at 14:23pm, 4th Dec 2010
							</p>

						</div>
					</article>
					<article class="comment">
						<a class="comment-img" href="#non">
							<img src="images/portrait.jpg" alt="" width="50" height="50"></a>
						<div class="comment-body">
							<div class="text">
								<p>This is a much longer one that will go on for a few lines.</p>
								<p>
									It has multiple paragraphs and is full of waffle to pad out the comment. Usually, you just wish these sorts of comments would come to an end.
								</p>
							</div>
							<p class="attribution">
								by
								<a href="#non">Joe Bloggs</a>
								at 14:23pm, 4th Dec 2010
							</p>
						</div>
					</article>
				</section>
				<div class="input-field" id="my-comment-area"> <i class="material-icons prefix">mode_edit</i>
					<textarea id="icon_prefix2" class="materialize-textarea"></textarea>
					<label for="icon_prefix2">你的评价</label>
					<a class="btn btn-primary">提交</a>
				</div>
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
				<div id="commentArea" class="row">
					<div class="col s12">
						<a class="waves-effect waves-light btn" onclick="return comment(0)"> <i class="material-icons left"></i>
							赞
						</a>
						<a class="waves-effect waves-light btn" onclick="return comment(1)">
							<i class="material-icons right"></i>
							踩
						</a>
					</div>
					<div class="col s12" id="commentNow"></div>
				</div>
				<!--
				<form id="download-form" method="post" action="/UniNote/DownLoadServlet" onsubmit="return check_login()">
					<input type="hidden" id="ID" name="ID" value="456">
					<input type="hidden" id="nickname" name="nickname" value="">
					<input type="submit" value=" 下载 " class="btn btn-primary">
				</form>
				-->
				<div id="form-area">
					
				</div>
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