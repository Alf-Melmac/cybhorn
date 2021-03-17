$(function () {
    "use strict";
    const $terminals = $('#terminals');

    $terminals.DataTable({
        ajax: {
            url: '/terminals/list',
            dataSrc: ""
        },
        columns: [
            {
                data: 'name'
            },
            {
                data: 'supportedRanTechnology'
            }
        ]
    });
});