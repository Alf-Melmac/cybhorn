$(function () {
    "use strict";
    const $card = $('.card');

    $card.hover(
        function () {
            $(this).addClass('shadow-lg').css('cursor', 'pointer');
        }, function () {
            $(this).removeClass('shadow-lg');
        }
    );

    $card.on('click', function () {
        window.location.href = $(this).attr('href');
    });

    let rainbowClick = 0;
    $('#nav-dragon').on('click', function () {
        if (rainbowClick === 7) {
            $('.card').addClass('rainbow rainbow_text_animated');
            rainbowClick = 0;
            return;
        }
        $card.removeClass('rainbow rainbow_text_animated');
        rainbowClick++;
    });
});