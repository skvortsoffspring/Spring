
async function GetCategories(){

    fetch(`http://localhost:8080/api/categories/names`, {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": getToken()
        },
        method: "GET"
    })
        .then(data => data.json())
        .then(result => {
            if(!result.error) {
                console.log(JSON.stringify(result));
                builderCategories(result);
            }else {
                alert(result.message);
            }
        });
}


let builderCategories = (result) => {
    let items = "";
    result.forEach((item) => {
        items += `<button class="category__button" onclick="getCoursesByCategory(this.firstChild.nodeValue)">${item.name}</button>`
    })
    document.getElementById("categories").innerHTML = items;
}