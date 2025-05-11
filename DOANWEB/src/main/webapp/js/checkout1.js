    // Địa chỉ giao hàng
    $(document).ready(function() {
    loadProvinces();
});

    function loadProvinces() {
    $('#provinceLoading').show();
    $.get('${pageContext.request.contextPath}/addressData?type=provinces', function(data) {
    $('#province').empty().append('<option value="">-- Chọn tỉnh/thành phố --</option>');
    $.each(data, function(index, province) {
    $('#province').append(`<option value="\${province.ProvinceID}">\${province.ProvinceName}</option>`);
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

    $.get('${pageContext.request.contextPath}/addressData?type=districts&parentId=' + provinceId, function(data) {
    $('#district').empty().append('<option value="">-- Chọn quận/huyện --</option>');
    $.each(data, function(index, district) {
    $('#district').append(`<option value="\${district.DistrictID}">\${district.DistrictName}</option>`);
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

    $.get('${pageContext.request.contextPath}/addressData?type=wards&parentId=' + districtId, function(data) {
    $('#ward').empty().append('<option value="">-- Chọn phường/xã --</option>');
    $.each(data, function(index, ward) {
    $('#ward').append(`<option value="\${ward.WardCode}">\${ward.WardName}</option>`);
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
    const province = $('#province option:selected').text();
    const district = $('#district option:selected').text();
    const ward = $('#ward option:selected').text();

    if (province && province !== '-- Chọn tỉnh/thành phố --') {
    let address = province;
    if (district && district !== '-- Chọn quận/huyện --') {
    address += ', ' + district;
    if (ward && ward !== '-- Chọn phường/xã --') {
    address += ', ' + ward;
}
}

    $('#addressText').text(address);
    $('#selectedAddress').show();
} else {
    $('#selectedAddress').hide();
}
}

    $('#province, #district, #ward').change(updateAddressText);

    // Tính phí GHN
    function calculateFee() {
    console.log("Calculating shipping fee...");
    $.ajax({
    url: 'calculateShippingFee',
    type: 'POST',
    data: $('#shippingForm').serialize(),
    success: function(response) {
    if (response.code === 200) {
    $('#result').html(
    '<h3 class="ketqua">Kết quả tính phí:</h3>' +
    '<p>Tổng phí: ' + response.data.total + ' VND</p>' +
    '<p class="service-fee">Phí dịch vụ: ' + response.data.service_fee + ' VND</p>' +
    '<p class="insurance_fee">Phí bảo hiểm: ' + response.data.insurance_fee + ' VND</p>'
    );
        $('.ketqua,.service-fee, .insurance-fee').hide();
    console.log("Shipping fee calculated: " + response.data.total + " VND");
} else {
    $('#result').html('<p style="color:red">Lỗi: ' + response.message + '</p>');
    console.error("API error: " + response.message);
}
},
    error: function(xhr) {
    $('#result').html('<p style="color:red">Lỗi: ' + xhr.responseText + '</p>');
    console.error("AJAX error: " + xhr.responseText);
}
});
}