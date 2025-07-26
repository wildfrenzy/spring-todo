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

        li.textContent = `${task.id}: ${task.task}`;
        li.appendChild(checkbox)
        list.appendChild(li);
    });
}

async function toggleDone(id) {
    await fetch(`/api/task/${id}`, {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id: id})
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