async function fetchTasks() {
    const res = await fetch('/api/tasks');
    const tasks = await res.json();

    const list = document.getElementById('taskList');
    list.innerHTML = '';

    tasks.forEach(task => {
        const li = document.createElement('li');
        const checkbox = document.createElement('input');
        checkbox.type = 'checkbox';
        checkbox.checked = task.isDone;
        checkbox.onchange = () => toggleDone(task.id);

        const span = document.createElement('span');
        span.textContent = ` ${task.id}: ${task.task}`;

        const delBtn = document.createElement('button');
        delBtn.textContent = 'X';
        delBtn.style.marginLeft = '10px';
        delBtn.style.color = 'red';
        delBtn.onclick = () => deleteTask(task.id);

        li.appendChild(delBtn);
        li.appendChild(checkbox)
        li.appendChild(span);

        list.appendChild(li);
    });
}

async function toggleDone(id) {
    await fetch(`/api/task/${id}`, {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id: id })
    });
    fetchTasks();
}
async function deleteTask(id) {
    await fetch(`/api/task/${id}`, {
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id: id })
    });
    fetchTasks();
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
    fetchTasks();
}

fetchTasks();