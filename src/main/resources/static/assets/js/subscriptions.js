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
            },
            {
                data: null,
                orderable: false,
                searchable: false,
                render: function (data) {
                    return '<div class="js-tresh" data-id="' + data.id + '" type="button">' +
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