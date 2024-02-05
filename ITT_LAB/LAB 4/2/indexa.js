function vowel(){
    let str = document.getElementById("in").value;
    let i;
    for(i=0;i<str.length;i++){
        if(str[i]== "a"||str[i]== "e"||str[i]== "i"||str[i]== "o"||str[i]== "u"){
            break;
        }
    }
    document.getElementById("out").innerHTML = i;
}


