var citySelector = {};

citySelector.pc = new Array();
citySelector.pc[0] = new Array("京", "清华大学|北京大学|中国人民大学|北京师范大学|北京理工大学|中国农业大学|中央民族大学|北京协和医学院|北京大学医学部|北京航空航天大学");
citySelector.pc[1] = new Array("沪", "复旦大学|同济大学|上海大学|东华大学|上海交通大学|华东师范大学|华东理工大学|上海财经大学|上海外国语大学|复旦大学上海医学院|上海交通大学医学院");
citySelector.pc[2] = new Array("津", "南开大学|天津大学|河北工业大学|天津医科大学");
citySelector.pc[3] = new Array("渝", "重庆大学|西南大学|西南大学荣昌校区");
citySelector.pc[4] = new Array("冀", "华东电力大学(保定)");
citySelector.pc[5] = new Array("晋", "太原理工大学");
citySelector.pc[6] = new Array("蒙", "内蒙古大学");
citySelector.pc[7] = new Array("辽", "大连理工大学|东北大学|辽宁大学|大连海事大学|大连理工大学盘锦校区");
citySelector.pc[8] = new Array("吉", "吉林大学|东北师范大学|延边大学");
citySelector.pc[9] = new Array("黑", "哈尔滨工业大学|哈尔滨工程大学|东北林业大学|东北农业大学");
citySelector.pc[10] = new Array("苏", "南京大学|东南大学|苏州大学|江南大学|河海大学|南京师范大学|南京农业大学|中国矿业大学|南京理工大学|中国药科大学|南京航空航天大学");
citySelector.pc[11] = new Array("浙", "浙江大学");
citySelector.pc[12] = new Array("皖", "中国科学技术大学|合肥工业大学|安徽大学");
citySelector.pc[13] = new Array("闽", "厦门大学");
citySelector.pc[14] = new Array("赣", "南昌大学");
citySelector.pc[15] = new Array("鲁", "山东大学|山东大学(威海)|中国海洋大学|中国石油大学(华东)|哈尔滨工业大学(威海)");
citySelector.pc[16] = new Array("豫", "郑州大学");
citySelector.pc[17] = new Array("鄂", "武汉大学|华中科技大学|武汉理工大学|华中师范大学|华中农业大学|中南财经政法大学|中国地质大学(武汉)");
citySelector.pc[18] = new Array("湘", "中南大学|湖南大学|湖南师范大学");
citySelector.pc[19] = new Array("粤", "中山大学|华南理工大学|暨南大学|华南师范大学");
citySelector.pc[20] = new Array("桂", "广西大学");
citySelector.pc[21] = new Array("琼", "海南大学");
citySelector.pc[22] = new Array("川", "四川大学|西南交通大学|西南财经大学|四川农业大学|电子科技大学");
citySelector.pc[23] = new Array("贵", "贵州大学");
citySelector.pc[24] = new Array("滇", "云南大学");
citySelector.pc[25] = new Array("藏", "西藏大学");
citySelector.pc[26] = new Array("陕", "西北大学|长安大学|西安交通大学|西北工业大学|陕西师范大学|西安电子科技大学|西北农林科技大学");
citySelector.pc[27] = new Array("甘", "兰州大学");
citySelector.pc[28] = new Array("宁", "宁夏大学");
citySelector.pc[29] = new Array("青", "青海大学");
citySelector.pc[30] = new Array("新", "石河子大学|新疆大学");
citySelector.pc[31] = new Array("港", "香港大学|香港城市大学|香港科技大学");
citySelector.pc[32] = new Array("澳", "澳门大学|澳门理工学院|澳门科技大学");
citySelector.pc[33] = new Array("台", "台湾大学|成功大学|台湾交通大学|台湾清华大学|台湾政治大学");

citySelector.hotCity = ["南京大学", "北京大学", "清华大学", "复旦大学"];

citySelector.hotCityhtmls = "";
citySelector.provHtmls = "";
for (var j = 0; j < citySelector.pc.length; j++) {
    citySelector.provHtmls += "<li data-xuhao='" + j + "'><span class='provinceName'>" + citySelector.pc[j][0] + "</span></li>";
}
for (var i = 0; i < citySelector.hotCity.length; i++) {
    citySelector.hotCityhtmls += "<li class='js_cityName'>" + citySelector.hotCity[i] + "</li>";
}

citySelector.template = '<div class="city-box" id="js_cityBox"><div class="prov-city" id="js_provCity"><p>热门学校</p><ul>' + citySelector.hotCityhtmls + '</ul></div><div class="provence"><div>选择省份</div><div><ul id="js_provList">' + citySelector.provHtmls + '</ul></div></div></div>';

citySelector.cityInit = function (input) {

    $("#" + input).click(function () {

        $("#js_cityBox").remove();
        $("body").append(citySelector.template);

        var _top = $(this).offset().top + 40,
            _left = $(this).offset().left,
            _width = $(window).width();
        if (_width - _left < 450) {
            $("#js_cityBox").css({ "top": _top + "px", "right": "0px" }).addClass("rightSelector");
        }
        else {
            $("#js_cityBox").css({ "top": _top + "px", "left": _left + "px" });
        }

        var label = "false";
        $("#js_provList").on("click", ".provinceName", function () {
            function createUl(_this){
                _this.css({ "background": "#fff", "border-color": "#d8d8d8", "border-bottom-color": "#fff", "position": "absolute", "top": "0", "left": "0", "z-index": "999999" });
                var xuhao = _this.parent("li").attr("data-xuhao"),
                    cityArr = citySelector.pc[xuhao][1].split("|"),
                    cityHtmls = "<ul id='js_provCitys'>";
                for (var i = 0; i < cityArr.length; i++) {
                    cityHtmls += "<li class='js_cityName'>" + cityArr[i] + "</li>";
                }
                cityHtmls += "</ul>";
                $("#js_provCitys").remove();
                _this.parent("li").append(cityHtmls);
            }

            if (label == "false") {
                label = "true";
                $(this).attr("now-item", "true");
                createUl($(this));
            }
            else {
                if ($(this).attr("now-item") == "" || $(this).attr("now-item") == "false" || $(this).attr("now-item") == undefined) {
                    $(this).parents("#js_provList").find("span").attr("now-item", "false");
                    $(this).attr("now-item", "true");
                    $("#js_provList span").css({ "background": "", "border-color": "", "border-bottom-color": "", "position": "", "top": "", "left": "", "z-index": "" });
                    $("#js_provCitys").remove();
                    createUl($(this));
                }
                else {
                    label = "false";
                    $(this).css({ "background": "", "border-color": "", "border-bottom-color": "", "position": "", "top": "", "left": "", "z-index": "" });
                    $("#js_provCitys").remove();
                }
            }

        });

        var _input = input;
        $("#js_cityBox").on("click", ".js_cityName", function (e) {
            e.stopPropagation();
            $("#" + _input).val($(this).html());
            $("#js_cityBox").remove();
        });
    });
}