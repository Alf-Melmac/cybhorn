$(function () {
    "use strict";

    const $wizard = $('#wizard');
    $wizard.on('submit', function (event) {
        event.preventDefault();

        let userInput = {};
        $('input,textarea,select').each(function (index, element) {
            const $el = $(element);
            const key = $el.attr('id');

            if (!key || key === '') {
                console.error('empty key');
                console.log($el);
                return;
            }

            const value = $el.val();
            if (value && value !== '') {
                userInput[key] = value;
            }
        });

        $.ajax($wizard.data('saveurl'), {
            method: $wizard.data('edit') ? 'PUT' : 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(userInput)
        })
            .done(() => window.location.href = $wizard.data('overviewurl'))
            .fail(response => alert(JSON.stringify(response) + '\nAction failed. Try again later\n' + JSON.stringify(event)));
    });
});
