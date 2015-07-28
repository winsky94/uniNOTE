
 function write_document_list(file_elements){
	var txt=" <script> \
    $(document).ready(function(){ \
        $('.marked').css('color','#ffc107');  \
        $('.not-marked').css('color','#777');  \
        $('.secondary-content').click(function(){\
            var title=$(this).attr('title');\
            if(title=='not-selected'){\
                $(this).addClass('marked');\
                $(this).css('color','#ffc107');\
                $(this).attr('title','selected'); \
            }else{ \
                $(this).removeClass('marked'); \
                $(this).css('color','#777');  \
                $(this).attr('title','not-selected'); \
            } \
        });\
    }); \
    </script>";
    for(i=0;i<file_elements.length;i++){

        //开头
        txt=txt + "<li class='collection-item avatar'>";

        //根据文件格式选图片       
        try{
            var extension=file_elements[i].getElementsByTagName("type")[0].firstChild.nodeValue; 
            txt=txt+"<i class='circle'><img src='images/document-icons/"+extension+".png'></i>";
        }catch(e){
            txt=txt+"<i class='circle teal'><font size='2'><span>other</span></font></i>";
        }
      

        var id=file_elements[i].getElementsByTagName("ID")[0].firstChild.nodeValue;
        var filename=file_elements[i].getElementsByTagName("filename")[0].firstChild.nodeValue;
        var name=file_elements[i].getElementsByTagName("name")[0].firstChild.nodeValue;
        var uploader=file_elements[i].getElementsByTagName("uploader")[0].firstChild.nodeValue;  
        var profile=file_elements[i].getElementsByTagName("profile")[0].firstChild.nodeValue;
        var marked=file_elements[i].getElementsByTagName("bookmark")[0].firstChild.nodeValue;
        var downloadNum=file_elements[i].getElementsByTagName("downloadNum")[0].firstChild.nodeValue;
        var zanNum=file_elements[i].getElementsByTagName("praise")[0].firstChild.nodeValue;
        var caiNum=file_elements[i].getElementsByTagName("criticism")[0].firstChild.nodeValue;
        var isOK=file_elements[i].getElementsByTagName("isOK")[0].firstChild.nodeValue;
        txt=txt+"<a class='title' href='" +"documnetView.jsp?ID="+id+"&filename="+filename+"&profile="+profile+"&name="+name+"&zanNum="+zanNum+"&caiNum="+caiNum+"&isOK="+isOK+"'>"+filename+'</a><p>上传者：'+uploader+'<br>下载量：'+downloadNum+'</p>';

        //结尾
        if(marked=='Y'){
            txt=txt+"<a href=\"#!\" class='secondary-content marked' title='selected' onclick='clickStar("+id+")'><i class='material-icons'>grade</i></a>";
        }else{
            txt=txt+"<a href=\"#!\" class='secondary-content not-marked' title='not-selected' onclick='clickStar("+id+")'><i class='material-icons'>grade</i></a>";
        }
        
        txt=txt +"</li>";
                
    } //end for
    return txt;
}
 function clickStar(id){
    var username=$('#user-name').attr('title');
    var xmlhttp_collect=getXmlHttp();
    if(xmlhttp_collect!=null){
        xmlhttp_collect.open("GET","/UniNote/CollectServlet?nickname="+username+"&documentID="+id,true);
        xmlhttp_collect.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xmlhttp_collect.send();
    }else{
        alert("Your browser does not support XMLHttpRequest.");
    }
}

function write_user_upload_files(file_elements){
    var txt=" <ul class='collection' id='filelist'>";
    for(i=0;i<file_elements.length;i++){

        //开头
        txt=txt + "<li class='collection-item avatar'>";

        //根据文件格式选图片       
        try{
            var extension=file_elements[i].getElementsByTagName("type")[0].firstChild.nodeValue; 
            txt=txt+"<i class='circle'><img src='images/document-icons/"+extension+".png'></i>";
        }catch(e){
            txt=txt+"<i class='circle teal'><font size='2'><span>other</span></font></i>";
        }
      

        var id=file_elements[i].getElementsByTagName("ID")[0].firstChild.nodeValue;
        var filename=file_elements[i].getElementsByTagName("filename")[0].firstChild.nodeValue;
        var origin_filename=file_elements[i].getElementsByTagName("name")[0].firstChild.nodeValue;
        var uploader=file_elements[i].getElementsByTagName("uploader")[0].firstChild.nodeValue; 
        var profile=file_elements[i].getElementsByTagName("profile")[0].firstChild.nodeValue;
        var downloadNum=file_elements[i].getElementsByTagName("downloadNum")[0].firstChild.nodeValue;
        var tag=file_elements[i].getElementsByTagName("tag")[0].firstChild.nodeValue;

        txt=txt+"<a class='title' href='" +"manage_file.jsp?ID="+id+"&filename="+filename+"&profile="+profile+"&origin_filename="+origin_filename+"&tag="+tag+"'>"+filename+'</a><p>上传者：'+uploader+'<br>下载量'+downloadNum+'</p>';

        //结尾
        txt=txt +"</li>";
                
    } //end for
    txt=txt+"</ul>";
    return txt;
}

