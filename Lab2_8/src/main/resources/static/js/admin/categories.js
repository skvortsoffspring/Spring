
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

async function GetCategoriesAdmin(category){

    fetch(`http://localhost:8080/api/categories/admin/info/${category}`, {
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
                document.getElementById("header__category__id").value = result.id;
                document.getElementById("header__category__input").value = result.name;
            }else {
                alert(result.message);
            }
        });
}

let builderCategories = (result) => {
    let items = "";
    let options = ""
    result.forEach((item) => {
        items += `<button class="category__button" onclick="getCoursesByCategory(this.firstChild.nodeValue)">${item.name}</button>`;
        options += `<option value="${item.id}">${item.name}</option>`;
    })
    document.getElementById("categories").innerHTML = items;
    document.getElementById("editor__select__category").innerHTML = options;
}
// SELECT UPDATE DELETE
let PostCategory = async () =>{
    fetch(`http://localhost:8080/api/categories/admin/add`, {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": getToken()
        },
        method: "POST",
        body: JSON.stringify(
            {
                name: document.getElementById("header__category__input").value
            }
        )
    })
        .then(result => {
                console.log(result);
                GetCategories();
        });
}

let PutCategory = async () =>{
    fetch(`http://localhost:8080/api/categories/admin/update`, {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": getToken()
        },
        method: "PUT",
        body: JSON.stringify(
            {
                id: document.getElementById("header__category__id").value,
                name: document.getElementById("header__category__input").value

            }
        )
    })
        .then(result => {
                console.log(result);
            GetCategories();
        });
}

let DeleteCategory = async () =>{
    fetch(`http://localhost:8080/api/categories/admin/del`, {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": getToken()
        },
        method: "DELETE",
        body: JSON.stringify(
            {
                id: document.getElementById("header__category__id").value
            }
        )
    })
        .then(result => {
            console.log(result);
            GetCategories();
            sessionStorage.removeItem("category");
        });
}