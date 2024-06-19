let regist = document.getElementById('family_regist');
let f_edit = document.getElementById("family_edit");
let u_edit = document.getElementById("user_edit");
let u_delete = document.getElementById("user_delete");

if (regist != null) {
    regist.addEventListener("click", () => {
        window.location.href = "/A3/AccountRegistServlet";
    })
}

if (f_edit != null) {
    f_edit.addEventListener("click", () => {
        window.location.href = "/A3/FamilyEditServlet";
    })
}

u_edit.addEventListener("click", () => {
    window.location.href = "/A3/AccountEditServlet";
})

if (user_delete != null) {
    u_delete.addEventListener("click", () => {
        window.location.href = "/A3/AccountDeleteServlet";
    })
}