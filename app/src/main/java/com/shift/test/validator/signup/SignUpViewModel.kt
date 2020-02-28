package com.shift.test.validator.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel: ViewModel() {

    private val _firstName = MutableLiveData<String>("")
    val firstName: LiveData<String>
        get() = _firstName

    private val _lastName = MutableLiveData<String>("")
    val lastName: LiveData<String>
        get() = _lastName

    private val _birthday = MutableLiveData<String>("")
    val birthday: LiveData<String>
        get() = _birthday

    private val _password = MutableLiveData<String>("")
    val password: LiveData<String>
        get() = _password

    private val _confirmedPassword = MutableLiveData<String>("")
    val confirmedPassword: LiveData<String>
        get() = _confirmedPassword

    private val _description = MutableLiveData<String>("")
    val description: LiveData<String>
        get() = _description

    private val _isValid = MutableLiveData<Boolean>()
    val isValid: LiveData<Boolean>
        get() = _isValid

    fun getFirstName(data: String){
        if (!_firstName.value.equals(data))
            _firstName.value = data
    }

    fun getlastName(data: String){
        if (!_lastName.value.equals(data))
            _lastName.value = data
    }

    fun getBirthday(data: String){
        if (!_birthday.value.equals(data))
            _birthday.value = data
    }

    fun getPassword(data: String){
        if (!_password.value.equals(data))
            _password.value = data
    }

    fun getConfirmedPassword(data: String){
        if (!_confirmedPassword.value.equals(data))
            _confirmedPassword.value = data
    }

    fun onSignUp() {
        clearDescription()
        isValidName(_firstName.value!!)
        isValidName(_lastName.value!!)
        isValidDate(_birthday.value!!)
        isValidPassword(_password.value!!)
        areValidPasswords(_password.value!!, _confirmedPassword.value!!)
        if (_description.value!!.isEmpty() )
            _isValid.value = true
        _isValid.value = false
    }

    private fun isValidName(value: String) {
        when {
            value.isEmpty() ->
                _description.value = _description.value + "-First name or last name  field is empty\n"
            value.contains(".*\\d.*".toRegex()) ->
                _description.value = _description.value + "-Numbers is not allowed in name fields\n"
            value.contains("[|/~^:,;?!&%$@*+]".toRegex()) ->
                _description.value = _description.value + "-Symbols is not allowed in name fields\n"
        }
    }

    private fun isValidDate(value: String) {
        when {
            value.isEmpty() ->
                _description.value = _description.value + "-Date is empty\n"
            value.matches("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)".toRegex()) ->
                _description.value = _description.value + "-Wrong date format\n"
        }
    }

    private fun isValidPassword(value: String) {
        when {
            value.isEmpty() ->
                _description.value = _description.value + "-Password is empty\n"
            value.length < 8 ->
                _description.value = _description.value + "-Too short password\n"
            !value.contains("(?=.*[0-9])".toRegex()) ->
                _description.value = _description.value + "-Password must contain numbers\n"
            !value.contains("(?=\\S+$)".toRegex()) ->
                _description.value = _description.value + "-Space is not allowed in password\n"
            value.contains("[|/~^:,;?!&%$@*+]".toRegex()) ->
                _description.value = _description.value + "-Symbols is not allowed in password\n"
        }
    }

    private fun areValidPasswords(pass: String, confPass: String) {
        if (pass != confPass)
            _description.value = _description.value + "-Passwords must be equal\n"
    }

    private fun clearDescription() {
        if (_description.value!!.isNotEmpty() )
            _description.value = ""
    }

    fun onSignedUp() {
        _isValid.value = false
    }
}