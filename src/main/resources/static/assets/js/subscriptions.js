$(function () {
    "use strict";
    const $subscriptions = $('#subscriptions');

    $subscriptions.DataTable({
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