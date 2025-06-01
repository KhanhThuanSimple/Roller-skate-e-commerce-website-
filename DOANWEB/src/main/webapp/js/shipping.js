const SHOP_DISTRICT_ID = 1463;

$(document).ready(function() {
    loadProvinces();
    $('#fromDistrictId').val(SHOP_DISTRICT_ID);
});

function loadProvinces() {
    $('#provinceLoading').show();
    $.get(contextPath + '/addressData?type=provinces', function(data) {
        $('#province').empty().append('<option value="">-- Chọn tỉnh/thành phố --</option>');
        $.each(data || [], function(index, province) {
            $('#province').append(`<option value="${province.ProvinceID}">${province.ProvinceName}</option>`);
        });
        $('#provinceLoading').hide();
    }).fail(function() {
        alert('Lỗi khi tải danh sách tỉnh/thành phố');
        $('#provinceLoading').hide();
    });
}

function loadDistricts() {
    const provinceId = $('#province').val();
    if (!provinceId) {
        $('#district').val('').prop('disabled', true);
        $('#ward').val('').prop('disabled', true);
        updateAddressText();
        return;
    }

    $('#districtLoading').show();
    $('#district').prop('disabled', true);
    $('#ward').val('').prop('disabled', true);

    $.get(contextPath + '/addressData?type=districts&parentId=' + provinceId, function(data) {
        $('#district').empty().append('<option value="">-- Chọn quận/huyện --</option>');
        $.each(data || [], function(index, district) {
            $('#district').append(`<option value="${district.DistrictID}">${district.DistrictName}</option>`);
        });
        $('#district').prop('disabled', false);
        $('#districtLoading').hide();
        updateAddressText();
    }).fail(function() {
        alert('Lỗi khi tải danh sách quận/huyện');
        $('#districtLoading').hide();
    });
}

function loadWards() {
    const districtId = $('#district').val();
    if (!districtId) {
        $('#ward').val('').prop('disabled', true);
        updateAddressText();
        return;
    }

    $('#wardLoading').show();
    $('#ward').prop('disabled', true);

    $.get(contextPath + '/addressData?type=wards&parentId=' + districtId, function(data) {
        $('#ward').empty().append('<option value="">-- Chọn phường/xã --</option>');
        $.each(data || [], function(index, ward) {
            $('#ward').append(`<option value="${ward.WardCode}">${ward.WardName}</option>`);
        });
        $('#ward').prop('disabled', false);
        $('#wardLoading').hide();
        updateAddressText();
    }).fail(function() {
        alert('Lỗi khi tải danh sách phường/xã');
        $('#wardLoading').hide();
    });
}

function updateAddressText() {
    const province = $('#province option:selected');
    const district = $('#district option:selected');
    const ward = $('#ward option:selected');

    if (province.val()) {
        let address = province.text();
        if (district.val()) {
            address += ', ' + district.text();
            $('#toDistrictId').val(district.val());

            if (ward.val()) {
                address += ', ' + ward.text();
                $('#toWardCode').val(ward.val());
                calculateFee();
            } else {
                $('#toWardCode').val('');
            }
        } else {
            $('#toDistrictId').val('');
            $('#toWardCode').val('');
        }

        $('#addressText').text(address);
        $('#selectedAddress').show();
    } else {
        $('#selectedAddress').hide();
        $('#toDistrictId').val('');
        $('#toWardCode').val('');
    }
}

function calculateFee() {
    if (!$('#toDistrictId').val() || !$('#toWardCode').val()) {
        $('#result').html('Vui lòng chọn đầy đủ địa chỉ để tính phí.');
        return;
    }

    $.ajax({
        url: contextPath + '/calculateShippingFee',
        type: 'POST',
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: $('#shippingForm').serialize(),
        success: function(response) {
            if (response.code === 200) {
                // $('#result').html(
                //
                //     '<p>' + response.data.total + ' VND</p>'
                //     // '<p class="service_fee">Phí dịch vụ : ' + response.data.service_fee + ' VND</p>' +
                //     // '<p class="insurance_fee">Phí bảo hiểm: ' + response.data.insurance_fee + ' VND</p>'
                // );
                var formattedTotal = response.data.total.toLocaleString('vi-VN') + ' VND';
                $('#result').html('<p>' + formattedTotal + '</p>');
                updateShippingFee(response.data.total);

            } else {
                $('#result').html('<p style="color:red">Cữa hàng chỉ hỗ trợ ship nội thành TP.Hồ Chí Minh </p>');
                updateShippingFee(32000); // Không tính phí ship nếu không hỗ trợ

            }
        },
        error: function(xhr, status, error) {
            $('#result').html('<p style="color:red">Lỗi kết nối: ' + error + '</p>');
        }
    });
}
