const searchInput = document.querySelector(".search-input");
const searchBar = document.getElementById("search-bar");
const suggestionBox = searchInput.querySelector(".autocomp-list");
const insertButton = document.getElementById("insertion-button");
const insertionInput = document.getElementById("insertion-input");

searchBar.onkeyup = (e)=>{
    suggestionBox.innerHTML = "";
    let userInput = e.target.value;
    let matchingResults = [];
    userInput = userInput.toLowerCase();
    callSearchAPI(userInput).then(function(matchingResults){
        if(matchingResults.length > 0){
            searchInput.classList.add("active");
            for(let i = 0; i < matchingResults.length; i++){
                let li = document.createElement("li");
                li.innerText = matchingResults[i];
                suggestionBox.append(li);
            }
        }
        else{
            searchInput.classList.remove("active");
        }
        if(userInput === ""){
            searchInput.classList.remove("active");
        }
    })
}

async function callSearchAPI(prefix){
    let formData = new FormData();
    formData.append("prefix", prefix);
    let response = await fetch("/search", {
        method: "Post",
        body: formData
    });
    let dataAsJSON = response.json();
    return dataAsJSON;
}

insertButton.onclick = ()=>{
    let userInput = insertionInput.value;
    if(userInput === ""){
        return;
    }
    userInput = userInput.toLowerCase();
    let confirmation;
    callInsertAPI(userInput).then((confirmation)=>{
    });
}

async function callInsertAPI(prefix){
    let formData = new FormData();
    formData.append("prefix", prefix);
    let response = await fetch("/insert", {
        method: "Post",
        body: formData
    });
    return response;

}