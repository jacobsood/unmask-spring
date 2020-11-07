
window.onload = function(){
    /*load profile*/
    document.getElementById("profileButton").onclick = function(){
        var historylist = null
        $.ajax({
            url : "/historylist",
            type : "get",
            dataType:"json",
            async : false,
            success : function(data) {
                historylist = data;
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.responseText);
            }
        })
        /*find the parent elem*/
        var  hisListUL = document.getElementById("readingList");
        var childs = hisListUL.childNodes;
        /*clean the history*/
        if (childs!=null){
            for(var i = childs .length - 1; i >= 0; i--) {
                hisListUL.removeChild(childs[i]);
            }
        }
        /*generate card*/
        for (var id in historylist){
            textNode = document.createTextNode(historylist[id]["title"]);
            var hisListLi = document.createElement("li");
            hisListLi.className="list-group-item";
            hisListLi.appendChild(textNode);
            hisListUL.appendChild(hisListLi);
        }


        var likelist = null
        $.ajax({
            url : "/likelist",
            type : "post",
            dataType:"json",
            async : false,
            success : function(data) {
                likelist = data;
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.responseText);
            }
        })
        /*find the parent elem*/
        var  likeListUL = document.getElementById("likeList");
        var childs = likeListUL.childNodes;
        /*clean the history*/
        if (childs!=null){
            for(var i = childs .length - 1; i >= 0; i--) {
                likeListUL.removeChild(childs[i]);
            }
        }
        /*generate card*/
        for (var id in likelist){
            textNode = document.createTextNode(likelist[id]["title"]);
            var likeListLi = document.createElement("li");
            likeListLi.className="list-group-item";
            likeListLi.appendChild(textNode);
            likeListUL.appendChild(likeListLi);
        }


    }
    /*like article button*/
    document.getElementById("likebtn").onclick = function likeArticle() {
        var articleID = document.getElementById("articleID").innerText;
        var status = null;
        $.ajax({
            url : "/likeArticle",
            data: {
                "articleID":articleID,
                "username":$.cookie('username'),
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
        if (status){
            document.getElementById("likebtn").innerHTML = "<i class=\"far fa-thumbs-up fa-6x red-text\"></i>";
        }else{
            document.getElementById("likebtn").innerHTML = "<i class=\"far fa-thumbs-up fa-6x \"></i>";
        }

    }
    /*Check article like status when loading pages*/
   $(function() {
        if ($.cookie('loginStatus')==null){
            return
        }
        let articleID = document.getElementById("articleID").innerText;
        let status = null;
        $.ajax({
            url : "/checkLikeStatus",
            data: {
                "articleID":articleID,
                "username":$.cookie('username'),
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
        if (status){
            document.getElementById("likebtn").innerHTML = "<i class=\"far fa-thumbs-up fa-6x red-text\"></i>";
        }else{
            document.getElementById("likebtn").innerHTML = "<i class=\"far fa-thumbs-up fa-6x \"></i>";
        }

    })
    /*set login layout*/
    let loginstatus = $.cookie('loginStatus');
    if (loginstatus!=null && loginstatus=="200"){
        // login success: display personal file and hide login button
        $("#likebtn").css('display','')
        $("#profileButton").css('display','')
        $("#likeListButton").css('display','')
        $("#loginButton").css('display','none')
    }else{
        // login fail: display personal file and hide login button
        $("#likebtn").css('display','none')
        $("#profileButton").css('display','none')
        $("#likeListButton").css('display','none')
        $("#loginButton").css('display','')
    }
}
/* Input validation*/
var loginConstraints = {
/*log-in constrains*/
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
    },
};
var resetConstraints = {
    /*reset password constrains*/
    "oldPassword": {
        presence: true,
        length: {
            minimum: 5
        }
    },"newPassword": {
        presence: true,
        length: {
            minimum: 5
        },
        equality: {
            attribute: "oldPassword",
            message: "^The passwords should not the same with old password",
            comparator: function(v1, v2) {
                return v1 != v2;
            }
        }
    },"confirmNewPassword": {
        presence: true,
        equality: {
            attribute: "newPassword",
            message: "^The passwords does not match"
        }
    },
};

var loginForm = document.querySelector("form#loginForm");
var resetform = document.querySelector("form#resetForm");

loginForm.addEventListener("submit", function(ev) {
    ev.preventDefault();
    var errors = validate(loginForm, loginConstraints);
    showErrors(loginForm, errors || {});
    if (!errors) {
        // if validate
        getLoginStatus();
    }
});

resetform.addEventListener("submit", function(ev) {
    ev.preventDefault();
    /*initiate the status of old password*/
    var status = true;
    $.ajax({
        url : '/checkOldPassword',
        type : "post",
        data : {
            "username": $.cookie('username'),
            "password": $("#oldPassword").val(),
        },
        async : true,
        success : function(data) {
            if (data=="false"){
                errors = ["Password Mismatch"];
                showErrorsForInput(document.getElementById("oldPassword"), errors);
                status = false;
            }else{
                status = true;
            }
        },
        error:function () {
            alert("Oops, seems something wrong. Refresh the page and try again later.");
        }
    });
    var errors = validate(resetform, resetConstraints);
    showErrors(resetform, errors || {});
    /*if old password is wrong*/
    if (!errors && status) {
        // if validate
        resetPassword();
        $.cookie('loginStatus',null,{path:'/'})
        window.location.reload();

    }
});

var loginInputs = document.getElementById("loginForm").querySelectorAll("input, textarea, select")

for (var i = 0; i < loginInputs.length; ++i) {
    loginInputs.item(i).addEventListener("change", function(ev) {
        var errors = validate(loginForm, loginConstraints) || {};
        showErrorsForInput(this, errors[this.name])
    });
}

var resetInputs = document.getElementById("resetForm").querySelectorAll("input, textarea, select")

for (var i = 0; i < resetInputs.length; ++i) {
    resetInputs.item(i).addEventListener("change", function(ev) {
        var errors = null;
        var component = this;
        if (this.id=="oldPassword"){
            $.ajax({
                url : '/checkOldPassword',
                type : "post",
                data : {
                    "username": $.cookie('username'),
                    "password":$("#oldPassword").val(),
                },
                async : true,
                success : function(data) {
                    if (data=="false"){
                        errors = ["Password Mismatch"];
                        showErrorsForInput(component, errors)
                    }else{
                        errors = validate(resetform, resetConstraints) || {};
                        showErrorsForInput(component, errors[component.name])
                    }
                },
                error:function () {
                    alert("Oops, seems something wrong. Refresh the page and try again later.");
                }
            });
        }else {
            errors = validate(resetform, resetConstraints) || {};
            showErrorsForInput(component, errors[component.name])
        }

    });
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

/*Check login status(200:success)*/
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
                // set the login status, check it when open the home page.
                $.cookie('loginStatus',result["loginStatus"],{expire:1,path:'/'});
                $.cookie('userEmail',result["userEmail"],{expire:1,path:'/'});
                $.cookie('userID',result["userID"],{expire:1,path:'/'});
                $.cookie('username',result["username"],{expire:1,path:'/'});
                window.location.reload()
            }
        },
        error:function () {
            alert("Oops, seems something wrong. Try again later");
        }
    });

}

