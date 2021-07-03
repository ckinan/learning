const GH_ACCESS_TOKEN_KEY = 'ghAccessToken';

window.onload = async (event) => {

    let ghAccessToken = await getGhAccessToken();

    if(ghAccessToken) {
        document.getElementById("login").style.display = "none";
        showRepos();
    } else {
        var loginBtn = document.getElementById('loginBtn');
        loginBtn.href = 'https://github.com/login/oauth/authorize?scope=user:email,read:org&client_id=' + ENV.GITHUB.CLIENT_ID;
        document.getElementById('result').innerHTML = "You need to login first";
    }
    
};

const getGhAccessToken = async () => {

    if(localStorage.getItem(GH_ACCESS_TOKEN_KEY)) {
        return localStorage.getItem(GH_ACCESS_TOKEN_KEY);
    }

    var url = new URL(location.href);
    var code = url.searchParams.get("code");
    window.history.replaceState({}, document.title, window.location.pathname);

    if(code == null) {
        return;
    }

    let ghAccessToken = await fetch('/.netlify/functions/github-client?code=' + code).then(function (response) {
        return response.json();
    }).catch(function (err) {
        console.warn('Could not get ghAccessToken.', err);
    });

    localStorage.setItem(GH_ACCESS_TOKEN_KEY, ghAccessToken.msg.access_token);

    return localStorage.getItem(GH_ACCESS_TOKEN_KEY);
}

const showRepos = async () => {
    fetch('https://api.github.com/user/repos', {
        method: 'GET',
        headers: { 'Authorization': 'Bearer ' + localStorage.getItem(GH_ACCESS_TOKEN_KEY) }
    }).then(function(response) {
        return response.json();
    }).then(function(data){
        document.getElementById('result').innerHTML += "<br/><br/>Repos";
        data.forEach(function (repo, index) {
            showReview(repo);
        });
    });
};

const showReview = async repo => {
    fetch('https://api.github.com/repos/' + repo.full_name + '/pulls?state=all', {
        method: 'GET',
        headers: { 'Authorization': 'Bearer ' + localStorage.getItem(GH_ACCESS_TOKEN_KEY) }
    }).then(function(response) {
        return response.json();
    }).then(function(data){
        document.getElementById('result').innerHTML += '<br/><a href="' + repo.html_url + '" target="_blank">' + repo.full_name + '</a>';
        data.forEach(function (pr, index) {
            document.getElementById('result').innerHTML += '<br/><a href="' + pr.html_url + '" target="_blank">---- #' + pr.number + ': ' + pr.title + ' (' + pr.state + ')' + '</a>';
        });
    });
};