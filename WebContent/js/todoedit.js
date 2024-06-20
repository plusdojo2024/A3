let edit = document.getElementById("todo_edit");

if (edit != null) {
    edit.addEventListener("click", () => {
        window.location.href = "/A3/TodoEditServlet";
    })
}