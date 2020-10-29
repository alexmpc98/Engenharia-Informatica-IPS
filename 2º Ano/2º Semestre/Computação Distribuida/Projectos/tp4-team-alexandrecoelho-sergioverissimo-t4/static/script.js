/**
 * REST Client
 *
 */

// ===========
//  PAGE START
// ===========
getLoginForm();

// ===========
//  USERS
// ===========
function getLoginForm() {
    let page_body = document.getElementById('page_body');
    page_body.innerHTML = '';
    let form = document.createElement('form');
    form.id = "form_login";
    form.innerHTML += "<h3 style='font-weight: bold'>Login</h3>";
    form.innerHTML += "<div class='form-group'>" +
                      "<label for='username'>Username</label>" +
                      "<input type='text' id='username' class='form-control' name='username' placeholder='Username'>" +
                      "</div>";
    form.innerHTML += "<div class='form-group'>" +
                      "<label for='password'>Password</label>" +
                      "<input type='password' id='password' class='form-control' name='password' placeholder='Password'>" +
                      "</div>";
    form.innerHTML += "<button type='button' class='btn btn-primary' onclick='login()'>Login</button>" +
                      "<button type='button' class='btn btn-primary' onclick='getRegisterForm()'>Register</button>";
    page_body.appendChild(form);
}

function getRegisterForm() {
    let page_body = document.getElementById('page_body');
    page_body.innerHTML = '';
    let form = document.createElement('form');
    form.id = "form_register";
    form.innerHTML += "<h3 style='font-weight: bold'>Register</h3>";
    form.innerHTML += "<div class='form-group'>" +
                      "<label for='name'>Name</label>" +
                      "<input type='text' id='name' class='form-control' name='name' placeholder='Name'>" +
                      "</div>";
    form.innerHTML += "<div class='form-group'>" +
                      "<label for='email'>Email</label>" +
                      "<input type='email' id='email' class='form-control' name='email' placeholder='Email'>" +
                      "</div>";
    form.innerHTML += "<div class='form-group'>" +
                      "<label for='username'>Username</label>" +
                      "<input type='text' id='username' class='form-control' name='username' placeholder='Username'>" +
                      "</div>";
    form.innerHTML += "<div class='form-group'>" +
                      "<label for='password'>Password</label>" +
                      "<input type='password' id='password' class='form-control' name='password' placeholder='Password'>" +
                      "</div>";
    form.innerHTML
    form.innerHTML += "<button type='button' class='btn btn-primary' onclick='registerUser()'>Create</button>";
    form.innerHTML += " <button type='button' class='btn btn-primary' onclick='getLoginForm()'>Cancel</button>";
    page_body.appendChild(form);
}

function getUser() {
    let req = new XMLHttpRequest();
    req.open("GET", "/api/user/");
    req.setRequestHeader("Authorization", "Basic " + sessionStorage.getItem("auth"));
    req.onreadystatechange = function() { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            let user = JSON.parse(this.responseText);
            document.getElementById('name').value = user.name;
            document.getElementById('email').value = user.email;
            document.getElementById('username').value = user.username;
            document.getElementById('password').value = user.password;
        }
    }
    req.send();
}

function getUserDetailsForm() {
    let page_body = document.getElementById('page_body');
    page_body.innerHTML = '';
    let form = document.createElement('form');
    form.id = "form_user_details";
    form.innerHTML += "<h3 style='font-weight: bold'>Perfil</h3>";
    form.innerHTML += "<div class='form-group'>" +
                      "<label for='name'>Name</label>" +
                      "<input type='text' id='name' class='form-control' name='name' placeholder='Name'>" +
                      "</div>";
    form.innerHTML += "<div class='form-group'>" +
                      "<label for='email'>Email</label>" +
                      "<input type='email' id='email' class='form-control' name='email' placeholder='Email'>" +
                      "</div>";
    form.innerHTML += "<div class='form-group'>" +
                      "<label for='username'>Username</label>" +
                      "<input type='text' id='username' class='form-control' name='username' placeholder='Username'>" +
                      "</div>";
    form.innerHTML += "<div class='form-group'>" +
                      "<label for='password'>Password</label>" +
                      "<input type='password' id='password' class='form-control' name='password' placeholder='Password'>" +
                      "</div>";
    getUser();
    form.innerHTML += "<button type='button' class='btn btn-primary' onclick='updateUser()'>Save</button>";
    form.innerHTML += "<button type='button' class='btn btn-primary' onclick='getHomePage()'>Cancel</button>";
    page_body.appendChild(form);
}

