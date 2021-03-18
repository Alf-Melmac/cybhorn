$(function () {
    "use strict";
    const $subscriptions = $('#subscriptions');

    $subscriptions.DataTable({
        search: {
            search: searchParam
        },
        ajax: {
            url: '/subscriptions/list',
            dataSrc: ""
        },
        columns: [
            {
                data: 'name'
            },
            {
                data: 'basicFee'
            },
            {
                data: 'secondsIncluded'
            },
            {
                data: 'pricePerSecond'
            },
            {
                data: 'dataVolume'
            }
        ]
    });
});