var deleteBtn = document.getElementsByClassName("deleteBtn");
var cardTask = document.getElementsByClassName("cardTask");
var todoInput = document.getElementById("todo-input");
let profilePicLink = document.getElementById("userProfilePic");

const client = filestack.init(fileStackApi);

profilePicLink.addEventListener("click", profileImgClickHandler);


function profileImgClickHandler(){
    console.log("handler hit")
    let imglink = "test";
    const imgOptions = {
        accept: ["image/*"],
        onFileUploadFinished:file =>{
            imglink = file.url;
            imglink = imglink.slice(8);
            console.log("Image Link: "+imglink);
            postData(imglink).then(data => console.log(data));
            console.log("FileUpload file url:"+file.url);
            console.log("FileUpload file size:"+file.size);

            setTimeout(()=>{
                location.reload();
            },500);

        }
    };


    client.picker(imgOptions).open();
    console.log("file picker ran");
    // postData(imglink).then(data => console.log(data));
}

async function postData(imgLink){
    let url = window.location.origin;

    let returnUrl = window.location.pathname;
    console.log("Return url: "+returnUrl);


    // await fetch(url,{
    //     method: 'GET',
    //     cache: "reload",
    //     body:{data:imgLink}
    // });


    url = url+"/users/imageUpload/"+imgLink+returnUrl;
    await fetch(url);
    console.log("Full url: "+url);
    console.log("img JS ran")
}

function isEmpty(){
    if(todoInput.value.length == 0){
        alert("Task field must have input.");
        return false;
    }
}

