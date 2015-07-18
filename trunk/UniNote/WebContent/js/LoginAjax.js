//用户名检验方法
//这个方法将使用XMLHTTPRequest对象进行AJAX的异步数据交互
function verify() {
	/*
	// 使用dom的方式获取文本框中的值
	// .value可以获取一个元素节点的value属性
	*/
	userName = document.getElementById("username").value;
	var password = document.getElementById("password").value;
   
	xmlHttp=getXmlHttp();
	xmlHttp.onreadystatechange = callback;

	// post方式请求的代码并访问servlet
	xmlHttp.open("POST", "/UniNote/LoginHandleServlet", true);
	// post方式需要自己设置http的请求头
	xmlHttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	// post方式发送数据
	xmlHttp.send("username=" + userName+"&password="+password);
	// 4.发送数据，开始和服务器端进行交互
	// 同步方式下，send这句话全在服务器端数据回来后才执行完
	// 异步方式下，send这句话会立即完成执行
}

function getXmlHttp(){
    var xmlhttp=null;
    if (window.XMLHttpRequest){
        // code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else{
        // code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xmlhttp;
}

// 回调函数
function callback() {
	// 5.接收响应数据
	// 判断对象的状态 是交互完成
	if (xmlHttp.readyState == 4) {
		// 判断http的交互是否成功
		if (xmlHttp.status == 200) {

			var text=xmlHttp.responseText;
			alert("text="+text);
			var message=text.substring(9,text.length-12);
			
			if (message.length > 0) {
	
				var responseMessage = message;
				var strs= new Array(); //定义一数组 
				strs=responseMessage.split("&"); //字符分割
				
				if(strs[0]=='h'){
					var my_school=strs[1];
				    //登陆成功，写入cookie
				    alert("username="+userName+" school="+my_school);
				    add_cookie('username',userName,30*24);
				    add_cookie('my_school',my_school,30*24);
				    window.location.href="/UniNote/list.html";
			    }
				else{
					// 将数据显示在页面上
					// 通过dom的方式到div标签所对应的元素节点
					var divNode = document.getElementById("result");
					// 设置元素节点中的html内容
					divNode.innerHTML = responseMessage;
				}
			} else {
				alert("XML数据格式错误，原始文本内容为：" + xmlHttp.responseText);
			}
		} else {
			alert("出错了");
		}
	}
}

function add_cookie(name,value,expireHours){
      var cookieString=name+"="+escape(value);
      //判断是否设置过期时间
      if(expireHours>0){
             var date=new Date();
             date.setTime(date.getTime+expireHours*3600*1000);
             cookieString=cookieString+"; expire="+date.toGMTString();
      }
      document.cookie=cookieString;
}