function login() {
    let form = document.getElementById("form_login");
    let username = form.username.value;
    let password = form.password.value;
    let json_obj = JSON.stringify({"username": username, "password": password});
    let req = new XMLHttpRequest();
    req.open("GET", "/api/user/");
    req.setRequestHeader("Authorization", "Basic " + btoa(username+':'+password));
    req.setRequestHeader("Content-Type", "application/json");
     req.onreadystatechange = function() { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            let user = JSON.parse(this.responseText);
            sessionStorage.setItem("id", user['id']);
            sessionStorage.setItem("auth", btoa(username+':'+password));
            let user_span = document.getElementById('user');
            user_span.innerText = "Authenticated as: " + user['name'].toUpperCase() + " ";
            user_span.innerHTML += "<button type='button' class='btn btn-primary  btn-sm' onclick='getUserDetailsForm()'>Perfil</button>";
            user_span.innerHTML += "<button type='button' class='btn btn-primary  btn-sm' onclick='logout()'>Logout</button>";
            getHomePage();
        } else if(this.readyState === XMLHttpRequest.DONE) {
            createAlert("form_login", "Incorrect credentials!");
        }
    }
    req.send(json_obj);
}

function registerUser() {
    let form = document.getElementById("form_register");
    let name = form.name.value;
    let email = form.email.value;
    let username = form.username.value;
    let password = form.password.value;
    let json_obj = JSON.stringify(
        {
            "name": name,
            "email": email,
            "username": username,
            "password": password
        })
    let req = new XMLHttpRequest();
    req.open("POST", "/api/user/register/");
    req.setRequestHeader("Content-Type", "application/json");
    req.onreadystatechange = function() { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 201) {
            let user = JSON.parse(this.responseText);
            sessionStorage.setItem("id", user['id']);
            sessionStorage.setItem("auth", btoa(username+':'+password));
            let user_span = document.getElementById('user');
            user_span.innerText = "Authenticated as: " + user['name'].toUpperCase() + " ";
            user_span.innerHTML += "<button type='button' class='btn btn-primary  btn-sm' onclick='getUserDetailsForm()'>Perfil</button>";
            user_span.innerHTML += "<button type='button' class='btn btn-primary  btn-sm' onclick='logout()'>Logout</button>";
            getHomePage();
        } else if(this.readyState === XMLHttpRequest.DONE && this.status === 400) {
            createAlert("form_register", "User already exists or the data is incomplete!");
        } else if(this.readyState === XMLHttpRequest.DONE) {
            createAlert("form_register", "An error occurred!");
        }
    }
    req.send(json_obj);
}

function updateUser() {
    let form = document.getElementById("form_user_details");
    let name = form.name.value;
    let email = form.email.value;
    let username = form.username.value;
    let password = form.password.value;
    let json_obj = JSON.stringify(
        {
            "id": sessionStorage.getItem("id"),
            "name": name,
            "email": email,
            "username": username,
            "password": password
        })
    let req = new XMLHttpRequest();
    req.open("PUT", "/api/user/");
    req.setRequestHeader("Authorization", "Basic " + sessionStorage.getItem("auth"));
    req.setRequestHeader("Content-Type", "application/json");
    req.onreadystatechange = function() { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            let user_span = document.getElementById('user');
            user_span.innerText = "Authenticated as: " + name.toUpperCase() + " ";
            user_span.innerHTML += "<button type='button' class='btn btn-primary  btn-sm' onclick='getUserDetailsForm()'>Perfil</button>";
            user_span.innerHTML += "<button type='button' class='btn btn-primary  btn-sm' onclick='logout()'>Logout</button>";
            let auth = btoa(username+':'+password);
            if (sessionStorage.getItem("auth") != auth) {
                 sessionStorage.setItem("auth", btoa(username+':'+password));
            }
            getHomePage();
        } else if(this.readyState === XMLHttpRequest.DONE && this.status === 400) {
            createAlert("form_user_details", "Incomplete data!");
        } else if(this.readyState === XMLHttpRequest.DONE && this.status === 403) {
            createAlert("form_user_details", "Permission required!");
        } else if(this.readyState === XMLHttpRequest.DONE) {
            createAlert("form_user_details", "An error occurred!");
        }
    }
    req.send(json_obj);
}

