//function open_modal(){
//    $('#modal1').openModal();
//}


//函数返回名称为name的cookie值，如果不存在则返回空
function get_cookie(name){
      var strcookie=document.cookie;
      var arrcookie=strcookie.split("; ");
      for(var i=0;i<arrcookie.length;i++){
            var arr=arrcookie[i].split("=");
            if(arr[0]==name)return unescape(arr[1]);
      }
      return "";
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

function delete_cookie(){
    var date=new Date();
    date.setTime(date.getTime()-10000);
    document.cookie='username'+'=v; expire='+date.toGMTString();
    document.cookie='my_school'+'=v; expire='+date.toGMTString();
    document.cookie="current_school"+'=v; expire='+date.toGMTString();
}


function check_cookie(){
  var username=get_cookie('username');
  if (username!=null && username!="" && username!='v'){
      return true;
  }else{
    return false;
  }
}

function write_header(){
  var login=check_cookie();
  if(!login){
    write_header_not_login();
  }else{
    var username=get_cookie('username');
    var my_school=get_cookie('my_school');
    var current_school=get_cookie('current_school');
    if(current_school!=null && current_school!="" && current_school !="v"){
      write_header_login(username,current_school);
    }else{
      write_header_login(username,my_school);
    }
  }
}

function exit(){
   delete_cookie('usernme');
   delete_cookie('my_school');
   window.location.reload(true);
}

function write_header_login(username,my_school){

    var txt='<script>  \
       $(document).ready(function(){  \
         $(".dropdown-button").dropdown();  \
         $(".button-collapse").sideNav();  \
         $(".modal-trigger").leanModal();  \
       }); \
    </script> \
    <!-- Dropdown Structure -->\
    <ul id="dropdown1" class="dropdown-content">\
      <li><a href="#!">'+my_school+'</a></li>\
      <li><a href="changeschool.html">切换</a></li>\
      <li class="divider"></li>\
      <li><a href="http://www.nju.edu.cn" target="_blank">主页</a></li>\
    </ul>\
    <!-- Dropdown2 Structure -->\
    <ul id="dropdown2" class="dropdown-content">\
      <li><a href="#!">'+username+'</a></li>\
      <li><a href="user.html">个人信息</a></li>\
      <li><a href="#!" onclick="exit()">退出登录</a></li>\
    </ul>\
    <!-- Dropdown3 Structure -->\
    <ul id="dropdown3" class="dropdown-content">\
      <li><a  href="#!">'+my_school+'</a></li>\
      <li><a href="changeschool.html">切换</a></li>\
      <li class="divider"></li>\
      <li><a href="http://www.nju.edu.cn" target="_blank">主页</a></li>\
    </ul>\
    <!-- Dropdown4 Structure -->\
    <ul id="dropdown4" class="dropdown-content">\
      <li><a href="#!">'+username+'</a></li>\
      <li><a href="user.html">个人信息</a></li>\
      <li><a href="#!" onclick="exit()">退出登录</a></li>\
    </ul>\
    <div class="navbar-fixed"> \
        <nav> \
            <div class="nav-wrapper teal lighten-3"> \
                <div class="container"> \
                    <div class="row"> \
                            <a href="#!" class="brand-logo">uniNOTE</a> \
                            <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a> \
                            <ul class="right hide-on-med-and-down"> \
                                <li> \
                                    <a href="index.html">首页</a> \
                                </li> \
                                <li> \
                                    <a href="list.html">文档</a> \
                                </li> \
                                <li> \
                                    <a href="upload.html">上传文档</a> \
                                </li> \
                                <!-- Dropdown Trigger --> \
                                <li> \
                                    <a class="dropdown-button" href="#!" id="university-info" title="'+my_school+'" data-activates="dropdown1" name="university"> \
                                        '+my_school+' <i class="material-icons right">arrow_drop_down</i> \
                                    </a></li> \
                                <li> \
                                    <a class="dropdown-button" id="user-name" title="'+username+'" href="#!" data-activates="dropdown2">'+username+'<i class="material-icons right">arrow_drop_down</i> \
                                    </a> \
                                </li> \
                            </ul> \
                            <ul class="side-nav" id="mobile-demo"> \
                                <li> \
                                    <a href="index.html">首页</a> \
                                </li> \
                                <li> \
                                    <a href="list.html">文档</a> \
                                </li> \
                                <li> \
                                    <a href="upload.html">上传文档</a> \
                                </li> \
                                <!-- Dropdown Trigger --> \
                                <li> \
                                    <a class="dropdown-button" href="#!" title="'+my_school+'" data-activates="dropdown3" name="university">'
                                    +my_school+' <i class="material-icons right">arrow_drop_down</i> \
                                    </a></li> \
                                <li> \
                                    <a class="dropdown-button" title="'+username+'" href="#!" data-activates="dropdown4">'+username+'<i class="material-icons right">arrow_drop_down</i> \
                                    </a> \
                                </li>  \
                            </ul> \
                    </div> \
                </div> \
                </div></nav></div>';
    $("header").html(txt);
}

function write_header_not_login(){
    var txt='<script>  \
       $(document).ready(function(){  \
         $(".dropdown-button").dropdown();  \
         $(".button-collapse").sideNav();  \
         $(".modal-trigger").leanModal();  \
       }); \
    </script> \
    <!-- Dropdown Structure --> \
        <ul id="dropdown1" class="dropdown-content"> \
            <li><a href="#!">南京大学</a></li> \
            <li><a href="changeschool.html">切换</a></li> \
            <li class="divider"></li> \
            <li><a href="http://www.nju.edu.cn" target="_blank">主页</a></li> \
        </ul> \
        <!-- Dropdown Structure --> \
        <ul id="dropdown2" class="dropdown-content"> \
            <li><a href="#!">南京大学</a></li> \
            <li><a href="changeschool.html">切换</a></li> \
            <li class="divider"></li> \
            <li><a href="http://www.nju.edu.cn" target="_blank">主页</a></li> \
        </ul> \
        <div class="navbar-fixed"> \
        <nav> \
            <div class="nav-wrapper teal lighten-3"> \
                <div class="container"> \
                    <div class="row"> \
                        <div class="col l6 s12"> \
                            <a href="#!" class="brand-logo">uniNOTE</a> \
                            <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a> \
                        </div> \
                        <ul class="right hide-on-med-and-down"> \
                            <li><a href="index.html">首页</a></li> \
                            <li><a href="list.html">文档</a></li> \
                            <li><a href="upload.html">上传文档</a></li> \
                            <!-- Dropdown Trigger --> \
                            <li><a class="dropdown-button" href="#!" \
                                data-activates="dropdown1" id="university-info" title="南京大学"> 南京大学 <i \
                                    class="material-icons right">arrow_drop_down</i> \
                            </a></li> \
                            <li><a class="modal-trigger" href="#modal1">登录/注册</a></li> \
                        </ul> \
                        <ul class="side-nav" id="mobile-demo"> \
                            <li><a href="index.html">首页</a></li> \
                            <li><a href="list.html">文档</a></li> \
                            <li><a href="upload.html">上传文档</a></li> \
                            <!-- Dropdown Trigger --> \
                            <li><a class="dropdown-button" id="school-dropdown" href="#!" \
                                data-activates="dropdown2"> 南京大学 <i \
                                    class="material-icons right">arrow_drop_down</i> \
                            </a></li> \
                            <li><a class="modal-trigger" id="login-trigger" href="#modal1">登录/注册</a></li> \
                        </ul> \
                    </div> \
                </div> \
            </div> \
        </nav> \
        </div> \
        <!-- Modal Structure --> \
        <div id="modal1" class="modal"> \
            <div class="modal-content"> \
                <section class="loginBox row-fluid"> \
                    <div class="tabbable" id="tabs-634549"> \
                            <div class="tab-content"> \
                                <div class="tab-pane" id="panel-60560"></div> \
                                <div class="tab-pane active" id="panel-549981"> \
                                    <div> \
                                        <input id="username" type="text" name="username" \
                                            placeholder="用户名" /> \
                                    </div> \
                                    <div> \
                                        <input id="password" type="password" name="password" onkeydown="if(event.keyCode==13){verify_login()}" \
                                            placeholder="密码" /> \
                                    </div> \
                                    <p> \
                                        <input type="checkbox" class="filled-in" id="test1" /> <label \
                                            for="test1">下次自动登录</label><font color="red" size="2"> \
                                            <span id="result"> </span></font><br/><br/> \
                                    </p> \
                                    <div class="span1"> \
                                        <input type="submit" value=" 登录 " onclick="verify_login()" \
                                            class="btn btn-primary"> <a href="sign_up.html"> \
                                            <input type="button" value=" 注册 " class="btn btn-primary"> \
                                        </a> \
                                    </div> \
                                </div> \
                            </div> \
                    </div> \
                </section> \
            </div> \
        </div>';
        $("header").html(txt);
} 
