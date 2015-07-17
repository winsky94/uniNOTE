
//函数返回名称为name的cookie值，如果不存在则返回空
function getcookie(name){
      var strcookie=document.cookie;
      var arrcookie=strcookie.split("; ");
      for(var i=0;i<arrcookie.length;i++){
            var arr=arrcookie[i].split("=");
            if(arr[0]==name)return arr[1];
      }
      return "";
}

function addcookie(name,value,expireHours){
      var cookieString=name+"="+escape(value);
      //判断是否设置过期时间
      if(expireHours>0){
             var date=new Date();
             date.setTime(date.getTime+expireHours*3600*1000);
             cookieString=cookieString+"; expire="+date.toGMTString();
      }
      document.cookie=cookieString;
}

function deletecookie(name){
       var date=new Date();
       date.setTime(date.getTime()-10000);
       document.cookie=name+"=v; expire="+date.toGMTString();
}

function checkCookie(){
  username=getcookie('username');
  university=getcookie('university');
  if (username!=null && username!="" && university!=null && university!=""){
    alert('Welcome again '+username+" from "+university+'!');
    write_header_login(username,university);
  }
  else {
    write_header_not_login();
  }
}