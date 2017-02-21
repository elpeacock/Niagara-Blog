/**
 * Created by user on 2/15/17.
 */
(function() {
    var request = $.ajax({
        url: '/posts.json',
    });

    request.done(function(posts) {
        console.log(posts);
        var i;
        var html = '';
        var imgUrl = '';
        for(i = 0; i < posts.length; i++) {

            html += '<div class="row"><div class="col-lg-3"><div class="image-thumbnail" id="image-target"></div></div>' +
                '<div class="col-lg-7"><h3>' + posts[i].title + '</h3><p>' + posts[i].body + '</p><p>Posted' +
                ' by: ' + posts[i].user.username + '</p><a class="btn btn-primary" href="/posts/'
                + posts[i].id + '">Show Post</a></div></div>';

            imgUrl = '/uploads/' + posts[i].image;
        }

        $('#load-posts').html(html);
        $('#image-target').css('background-image', 'url(' + imgUrl + ')');

    });
})();
