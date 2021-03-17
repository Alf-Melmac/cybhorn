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
                data: 'terminal.name',
                render: function (data) {
                    return '<a href="/terminals?filter=' + data + '">' + data + '</a>';
                }
            },
            {
                data: 'subscription.name',
                render: function (data) {
                    return '<a href="/subscriptions?filter=' + data + '">' + data + '</a>';
                }
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