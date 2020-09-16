//初始化
function initAddress(element, address) {
  let add = {};
  // 地址
  element.find('[name="address"], [name="address"]').val('');
  element.find('[name="country"]').data('selected', '');
  element.find('[name="district"]').data('selected', '');
  element.find('[name="country"] option').remove();
  element.find('[name="district"] option').remove();
  element.dk_tw_citySelector('.country', '.district', '.zipcode');
  if (address) {
    add = {};
    add.zip = address.slice(0, 3);
    add.blurryCountry = address.slice(3, 6);
    // 選城市
    add.country = $('[value^="' + add.blurryCountry + '"]').val();
    element.find('[name="country"]').data('selected', add.country);
    element.dk_tw_citySelector('.country', '.district', '.zipcode');
    // 選區
    element.find('[name="country"] option').remove(); // 清空多餘城市
    add.district = $('[data-zip="' + add.zip + '"]').val();
    element.find('[name="district"] option').remove(); // 清空多餘區域
    add.detail = address.slice(
      add.zip.length + add.district.length + add.country.length,
      address.length
    );
    element.find('[name="district"]').data('selected', add.district);
    element.dk_tw_citySelector('.country', '.district', '.zipcode');
    // 填詳細地址
    element.find('[name="address"]').val(add.detail);
  }
}
