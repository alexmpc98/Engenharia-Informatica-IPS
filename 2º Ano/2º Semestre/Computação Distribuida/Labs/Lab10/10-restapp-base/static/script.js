/**
 * REST Client
 *
 */

function getUsers() {
    var req = new XMLHttpRequest();
    req.open("GET", "/api/users/");
    req.addEventListener("load", function() {
        var users = JSON.parse(this.responseText);
        var ul = document.getElementById('users');
        ul.innerHTML = '';
        for (var i in users) {
            var li = document.createElement('li');
            li.innerHTML = users[i].name + ' (' + users[i].age + ')';
            li.innerHTML += " <button onclick='updateUser(" + users[i].id +  ")'>Update</button>";
            li.innerHTML += " <button onclick='deleteUser(" + users[i].id +  ")'>Delete</button>";
            ul.appendChild(li);
        }
    });
    req.send();
}

function addUser() {
    var form = document.getElementById("form");
    var name = form.name.value;
    var age = form.age.value;

    console.log("Add: " + name + " " + age);
}

function updateUser(id) {
    var form = document.getElementById("form");
    var name = form.name.value;
    var age = form.age.value;

    console.log("Update: " + name + " " + age);
}

function deleteUser(id) {
    console.log("Delete: " + id)
}

getUsers();
