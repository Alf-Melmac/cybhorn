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
            },
            {
                data: null,
                orderable: false,
                searchable: false,
                render: function (data) {
                    return '<div class="js-tresh" data-id="' + data.imsi + '" type="button">' +
                        '<em class="far fa-trash-alt"></em>' +
                        '</div>';
                }
            }
        ],
        createdRow: function (row, data, index) {
            $(row).find('.js-tresh').on('click', function () {
                const $this = $(this);
                $.ajax(deleteUrl.replace('{id}', $this.data('id')), {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json'
                    }
                })
                    .done($this.parents('tr').fadeOut())
                    .fail(response => alert(JSON.stringify(response) + '\nAction failed. Try again later\n'));
            });
        }
    });
});