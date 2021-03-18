$(function () {
    "use strict";
    const $terminals = $('#terminals');

    $terminals.DataTable({
        search: {
            search: searchParam
        },
        ajax: {
            url: '/terminals/list',
            dataSrc: ""
        },
        columns: [
            {
                data: 'name'
            },
            {
                data: 'supportedRanTechnologyName'
            }
        ]
    });
});