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
                data: 'name',
                render: $.fn.dataTable.render.text()
            },
            {
                data: 'supportedRanTechnologyName'
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
});