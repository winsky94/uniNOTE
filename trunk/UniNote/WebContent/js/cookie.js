
//函数返回名称为name的cookie值，如果不存在则返回空
function get_cookie(name){
      var strcookie=document.cookie;
      var arrcookie=strcookie.split("; ");
      for(var i=0;i<arrcookie.length;i++){
            var arr=arrcookie[i].split("=");
            if(arr[0]==name)return arr[1];
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
}


function check_cookie(){
  username=get_cookie('username');
  my_school=get_cookie('my_school');
  if (username!=null && username!="" && my_school!=null && my_school!=""){
    if(username!='v'){
      write_header_login(username,my_school);
    }
  }
}


function write_header_login(username,university){

    var txt='<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script> \
    <script src="js/materialize.js"></script> \
    <!-- Dropdown Structure -->\
    <ul id="dropdown1" class="dropdown-content">\
      <li><a href="#!">南京大学</a></li>\
      <li><a href="changeschool.html">切换</a></li>\
      <li class="divider"></li>\
      <li><a href="http://www.nju.edu.cn" target="_blank">主页</a></li>\
    </ul>\
    <!-- Dropdown2 Structure -->\
    <ul id="dropdown2" class="dropdown-content">\
      <li><a href="#!">王宁</a></li>\
      <li><a href="user_edit_info.html">个人信息</a></li>\
    </ul>\
    <!-- Dropdown Structure -->\
    <ul id="dropdown3" class="dropdown-content">\
      <li><a  href="#!">南京大学</a></li>\
      <li><a href="changeschool.html">切换</a></li>\
      <li class="divider"></li>\
      <li><a href="http://www.nju.edu.cn" target="_blank">主页</a></li>\
    </ul>\
    <!-- Dropdown2 Structure -->\
    <ul id="dropdown4" class="dropdown-content">\
      <li><a href="#!">王宁</a></li>\
      <li><a href="user_edit_info.html">个人信息</a></li>\
    </ul>\
        <nav> \
            <div class="nav-wrapper teal lighten-3"> \
                <div class="container"> \
                    <div class="row"> \
                        <div class="col l6 s12"> \
                            <a href="#!" class="brand-logo">uniNOTE</a> \
                        </div> \
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
                                <a class="dropdown-button" href="#!" id="university-info" title="'+university+'" data-activates="dropdown1" name="university"> \
                                    '+university+' <i class="material-icons right">arrow_drop_down</i> \
                                </a></li> \
                            <li> \
                                <a class="dropdown-button" id="user-name" title="'+username+'" href="#!" data-activates="dropdown2"> \
                                    '+username+' <i class="material-icons right">arrow_drop_down</i> \
                                </a> \
                            </li> \
                        </ul> \
                        <a href="#" data-activates="slide-out" class="button-collapse"> \
                            <i class="mdi-navigation-menu"></i></a> \
                    </div> \
                </div> \
            </div></nav>';
    $("header").html(txt);
}

function write_header_not_login(){
    var txt=' <!-- Dropdown Structure --> \
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
                                data-activates="dropdown1"> 南京大学 <i \
                                    class="material-icons right">arrow_drop_down</i> \
                            </a></li> \
                            <li><a class="modal-trigger" href="#modal1">登录/注册</a></li> \
                        </ul> \
                        <ul class="side-nav" id="mobile-demo"> \
                            <li><a href="index.html">首页</a></li> \
                            <li><a href="list.html">文档</a></li> \
                            <li><a href="upload.html">上传文档</a></li> \
                            <!-- Dropdown Trigger --> \
                            <li><a class="dropdown-button" href="#!" \
                                data-activates="dropdown2"> 南京大学 <i \
                                    class="material-icons right">arrow_drop_down</i> \
                            </a></li> \
                            <li><a class="modal-trigger" href="#modal1">登录/注册</a></li> \
                        </ul> \
                        <a href="#" data-activates="slide-out" class="button-collapse"> \
                            <i class="mdi-navigation-menu"></i> \
                        </a> \
                    </div> \
                </div> \
            </div> \
        </nav> \
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
                                        <input id="password" type="password" name="password" \
                                            placeholder="密码" /> \
                                    </div> \
                                    <p> \
                                        <input type="checkbox" class="filled-in" id="test1" /> <label \
                                            for="test1">下次自动登录</label><font color="red" size="2"> \
                                            <span id="result"> </span></font><br/><br/> \
                                    </p> \
                                    <div class="span1"> \
                                        <input type="submit" value=" 登录 " onclick="verify();" \
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
