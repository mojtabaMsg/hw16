function getJson() {
    var http = new XMLHttpRequest();
    var url = "https://5fea38808ede8b0017ff183f.mockapi.io/persons";

    http.open("GET",url);
    http.send();

    http.onreadystatechange  = function () {
        if (http.readyState == 4 && http.status === 200) {
            var result = JSON.parse(http.responseText);
            for (var i = 0; i < result.length; i++) {
                console.log("  FirstName :"+result[i].name+"  avatar :"+result[i].avatar)

            }
        }
    }
}