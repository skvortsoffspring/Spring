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
                let items = `<div class="wrapper__item">
                                <div class="content__item__button"></div>
                                <div class="content__item__name">NAME</div>
                             </div>`;
                result.forEach((item) => {
                    items += builderItem(item);
                })
                items +=`<div class="nav__page" id="nav__page"></div>`;
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
    await getCourses();
    await GetCategoriesAdmin(category);
}

let builderItem = (item) => {
    return `<div class="wrapper__item">
                <button class="content__item__button" onclick="LoadToEditor(${item.id})">Load</button>
                <div class="content__item__name">${item.name}</div>
            </div>`
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
    fetch(`http://localhost:8080/index`, {
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
