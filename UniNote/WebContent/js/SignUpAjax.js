﻿//用户名检验方法  
//这个方法将使用XMLHTTPRequest对象进行AJAX的异步数据交互  
var xmlHttp;  
function verify(){  
    //使用dom的方式获取文本框中的值  
    //.value可以获取一个元素节点的value属性  
    var userName = document.getElementById("nickname").value;
//    var password = document.getElementById("password").value;
//    var email = document.getElementById("email").value;
      
    //创建XMLHttprequest对象  
    //需要针对IE和其他类型的浏览器建议这个对象的不同方式写不同的代码  
      
    if(window.XMLHttpRequest){  
          
        //针对FireFox,Mozillar,opera,safari,ie7,ie8  
        xmlHttp = new XMLHttpRequest();  
        //针对某些特定版本的moziller浏览器的BUG进行修正  
        if(xmlHttp.overrideMimeType){  
            xmlHttp.overrideMimeType("text/xml");  
        }  
    }else if(window.ActiveXObject){  
        //针对IE6,ie5.5 ie5  
        //两个可以用于创建XMLHTTPREQUEST对象控件名称，保存在一个JS数组中  
        //排在前面的版本较新  
        var activexName = new ActiveXObject["MSXML2.XMLHTTP","Microsoft.XMLHTTP"];  
        for(var i=0; i<activexName.length; i++){  
            try{  
                //取出一个控件名进行创建，如果创建成功就终止循环  
                //如果创建失败，会抛出异常，然后可以继续循环，继续尝试创建  
                xmlHttp = new ActiveXObject(activexName[i]);  
                break;  
            }catch(e){  
                  
            }  
        }  
          
    }  
    //确认XMLHTTPREQUEST对象创建成功  
    /*if(!xmlHttp){ 
        alert("xmlhttprequest对象创建失败"); 
        return; 
    }else{ 
        alert(xmlHttp); 
    }*/  
      
    //2.注册回调函数  
    //注册回调函数时，只需要函数名，不要加括号  
    //我们需要将函数名注册，如果加上括号，就会把函数的返回值注册上，这是错误的  
    xmlHttp.onreadystatechange = callback;  
    //3.设置连接信息  
    //第一个参数表示http的请求方式，支持所有http的请求方式，主要是get和post  
    //第二个表示请求的url地址，get方式请求的参数也是url中  
    //第三个参数表示采用异步还是同步方式交互，true表示异步  
    //xmlHttp.open("GET", "servlet/AjaxServlet?name=" + userName, true);  
      
    //post方式请求的代码并访问servlet  
    xmlHttp.open("GET", "/UniNote/SignUpServlet", true);  
    //post方式需要自己设置http的请求头  
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");  
    //post方式发送数据  
    xmlHttp.send("nickname=" + userName);
//    xmlHttp.send("password=" + password);
//    xmlHttp.send("email=" + email);
    //4.发送数据，开始和服务器端进行交互  
    //同步方式下，send这句话全在服务器端数据回来后才执行完  
    //异步方式下，send这句话会立即完成执行  
    //xmlHttp.send(null);  
}  
//回调函数   
function callback(){  
    //5.接收响应数据  
    //判断对象的状态 是交互完成  
    if(xmlHttp.readyState == 4){  
        //判断http的交互是否成功  
        if(xmlHttp.status == 200||xmlHttp.status == 0){  
            //使用responseXML的方式来接收xml数据对象的DOM对象  
            var domObj = xmlHttp.responseXML;  
            //<message>ggggg</message>  
            //getElementsByTagName根据标签名获取元素节点,返回的是一个数组  
            var messageNodes = domObj.getElementsByTagName("message");  
            if(messageNodes.length >0){  
              
            //获取message节点的文本内容  
            var textNode1 = messageNodes[0].firstChild;  
            var responseMessage1 = textNode1.nodeValue;  
//            alert("1");
//            var textNode2 = messageNodes[1].firstChild; 
//            var responseMessage2 = textNode2.nodeValue;
//            alert("2");
//            var textNode3 = messageNodes[2].firstChild;  
//            var responseMessage3 = textNode3.nodeValue;
//            alert("3");
            //将数据显示在页面上  
            //通过dom的方式到div标签所对应的元素节点  
            var divNode1 = document.getElementById("result");  
//            var divNode2 = document.getElementById("resultpassword");  
//            var divNode3 = document.getElementById("resultemail");  
            //设置元素节点中的html内容  
            divNode1.innerHTML = responseMessage1; 
//            divNode2.innerHTML = responseMessage2; 
//            divNode3.innerHTML = responseMessage3; 
              
            }else{  
                alert("XML数据格式错误，原始文本内容为："+xmlHttp.responseText);  
            }  
        }else{  
            alert("出错了");  
            alert(xmlHttp.status);  
            alert(xmlHttp.responseText);
        }  
    }  
}  