function write_user_collect_files(file_elements){
    var txt=" <ul class='collection' id='filelist'> \
    <script> \
    $(document).ready(function(){ \
        $('.marked').css('color','#ffc107');  \
        $('.not-marked').css('color','#777');  \
        $('.secondary-content').click(function(){  \
            var title=$(this).attr('title'); \
            if(title=='not-selected'){ \
                $(this).addClass('marked');  \
                $(this).css('color','#ffc107');  \
                $(this).attr('title','selected'); \
            }else{ \
                $(this).removeClass('marked'); \
                $(this).css('color','#777');  \
                $(this).attr('title','not-selected'); \
            }   \
        }); \
    }); \
    </script>";
    for(i=0;i<file_elements.length;i++){

        //开头
        txt=txt + "<li class='collection-item avatar'>";

        //根据文件格式选图片       
        try{
            var extension=file_elements[i].getElementsByTagName("type")[0].firstChild.nodeValue; 
            txt=txt+"<i class='circle'><img src='images/document-icons/"+extension+".png'></i>";
        }catch(e){
            txt=txt+"<i class='circle teal'><font size='2'><span>other</span></font></i>";
        }
      

        var id=file_elements[i].getElementsByTagName("ID")[0].firstChild.nodeValue;
        var filename=file_elements[i].getElementsByTagName("filename")[0].firstChild.nodeValue;
        var name=file_elements[i].getElementsByTagName("name")[0].firstChild.nodeValue;
        var uploader=file_elements[i].getElementsByTagName("uploader")[0].firstChild.nodeValue; 
        var profile=file_elements[i].getElementsByTagName("profile")[0].firstChild.nodeValue;
        var downloadNum=file_elements[i].getElementsByTagName("downloadNum")[0].firstChild.nodeValue;
        var tag=file_elements[i].getElementsByTagName("tag")[0].firstChild.nodeValue;
        var zanNum=file_elements[i].getElementsByTagName("praise")[0].firstChild.nodeValue;
        var caiNum=file_elements[i].getElementsByTagName("criticism")[0].firstChild.nodeValue;
        var isOK=file_elements[i].getElementsByTagName("isOK")[0].firstChild.nodeValue;
        txt=txt+"<a class='title' href='" +"documnetView.jsp?ID="+id+"&filename="+filename+"&profile="+profile+"&name="+name+"&zanNum="+zanNum+"&caiNum="+caiNum+"&isOK="+isOK+"'>"+filename+'</a><p>上传者：'+uploader+'<br>下载量：'+downloadNum+'</p>';

        //结尾
        txt=txt+"<a href=\"#!\" class='secondary-content marked' title='selected' onclick='clickStar("+id+")'><i class='material-icons'>grade</i></a>";
        txt=txt +"</li>";
                
    } //end for
    txt=txt+"</ul>";
    return txt;
}

