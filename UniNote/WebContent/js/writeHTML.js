 collapsible_initialization(){
    $('.collapsible').collapsible();
 }


 function write_document_list(file_elements){
	var txt=" ";
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
    var txt=""

}

function write_edit_user_info(){  
    var nickname=$("#nickname").html();
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
                    <input id="nickname" type="hidden" name="nickname" value=""> \
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
                <div class="col s8 table-container"> \
                    <div class="row-container"> \
                        <div class="input-field cell">  \
                            <input id="flag" type="hidden" name="flag" value="userinfo"> \
                            <input type="submit" value=" 保存 " onclick="verify()" class="btn btn-primary">  \
                        </div>  \
                        <div class="input-field cell"> \
            	            <font color="red" size="2"><span id="result" >hehe</span></font> \
                        </div> \
                    </div> \
                </div>';
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

/*
function write_course_list(departments){
    var txt="<ul class='collapsible' data-collapsible='expandable'>";
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
                "'>"
                +courses[j].firstChild.nodeValue+"</a>";
            }

            txt=txt +" </div></div></li>";       
               

    } //end for
    txt=txt+"</ul>";
    return txt;
}
*/

function write_course_list(departments){

    var txt='<ul class="collapsible" data-collapsible="accordion">  \
    <li> \
      <div class="collapsible-header" onclick="collapsible_initialization()"><i class="material-icons">filter_drama</i>First</div> \
      <div class="collapsible-body"><p>Lorem ipsum dolor sit amet.</p></div> \
    </li> \
    <li> \
      <div class="collapsible-header" onclick="collapsible_initialization()"><i class="material-icons">place</i>Second</div> \
      <div class="collapsible-body"><p>Lorem ipsum dolor sit amet.</p></div> \
    </li> \
    <li> \
      <div class="collapsible-header" onclick="collapsible_initialization()"><i class="material-icons">whatshot</i>Third</div> \
      <div class="collapsible-body"><p>Lorem ipsum dolor sit amet.</p></div> \
    </li> \
  </ul>';

    return txt;
}