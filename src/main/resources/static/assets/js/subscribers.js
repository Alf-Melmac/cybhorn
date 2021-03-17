$(function () {
    "use strict";
    const $subscribers = $('#subscribers');

    $subscribers.DataTable({
        ajax: {
            url: '/subscribers/list',
            dataSrc: ""
        },
        columns: [
            {
                data: 'imsi'
            },
            {
                data: 'terminal.name'
            },
            {
                data: 'subscription.name'
            },
            {
                data: 'secondsCalled'
            },
            {
                data: 'dataUsed'
            }
        ]
    });
});