function write_user_download_files(file_elements){
    var txt=" <ul class='collection' id='filelist'>";
    for(i=0;i<file_elements.length;i++){

        //开头
        txt=txt + "<li class='collection-item avatar'>";

        //根据文件格式选图片       
        try{
            var extension=file_elements[i].getElementsByTagName("type")[0].firstChild.nodeValue; 
            txt=txt+"<i class='circle'><img src='images/document-icons/"+extension+".png'></i>";
        }catch(e){
            txt=txt+"<i class='circle teal'><font size='2'><span>other</span></font></i>";
        }
      

        var id=file_elements[i].getElementsByTagName("ID")[0].firstChild.nodeValue;
        var filename=file_elements[i].getElementsByTagName("filename")[0].firstChild.nodeValue;
        var name=file_elements[i].getElementsByTagName("name")[0].firstChild.nodeValue;
        var uploader=file_elements[i].getElementsByTagName("uploader")[0].firstChild.nodeValue; 
        var profile=file_elements[i].getElementsByTagName("profile")[0].firstChild.nodeValue;
        var downloadNum=file_elements[i].getElementsByTagName("downloadNum")[0].firstChild.nodeValue;
        var tag=file_elements[i].getElementsByTagName("tag")[0].firstChild.nodeValue;
        var zanNum=file_elements[i].getElementsByTagName("praise")[0].firstChild.nodeValue;
        var caiNum=file_elements[i].getElementsByTagName("criticism")[0].firstChild.nodeValue;
        var isOK=file_elements[i].getElementsByTagName("isOK")[0].firstChild.nodeValue;
        txt=txt+"<a class='title' href='" +"documnetView.jsp?ID="+id+"&filename="+filename+"&profile="+profile+"&name="+name+"&zanNum="+zanNum+"&caiNum="+caiNum+"&isOK="+isOK+"'>"+filename+'</a><p>上传者：'+uploader+'<br>下载量：'+downloadNum+'</p>';

        //结尾
        txt=txt +"</li>";
                
    } //end for
    txt=txt+"</ul>";
    return txt;
}

function write_user_info(user_info){
    var nickname=$("#user-name").attr("title");
    $("#username").html(nickname);
    var email=user_info.getElementsByTagName("email")[0].firstChild.nodeValue;
    var school=user_info.getElementsByTagName("school")[0].firstChild.nodeValue;
    var phonenumber=user_info.getElementsByTagName("phoneNumber")[0].firstChild.nodeValue;
    var point=user_info.getElementsByTagName("point")[0].firstChild.nodeValue;
    var uploadNum=user_info.getElementsByTagName("uploadNum")[0].firstChild.nodeValue;
    var downloadNum=user_info.getElementsByTagName("downloadNum")[0].firstChild.nodeValue;

    //<div class="card teal lighten-5"> \
    //   <div class="card-content" id="user-info-container"> \

    var txt='<div class="card teal lighten-3"> \
                <div class="card-content" id="user-info-container">  \
                    <div class="table-row"> \
                        <div class="property">Nickname</div> \
                        <div class="value" id="nickname">'+nickname+'</div> \
                    </div> \
                    <div class="table-row"> \
                        <div class="property">Email</div> \
                        <div class="value" id="email">'+email+'</div> \
                    </div> \
                    <div class="table-row"> \
                        <div class="property">School</div> \
                        <div class="value" id="school">'+school+'</div> \
                    </div> \
                    <div class="table-row"> \
                        <div class="property">Phonenumber</div> \
                        <div class="value" id="phonenumber">'+phonenumber+'</div> \
                    </div> \
                    <div class="table-row"> \
                        <div class="property">积分</div> \
                        <div class="value">'+point+'</div> \
                    </div> \
                    <div class="table-row"> \
                        <div class="property">上传量</div> \
                        <div class="value">'+uploadNum+'</div> \
                    </div> \
                    <div class="table-row"> \
                        <div class="property">下载量</div> \
                        <div class="value">'+downloadNum+'</div> \
                    </div> \
                    <div class="card-action"> \
                        <a href="#" onclick="write_edit_user_info()">编辑</a> \
                        <a href="#" onclick="write_changepw()">修改密码</a> \
                    </div> \
                </div> \
            </div>';
    return txt;

}

