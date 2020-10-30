Vue.component('comment-dialog',{
    template: '<div class="dialog-mask">' +
                   '<div class="c-window">' +
                       '<div class="c-header">' +
                           '<h1 class="c-title">Comment</h1>' +
                           '<h2 class="c-author">Author</h2>' +
                       '</div>' +
                       '<div class="c-container">' +
                           '<div class="c-box">' +
                               '<button class="button-previous-comment"><</button>' +
                               '<div class="c-content">' +
                                   '<p>This is a comment which will overflowwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww</p>' +
                               '</div>' +
                               '<button class="button-next-comment">></button>' +
                           '</div>' +
                           '<img class="button-up-vote" src="https://www.flaticon.com/svg/static/icons/svg/1152/1152935.svg" />' +
                       '</div>' +
                   '</div>',
    data: function(){
        return {};
    },
});

