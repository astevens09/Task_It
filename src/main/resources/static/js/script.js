var deleteBtn = document.getElementsByClassName("deleteBtn");
var cardTask = document.getElementsByClassName("cardTask");
var todoInput = document.getElementById("todo-input");

// for (let i = 0; i < deleteBtn.length; i++) {
//     deleteBtn.item(i).addEventListener("click",(ele)=>{
//         var taskId = deleteBtn.item(i).getAttribute("data-id");
//         console.log(taskId)
//         fetch("/tasks/delete/"+taskId).then(result => console.log(result));
//     });
//
// }

// for (let i = 0; i < cardTask.length; i++) {
//     cardTask.item(i).
// }

// deleteBtn.addEventListener("click", deleteFunction);

function isEmpty(){
    if(todoInput.value.length == 0){
        alert("Task field must have input.");
        return false;
    }
}