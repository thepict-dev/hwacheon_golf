$(document).ready(function(){
    function titleText() {
        $('.fc-center>h2').each(function () {
            var titleH2 = $(this).text(),
                titleSlice = titleH2.slice(0,4),
                titleSlice2 = titleH2.slice(6,8),
                titleSlice3 = titleSlice2.replace(/[^0-9]/g," ");

            var changeH2 = (titleSlice + '<span class="month">' +'.'+ titleSlice3 + '</span>');

            $(this).text("").append(changeH2);
        });

        $('.fc-day-number').each(function () {
            var text = $(this).text();
            $(this).text(text.replace('일',''));
        });
    }

    function chkEvent() {
        $('.fullcalendar.playground .fc-event-container').each(function () {
            var addPersonnel = '<span class="person"><em class="num">10</em>/<em class="total-num">12</em></span>';
            var SliceTit = $(this).find('.fc-title').text().slice(0,2);
            var Slice = $(this).find('.fc-title').text().slice(3);
            var Split = Slice.split('/');
            for(var t in Split){
                addPersonnel = '<span class="person"><em class="num">'+Split[0]+'</em>/<em class="total-num">'+Split[1]+'</em></span>';
            }
            $(this).find('.fc-title').text('').append(SliceTit);
            $(this).find('.fc-content').append(addPersonnel);
        });

        //완료/가능
        $('.fullcalendar.cal .fc-event-container').each(function () {
            var possible = $(this).find('.fc-title').text();
            if(possible == '가능'){
                $(this).find('.fc-title').addClass('possible');
            }
        });
    }
    function numChk(){
        $('.fullcalendar.playground .fc-event-container').each(function () {
            var numTxt = $(this).find('.person> .num').text(),
                totalTxt = $(this).find('.person> .total-num').text();

            if(numTxt == totalTxt){
                $(this).find('.person').addClass('over');
            }
        });
    }

    $('#reservation .fc-button-primary, #list .fc-button-primary').on('click',function () {
        titleText();
        chkEvent();
        numChk();
    });

    titleText();
    chkEvent();
    numChk();

    $(window).innerWidth()
});