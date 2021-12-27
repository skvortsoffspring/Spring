window.onload = function() {
    GetCategories();
    getCourses(1);
    LoadForm(false);
}

//check token
document.addEventListener("DOMContentLoaded", CheckStorageToken);

// if token exist then button "log out" else "log in"
function CheckStorageToken(){
    if(getToken()!== null) {
        document.getElementById("header__login__button").innerText = "LOG OUT";
        ManagerFormLogin(true);
        return true;
    }else{
        document.getElementById("header__login__button").innerText = "LOG IN";
        ManagerFormLogin(false);
        return false;
    }
}

async function SignIn() {
    fetch("http://localhost:8080/api/auth/login", {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        method: "POST",
        body: JSON.stringify(
            {
                email: document.getElementsByName("email")[0].value,
                password: document.getElementsByName("password")[0].value
            }
        )
    })
        .then(data => data.json())
        .then(result => {
            if(!result.error) {
                console.log(result.email, result.token);
                console.log(document.getElementById("remember").checked);
                if(document.getElementById("remember").checked){
                    localStorage.setItem("token", result.token);
                    localStorage.setItem("email", result.email);
                    sessionStorage.clear();
                }else {
                    sessionStorage.setItem("token", result.token);
                    sessionStorage.setItem("email", result.email);
                    localStorage.clear();
                }
                CheckStorageToken();
            }else
            {
                console.log(result.error);
            }

        });
}

async function SignOut(){
    if(!CheckStorageToken()){
        return;
    }
    await fetch("http://localhost:8080/api/auth/logout", {
        headers: {
            "Content-Type": "application/json",
            "Authorization": getToken()
        },
        method: "POST"
    });
    localStorage.clear();
    sessionStorage.clear();
    CheckStorageToken();
}

let getToken = () => {
    if (localStorage.getItem("token")) {
        return localStorage.getItem("token");
    } else if (sessionStorage.getItem("token")) {
        return sessionStorage.getItem("token")
    }else
        return null;
}

let ManagerFormLogin = (visible) => {
    let formLogin = document.getElementById("login");
    if(visible){
        formLogin.style.display  = "none";
        return false;
    }else{
        formLogin.style.display  = "grid";
        return true;
    }
}

let LoadForm = (val) => {
    let LogInForm = document.getElementById("login");
    if(val){
        LogInForm.innerHTML = `<div class="login__head">
        <span class="login__head__text">PROTECTED COURSES</span>
    </div>
    <div class="login__email">
        <label class="login__label">EMAIL
            <input  class="login__input" type="email" name="email">
        </label>
    </div>
    <div class="login__password">
        <label class="login__label">PASSWORD
            <input  class="login__input" type="password" name="password">
        </label>
    </div>
    <div>
        <button onclick="SignIn()">Sign IN</button>
    </div>
    <div>
        <label>
            <input id="remember" type="checkbox" checked="checked">
            Remember me
        </label>
        <button onclick="LoadForm(false)">Register Form</button>
    </div>`;
    }else{
        LogInForm.innerHTML = `    <div class="login__head" >
        <span class="login__head__text">REGISTER</span>
    </div>
    <div class="login__login">
        <label class="login__label">LOGIN
            <input  class="login__input" type="text" max="20" name="login">
        </label>
    </div>
    <div class="login__email">
        <label class="login__label">EMAIL
            <input  class="login__input" type="email" name="email">
        </label>
    </div>
    <div class="login__phone">
        <label class="login__label">PHONE
            <input  class="login__input" type="text" name="phone">
        </label>
    </div>
    <div class="login__password">
        <label class="login__label">PASSWORD
            <input  class="login__input" type="password" name="password">
        </label>
    </div>
    <div>
        <button onclick="Register()">Register</button>
    </div>
    <div>
        <button onclick="LoadForm(true)">Log In Form</button>
    </div>`;
    }
}
