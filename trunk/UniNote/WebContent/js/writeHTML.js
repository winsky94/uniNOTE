
function collapsible_open(message){
    alert(message);
    alert($(this).next().attr('title'));
    $(this).parent().addClass('active');
    $(this).addClass('active');
    $(this).next().css('display','block');
}


 function write_document_list(file_elements){
	var txt=" <script> \
     $(document).ready(function(){ \
    $('.secondary-content').css('color','#777777'); \
    $('.secondary-content').click(function(){  \
        var title=$(this).attr('title'); \
        if(title=='not-selected'){ \
            $(this).css('color','#26A69A');  \
            $(this).attr('title','selected'); \
        }else{ \
            $(this).css('color','#777777');  \
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
       // var uploader=file_elements[i].getElementsByTagName("uploader")[0].firstChild.nodeValue;  // undefined
        var profile=file_elements[i].getElementsByTagName("profile")[0].firstChild.nodeValue;

        txt=txt+"<a class='title' href='" +"documnetView.jsp?ID="+id+"&filename="+filename+"&profile="+profile+"&name="+name+"'>"+filename+'</a><p>First Line<br>Second Line</p>';

        //结尾
        txt=txt+"<a href='#!' class='secondary-content' title='not-selected'><i class='material-icons'>grade</i></a>";
        txt=txt +"</li>";
                
    } //end for
    return txt;
}




function write_user_info(user_info){
    var txt='<div class="table-row"> \
                <div class="property">Nickname</div> \
                <div class="value" id="nickname">王宁</div> \
            </div> \
            <div class="table-row"> \
                <div class="property">Email</div> \
                <div class="value" id="email">wn13@software.nju.edu.cn</div> \
            </div> \
            <div class="table-row"> \
                <div class="property">School</div> \
                <div class="value" id="school">南京大学</div> \
            </div> \
            <div class="table-row"> \
                <div class="property">Phonenumber</div> \
                <div class="value" id="phonenumber">6465132135494</div> \
            </div> \
            <div class="table-row"> \
                <div class="property">积分</div> \
                <div class="value">10</div> \
            </div> \
            <div class="table-row"> \
                <div class="property">上传量</div> \
                <div class="value">10</div> \
            </div> \
            <div class="table-row"> \
                <div class="property">下载量</div> \
                <div class="value">10</div> \
            </div> \
            <div class="card-action"> \
                <a href="#" onclick="write_edit_user_info()">编辑</a> \
            </div>';
    $("#user-info-container").html(txt);

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
                            <input type="submit" value=" 保存 " onclick="verify()" class="btn btn-primary">  \
                        </div>  \
                        <a class="btn btn-primary" onclick="window.location.reload(true)">取消</a> \
                        <div class="input-field cell"> \
            	            <font color="red" size="2"><span id="result" >hehe</span></font> \
                        </div> \
                    </div> \
                </div>';
    $("#user-info-container").html(txt);
}

function write_changepw(){
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
                    <input disabled id="nickname" type="text" name="nickname" class="validate" value="王宁"> \
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
                            <input type="hidden" name="flag" value="password"> \
                            <input type="submit" value=" 提交 " onclick="verify()" class="btn btn-primary"> \
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


function write_course_list(departments){
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
    function get_documents_by_course(department_chosen,course_chosen){\
            var xmlhttp=getXmlHttp();\
            var university=$('#university-info').attr('title');\
            alert(university);\
            if(xmlhttp!=null){\
                xmlhttp.open('GET','/UniNote/DocumentOverViewServlet?school='+encodeURI(encodeURI(university))+'&department='+encodeURI(encodeURI(department_chosen))+\
                    '&course='+encodeURI(encodeURI(course_chosen)),true);\
                xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');\
                xmlhttp.onreadystatechange=function(){onResponse(xmlhttp)};\
                xmlhttp.send();\
            }else{\
                alert('Your browser does not support XMLHttpRequest.');\
            } \
        }\
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
                +courses[j].firstChild.nodeValue+
                "' onclick=\'get_documents_by_course(\'"+departments[i].getAttribute('院系')+"\',\'"+courses[j].firstChild.nodeValue+"\')\'>"
                +courses[j].firstChild.nodeValue+"</a>";
            }

            txt=txt +" </div></div></li>";       
               

    } //end for
    txt=txt+"</ul>";
    return txt;
}
