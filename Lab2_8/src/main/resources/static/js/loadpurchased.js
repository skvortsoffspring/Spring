async function GetPurchased(){

    fetch(`http://localhost:8080/api/purchased/get`, {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": getToken()
        },
        method: "POST",
    })
        .then(data => data.json())
        .then(result => {
            let items = "";
            if(!result.error) {
                //console.log(JSON.stringify(result));
                result.forEach((item) => {
                    items += builderPurchased(item);
                });

                document.getElementById("wrapper").innerHTML = items;
            }else {
                alert(result.message);
            }
        });
}

let builderPurchased = (item) => {
    return `<div class = "content__purchased">
        ${builderItem(item.course)}
        <div class="content__purchased__key">Serial: ${item.key}</div>
        <div class="content__purchased__activated">${checkActivated(item.active)}</div>
        <div class="content__item__dateActivated">${checkActivatedDate(item.dateActivated)}</div>
     </div>`
}

let checkActivated = (val)=>{
    if(!val){
        return "Not activated"
    }
    else {
        return "Activated";
    }
}


let checkActivatedDate = (val)=>{
    if(!val){
        return "Not activated"
    }
    else {
        return val;
    }
}

