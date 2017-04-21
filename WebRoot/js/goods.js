function validateTitle() {
    return validateEmpty("title") ;
}
function validatePrice() {
    return validateRegex("price",/^\d+(\.\d{1,2})?$/) ;
}
function validateAmount() {
    return validateRegex("amount",/^\d+$/) ;
}
function validateNote() {
    return validateEmpty("note") ;
}
function validateInsert() {
    return validateTitle() && validatePrice() && validateAmount() && validateNote() ;
}