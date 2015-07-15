 
 function write_document_list(file_elements){
	var txt=" ";
    for(i=0;i<file_elements.length;i++){

        //开头
        txt=txt + "<li class='collection-item avatar'>";

        //根据文件格式选图片       
        var extension=file_elements[i].getElementsByTagName("path").nodeValue;  //TagName undefined
        if(extension!=null){
           txt=txt+"<i class='circle'><img src='images/document-icons/"+extension+".png'></i>";
        }else{
        	txt=txt+"<i class='circle'><span>other</span></i>";
        }        
                
        txt=txt+"<i class='circle'><img src='images/document-icons/"+"pdf"+".png'></i>";
                
        //文件链接 文件名
        txt=txt+"<a class='title' href='"+file_elements[i].getElementsByTagName("path").nodeValue +"'>"+file_elements[i].getElementsByTagName("name").nodeValue +"</a><p>First Line<br>Second Line</p>";

        //结尾
        txt=txt+"<a href='#!' class='secondary-content' title='not-selected'><i class='material-icons'>grade</i></a>";
        txt=txt +"</li>";
                
    } //end for
    return txt;
}


function write_user_info(user_info){
    var txt=""

}

function write_edit_user_info(){
    var txt="<form action='' method='post' class='col s12'>";   //server undefined
    var nickname=$("#nickname").html();
    txt = txt + '<div class="input-field col s12">  \
                    <input type="hidden" name="nickname" value="'+nickname+'"> \
                    <input id="email" type="email" name="email" class="validate">  \
                    <label for="email">Email</label>  \
                  </div>  \
                <div class="input-field col s12">  \
                 <input id="school" type="text" name="school" class="validate">  \
                 <label for="school">School</label>  \
                </div>  \
                <div class="input-field col s12">  \
                  <input id="phonenumber" type="text" name="phonenumber" class="validate">  \
                  <label for="phonenumber">PhoneNumber</label>  \
                </div>  \
                <div class="input-field col s6">  \
                <input type="hidden" name="flag" value="userinfo">\
                  <input type="submit" value=" 保存 " class="btn btn-primary">  \
                </div>  \
        </form> ';
    $("#user-info-container").html(txt);
}

function write_header(){

    var txt='<!-- Dropdown Structure -->\
		<ul id="dropdown1" class="dropdown-content">\
			<li><a href="#!">南京大学</a></li>\
			<li><a href="#!">切换</a></li>\
			<li class="divider"></li>\
			<li><a href="http://www.nju.edu.cn" target="_blank">主页</a></li>\
		</ul>\
		<!-- Dropdown2 Structure -->\
		<ul id="dropdown2" class="dropdown-content">\
			<li><a href="#!">王宁</a></li>\
			<li><a href="#!">个人信息</a></li>\
		</ul>\
		<!-- Dropdown Structure -->\
		<ul id="dropdown3" class="dropdown-content">\
			<li><a href="#!">南京大学</a></li>\
			<li><a href="#!">切换</a></li>\
			<li class="divider"></li>\
			<li><a href="http://www.nju.edu.cn" target="_blank">主页</a></li>\
		</ul>\
		<!-- Dropdown2 Structure -->\
		<ul id="dropdown4" class="dropdown-content">\
			<li><a href="#!">王宁</a></li>\
			<li><a href="#!">个人信息</a></li>\
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
                                <a class="dropdown-button" href="#!" data-activates="dropdown1" name="university"> \
                                    南京大学 <i class="material-icons right">arrow_drop_down</i> \
                                </a></li> \
                            <li> \
                                <a class="dropdown-button" href="#!" data-activates="dropdown2"> \
                                    王宁 <i class="material-icons right">arrow_drop_down</i> \
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

function write_footer(){
    var txt='<footer class="page-footer teal lighten-3"> \
    <div class="container"> \
            <div class="row"> \
                <div class="col l6 s12"> \
                    <h5 class="white-text">uniNOTE 优记</h5> \
                    <p class="grey-text text-lighten-4">面向全国高校的课程分类式文库。</p> \
                </div> \
                <div class="col l4 offset-l2 s12"> \
                    <h5 class="white-text">Links</h5> \
                    <ul> \
                         <li><a class="grey-text text-lighten-3" href="#!">Link 1</a> \
                        </li> \
                        <li><a class="grey-text text-lighten-3" href="#!">Link 2</a> \
                        </li> \
                    </ul> \
                </div> \
            </div> \
        </div> \
        <div class="footer-copyright"> \
            <div class="container"> \
                © 2015 uniNOTE Group,software institute, NJU <a \
                    class="grey-text text-lighten-4 right" href="#!">Links</a> \
            </div> \
        </div> \
            </footer>';
    $("#footer").html(txt);
}