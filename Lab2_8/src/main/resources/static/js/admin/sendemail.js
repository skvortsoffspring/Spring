let SendEmail = () => {
    fetch("http://localhost:8080/api/email/send", {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": getToken()
        },
        method: "POST",
        body: JSON.stringify(
            {
                email: document.getElementById("senderEmail").value,
                from: document.getElementById("senderFrom").value,
                message: document.getElementById("senderMessage").value
            }
        )
    })
        .then(data => data.json())
        .then(result => {
            console.log(result);

        });
}

let getFormContact = () => {
    let formEmail = document.getElementById("email");
    formEmail.style.display = "grid";
    formEmail.innerHTML = ` 
  <div class="email__head">FEEDBACK</div>
    <button class="email__button__close" onclick="FormEmailClose()">X</button>
    <label class="email__input__email"> EMAIL
        <input id="senderEmail" type="email"placeholder="mail@gmail.com">
    </label>
    <label class="email__input__from"> FOR
        <input id="senderFrom" type="text" placeholder="Jack Weinstein">
    </label>
    <label>
        <textarea id="senderMessage" class="email__textarea" placeholder="message..."></textarea>
    </label>
    <button class="email__button" onclick="SendEmail()">SEND ➤</button>`;
}

let FormEmailClose = () => {
    let formEmail = document.getElementById("email");
    formEmail.innerHTML = "";
    formEmail.style.display = "none";
}
