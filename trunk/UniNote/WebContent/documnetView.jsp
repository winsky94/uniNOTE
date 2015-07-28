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
	String isOK=request.getParameter("isOK");
	System.out.println("isOK="+isOK);
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
	<script src="js/jquery.form.js"></script>
	<script src="js/writeHTML.js"></script>
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
		.table-container{
			display: table;

		}
		.row-container{
			display: table-row;
		}
		.cell-container{
			display: table-cell;
			margin: 5px;
			padding-right: 10px;
		}
    </style>

	<script type="text/javascript">

        $(document).ready(function(){
        	$('#file-name').html("<%=filename%>");
        	$('#file-profile').html("<%=profile%>");
        	$('#ID').attr("value","<%=id%>");
        	$('#commentNow').html("好评数：<%=zanNum%>，差评数：<%=caiNum%>");
        	show_chats();
        });
        function show_chats(){
	    	var xmlhttp_sc=getXmlHttp();
        	var id=<%=id%>;
            if(xmlhttp_sc!=null){
            	xmlhttp_sc.open("GET","/UniNote/ChatServlet?documentID="+id,true);
            	xmlhttp_sc.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            	xmlhttp_sc.onreadystatechange=function(){
            		if (xmlhttp_sc.readyState==4 && xmlhttp_sc.status==200){
            			var text=xmlhttp_sc.responseText;
            			try{
			                var xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
			                xmlDoc.async="false";
			                xmlDoc.loadXML(text);
			            }catch(e){
			                try{
			                    var parser=new DOMParser();
			                    xmlDoc=parser.parseFromString(text,"text/xml");
			                }catch(e) {
			            	    alert(e.message);
			                }
			            }
			            var chats=xmlDoc.getElementsByTagName("chat");
			            var result=write_chat_list(chats);
            			$("#chatArea").html(result);
            		}
            	};
            	xmlhttp_sc.send();
 				
            }else{
            	alert("Your browser does not support XMLHttpRequest.");
            }
        }
        function submit_chat(){
        	var xmlhttp_sub=getXmlHttp();
        	var id=<%=id%>;
        	var username=$('#user-name').attr('title');
        	var chatContent=document.getElementById("chat_textArea").value;
            if(xmlhttp_sub!=null){
            	xmlhttp_sub.open("GET","/UniNote/SubmitChatServlet?documentID="+id+"&nickname="+username+"&content="+chatContent,true);
            	xmlhttp_sub.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            	xmlhttp_sub.send();
            	document.getElementById("chat_textArea").value="";
 				setTimeout("show_chats();",1000);
            }else{
            	alert("Your browser does not support XMLHttpRequest.");
            }
        }
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
            		var result="<p>评价你自己？</p><p>好评数："+comments[0]+"，差评数："+comments[1]+"</p>";
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
        	var nickname=$("#user-name").attr("title");
        	form=$("<form>");//定义一个form表单
            form.attr("style","display:block");
            form.attr("target","");
            form.attr("method","post");
            form.attr("action","DownLoadServlet");
            form.attr("onsubmit","return check_login()");

            var input1=$("<input>");
            input1.attr("type","hidden");
            input1.attr("name","ID");
            input1.attr("value","<%=id%>");        
            form.append(input1);

            var input2=$("<input>");
            input2.attr("type","hidden");
            input2.attr("name","nickname");
            input2.attr("value",nickname);   
            form.append(input2);

            var input3=$("<input>");
            input3.attr("type","button");
            input3.attr("name","download");
            input3.attr("value","下载");   
            input3.addClass("btn");
            input3.addClass("btn-primary");
            input3.attr("onclick","submit_form(form)");
            form.append(input3);   

            $("#form-area").append(form);//将表单放置在web中        
        }

        function submit_form(form){
        	if("<%=isOK%>"=='h'){
                form.submit();       	    
            }else{
            	$('#result').html("您的积分不足");
            } 
        }
        
    </script>

	<title>detail</title>
</head>
<body onload="write_header();write_footer();init_form()">

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
					 ZoomTransition : 'easenone',
					 ZoomTime : 0.5,
					 ZoomInterval : 0.2,
					 FitPageOnLoad : true,
					 FitWidthOnLoad : true,
					 FullScreenAsMaxWindow : false,
					 ProgressiveLoading : true,
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
				<section class="comments" id="chatArea"></section>
				<div class="input-field" id="my-chat-area"> <i class="material-icons prefix">mode_edit</i>
					<textarea id="chat_textArea" class="materialize-textarea"></textarea>
					<label for="chat_textArea">你的评价</label>
					<a class="btn btn-primary" onclick="submit_chat()">提交</a>
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

				
				<div class="table-container">
					<div class="row-container">
					        <div class="cell-container" id="form-area">
					        </div>
					        <div class="cell-container">
							<font color="red" size="2"><span id="result" ></span></font>
						   </div>
					</div>				
				</div>

			</div>
		</div>
	</div>
<div id="footer"></div>
</body>
</html>