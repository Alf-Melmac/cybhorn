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
                data: 'name',
                render: $.fn.dataTable.render.text()
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
                data: 'secondsCalled',
                render: function (data) {
                    if(data != 0) return parseInt(data/60) + 1;
                    else return 0;
                }
            },
            {
                data: 'dataUsed',
                render: function (data) {
                    
                    if(data > 1000) return data/1000 + " GB";
                    else return data + " MB";
                }
            },
            {
                data: null,
                orderable: false,
                searchable: false,
                render: function (data) {
                    return '<div class ="row">' +
                                '<div class="js-tresh ml-3" data-id="' + data.imsi + '" type="button">' +
                                    '<em class="far fa-trash-alt"></em>' +
                                '</div>' +
                                '<div class="js-edit ml-3" data-id="' + data.imsi + '" type="button">' +
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
                    .fail(response => {
                        const $errorToast = $('#errorToast');
                        $errorToast.find('.toast-body').text(response.responseJSON.errorMessage);
                        $errorToast.toast('show');
                    });
            });

            $(row).find('.js-edit').on('click', function () {
                window.location.href = editUrl.replace('{id}', $(this).data('id'));
            });
        }
    });

    $('#generate-invoices').on('click', function () {
        $.ajax(invoiceUrl, {
            method: 'GET'
        })
            .done(() => {
                $('#savedToast').toast('show');
                setTimeout("window.location.reload();", 4000);
            });
    });
});