function write_edit_user_info(){  
    var nickname=$("#nickname").html();
    var email=$("#email").html();
    var school=$("#school").html();
    var phonenumber=$("#phonenumber").html();
    var txt ='<style type="text/css"> \
        .table-container{ \
            display: table; \
            border-spacing: 10px; \
        } \
        .row-container{ \
            display: table-row;  \
        } \
        .cell{ \
            display: table-cell; \
        } \
    </style> \
    <div class="input-field col s12">  \
                    <input id="nickname" type="hidden" name="nickname" value="'+nickname+'"> \
                    <input id="email" type="email" name="email" class="validate" value="'+email+'">  \
                    <label for="email" class="active">Email</label>  \
                  </div>  \
                <div class="input-field col s12">  \
                 <input id="school" type="text" name="school" class="validate" value="'+school+'">  \
                 <label for="school" class="active">School</label>  \
                </div>  \
                <div class="input-field col s12">  \
                  <input id="phonenumber" type="text" name="phonenumber" class="validate" value="'+phonenumber+'">  \
                  <label for="phonenumber" class="active">PhoneNumber</label>  \
                </div>  \
                <div class="col s8 table-container"> \
                    <div class="row-container"> \
                        <div class="input-field cell">  \
                            <input id="flag" type="hidden" name="flag" value="userinfo"> \
                            <input type="submit" value="保存" onclick="verify_change_user_info()" class="btn btn-primary">  \
                        </div>  \
                        <a class="btn btn-primary" onclick="window.location.reload(true)">取消</a> \
                        <div class="input-field cell"> \
            	            <font color="red" size="2"><span id="result" ></span></font> \
                        </div> \
                    </div> \
                </div>';
    $("#user-info-container").html(txt);
}

function write_changepw(){
    var nickname=$("#nickname").html();
    var txt='<style type="text/css"> \
        .table-container{ \
            display: table; \
            border-spacing: 10px; \
        } \
        .row-container{ \
            display: table-row;  \
        } \
        .cell{ \
            display: table-cell; \
        } \
    </style> \
                <div class="input-field col s12"> \
                    <input disabled type="text" id="nickname" name="nickname" class="validate" value="'+nickname+'"> \
                    <label for="last_name" class="active">Nickname</label> \
                </div> \
                <div class="input-field col s12"> \
                    <input id="password" type="password" name="password" class="validate"> \
                    <label for="password">当前密码:</label> \
                </div> \
                <div class="input-field col s12"> \
                    <input id="newpassword" type="password" name="newpassword" class="validate"> \
                    <label for="newpassword">修改密码:</label> \
                </div> \
                <div class="input-field col s12"> \
                    <input id="confirmpassword" type="password" name="confirmpassword" class="validate"> \
                    <label for="confirmpassword">确认密码:</label> \
                </div> \
                <div class="col s12 table-container"> \
                    <div class="row row-container"> \
                        <div class="input-field cell"> \
                            <input type="hidden" id="flag" name="flag" value="password"> \
                            <input type="submit" value="提交" onclick="verify_changepw()" class="btn btn-primary"> \
                        </div> \
                        <a class="btn btn-primary" onclick="window.location.reload(true)">取消</a> \
                        <div class="input-field cell">  \
                            <font color="red" size="2"><span id="result" >23</span></font>  \
                        </div> \
                    </div> \
            </div>' ;
    $("#user-info-container").html(txt);
}

function write_footer(){
    var txt='<footer class="page-footer teal lighten-2"> \
    <div class="container"> \
            <div class="row"> \
                <div class="col l6 s12"> \
                    <h5 class="white-text">uniNOTE 优记</h5> \
                    <p class="grey-text text-lighten-4">面向全国高校的课程分类式文库。</p> \
                </div> \
                <!--<div class="col l4 offset-l2 s12"> \
                    <h5 class="white-text">Links</h5> \
                    <ul> \
                         <li><a class="grey-text text-lighten-3" href="#!">Link 1</a> \
                        </li> \
                        <li><a class="grey-text text-lighten-3" href="#!">Link 2</a> \
                        </li> \
                    </ul> \
                </div>--> \
            </div> \
        </div> \
        <div class="footer-copyright"> \
            <div class="container"> \
                © 2015 uniNOTE Group,software institute, NJU <a \
                    class="grey-text text-lighten-4 right" href="http://www.hao123.com">联系我们</a> \
            </div> \
        </div> \
            </footer>';
    $("#footer").html(txt);
}


