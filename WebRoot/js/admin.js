function validateAid() {
    return validateEmpty("aid") ;
}
function validatePassword() {
    return validateEmpty("password") ;
}
function validateCode() {
    return validateEmpty("code") ;
}
function validateLogin() {
    return validateAid() && validatePassword() && validateCode() ;
}