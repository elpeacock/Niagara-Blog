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
        for(i = 0; i < posts.length; i++) {

            var style = "background-image: url(/uploads/" + posts[i].image + ");";

            html += '<h3>' + posts[i].title + '</h3><div class="row"><div class="col s12 m3 l3"><div' +
                ' class="image-thumbnail image-target" style="' + style + '"></div></div>' +
                '<div class="col s12 m9 l9"><p>' + posts[i].body + '</p><p>Posted' +
                ' by: ' + posts[i].user.username + '</p><a class="btn btn-primary" href="/posts/'
                + posts[i].id + '">Show Post</a></div></div>';

        }

        $('#load-posts').html(html);


    });
})();
