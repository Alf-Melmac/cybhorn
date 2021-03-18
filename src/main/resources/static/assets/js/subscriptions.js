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
                    return '<div class ="row">' +
                                '<div class="js-tresh ml-3" data-id="' + data.id + '" type="button">' +
                                    '<em class="far fa-trash-alt"></em>' +
                                '</div>' +
                                '<div class="js-edit ml-3" data-id="' + data.id + '" type="button">' +
                                    '<em class="fas fa-edit"></em>' +
                                '</div>' + 
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
            $(row).find('.js-edit').on('click', function () {
                window.location.href = editUrl.replace('{id}', $(this).data('id'));
            });
        }
    });
});