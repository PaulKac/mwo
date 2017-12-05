var CryptoJS = require("crypto-js");
var secret = "B4C4FA34EFABF21C7F4B6E99D1F7799F";

module.exports = {
    logIn: function(token) {
        this.setToken(token);
        localStorage.setItem("logged", 'true');
    },
    isLoggedIn: function() {
        return localStorage.getItem("logged") == "true";
    },

    getToken: function() {
        return localStorage.getItem("token");
    },

    setToken: function(token) {
        return localStorage.setItem("token", token);
    },

    logout: function() {
        localStorage.setItem("token", '');
        localStorage.setItem("logged", 'false');
    },

    encryptPassword: function(password) {
        var encrypted = CryptoJS.AES.encrypt(password, secret);
        return encrypted.toString();
    }
};