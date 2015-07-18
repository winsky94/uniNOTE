//用户名检验方法
//这个方法将使用XMLHTTPRequest对象进行AJAX的异步数据交互
function verify() {
	/*
	// 使用dom的方式获取文本框中的值
	// .value可以获取一个元素节点的value属性
	*/
	userName = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	/*

	// 创建XMLHttprequest对象
	// 需要针对IE和其他类型的浏览器建议这个对象的不同方式写不同的代码
	if (window.XMLHttpRequest) {
		// 针对FireFox,Mozillar,opera,safari,ie7,ie8
		xmlHttp = new XMLHttpRequest();
		// 针对某些特定版本的moziller浏览器的BUG进行修正
		if (xmlHttp.overrideMimeType) {
			xmlHttp.overrideMimeType("text/xml");
		}
	} else if (window.ActiveXObject) {
		// 针对IE6,ie5.5 ie5
		// 两个可以用于创建XMLHTTPREQUEST对象控件名称，保存在一个JS数组中
		// 排在前面的版本较新
		var activexName = new ActiveXObject["MSXML2.XMLHTTP",
				"Microsoft.XMLHTTP"];
		for (var i = 0; i < activexName.length; i++) {
			try {
				// 取出一个控件名进行创建，如果创建成功就终止循环
				// 如果创建失败，会抛出异常，然后可以继续循环，继续尝试创建
				xmlHttp = new ActiveXObject(activexName[i]);
				break;
			} catch (e) {

			}
		}

	}
	// 确认XMLHTTPREQUEST对象创建成功
	/*
	 * if(!xmlHttp){ alert("xmlhttprequest对象创建失败"); return; }else{
	 * alert(xmlHttp); }
	 */
   
	// 2.注册回调函数
	// 注册回调函数时，只需要函数名，不要加括号
	// 我们需要将函数名注册，如果加上括号，就会把函数的返回值注册上，这是错误的
	xmlHttp=getXmlHttp();
	xmlHttp.onreadystatechange = callback;
	// 3.设置连接信息
	// 第一个参数表示http的请求方式，支持所有http的请求方式，主要是get和post
	// 第二个表示请求的url地址，get方式请求的参数也是url中
	// 第三个参数表示采用异步还是同步方式交互，true表示异步
	// xmlHttp.open("GET", "servlet/AjaxServlet?name=" + userName, true);

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
	// xmlHttp.send(null);
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
			// 使用responseXML的方式来接收xml数据对象的DOM对象
			var text=xmlHttp.responseText;
			alert("text="+text);

			//var messageNodes = xmlDoc.getElementsByTagName("message");
			//alert("messageNode="+(new XMLSerializer()).serializeToString(messageNodes[0]));
			var message=text.substring(9,text.length-9);

			
			if (message.length > 0) {
				// 获取message节点的文本内容
				var responseMessage = message;
				alert("nodeValue="+responseMessage);
				var strs= new Array(); //定义一数组 
				strs=responseMessage.split("&"); //字符分割
				
				if(strs[0]=='h'){
					var my_school=strs[1];
				    //登陆成功，写入cookie
				    add_cookie('username',username,30*24);
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