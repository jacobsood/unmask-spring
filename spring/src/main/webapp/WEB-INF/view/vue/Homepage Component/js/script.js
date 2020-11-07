window.onload = function(){
    document.getElementById("profileButton").onclick = function(){
        var history = null
        $.ajax({
            url : "/historylist",
            type : "post",
            dataType:"json",
            async : false,
            success : function(data) {
                history = data;
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.responseText);
            }
        })
        var  hisListUL = document.getElementById("readingHistoryList");
        var  textNode = document.createTextNode(history);
        var  hisListLI = document.createElement("p");
        hisListLI.appendChild(textNode);
        hisListUL.appendChild(hisListLI);
    }
    document.getElementById("likebtn").onclick = function likeArticle() {
        var articleID = document.getElementById("articleID").innerText;
        var status = null;
        $.ajax({
            url : "/likeArticle",
            data: {
                "articleID":articleID,
                "username":"Raymond",
            },
            type : "post",
            dataType:"json",
            async : false,
            success : function(data) {
                status = data
            },
            error : function(XMLHttpRequest) {
                alert("Oops, Like fail");
            }
        })
        document.getElementById("likebtn").innerHTML = status;
    }
    document.getElementById("likeListButton").onclick = function(){
        var  hisListUL = document.getElementById("likeList");

        $.ajax({
            url : "/likelist",
            data: {
                "username":"Raymond",
            },
            type : "post",
            datatype:"json",
            async : false,
            success : function(data) {
                let likes = JSON.parse(data);

                var childs = hisListUL.childNodes;
                for(var i = childs .length - 1; i >= 0; i--) {
                    hisListUL.removeChild(childs[i]);
                }
                for (var id in likes){
                    var card = "<div class=\"card\">" +
                        "  <div class=\"card-body\">" +
                        "    <h5 class=\"card-title\">" +
                        likes[id]["title"]+
                        "</h5>" +
                        "    <p class=\"card-text\">" +
                        likes[id]["text"] +
                        "</p>" +
                        "    <a class=\"card-link\">" +
                        likes[id]["audioUrl"] +
                        "</a>" +
                        "    <a class=\"card-link\">" +
                        likes[id]["source"] +
                        "</a>" +
                        "  </div>" +
                        "</div>";
                    var obj = parseElement(card);
                    hisListUL.appendChild(obj);
                }


            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.responseText);
            }
        })

    }
}
var constraints = {

    password: {
        presence: true,
        length: {
            minimum: 5
        }
    },
    username: {
        presence: true,
        length: {
            minimum: 3,
            maximum: 20
        },
        format: {
            pattern: "[a-z0-9]+",
            flags: "i",
            message: "can only contain a-z and 0-9"
        }
    }
};

var form = document.querySelector("form#loginForm");
form.addEventListener("submit", function(ev) {
    ev.preventDefault();
    handleFormSubmit(form);
});

var inputs = document.querySelectorAll("input, textarea, select")

for (var i = 0; i < inputs.length; ++i) {
    inputs.item(i).addEventListener("change", function(ev) {
        var errors = validate(form, constraints) || {};
        showErrorsForInput(this, errors[this.name])
    });
}

function handleFormSubmit(form, input) {
    var errors = validate(form, constraints);
    showErrors(form, errors || {});
    if (!errors) {
        // if validate
        getLoginStatus();
    }
}

function showErrors(form, errors) {
    _.each(form.querySelectorAll("input[name], select[name]"), function(input) {
        showErrorsForInput(input, errors && errors[input.name]);
    });
}

function showErrorsForInput(input, errors) {
    var formGroup = closestParent(input.parentNode, "form-group")
        , messages = formGroup.querySelector(".messages");
    resetFormGroup(formGroup);
    if (errors) {
        formGroup.classList.add("has-error");
        _.each(errors, function(error) {
            addError(messages, error);
        });
    } else {
        formGroup.classList.add("has-success");
    }
}

function closestParent(child, className) {
    if (!child || child == document) {
        return null;
    }
    if (child.classList.contains(className)) {
        return child;
    } else {
        return closestParent(child.parentNode, className);
    }
}

function resetFormGroup(formGroup) {
    formGroup.classList.remove("has-error");
    formGroup.classList.remove("has-success");
    _.each(formGroup.querySelectorAll(".help-block.error"), function(el) {
        el.parentNode.removeChild(el);
    });
}

function addError(messages, error) {
    var block = document.createElement("p");
    block.classList.add("help-block");
    block.classList.add("error");
    block.classList.add("text-danger");
    block.innerText = error;
    messages.appendChild(block);
}

function getLoginStatus() {
    $.ajax({
        url : '/login',
        type : "post",
        data : {
            "username":$("#username").val(),
            "password":$("#password").val(),
        },
        async : false,
        success : function(data) {
            let result = JSON.parse(data);
            if (result["loginStatus"]=="101"){
                alert("wrong password")
            }else if(result["loginStatus"]=="100"){
                alert("user not exist")
            }else{
                alert("login success"),
                // cookie can not be test on local host, it can only be tested on server.
                $.cookie('loginStatus',result["loginStatus"],{expire:1,path:'/'});
                $.cookie('userEmail',result["userEmail"],{expire:1,path:'/'});
                $.cookie('userID',result["userID"],{expire:1,path:'/'});
                $.cookie('username',result["username"],{expire:1,path:'/'});

            }
        },
        error:function () {
            alert("Oops, seems something wrong. Try again later");
        }
    });

}

function parseElement(htmlString){

    return new DOMParser().parseFromString(htmlString,'text/html').body

}

