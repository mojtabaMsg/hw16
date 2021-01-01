var i = 0
function addNewBox() {

    var div = document.createElement("div");
    div.className = "child"
    div.innerText = i++;
    document.getElementById("parent").appendChild(div);
    div.setAttribute("style", "background-color:"+getRandomColor());
}

function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}