function logout(user_span) {
    document.getElementById('user').innerHTML = "";
    getLoginForm();
    clearSession();
}


// ===========
//  PROJECTS
// ===========
function getProjects() {
    let req = new XMLHttpRequest();
    req.open("GET", "/api/projects/");
    req.setRequestHeader("Authorization", "Basic " + sessionStorage.getItem("auth"));
    req.addEventListener("load", function() {
        let projects = JSON.parse(this.responseText);
        let page_body = document.getElementById('left_div');
        page_body.innerHTML = '';
        let table = document.createElement('table');
        table.id = "projects";
        table.className = "table";
        table.innerHTML = "<thead>" +
                          "<tr>" +
                          "<th scope='col'>Title</th>" +
                          "<th scope='col'>Creation Date</th>" +
                          "<th scope='col'>Last Updated</th>" +
                          "<th scope='col'>Update</th>" +
                          "<th scope='col'>Delete</th>" +
                          "<th scope='col'>Tasks</th>" +
                          "</tr>" +
                          "</thead>";
        page_body.appendChild(table);
        for (var i in projects) {
            let tr = document.createElement('tr');
            tr.id = "tr"+projects[i].id;
            if (tr.id == sessionStorage.getItem("selected_tr")) {
                tr.style = "background-color: rgba(181, 181, 181, 0.7) !important;";
            }
            table.appendChild(tr);
            let td_title = document.createElement('td');
            let td_create_date = document.createElement('td');
            let td_update_date = document.createElement('td');
            let td_btn_update = document.createElement('td');
            let td_btn_delete = document.createElement('td');
            let td_btn_tasks = document.createElement('td');
            td_title.innerHTML = projects[i].title;
            tr.appendChild(td_title);
            td_create_date.innerHTML = projects[i].creation_date;
            tr.appendChild(td_create_date);
            td_update_date.innerHTML = projects[i].last_updated;
            tr.appendChild(td_update_date);
            // UPDATE BUTTON
            td_btn_update.innerHTML = "<button class='btn btn-success btn-sm' onclick='getProjectForm(" + 2 + ", " + JSON.stringify(projects[i]) + ")'>Update</button>";
            tr.appendChild(td_btn_update);
            // DELETE BUTTON
            td_btn_delete.innerHTML = "<button class='btn btn-danger btn-sm' onclick='deleteProject(" + projects[i].id +  ")'>Delete</button>";
            tr.appendChild(td_btn_delete);
            // TASK BUTTON
            td_btn_tasks.innerHTML = "<button class='btn btn-primary btn-sm' onclick='getTasks(" + projects[i].id +  ")'>Load</button>";
            tr.appendChild(td_btn_tasks);
        }
        page_body.innerHTML += "<button class='btn btn-primary' onclick='getProjectForm(" + 1 + ", " + null + ")'>Create Project</button>";
        let form_div = document.createElement('div');
        form_div.id = "project_form_div";
        page_body.appendChild(form_div);
    });
    req.send();
}

function getProject(project) {
    let req = new XMLHttpRequest();
    req.open("GET", "/api/projects/"+project['id']+"/");
    req.setRequestHeader("Authorization", "Basic " + sessionStorage.getItem("auth"));
    req.onreadystatechange = function() { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            let project = JSON.parse(this.responseText);
            document.getElementById('title_project').value = project.title;
        }
    }
    req.send();
}

function getProjectForm(form_type, project) {
    let page_body = document.getElementById('project_form_div');
    page_body.innerHTML = '';
    let form = document.createElement('form');
    form.id = "form_project";
    let type = "";
    if (form_type == 1) {
        type = "Creation";
    } else {
        type = "Update"
    }
    page_body.appendChild(form);
    form.innerHTML += "</br></br>";
    form.innerHTML += "<h3 style='font-weight: bold'>Project " + type + " Form</h3>";
    form.innerHTML += "<div class='form-group'>" +
                      "<label for='title_project'>Title</label>" +
                      "<input type='text' id='title_project' class='form-control' name='title_project' placeholder='Title'>" +
                      "</div>";
    let form_id = '"project_form_div"';
    if (form_type == 1) {
        form.innerHTML += "<button type='button' class='btn btn-primary' onclick='createProject()'>Create</button>";
        form.innerHTML += "<button type='button' class='btn btn-primary' onclick='clearDiv(" + form_id + ")'>Cancel</button>";
    } else {
        getProject(project);
        form.innerHTML += "<button type='button' class='btn btn-primary' onclick='updateProject(" + JSON.stringify(project) + ")'>Update</button>";
        form.innerHTML += "<button type='button' class='btn btn-primary' onclick='clearDiv(" + form_id + ")'>Cancel</button>";
    }

}

