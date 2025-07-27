async function fetchTasks() {
    const res = await fetch('/api/tasks');
    const tasks = await res.json();

    const list = document.getElementById('taskList');
    list.innerHTML = '';

    tasks.forEach(task => {
        const li = document.createElement('li');
        const checkbox = document.createElement('input');
        checkbox.id = task.id
        checkbox.type = 'checkbox';
        checkbox.checked = task.isDone;
        checkbox.onchange = () => toggleDone(task.id);

        const label = document.createElement('label');
        label.htmlFor = task.id
        const sp = document.createElement('span');
        sp.className = "check"

        const span = document.createElement('span');
        span.textContent = ` ${task.id}. ${task.task}`;

        const delBtn = document.createElement('button');
        delBtn.className = "far fa-trash-alt delete"
        delBtn.style.color = '#fb9e98';
        delBtn.style.border= 'none';
        delBtn.style.background= 'none';
        delBtn.style.padding= '5px';
        delBtn.onclick = () => deleteTask(task.id);

        li.appendChild(delBtn);
        label.appendChild(sp);
        label.appendChild(span);
        li.appendChild(checkbox)
        li.appendChild(label)
        // li.appendChild(span);

        list.appendChild(li);
    });
}

async function toggleDone(id) {
    await fetch(`/api/task/${id}`, {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id: id })
    });
    await fetchTasks();
}
async function deleteTask(id) {
    await fetch(`/api/task/${id}`, {
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id: id })
    });
    await fetchTasks();
}

async function addTask() {
    const input = document.getElementById('newTask');
    const title = input.value.trim();
    if (!title) {
        alert('Please enter a task.');
        return;
    }
    await fetch('/api/task', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ task: title, isDone: false })
    });

    input.value = '';
    await fetchTasks();
}

fetchTasks();