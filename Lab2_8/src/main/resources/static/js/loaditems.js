async function getCourses(page){
    fetch(`http://localhost:8080/api/courses/get/page/${page}`, {
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
                //console.log(JSON.stringify(result));
                let items = "";
                result.forEach((item) => {
                    items += builderItem(item);
                });
                document.getElementById("wrapper").innerHTML = items;
            }else {
                alert(result.message);
            }
        });
}

let builderItem = (item) => {
    return `<div class="content__item">
            <img class="content__item__img" src="data:image/jpg;base64,${item.image}" alt="">
                <div class="content__item__name">${item.name}</div>
                <span
                    class="content__item__complexity">${GetComplexity(item.complexity)}</span>
                <div class="content__item__price">Price ${item.price}</div>
                <button class="content__item__button" onclick="ByCourse(${item.id})">
                    Buy now
                </button>
        </div>`
}

let GetComplexity = (complexity) => {
    let response = "";
    if(!complexity){
        return "&#9733;";
    }else{
        for (let i = 0; i < complexity; i++) {
            response += "&#9733;";
        }
        return response;
    }
}
