async function getCourses(page){
    let category = sessionStorage.getItem("category");
    console.log(category);
    if(category === null){
        category = "empty";
    }

    if(!page){
        page = 1;
    }

    fetch(`http://localhost:8080/api/courses/get/page/${page}/${category}`, {
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
                let items = "";
                result.forEach((item) => {
                    items += builderItem(item);
                })
                GetSize(page, category);
                document.getElementById("wrapper").innerHTML = items;

            }else {
                alert(result.message);
            }
        });



}

async function getCoursesByCategory(category){
    console.log(category);
    sessionStorage.setItem("category", category);
    await getCourses(1);
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

let GetSize = (page, category) => {
    fetch(`http://localhost:8080/api/courses/get/size/${page}/${category}`, {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": getToken()
        },
        method: "GET"
    })
        .then(data => {console.log(data); return data.json()})
        .then(result => {
            if(!result.error) {
                BuildPagination(result);
            }else {
                alert(result.message);
            }
        });
}

let BuildPagination = (size) => {
    if(size === 0) return;

    const sizeItemsPage = 8;
    let page = size/sizeItemsPage | 0;
    let pagination = document.getElementById("nav__page");
    if(size % sizeItemsPage !== 0){
        page++;
    }
    pagination.innerHTML = "";
    for (let i = 0; i < page; i++) {
        pagination.innerHTML += `<button class="nav__page__number" onClick="getCourses(this.firstChild.nodeValue)">${i + 1}</button>`
    }
}

let ForwardToAdmin = (string, type) =>{
    fetch(`http://localhost:8080/admin`, {
        headers: {
            "Accept": "text/html",
            "Content-Type": "application/json",
            "Authorization": getToken()
        },
        method: "GET"
    })
            .then(result => { return result.text()})
            .then(text => {
                console.log(text);
                let local = new DOMParser().parseFromString(text, "text/html");
                document.body.innerHTML = local.body.innerHTML;
                document.head.innerHTML = local.head.innerHTML;
            })
};
