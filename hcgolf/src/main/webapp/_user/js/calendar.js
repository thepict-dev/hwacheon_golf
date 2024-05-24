$(document).ready(function(){
    function titleText() {
        $('.fc-center>h2, .fc-right>h2').each(function () {
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

    $('#exhibition .fc-button-primary').on('click',function () {
        titleText();
        chkEvent();
    });

    function chkEvent() {
        $('.reservation-cal .fc-event-container').each(function () {
            var thisData = $(this).find('.fc-event').attr('href');
            var thisIndex = $(this).index(),
                $bgTd = $(this).parents('.fc-week').find('.fc-bg td');
            var dayTitle = $(this).find('.fc-title').text();
            var btnData = '<div class="h-full"><button type="button" class="btn-data" onClick="'+thisData+'">'+dayTitle+'</button></div>';

            $(this).find('.fc-event').addClass('screen-out');
            $bgTd.eq(thisIndex).css('position','relative');
            if($bgTd.eq(thisIndex).hasClass('fc-today')){
                $bgTd.find('.h-full.today').remove();
                $bgTd.eq(thisIndex).append(btnData);
            } else {
                $bgTd.eq(thisIndex).append(btnData);
            }
            if($(this).find('.fc-title').text() == '0명'){
                $bgTd.eq(thisIndex).addClass('complete');
            } else {
                $bgTd.eq(thisIndex).addClass('possible');
                $(this).parents('.fc-content-skeleton').find('td').eq(thisIndex).find('.fc-day-number').addClass('white');
            }
        });
    }

    chkEvent();
    titleText();
});
