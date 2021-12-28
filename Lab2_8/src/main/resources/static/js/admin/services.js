let Search =  async (val) =>{
    if(val === "") return;
    fetch(`http://localhost:8080/api/courses/get/search/${val}`, {
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

let Register =  async () =>{
    fetch(`http://localhost:8080/api/auth/register`, {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
        },
        method: "POST",
        body: JSON.stringify(
            {
                email: document.getElementsByName("email")[0].value,
                login: document.getElementsByName("login")[0].value,
                phone: document.getElementsByName("phone")[0].value,
                password: document.getElementsByName("password")[0].value
            })
    })
        .then(data => data.json())
        .then(result => {
            if(!result.error) {
                console.log(JSON.stringify(result));
            }else {
                alert(result.message);
            }
        });
}

let ClearSearchResult = () => {
    sessionStorage.removeItem("category");
    document.getElementById('header__field__search').value = "";
    getCourses();
}

let ByCourse = (id) => {
    console.log(id);
    fetch(`http://localhost:8080/api/purchased/buy`, {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": getToken()
        },
        method: "POST",
        body: JSON.stringify(
            {
                id: id
            }
        )
    })
        .then(data => data.json())
        .then(result => {
            console.log(result);
        });
}