function num(){
    var temp = document.getElementById("input").value;
    
    var arr = temp.split(")");
    var areacode = arr[0].substring(1,arr[0].length);
    var phoneno = arr[1].substring(0,arr[1].length);
    document.getElementById("area").innerHTML= "Area code: " + areacode;
    document.getElementById("no").innerHTML= "Phone no: "+phoneno;
}