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
                data: 'name'
            },
            {
                data: 'terminal.name',
                render: function (data) {
                    if (data) {
                        return '<a href="/terminals?filter=' + data + '">' + data + '</a>';
                    } else {
                        return '-';
                    }
                }
            },
            {
                data: 'subscription.name',
                render: function (data) {
                    if (data) {
                        return '<a href="/subscriptions?filter=' + data + '">' + data + '</a>';
                    } else {
                        return '-';
                    }
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