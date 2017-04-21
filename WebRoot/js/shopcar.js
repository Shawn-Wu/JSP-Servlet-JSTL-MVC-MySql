var allPrice = 0.0 ;    // 保存总价格
window.onload = function() {    // 在页面加载完毕之后进行统计计算
    document.getElementById("result").innerHTML = "<font color='red'>总金额：" + allPrice + "</font>" ;
}
function calGoods(gid) {
    var price = parseFloat(document.getElementById("price-" + gid).innerHTML) ;
    var count = parseInt(document.getElementById(gid).value) ;
    allPrice += (price * count) ;
    document.getElementById("cal-" + gid).innerHTML = "<font color='red'>" + (price * count) + "</font>" ;
    if (document.getElementById("result") != undefined) {
        document.getElementById("result").innerHTML = "<font color='red'>总金额：" + allPrice + "</font>" ;
    }
}

function addBut(gid) {
    var price = parseFloat(document.getElementById("price-" + gid).innerHTML) ;
    var count = parseInt(document.getElementById(gid).value) ;
    allPrice -= (price * count) ;
    count ++ ;
    document.getElementById(gid).value = count ;
    calGoods(gid) ; // 重新计算新的价格
}
function subBut(gid) {
    var price = parseFloat(document.getElementById("price-" + gid).innerHTML) ;
    var count = parseInt(document.getElementById(gid).value) ;
    allPrice -= (price * count) ;
    if (count == 0) {
        count--;
        document.getElementById(gid).value = count;
        calGoods(gid); // 重新计算新的价格
    }
}
function calResult() {

}