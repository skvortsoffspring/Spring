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

let GetComplexity = (complexity) => {
    let response = "";
    if(!complexity){
        return `<div>&#9733;</div>`;
    }else{
        for (let i = 0; i < complexity; i++) {
            response += `<div>&#9733;</div>`;
        }
        return response;
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

async function PostCourse(){

    let category = {id:0, name:null, image:null };
    let courseId = document.getElementById("id").value;
    let name = document.getElementById("name").value;
    let file = await FileToByte();
    let complexity = document.getElementById("editor__complexity__value").value;
    let price = document.getElementById("price").value;
    category.id = document.getElementById("editor__select__category").value;
    category.name = document.getElementById("editor__select__category").innerText;
    console.log("file" + file);

    fetch(`http://localhost:8080/api/courses/admin/add`, {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": getToken()
        },
        method: "POST",
        body: JSON.stringify({
            id: courseId,
            name: name,
            file: file,
            category: category,
            complexity: complexity,
            price: price
        })
    })
        .then(data => data.json())
        .then(result => {
            if(!result.error) {
                console.log(result);
            }else {
                alert(result.message);
            }
        });
}

let LoadToEditor = (val) => {
    let id = document.getElementById("id");
    let name = document.getElementById("name");
    let category = document.getElementById("editor__select__category");
    let image = document.getElementById("image");
    let complexity = document.getElementById("editor__complexity__value");
    let price = document.getElementById("price");

    fetch(`http://localhost:8080/api/courses/get/${val}`, {
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
                console.log(result.category.id);
                id.value = result.id;
                name.value = result.name;
                category.value = result.category.id;
                image.src = `data:image/jpg;base64,${result.image}`
                complexity.value = result.complexity;
                price.value = result.price;
                GetComplexity(result.complexity);
            }else {
                alert(result.message);
            }
        });
}

let FileToByte = () => {
    let ready = false;
    let result = '';

    let check = function() {
        if (ready === true) {
            console.log(result);
            return result;
        }
        setTimeout(check, 1000);
    }

    check();

    let reader = new FileReader();
    reader.onloadend = function(evt) {
        result = evt.target.result;
        ready = true;
    };
    reader.readAsArrayBuffer(document.getElementById("file").files[0]);
}