function createProject() {
    let form = document.getElementById("form_project");
    let title = form.title_project.value;
    let json_obj = JSON.stringify({"title": title})
    let req = new XMLHttpRequest();
    req.open("POST", "/api/projects/");
    req.setRequestHeader("Authorization", "Basic " + sessionStorage.getItem("auth"));
    req.setRequestHeader("Content-Type", "application/json");
    req.onreadystatechange = function() { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 201) {
            getProjects();
        } else if(this.readyState === XMLHttpRequest.DONE && this.status === 400) {
            createAlert("form_project", "Incomplete data!");
        } else if(this.readyState === XMLHttpRequest.DONE && this.status === 403) {
            createAlert("form_project", "Permission required!");
        } else if(this.readyState === XMLHttpRequest.DONE) {
            createAlert("form_project", "An error occurred!");
        }
    }
    req.send(json_obj);
}


function updateProject(project) {
    let form = document.getElementById("form_project");
    project['title'] = form.title_project.value;
    let json_obj = JSON.stringify(project);
    let req = new XMLHttpRequest();
    req.open("PUT", "/api/projects/" + project['id'] + "/");
    req.setRequestHeader("Authorization", "Basic " + sessionStorage.getItem("auth"));
    req.setRequestHeader("Content-Type", "application/json");
    req.onreadystatechange = function() { // Call a function when the state changes.
         if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
             getProjects();
         } else if(this.readyState === XMLHttpRequest.DONE && this.status === 400) {
             createAlert("form_project", "Incomplete data!");
         } else if(this.readyState === XMLHttpRequest.DONE && this.status === 403) {
             createAlert("form_project", "Permission required!");
         } else if(this.readyState === XMLHttpRequest.DONE) {
             createAlert("form_project", "An error occurred!");
         }
    }
    req.send(json_obj);
}


function deleteProject(project_id) {
    let req = new XMLHttpRequest();
    req.open("DELETE", "/api/projects/" + project_id + "/");
    req.setRequestHeader("Authorization", "Basic " + sessionStorage.getItem("auth"));
    req.onreadystatechange = function() { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            sessionStorage.removeItem('selected_tr');
            getProjects();
            document.getElementById('right_div').innerHTML = '';
        }
    }
    req.send();
}


// ===========
//  TASKS
// ===========
function getTasks(project_id) {
    setSelectedProjectRow(project_id);
    let req = new XMLHttpRequest();
    req.open("GET", "/api/projects/"+project_id+"/tasks/");
    req.setRequestHeader("Authorization", "Basic " + sessionStorage.getItem("auth"));
    req.addEventListener("load", function() {
        let tasks = JSON.parse(this.responseText);
        let page_body = document.getElementById('right_div');
        page_body.innerHTML = '';
        let table = document.createElement('table');
        table.id = "tasks";
        table.className = "table";
        table.innerHTML = "<thead>" +
                          "<tr>" +
                          "<th scope='col'>Title</th>" +
                          "<th scope='col'>Creation Date</th>" +
                          "<th scope='col'>Completed</th>" +
                          "<th scope='col'>Update</th>" +
                          "<th scope='col'>Delete</th>" +
                          "</tr>" +
                          "</thead>";
        page_body.appendChild(table);
        tasks.forEach(function(task){
            let tr = document.createElement('tr');
            table.appendChild(tr);
            let td_title = document.createElement('td');
            let td_create_date = document.createElement('td');
            let td_completed = document.createElement('td');
            let td_btn_update = document.createElement('td');
            let td_btn_delete = document.createElement('td');
            // TITLE
            td_title.innerHTML = task.title;
            tr.appendChild(td_title);
            // CREATION DATE
            td_create_date.innerHTML = task.creation_date;
            tr.appendChild(td_create_date);
            // COMPLETED
            td_completed.className = "complete_td";
            if (task.completed == 1) {
                tr.className = "complete_tr";
                td_completed.innerHTML += "<div class='form-check-input position-static'><input type='checkbox' id='check"+task.id+"' class='form-check-input' onchange='updateTaskCompletion(" + JSON.stringify(task) + ")' checked></div>";
            } else {
                td_completed.innerHTML += "<div class='form-check-input position-static'><input type='checkbox' id='check"+task.id+"' class='form-check-input' onchange='updateTaskCompletion(" + JSON.stringify(task) + ")'></div>";
            }
            tr.appendChild(td_completed);
            // UPDATE BUTTON
            td_btn_update.innerHTML = "<button class='btn btn-success btn-sm' onclick='getTaskForm(" + 2 + ", " + JSON.stringify(task) + ")'>Update</button>";
            tr.appendChild(td_btn_update);
            // DELETE BUTTON
            td_btn_delete.innerHTML = "<button class='btn btn-danger btn-sm' onclick='deleteTask(" + task.id + ", " + project_id + ")'>Delete</button>";
            tr.appendChild(td_btn_delete);
        });
        page_body.innerHTML += "<button class='btn btn-primary' onclick='getTaskForm(" + 1 + ", " + JSON.stringify({"project_id": project_id}) + ")'>Create Task</button>";
        let form_div = document.createElement('div');
        form_div.id = "task_form_div";
        page_body.appendChild(form_div);
    });
    req.send();
}

