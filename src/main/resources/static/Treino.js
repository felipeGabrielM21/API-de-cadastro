function submitLogin() {
    const login = document.getElementById("login").value;
    const senha = document.getElementById("senha").value;

    fetch("http://localhost:8080/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ login: login, senha: senha })
    })
    .then(response => {
        if (response.ok) {
            alert("Login bem-sucedido!");
            window.location.href = "/pagina-principal";
        } else {
            alert("Falha no login. Verifique suas credenciais.");
        }
    })
    .catch(error => {
        console.error("Erro ao fazer login:", error);
        alert("Ocorreu um erro ao tentar fazer login.");
    });
}
