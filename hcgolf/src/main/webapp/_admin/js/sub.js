$(document).ready(function (){
    //file upload
    var fileTarget=$('.add-file input[type="file"], .add-file-box input[type="file"]');
    fileTarget.on('change',function(){
        if(window.FileReader){
            var filename=$(this)[0].files[0].name;
        } else {
            var filename=$(this).val().split('/').pop().split('\\').pop();
        }

        $(this).siblings('.upload').val(filename);
    });

    //counsoler popup
    var regist = $('.open-pop'),
        counPopup = $('.counselor-popup'),
        popClose = $('.counselor-popup .close-pop'),
        dimm2 = $('.wrapper .dimm');

    regist.on('click',function () {
        counPopup.show();
        dimm2.addClass('active');
        $("body").css('overflow','visible').css('display','fixed');
    });

    var removeActive2 = function(){
        dimm2.removeClass('active');
        counPopup.hide();
        $("body").css('overflow','visible');
    };

    popClose.on('click',function () {
        removeActive2();
    });

    dimm2.on('click',function(){
        removeActive2();
    });

    //input numberOnly
    $("input:text[numberOnly]").on("keyup", function() {
        $(this).val($(this).val().replace(/[^0-9]/g,""));
    });

    //maxlength
    function maxLengthCheck(object){
        if (object.value.length > object.maxLength){
            object.value = object.value.slice(0, object.maxLength);
        }
    }

    //tooltip
    $.fn.animateAuto = function(prop, speed, callback) {
        var elem, width;
        return this.each(function(i, el) {
            el = $(el), elem = el.clone().css({
                "width": "auto"
            }).appendTo("body");
            width = elem.css("width"), elem.remove();

            if (prop === "width") {
                el.animate({
                    "width": width
                }, speed, callback);
            }
            else if (prop === "both") {
                el.animate({
                    "width": width
                }, speed, callback);
            }
        });
    };

    $('.tooltip').on('mouseenter',function() {
        $(this).find('p').stop().animateAuto('both', 800);
        $('.tbl-wrap').css('overflow-x','hidden');
    });
    $('.tooltip').on('mouseleave',function() {
        $(this).find('p').stop().animate({
            'width': 0
        },500);
    });

    //200722 input addComma
    function addCommas(x) {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }

    $(".addCom").on("keyup", function() {
        $(this).val(addCommas($(this).val().replace(/[^0-9]/g,"")));
    });

    //2020.10.13 add-cell
    var $changeEl = $('.tbl-wrap.change-tbl');

    $changeEl.each(function () {
        var $changeTbl = $(this).find('table'),
            $tbodyTr = $changeTbl.find('tbody>tr'),
            cellLength = $tbodyTr.length,
            $addTr = $(this).find('button.add-cell'),
            $addTr2 = $(this).find('button.add-cell2');

        if(cellLength < 2){
            $tbodyTr.find('.del-btn').hide();
        }

        $addTr.on('click',function () {
            var trLength = $changeTbl.find('tr').length,
                num = trLength;

            el = $('<tr>'+
                '<td>'+
                '<input type="text" placeholder="날짜 입력" title="날짜 입력" class="pro-date" id="proDate'+num+'" name="historyDate">'+
                '</td>'+
                '<td>'+
                '<input type="text" placeholder="진행상황 입력" title="진행상황 입력" class="progress" id="progress'+num+'" name="historyContent">'+
                '</td><td><span class="manage-btn del-btn"><button type="button">삭제</button></span></td>'+
                '</tr>');

            $changeTbl.append(el);
            changeId();
        });
        $addTr2.on('click',function () {
            var trLength = $changeTbl.find('tr').length,
                num = trLength;

            /*el = $('<tr>'+
                '<td>'+
                '<select id="sel'+num+'" name="departmentId">'+
                '<option value="선택">선택</option><option value="소속">소속</option><option value="관리">관리</option>'+
                '</select></td>'+
                '<td>'+
                '<input type="number" class="ownership" id="ownership'+num+'" name="ownership">'+
                '</td>'+
                '<td>'+
                '<input type="number" class="issue1" id="issue1-'+num+'" name="result1">'+
                '</td>'+
                '<td>'+
                '<input type="number" class="issue2" id="issue2-'+num+'" name="result2">'+
                '</td>'+
                '<td>'+
                '<input type="number" class="issue3" id="issue3-'+num+'" name="result3">'+
                '</td>'+
                '<td>'+
                '<input type="number" class="issue4" id="issue4-'+num+'" name="result4">'+
                '</td>'+
                '<td>'+
                '<input type="number" class="issue5" id="issue5-'+num+'" name="result5">'+
                '</td><td><span class="manage-btn del-btn"><button type="button">삭제</button></span></td>'+
                '</tr>');*/
            var department = $("#department").html();
            el = $('<tr>'+
                '<td>'+
            	department+
                '</td>'+
                '<td>'+
                '<input type="number" class="ownership" id="ownership'+num+'" name="ownership">'+
                '</td>'+
                '<td>'+
                '<input type="number" class="issue1" id="issue1-'+num+'" name="result1">'+
                '</td>'+
                '<td>'+
                '<input type="number" class="issue2" id="issue2-'+num+'" name="result2">'+
                '</td>'+
                '<td>'+
                '<input type="number" class="issue3" id="issue3-'+num+'" name="result3">'+
                '</td>'+
                '<td>'+
                '<input type="number" class="issue4" id="issue4-'+num+'" name="result4">'+
                '</td>'+
                '<td>'+
                '<input type="number" class="issue5" id="issue5-'+num+'" name="result5">'+
                '</td><td><span class="manage-btn del-btn"><button type="button">삭제</button></span></td>'+
                '</tr>');

            $changeTbl.append(el);
            changeId2();
        });
        changeId();
        changeId2();
        function changeId(){
            var $fields = $changeTbl.find('tbody>tr');
            var count = 1;
            if($fields.length >= 2){
                $fields.find('.del-btn').show();
            } else {
                $fields.find('.del-btn').hide();
            }

            $.each($fields, function () {
                $(this).find('.pro-date').attr('id', 'proDate'+count);
                $(this).find('.progress').attr('id', 'progress'+count);
                count++;
            });
        }
        function changeId2(){
            var $fields = $changeTbl.find('tbody>tr');
            var count = 1;
            if($fields.length >= 2){
                $fields.find('.del-btn').show();
            } else {
                $fields.find('.del-btn').hide();
            }

            $.each($fields, function () {
                $(this).find('select').attr('id', 'sel'+count);
                $(this).find('.ownership').attr('id', 'ownership'+count);
                $(this).find('.issue1').attr('id', 'issue1-'+count);
                $(this).find('.issue2').attr('id', 'issue2-'+count);
                $(this).find('.issue3').attr('id', 'issue3-'+count);
                $(this).find('.issue3').attr('id', 'issue3-'+count);
                $(this).find('.issue4').attr('id', 'issue4-'+count);
                $(this).find('.issue5').attr('id', 'issue5-'+count);
                count++;
            });
        }

        $(document).on('click','.del-btn',function () {
            $(this).closest('tr').remove();

            changeId();
            changeId2();
        });
    });

    //chart-tab
    var chartTab = $('.chart-top ul li');

    chartTab.click(function(e){
        e.preventDefault();
        $(this).addClass('active').siblings().removeClass('active');

        var tabIdx = $(this).index(),
            chartCon = $('.chart-wrap .chart');

        chartCon.eq(tabIdx).show().siblings().hide();

        if(chartCon.css('display')=='none'){
            $('.chart-right').hide();
        } else {
            $('.chart-right').show();
        }
    });

    //datepicker
    var $datepicker2 = $('.js-datepicker.maxnone');
    $datepicker2.datepicker({
        changeYear:true,
        changeMonth:true,
        setDate:'today',
        inline: true,
        showOtherMonths: true,
        dateFormat: 'yy-mm-dd',
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        monthNames : ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        showOn: "both",
        buttonImage: "/_admin/img/ico_calendar.png",
        buttonImageOnly: true,
        buttonText: "날짜 선택",
        minDate: null
    });

    var $datepicker = $('.js-datepicker');
    $datepicker.datepicker({
        changeYear:true,
        changeMonth:true,
        setDate:'today',
        inline: true,
        showOtherMonths: true,
        dateFormat: 'yy-mm-dd',
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        monthNames : ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        showOn: "both",
        buttonImage: "/_admin/img/ico_calendar.png",
        buttonImageOnly: true,
        buttonText: "날짜 선택",
        minDate: null
    });

    //dynamic add datepickerOptions
    var datepickerOptions = {
        changeYear:true,
        changeMonth:true,
        setDate:'today',
        inline: true,
        showOtherMonths: true,
        dateFormat: 'yy-mm-dd',
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        monthNames : ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        showOn: "both",
        buttonImage: "/_admin/img/ico_calendar.png",
        buttonImageOnly: true,
        buttonText: "날짜 선택",
        minDate: null
    };
});
