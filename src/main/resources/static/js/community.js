
function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    commentTarrget(questionId, 1, content);
}

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    commentTarrget(commentId, 2, content);

}

function commentTarrget(targetId, type, content) {
    if(content==null || content==""){
        alert("不能回复空内容！");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/comment",
        data: JSON.stringify({
            "parentId":targetId,
            "content":content,
            "type":type
        }),
        success: function (response) {
            if(response .code == 200){
                window.location.reload();
            }else {
                if (response.code == 2003){
                    var isAccepted = confirm(response.message);
                    if(isAccepted){
                        window.open("https://github.com/login/oauth/authorize?client_id=Iv1.9ac3ef662ce554a9&redirect_uri=http://localhost:8888/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                }else {
                    alert(response.message);
                }
            }
            console.log(response);
        },
        dataType: "json",
        contentType:"application/json"
    });
}

<!--展开二级评论-->
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment" + id);

    var collapse = e.getAttribute("data-collapse")
    if(collapse){
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    }else{

        var subCommentContainer = $("#comment" + id);
        if(subCommentContainer.children().length != 2){
            comments.addClass("in");
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        }else{
            $.getJSON( "/comment/" + id , function( data ) {
                console.log(data);
                $.each( data.data.reverse(), function(index, comment) {

                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "img-rounded",
                        "style":"width: 38px;height: 38px;margin-top:15px;margin-top: 7px",
                        "src":comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class":"media-heading",
                        "style":"margin-top: 7px",
                        "html":comment.user.name
                    })).append($("<div/>", {
                        "style":"word-break: break-word",
                        "html":comment.content
                    })).append($("<div/>", {
                        "style":"color: slategray;"
                    }).append($("<span/>", {
                        "class":"pull-right" ,
                        "html":moment(comment.gmtCreate).format(" YYYY-MM-DD HH ")
                    })));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 panel panel-default",
                        "style":"margin-top:10px; margin-bottom:5px",
                    });
                    commentElement.append(mediaElement)
                    subCommentContainer.prepend(commentElement);
                });

                comments.addClass("in");
                e.setAttribute("data-collapse", "in");
                e.classList.add("active");
            });
        }
    }
}

function selectTag(value) {
    debugger;
    var previous = $('#tag').val();

    if(previous.indexOf(value) == -1){
        if(previous){
            $("#tag").valueOf(previous + ',' + value);
        }else {
            $("#tag").valueOf(value);
        }
    }

}

function showSelectTag() {
    $('#select-tag').show();
}