function write_course_list(departments,isUploading){
    var txt="<script>\
    $(document).ready(function(){\
    $('.collapsible').collapsible({\
      accordion : false \
    });\
    });\
    $('a.collection-item').click(function(){\
        $('a.collection-item').css('background-color','#fff');\
        $(this).css('background-color','#e0f2f1');\
        });\
        </script>\
    <ul class='collapsible' data-collapsible='expandable'>";
    for(i=0;i<departments.length;i++){
        //开头
        txt=txt+"<li>";
        txt=txt+"<div class='collapsible-header'> <i class='material-icons'>list</i>"+
                           departments[i].getAttribute("院系")+
                "</div>\
                 <div class='collapsible-body'> \
                    <div class='collection' title='"+departments[i].getAttribute("院系")+"'>";
            var courses=departments[i].getElementsByTagName("course");
            if(isUploading==0){
            	txt=txt+"<a href='#!' class='collection-item' title='考研资料' onclick=\"get_page_num(\'"+departments[i].getAttribute('院系')+"\',\'"+'考研资料'+"\')\">"
            	 +'考研资料'+"</a>";
            }
            for(j=0;j<courses.length;j++){
                txt=txt+"<a href='#!' class='collection-item' title='"
                +courses[j].firstChild.nodeValue+
                "' onclick=\"get_page_num(\'"+departments[i].getAttribute('院系')+"\',\'"+courses[j].firstChild.nodeValue+"\')\">"
                +courses[j].firstChild.nodeValue+"</a>";
            }

            txt=txt +" </div></div></li>";       
               

    } //end for
    txt=txt+"</ul>";
    return txt;
}
function write_upload_course_list(departments){
    var txt="<script>\
    $(document).ready(function(){\
    $('.collapsible').collapsible({\
      accordion : false \
    });\
    });\
    $('a.collection-item').click(function(){\
        $('a.collection-item').css('background-color','#fff');\
        $(this).css('background-color','#e0f2f1');\
        });\
        </script>\
    <ul class='collapsible' data-collapsible='expandable'>";
    for(i=0;i<departments.length;i++){
        //开头
        txt=txt+"<li>";
        txt=txt+"<div class='collapsible-header'> <i class='material-icons'>list</i>"+
                           departments[i].getAttribute("院系")+
                "</div>\
                 <div class='collapsible-body'> \
                    <div class='collection' title='"+departments[i].getAttribute("院系")+"'>";
            var courses=departments[i].getElementsByTagName("course");
            for(j=0;j<courses.length;j++){
                txt=txt+"<a href='#!' class='collection-item' title='"
                +courses[j].firstChild.nodeValue+"' onclick=\"set_course(this.parentNode.title,this.title)\">"+courses[j].firstChild.nodeValue+"</a>";
            }
            txt=txt +" </div></div></li>";       
    } //end for
    txt=txt+"</ul>";
    return txt;
}

function write_chat_list(chats){
    var txt="";
    for(i=0;i<chats.length;i++){
        var username=chats[i].getElementsByTagName("name")[0].firstChild.nodeValue;
        var date=chats[i].getElementsByTagName("date")[0].firstChild.nodeValue;
        var content=chats[i].getElementsByTagName("content")[0].firstChild.nodeValue;
        txt=txt+"<article class='comment'>\
        <a class='comment-img' href='#non'>\
        <img src=\"images/portrait.jpg\" alt=\"\" width=\"50\" height=\"50\"></a>\
        <div class=\"comment-body\"><div class=\"text\"><p>";
        txt=txt+content+"</p></div><p class=\"attribution\">by&nbsp;<a href=\"#non\">";
        txt=txt+username+"</a>&nbsp;at&nbsp;"+date+"</p></div></article>";
    }
    return txt;
}

function write_pagination(start,end,active_num,page_num){
    var txt;
    if(start==1 && active_num==1){
        txt='<li class="disabled"> \
                <a href="#!">  \
                    <i class="material-icons">chevron_left</i> \
                </a> \
            </li>';
    }else{
        txt='<li class="waves-effect"> \
                <a href="#!" onclick="page_up(active_num)">  \
                    <i class="material-icons">chevron_left</i> \
                </a> \
            </li>';
    }
    

    for( i=start ; i<=end ; i++){
        if(i==active_num){
            txt=txt+'<li class="page-selector active" title="'+i+'"> \
                        <a href="#!">'+i+'</a> \
                    </li>';
        }else{
            txt=txt+'<li class="page-selector waves-effect" title="'+i+'" onclick="turn_to_page(this.title)"> \
                        <a href="#!">'+i+'</a> \
                    </li>';
        }
    }
     
    if(end==page_num && active_num==page_num){
        txt=txt+'<li class="disabled"> \
                <a href="#!"> \
                    <i class="material-icons">chevron_right</i> \
                </a> \
             </li>';  
    }else{
        txt=txt+'<li class="waves-effect"> \
                <a href="#!" onclick="page_down(active_num)"> \
                    <i class="material-icons">chevron_right</i> \
                </a> \
             </li>';  
    }

    $(".pagination").html(txt);
}