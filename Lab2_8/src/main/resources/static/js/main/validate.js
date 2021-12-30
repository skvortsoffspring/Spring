window.onload = function() {
    GetCategories();
    getCourses();
    LoadForm(true);
}

//check token
document.addEventListener("DOMContentLoaded", CheckStorageToken);

// if token exist then button "log out" else "log in"
function CheckStorageToken(){
    if(getToken()!== null) {
        document.getElementById("header__login__button").innerText = "LOG OUT";
        document.getElementById("header__login__button").onclick = SignOut;
        return true;
    }else{
        document.getElementById("header__login__button").innerText = "LOG IN";
        document.getElementById("header__login__button").onclick = ManagerFormLogin;
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
            console.log(result);
            if(result.email) {
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
                ManagerFormLogin();
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

let ManagerFormLogin = () => {

    let formLogin = document.getElementById("login");

    if(localStorage.getItem("login")){
        formLogin.style.display = "none";
        localStorage.removeItem("login");
    }else{
        formLogin.style.display = "grid";
        localStorage.setItem("login", "true");
    }
}

let LoadForm = (val) => {
    let LogInForm = document.getElementById("login");
    if(val){
        LogInForm.innerHTML = `
    <button class="login__button__close" onclick="ManagerFormLogin()">X</button>
    <div class="login__head">
        <span class="login__head__text">PROTECTED COURSES</span>
    </div>
    <div class="login__email">
        <label class="login__label">EMAIL
            <input  class="login__input" type="email" name="email" placeholder="mail@mail.com">
        </label>
    </div>
    <div class="login__password">
        <label class="login__label">PASSWORD
            <input  class="login__input" type="password" name="password" placeholder="******">
        </label>
    </div>
        <button class="login__button__sign" onclick="SignIn()">Sign IN</button>
    <div>
        <label>
            <input id="remember" type="checkbox" checked="checked">
            Remember me
        </label>
        <button class="login__button__form__change" onclick="LoadForm(false)">Register Form</button>
    </div>`
    }
    else{
        LogInForm.innerHTML = `    
    <button class="login__button__close" onclick="ManagerFormLogin()">X</button>
    <div class="login__head" >
        <span class="login__head__text">REGISTER</span>
    </div>
    <div class="login__login">
        <label class="login__label">LOGIN
            <input  class="login__input" type="text" max="20" name="login" placeholder="skvortsoff">
        </label>
    </div>
    <div class="login__email">
        <label class="login__label">EMAIL
            <input  class="login__input" type="email" name="email" placeholder="mail@gmail.com">
        </label>
    </div>
    <div class="login__phone">
        <label class="login__label">PHONE
            <input  class="login__input" type="text" name="phone" placeholder="**-***-**-**">
        </label>
    </div>
    <div class="login__password">
        <label class="login__label">PASSWORD
            <input  class="login__input" type="password" name="password">
        </label>
    </div>
    <div>
        <button class="login__button__sign" onclick="Register()">Register</button>
    </div>
    <div>
        <button class="login__button__form__change" onclick="LoadForm(true)">Log In Form</button>
    </div>`;
    }
}