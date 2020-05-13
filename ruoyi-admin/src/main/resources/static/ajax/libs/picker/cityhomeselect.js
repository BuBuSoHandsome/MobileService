var homeCity = document.getElementById('homeCity');
var firstH = []; /* 省，直辖市 */
var secondH = []; /* 市 */

var selectedIndex = [0, 0]; /* 默认选中的地区 */

var checked = [0, 0]; /* 已选选项 */

function creatList(obj, list){
  obj.forEach(function(item, index, arr){
  var temp = new Object();
  temp.text = item.name;
  temp.value = index;
  list.push(temp);
  })
}
creatList(city, firstH);

if (city[selectedIndex[0]].hasOwnProperty('sub')) {
  creatList(city[selectedIndex[0]].sub, secondH);
} else {
  secondH = [{text: '', value: 0}];
}

/*if (city[selectedIndex[0]].sub[selectedIndex[1]].hasOwnProperty('sub')) {
  creatList(city[selectedIndex[0]].sub[selectedIndex[1]].sub, third);
} else {
  third = [{text: '', value: 0}];
}*/

var pickerH = new Picker({
    data: [firstH, secondH],
    title: '号码归属地'
});


pickerH.on('picker.select', function (selectedVal, selectedIndex) {
    var text1 = firstH[selectedIndex[0]].text;
    var text2 = secondH[selectedIndex[1]].text;
    homeCity.value = text1 + ' ' + text2;
});

pickerH.on('picker.change', function (index, selectedIndex) {
    if (index === 0) {
        firstChange(selectedIndex);
    }
});

function firstChange(selectedIndex) {
    secondH = [];
    checked[0] = selectedIndex;
    var firstCity = city[selectedIndex];
    if (firstCity.hasOwnProperty('sub')) {
        creatList(firstCity.sub, secondH);
    } else {
        secondH = [{text: '', value: 0}];
        checked[1] = 0;
    }
    pickerH.refillColumn(1, secondH);
    pickerH.scrollColumn(1, 0)
}
pickerH.on('picker.valuechange', function (selectedVal, selectedIndex) {
    var a = city[selectedIndex[0]];
    var b = a.hasOwnProperty('sub')? a.sub[selectedIndex[1]]:'';
    $('#homeProvinceCode').val(a.code);
    $('#homeCityCode').val(b.code);
});
homeCity.addEventListener('click', function () {
    pickerH.show();
});