/*reset password*/
function resetPassword() {
    $.ajax({
        url : '/setNewPassword',
        type : "post",
        data : {
            "username": $.cookie('username'),
            "password":$("#newPassword").val(),
        },
        async : false,
        success : function(data) {
            if (data=="true"){
                alert("Reset Success.")
            }else{
                alert("Reset fail.");
            }

        },
        error:function () {
            alert("Oops, seems something wrong. Refresh the page and try again later.");
        }
    });
}

/*load detailed history*/
document.getElementById("detailedHistoryTrigger").onclick = function () {
    var historylist = null
    $.ajax({
        url : "/detailedHistoryList",
        type : "post",
        dataType:"json",
        async : false,
        success : function(data) {
            historylist = data;
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.responseText);
        }
    })
    /*find the parent elem*/
    var hisListUL = document.getElementById("detailedHistoryList");
    var childs = hisListUL.childNodes;
    /*clean the history*/
    if (childs!=null){
        for(var i = childs .length - 1; i >= 0; i--) {
            hisListUL.removeChild(childs[i]);
        }
    }
    /*generate card*/
    for (var id in historylist){
        textNode = document.createTextNode(historylist[id]["title"]);
        var hisListLi = document.createElement("li");
        hisListLi.className="list-group-item";
        hisListLi.appendChild(textNode);
        hisListUL.appendChild(hisListLi);
    }
}

/*load detailed likes*/
document.getElementById("detailedLikesTrigger").onclick = function () {
    var likelist = null
    $.ajax({
        url : "/detailedLikeList",
        type : "post",
        dataType:"json",
        async : false,
        success : function(data) {
            likelist = data;
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.responseText);
        }
    })
    /*find the parent elem*/
    var likeListUL = document.getElementById("detailedLikeList");
    var childs = likeListUL.childNodes;
    /*clean the history*/
    if (childs!=null){
        for(var i = childs .length - 1; i >= 0; i--) {
            likeListUL.removeChild(childs[i]);
        }
    }
    /*generate card*/
    for (var id in likelist){
        let textNode = document.createTextNode(likelist[id]["title"]);
        let likeListLi = document.createElement("li");
        likeListLi.className="list-group-item";
        likeListLi.appendChild(textNode);
        likeListUL.appendChild(likeListLi);
    }
}