function getTask(project_id, task_id) {
    let req = new XMLHttpRequest();
    req.open("GET", "/api/projects/"+project_id+"/tasks/"+task_id+"/");
    req.setRequestHeader("Authorization", "Basic " + sessionStorage.getItem("auth"));
    req.onreadystatechange = function() { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            let task = JSON.parse(this.responseText);
            document.getElementById('title_task').value = task.title;
            if (task.completed == 1) {
                document.getElementById('completed').checked = true;
            }
        }
    }
    req.send();
}

function getTaskForm(form_type, task) {
    let page_body = document.getElementById('task_form_div');
    page_body.innerHTML = '';
    let form = document.createElement('form');
    form.id = "form_task";
    let type = "";
    if (form_type == 1) {
        type = "Creation";
    } else {
        type = "Update";
    }
    form.innerHTML += "</br></br>";
    form.innerHTML += "<h3 style='font-weight: bold'>Task " + type + " Form</h3>";
    form.innerHTML += "<div class='form-group'>" +
                      "<label for='title_task'>Title</label>" +
                      "<input type='text' id='title_task' class='form-control' name='title_task' placeholder='Title'>" +
                      "</div>";
    form.innerHTML += "<div class='form-check'>" +
                      "<input type='checkbox' id='completed' class='form-check-input' name='completed_form'>" +
                      "<label class='form-check-label' for='completed_form'>Complete</label>" +
                      "</div>";
    form.innerHTML += "</br>";
    let form_id = '"task_form_div"';
    if (form_type == 1) {
        form.innerHTML += "<button type='button' class='btn btn-primary' onclick='createTask(" + task['project_id'] + ")'>Create</button>";
        form.innerHTML += "<button type='button' class='btn btn-primary' onclick='clearDiv(" + form_id + ")'>Cancel</button>";
    } else {
        getTask(task['project_id'], task['id']);
        form.innerHTML += "<button type='button' class='btn btn-primary' onclick='updateTask(" + JSON.stringify(task)  + ")'>Update</button>";
        form.innerHTML += "<button type='button' class='btn btn-primary' onclick='clearDiv(" + form_id + ")'>Cancel</button>";
    }
    page_body.appendChild(form);
}

function createTask(project_id) {
    let form = document.getElementById("form_task");
    let title = form.title_task.value;
    let complete = form.completed.checked == true ? 1 : 0;
    let json_obj = JSON.stringify({"project_id": project_id, "title": title, "completed": complete})
    let req = new XMLHttpRequest();
    req.open("POST", "/api/projects/"+project_id+"/tasks/");
    req.setRequestHeader("Authorization", "Basic " + sessionStorage.getItem("auth"));
    req.setRequestHeader("Content-Type", "application/json");
    req.onreadystatechange = function() { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 201) {
            getTasks(project_id);
            getProjects();
        } else if(this.readyState === XMLHttpRequest.DONE && this.status === 400) {
            createAlert("form_task", "Incomplete data!");
        } else if(this.readyState === XMLHttpRequest.DONE && this.status === 403) {
            createAlert("form_task", "Permission required!");
        } else if(this.readyState === XMLHttpRequest.DONE) {
            createAlert("form_task", "An error occurred!");
        }
    }
    req.send(json_obj);
}

