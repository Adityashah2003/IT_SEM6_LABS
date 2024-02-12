function printLatin(text) {
    var text = document.getElementById("input").value;
    var a = text.split(' ');
    var temp;

    for(let i =0;i<a.length;i++){
        temp = a[i];
        var s = "";
        s = temp.substring(1, temp.length);
        s += temp.charAt(0);
        s += "ay"; 
        document.getElementById("output").innerHTML = s;  
    }
}
    