function validateIid() {
    return validateEmpty("iid") ;
}
function validateTitle() {
    return validateEmpty("title") ;
}
function goUpdate(iid) {
    if (validateEmpty("title-" + iid)) {    // 验证可以通过
        title = document.getElementById("title-" + iid).value ;
        document.getElementById("iid").value = iid ;
        document.getElementById("title").value = title ;
        document.getElementById("itemForm").submit() ;
    }
}
function validateInsert() {
    return validateTitle() ;
}