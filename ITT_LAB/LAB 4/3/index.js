window.onload = function() {
    var para1 = document.getElementById('para1');
    var para2 = document.getElementById('para2');
    var para3 = document.getElementById('para3');

    para1.onmouseover = function() { this.style.zIndex = "10"; }
    para1.onmouseout = function() { this.style.zIndex = "1"; }

    para2.onmouseover = function() { this.style.zIndex = "10"; }
    para2.onmouseout = function() { this.style.zIndex = "2"; }

    para3.onmouseover = function() { this.style.zIndex = "10"; }
    para3.onmouseout = function() { this.style.zIndex = "3"; }
}
