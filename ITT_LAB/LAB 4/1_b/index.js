function square(n){
    let nums = [];
    for(let i=1;i<=n;i++){
        let j=i;
        nums[i]= j*j;
    }
    return nums;
}
var n = prompt("Enter N");
let ans = square(n);
alert("The squares are: " + ans.join(","));