function updateTask(task) {
    let form = document.getElementById("form_task");
    task['title'] = form.title_task.value;
    task['completed'] = form.completed.checked == true ? 1 : 0;
    let json_obj = JSON.stringify(task);
    let req = new XMLHttpRequest();
    req.open("PUT", "/api/projects/"+task['project_id']+"/tasks/"+task['id']+"/");
    req.setRequestHeader("Authorization", "Basic " + sessionStorage.getItem("auth"));
    req.setRequestHeader("Content-Type", "application/json");
    req.addEventListener("load", function() {
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            getTasks(task['project_id']);
            getProjects();
        } else if(this.readyState === XMLHttpRequest.DONE && this.status === 400) {
            createAlert("form_task", "Incomplete data!");
        } else if(this.readyState === XMLHttpRequest.DONE && this.status === 403) {
            createAlert("form_task", "Permission required!");
        } else if(this.readyState === XMLHttpRequest.DONE) {
            createAlert("form_task", "An error occurred!");
        }
    });
    req.send(json_obj);
}

function updateTaskCompletion(task) {
    let checkbox = document.getElementById('check'+task['id']);
    task.completed = checkbox.checked == true ? 1 : 0;
    let json_obj = JSON.stringify(task);
    let req = new XMLHttpRequest();
    req.open("PUT", "/api/projects/"+task['project_id']+"/tasks/"+task['id']+"/");
    req.setRequestHeader("Authorization", "Basic " + sessionStorage.getItem("auth"));
    req.setRequestHeader("Content-Type", "application/json");
    req.addEventListener("load", function() {
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            getTasks(task['project_id']);
            getProjects();
        }
    });
    req.send(json_obj);
}

function deleteTask(task_id, project_id) {
    let req = new XMLHttpRequest();
    req.open("DELETE", "/api/projects/"+project_id+"/tasks/"+task_id+"/");
    req.setRequestHeader("Authorization", "Basic " + sessionStorage.getItem("auth"));
    req.onreadystatechange = function() { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            getTasks(project_id);
        }
    }
    req.send();
}

function getHomePage() {
    let page_body = document.getElementById('page_body');
    page_body.innerHTML = '';
    let subtitle_div = document.createElement('div');
    subtitle_div.id = "subtitle_div";
    page_body.appendChild(subtitle_div);
    let subtitle_left_div = document.createElement('div');
    subtitle_left_div.id = "subtitle_left_div";
    subtitle_left_div.innerHTML = "<h2 class='subtitles'>Projects</h2>";
    subtitle_div.appendChild(subtitle_left_div);
    let subtitle_right_div = document.createElement('div');
    subtitle_right_div.id = "subtitle_right_div";
    subtitle_right_div.innerHTML = "<h2 class='subtitles'>Tasks</h2>";
    subtitle_div.appendChild(subtitle_right_div);
    let tables_div = document.createElement('div');
    tables_div.id = "tables_div";
    page_body.appendChild(tables_div);
    let left_div = document.createElement('div');
    left_div.id = "left_div";
    tables_div.appendChild(left_div);
    let right_div = document.createElement('div');
    right_div.id = "right_div";
    tables_div.appendChild(right_div);
    getProjects();
}


// ===========
//  OTHERS
// ===========
function clearDiv(id) {
    let page_body = document.getElementById(id);
    page_body.innerHTML = '';
}

function clearSession() {
    sessionStorage.removeItem('id');
    sessionStorage.removeItem('auth');
    sessionStorage.removeItem('selected_tr');
}

function setSelectedProjectRow(project_id) {
    if (sessionStorage.getItem("selected_tr")) {
        let old_tr = document.getElementById(sessionStorage.getItem("selected_tr"));
        old_tr.style = "background-color: white !important;";
    }
    let tr = document.getElementById("tr" + project_id);
    tr.style = "background-color: rgba(181, 181, 181, 0.7) !important;";
    sessionStorage.setItem("selected_tr", "tr"+project_id);
}

function createAlert(form_id, text) {
    let form = document.getElementById(form_id);
    form.innerHTML += "<div class='alert alert-danger' role='alert'>" +
                      text +
                      "</div>";
}

